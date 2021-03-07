import StatisticStock from "src/components/Statistics/StatisticStock";
import Chart from "src/components/Stockdetail/Chart";
import { getStockByAssetId } from "src/services/assets";
import { Header, Loader } from "semantic-ui-react";
import StockTicket from "src/components/ticket/StockTicket"

export default function Stockdetail(props) {
  const [isComplete, response] = getStockByAssetId(props.stockId);
  return isComplete ? (
    <div>
      <Header className="stats">
        {response.stockDetail.assetDetail.name}
      </Header>
      <div className="ticket">
      <Chart />
      <StockTicket assetId={props.stockId} stockId={response.stockDetail.id}/>
      </div>
      <StatisticStock stockDetail={response} />
    </div>
  ) : response.error ? (
    <div>{response.error}</div>
  ) : (
    <Loader active={!isComplete}></Loader>
  );
}
