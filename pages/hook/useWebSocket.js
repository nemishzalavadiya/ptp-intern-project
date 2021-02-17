/*
  hook: useWebSocket
  argument: uuidList
  TODO:
      1. manage throw errors
      2. handle unsubscription
*/
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { useState, useEffect } from "react";
export default function useWebSocket(uuidList) {
  const [myMap, setMyMap] = useState(new Map());
  const [isCompleted, setCompleted] = useState(false);
  useEffect(() => {
    const webSocket = new SockJS("http://localhost:8080/ptp");
    const stompClient = Stomp.over(webSocket);
    stompClient.debug = (f) => f;
    stompClient.connect({}, function (frame) {
      uuidList.forEach((uuid) => {
        stompClient.subscribe(
          "/topic/" + uuid,
          function (data) {
            let contentBody = JSON.parse(data.body);
            myMap.set(uuid, contentBody);
            setMyMap(new Map(myMap));
          },
          { id: uuid }
        );
      });
      setCompleted(true);
    });
    return () => {
      async function cleanUp(uuidList, stompClient) {
        uuidList.forEach((uuid) => {
          stompClient.unsubscribe(uuid);
        });
        stompClient.disconnect();
      }
      cleanUp(uuidList, stompClient);
    };
  }, [uuidList]);
  return [isCompleted, myMap];
}
