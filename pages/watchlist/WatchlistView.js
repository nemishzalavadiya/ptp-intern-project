/*
  Component: WatchlistView
  props: header*: list, sign: list of icon, companyUuids*: list of company uuids

  TODO:
    1. remove div block space around grid with appropriate margin
*/
import GridContainer from "../grid/GridContainer";
import Loading from "../loader/Loading";
import useWebSocket from "../hook/useWebSocket";
export default function WatchlistView(props) {
  let data = new Map();
  let [isSubscriptionCompleted, myMap] = [false];

  [isSubscriptionCompleted, myMap] = useWebSocket(props.companyUuids);

  Array.from(myMap.values()).map((row) => {
    let companyData = Object.values(row);
    companyData.shift();
    let key = companyData.shift();
    data.set(key, companyData);
  });

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
          <div
            className="leftSpace"
            style={{ width: "60vh", height: "100vh" }}
          ></div>
          <div>
            <div
              className="topSpace"
              style={{ width: "100vh", height: "20vh" }}
            ></div>
            <GridContainer
              data={Array.from(data.values())}
              header={props.header}
              icon={props.sign}
            />
            <div
              className="bottomSpace"
              style={{ width: "100vh", height: "20vh" }}
            ></div>
          </div>
          <div
            className="rightSpace"
            style={{ width: "60vh", height: "100vh" }}
          ></div>
        </div>
      }
    </>
  ) : (
    <Loading />
  );
}
