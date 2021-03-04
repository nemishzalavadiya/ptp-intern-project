import StatisticMf from "src/components/Statistics/StatisticMf";
import { getMfByAssetId } from "src/services/assets";
import { Header, Loader } from "semantic-ui-react";
import Chart from "src/components/MutualFund/Chart";
import MutualFundTicket from "src/components/ticket/mutualFundTicket.tsx";

export default function MutualFundDetail(props) {
  const [isComplete, response] = getMfByAssetId(props.mfId) ;

  return isComplete ? (
    <div>
      <Header className="stats">
        {response.mutualFundDetail.assetDetail.name}
      </Header>
      <div className="ticket">
      <Chart />
      <MutualFundTicket  mfDetail={response} />
      </div>
      <StatisticMf mfDetail={response} />
    </div>
  ) : response.error ? (
    <div>{response.error}</div>
  ) : (
    <Loader active={!isComplete}></Loader>
  );
}