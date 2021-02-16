import GridContainer from "../grid/GridContainer";
import Loading from "../loader/Loading";
import SockJS from "sockjs-client";
import Stomp from 'stompjs';
import { useState, useEffect } from 'react';
export default function Watchlist() {
    const [myMap,setMyMap] = useState(new Map());
    const [isCompleted,setCompleted] = useState(false);
    const Header = [ "Company_Id","Open","Close","Market Pr.","High","Low","% CHG" ];
    useEffect(()=>{
        const webSocket= new SockJS('http://localhost:8080/ptp');
        // Watchlist
        const stompClient = Stomp.over(webSocket);
        stompClient.debug = f=>f;
        stompClient.connect({ }, function(frame) {
            let uuid = ["00000000-0000-0000-0000-000000000000",
                        "00000000-0000-0000-0000-000000000001",
                        "00000000-0000-0000-0000-000000000002",
                        "00000000-0000-0000-0000-000000000003",
                        "00000000-0000-0000-0000-000000000004" ];
            uuid.forEach((uuid)=>{
                stompClient.subscribe("/topic/"+uuid, function(data) {
                    let contentBody = Object.values(JSON.parse(data.body));
                    contentBody.shift();
                    let key = contentBody.shift();
                    contentBody[contentBody.length-1]=String(contentBody[contentBody.length-1]);
                    myMap.set(key,contentBody);
                    setMyMap(new Map(myMap));
                });
            });
            setCompleted(true);
        });
    },[])
    
  return ( isCompleted?
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
          style={{ width: "60vh", height: "100vh"}}
        ></div>
        <div>
          <GridContainer data={Array.from(myMap.values())} header={Header} title={"Watchlist"} />
        </div>
        <div
          className="sideBar"
          style={{ width: "60vh", height: "100vh" }}
        ></div>
      </div>
      }
    </>
    :<Loading/>
  );
}
