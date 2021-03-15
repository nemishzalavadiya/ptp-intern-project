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
import { Loader } from "semantic-ui-react";
import { useState } from "react";
import Link from "next/link";
import useWebSocket from "src/hooks/useWebSocket";
import Sorting from "src/components/Sorting/Sorting";
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
      companyData[0] = (
        <Link className="nav-link" href={`/details/${key}`}>
          {companyData[0]}
        </Link>
      );
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

  let intialPatternState = [];
  for (let i = 0; i < props.content.length; i++) {
    intialPatternState.push(0);
  }
  const [pattern, setPattern] = useState(intialPatternState);
  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let midPattern = [];
    let size = props.content.length;
    for (let i = 0; i < size; i++) {
      midPattern.push(0);
    }
    midPattern[index] = 1 - pattern[index];
    setPattern(midPattern);
    if (midPattern[index] == 1) {
      setOrderBy("DESC");
    } else {
      setOrderBy("ASC");
    }
    setSortingField(fieldName);
  }
  return isSubscriptionCompleted && data.size === companyUuids.length ? (
    <>
      {
        <>
          {props.content.length !== 0 ? (
            <Sorting
              content={props.content}
              pattern={pattern}
              onclick={changeArrow}
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
