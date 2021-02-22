import Statistics from "src/components/Statistics/Statistics";
import Chart from "src/components/Stockdetail/Chart";
import { getStockByAssetId } from "src/services/assets";
import { Loader } from "semantic-ui-react";
import styles from "src/styles/Stockdetail.module.scss";
export default function Stockdetail(props) {
  const [isComplete, response] = getStockByAssetId(props.stockId);
  return isComplete ? (
    <div className={styles.divMain}>
      <h2 className="ui header" style={{ color: "white" }}>
        {response.stockDetail.assetDetail.name}
      </h2>
      <Chart />
      <Statistics
        stockDetail={response}
        assetClass={response.stockDetail.assetDetail.assetClass}
      />
    </div>
  ) : response.error ? (
    <div>{response.error}</div>
  ) : (
    <Loader active={!isComplete}></Loader>
  );
}
