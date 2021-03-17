
/*
  Component: WatchlistView
  props:  content*: [ {header*: string,icon:<i></i>} ],
          pagination*: Object {
            activePage*:number,totalPages*:number,
            handlePaginationChange(pageNumber): method
          },
          companyUuids*: company uuid list
          tabId: watchlistId
*/
import GridContainer from "src/components/grid/GridContainer";
import { Loader, Icon } from "semantic-ui-react";
import { useState } from "react";
import Link from "next/link";
import useWebSocket from "src/hooks/useWebSocket";
import Sorting from "src/components/Sorting/Sorting";
import { removeFromWatchlist } from "src/services/watchlistService";
export default function WatchlistView(props) {
  const [companyUuids, setCompanyUuids] = useState([]);
  let data = new Map();
  let isSubscriptionCompleted = false,
    subscriptionDataMap = new Map();

  //checking for state change
  if (
    !(
      companyUuids.length === props.companyUuids.length &&
      companyUuids.every((value, index) => value === props.companyUuids[index])
    )
  ) {
    setCompanyUuids(props.companyUuids);
  }
  //websocket connection
  [isSubscriptionCompleted, subscriptionDataMap] = useWebSocket(companyUuids);

  //processing data
  function subscriptionDataProcessing() {
    Array.from(subscriptionDataMap.values()).forEach((row) => {
      let companyData = Object.values(row);
      companyData.shift(); //remove time stamp
      let key = companyData.shift(); // geting companyId
      companyData.push(
        <Icon
          onClick={()=>props.removeFromWatchlist(row.companyId)}
          name={"minus circle"}
        />
      );
      (companyData[0] = (
        <Link className="nav-link" href={`/details/${key}`}>
          {companyData[0]}
        </Link>
      )),
        data.set(key, companyData);
    });
  }

  if (
    isSubscriptionCompleted &&
    subscriptionDataMap.size === companyUuids.length
  ) {
    data.clear();
    subscriptionDataProcessing();
    subscriptionDataMap.clear();
  }

  return isSubscriptionCompleted && data.size === companyUuids.length ? (
    <>
      {
        <>
          {props.content.length !== 0 ? (
            <Sorting
              content={props.content}
              pattern={props.pattern}
              onclick={props.onclick}
            />
          ) : null}
          <GridContainer 
            content={props.content}
            pagination={props.pagination}
            data={Array.from(data.values())}
            tabId={props.tabId}
            showHeaderGrid="disable"
          />
        </>
      }
    </>
  ) : (
    <Loader active>Loading</Loader>
  );
}