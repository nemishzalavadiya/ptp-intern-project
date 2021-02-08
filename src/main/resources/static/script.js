let element = document.getElementById("price");
let socket = new SockJS('/ptp');
let stompClient = Stomp.over(socket);

stompClient.connect({ }, function(frame) {
    let uuid = ["00000000-0000-0000-0000-000000000000",
                "00000000-0000-0000-0000-000000000001",
                "00000000-0000-0000-0000-000000000002",
                "00000000-0000-0000-0000-000000000003",
                "00000000-0000-0000-0000-000000000004" ];
    let count=0;
    uuid.forEach((uuid)=>{
        stompClient.subscribe("/topic/"+uuid, function(data) {
            document.getElementById("price"+count).innerText=data.body;
            count=(count+1)%5;
        });
    });
});

