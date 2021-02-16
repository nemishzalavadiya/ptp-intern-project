import SockJS from "sockjs-client";
import Stomp from 'stompjs';
import { useState, useEffect } from 'react';
export default function WebSocket(props){
    const [myMap,setMyMap] = useState(new Map());
    const [isCompleted,setCompleted] = useState(false);
    useEffect(()=>{
        const webSocket= new SockJS('http://localhost:8080/ptp');
        const stompClient = Stomp.over(webSocket);
        stompClient.debug = f=>f;
        stompClient.connect({ }, function(frame) {
            
            props.forEach((uuid)=>{
                stompClient.subscribe("/topic/"+uuid, function(data) {
                    let contentBody = JSON.parse(data.body);
                    myMap.set(uuid,contentBody);
                    setMyMap(new Map(myMap));
                });
            });
            setCompleted(true);
        });
    },[])
    return [isCompleted,myMap];
}