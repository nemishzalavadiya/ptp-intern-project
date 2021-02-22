import Statistics from "src/components/Statistics/Statistics";
import { getMfByAssetId } from "src/services/assets";
import { Loader } from "semantic-ui-react";
import Chart from "src/components/MutualFund/Chart";
import styles from "src/styles/MutualFundDetail.module.scss";
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
