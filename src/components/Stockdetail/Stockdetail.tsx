import StatisticStock from "src/components/Statistics/StatisticStock";
import Chart from "src/components/Stockdetail/Chart";
import { getStockByAssetId } from "src/services/assets";
import { Header, Loader,Icon } from "semantic-ui-react";
import StockTicket from "src/components/ticket/StockTicket";
import {useState,useEffect} from 'react';
import { WebSocketUrl} from "src/components/Objects";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
export default function Stockdetail(props) {
  const [isComplete, response] = getStockByAssetId(props.stockId);
  const [currentPrice, setCurrentPrice] = useState(0);
  useEffect(() => {
    const webSocket = new SockJS(WebSocketUrl.url);
    const stompClient = Stomp.over(webSocket);
    stompClient.debug = (f) => f;
    stompClient.connect({}, async function (frame) {
      stompClient.subscribe(
        "/topic/" + props.stockId,
        function (data) {
          let contentBody = JSON.parse(data.body);
          setCurrentPrice((contentBody.marketPrice).toFixed(2));
        },
        { id: props.stockId }
      );
    });
    return () => {
      stompClient.unsubscribe(props.stockId);
      stompClient.disconnect();
    };
  }, []);
  return isComplete ? (
    <div>
      <Header className="stats">
        <div>{response.stockDetail.assetDetail.name}</div>
        <div className="ticker">
          <div>
            <a className="ui grey label">{response.stockDetail?.assetDetail?.tickerSymbol}</a>
          </div>
          <div><a className="ui black label"><Icon name="rupee sign"/>{currentPrice}</a></div>
        </div>
      </Header>
      <div className="ticket">
        <Chart />
        <StockTicket
          assetId={props.stockId} currentPrice={currentPrice} stockId={response.stockDetail.id}
        />
      </div>
      <StatisticStock stockDetail={response} />
    </div>
  ) : response.error ? (
    <div>{response.error}</div>
  ) : (
    <Loader active>Loading</Loader>
  );
}
