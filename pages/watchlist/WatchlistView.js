import GridContainer from "../grid/GridContainer";
import Loading from "../loader/Loading";
import WebSocket from "../util/websocket";
export default function WatchlistView(props) {
  let data = new Map();
  let [isSubscriptionCompleted, myMap] = [false];

  [isSubscriptionCompleted, myMap] = WebSocket(props.companyUuids);

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
            className="sideBar"
            style={{ width: "60vh", height: "100vh" }}
          ></div>
          <div>
            <GridContainer
              data={Array.from(data.values())}
              header={props.header}
              icon={props.sign}
            />
          </div>
          <div
            className="sideBar"
            style={{ width: "60vh", height: "100vh" }}
          ></div>
        </div>
      }
    </>
  ) : (
    <Loading />
  );
}
