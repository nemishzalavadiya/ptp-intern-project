import Statistics from "../Statistics/Statistics";
import { getMfByAssetId } from "../../service/assets";
import { Loader } from "semantic-ui-react";
import Chart from "../MutualFund/Chart";
import styles from "../../styles/MutualFundDetail.module.css";
export default function MutualFundDetail(props) {
  const [isComplete, response] = getMfByAssetId(props.mfId);

  return isComplete ? (
    <div className={styles.divMain}>
      <h2 className="ui header" style={{ color: "white" }}>
        {response.mutualFundDetail.assetDetail.name}
      </h2>
      <Chart />
      <Statistics
        mfDetail={response}
        assetClass={response.mutualFundDetail.assetDetail.assetClass}
      />
    </div>
  ) : response.error ? (
    <div>{response.error}</div>
  ) : (
    <Loader active={!isComplete}></Loader>
  );
}
