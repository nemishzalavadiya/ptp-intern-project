/*
  hook: useWebSocket
  argument: uuidList
*/
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { useState, useEffect } from "react";
import { WebSocketUrl } from 'src/components/Objects'
export default function useWebSocket(uuidList) {
  const [myMap, setMyMap] = useState(new Map());
  const [isCompleted, setCompleted] = useState(false);

  async function setUpSubscription(stompClient) {
    stompClient.debug = (f) => f;
    stompClient.connect({}, async function (frame) {
      await uuidList.forEach((uuid) => {
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
  }
  async function cleanUp(uuidList, stompClient) {
    await uuidList.forEach((uuid) => {
      stompClient.unsubscribe(uuid);
    });
    stompClient.disconnect();
  }

  useEffect(() => {
    const webSocket = new SockJS(WebSocketUrl.url);
    const stompClient = Stomp.over(webSocket);
    setUpSubscription(stompClient);
    return () => {
      cleanUp(uuidList, stompClient);
    };
  }, [uuidList]);
  return [isCompleted, myMap];
}
