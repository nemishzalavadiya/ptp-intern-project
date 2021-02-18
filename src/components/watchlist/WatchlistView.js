/*
  Component: WatchlistView
  props:  content*: [ {header*: string,icon:<i></i>} ],
          pagination*: Object {
            activePage*:number,totalPages*:number,
            handlePaginationChange(pageNumber): method
          },
          companyUuids*: company uuid list
  TODO:
    1. remove div block space around grid with appropriate margin
*/
import GridContainer from "../grid/GridContainer";
import Loading from "../loader/Loading";
import useWebSocket from "../../hooks/useWebSocket";
export default function WatchlistView(props) {
  let data = new Map();
  let [isSubscriptionCompleted, myMap] = [false];
  [isSubscriptionCompleted, myMap] = useWebSocket(props.companyUuids);

  data.clear();
  Array.from(myMap.values()).map((row) => {
    let companyData = Object.values(row);
    companyData.shift();//remove time stamp
    let key = companyData.shift();// geting companyId
    //adding commas to the numbers
    companyData.map((item,index)=>{
          if(isFinite(item)){
              companyData[index] = item.toLocaleString();
          }
    })
    data.set(key, companyData);
  });
  myMap.clear();
  return isSubscriptionCompleted ? (
    <>
      {
        <div
          style={{
            backgroundColor: "black",
            overflow: "scroll",
            display: "flex",
            flexDirection: "row",
          }}
        >
            <GridContainer
              content={props.content}
              pagination={props.pagination}
              data = {Array.from(data.values())}
            />
        </div>
      }
    </>
  ) : (
    <Loading />
  );
}
