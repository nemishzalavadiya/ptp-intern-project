import StatisticStock from "src/components/Statistics/StatisticStock";
import Chart from "src/components/Stockdetail/Chart";
import { getStockByAssetId } from "src/services/assets";
import { Header, Loader } from "semantic-ui-react";
export default function Stockdetail(props) {
  const [isComplete, response] = getStockByAssetId(props.stockId);
  return isComplete ? (
    <div className="divMain">
      <Header as="h2" className="ui header stockDetail">
        {response.stockDetail.assetDetail.name}
      </Header>
      <Chart />
      <StatisticStock stockDetail={response} />
    </div>
  ) : response.error ? (
    <div>{response.error}</div>
  ) : (
    <Loader active={!isComplete}></Loader>
  );
}
