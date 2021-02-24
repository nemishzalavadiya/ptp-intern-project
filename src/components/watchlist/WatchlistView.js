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
import Link from 'next/link'
import useWebSocket from "src/hooks/useWebSocket";
export default function WatchlistView(props) {
  let data = new Map();
  let isSubscriptionCompleted=false,myMap= new Map();
  [isSubscriptionCompleted, myMap] = useWebSocket(props.companyUuids);
  if (isSubscriptionCompleted) {
    data.clear();
    //processing data
    Array.from(myMap.values()).forEach((row) => {
      let companyData = Object.values(row);
      companyData.shift(); //remove time stamp
      let key = companyData.shift(); // geting companyId
      companyData[0]=<Link className="nav-link" href={`/details/${key}`}>{companyData[0]}</Link>;
      data.set(key, companyData);
    });
    myMap.clear();
  }
  return isSubscriptionCompleted && data.size === props.companyUuids.length ? (
    <>
      {
        <GridContainer
          content={props.content}
          pagination={props.pagination}
          data={Array.from(data.values())}
          tabId={props.tabId}
        />
      }
    </>
  ) : (
    <Loader
      active={
        !(isSubscriptionCompleted && data.size === props.companyUuids.length)
      }
    >
      Loading
    </Loader>
  );
}
