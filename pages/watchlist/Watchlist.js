import GridContainer from "../grid/GridContainer";
import Loading from "../loader/Loading";
import WebSocket from "../util/websocket";
export default function Watchlist() {
  let uuid = [
    "00000000-0000-0000-0000-000000000000",
    "00000000-0000-0000-0000-000000000001",
    "00000000-0000-0000-0000-000000000002",
    "00000000-0000-0000-0000-000000000003",
    "00000000-0000-0000-0000-000000000004",
  ];
  let data = new Map();
  const Header = [
    "Company_Id",
    "Open",
    "Close",
    "Market Pr.",
    "High",
    "Low",
    "% CHG",
  ];
  const sign = [
    "",
    <i className="rupee sign icon small"></i>,
    <i className="rupee sign icon small"></i>,
    <i className="rupee sign icon small"></i>,
    <i className="rupee sign icon small"></i>,
    <i className="rupee sign icon small"></i>,
    <i className="percent icon small"></i>
  ];
  let [isCompleted, myMap] = WebSocket(uuid);

  Array.from(myMap.values()).map((row) => {
    let companyData = Object.values(row);
    companyData.shift();
    let key = companyData.shift();
    data.set(key, companyData);
  });
  return isCompleted ? (
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
              header={Header}
              title={"Watchlist"}
              icon = {sign}
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
