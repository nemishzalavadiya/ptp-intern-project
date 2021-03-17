import StatisticMf from "src/components/Statistics/StatisticMf";
import { getMfByAssetId } from "src/services/assets";
import { Header, Loader,Icon } from "semantic-ui-react";
import Chart from "src/components/MutualFund/Chart";
import MutualFundTicket from "src/components/ticket/mutualFundTicket";
import {useState} from 'react';
export default function MutualFundDetail(props) {
  const [isComplete, response] = getMfByAssetId(props.mfId) ;
  return isComplete ? (
    <div>
      <Header className="stats">
        <div>{response.mutualFundDetail.assetDetail.name}</div>
        <div className="ticker">
          <div>
            <a className="ui grey label">{response.mutualFundDetail?.assetDetail?.tickerSymbol}</a>
          </div>
          <div><a className="ui black label"><Icon name="rupee sign" />{response.nav}</a></div>
        </div>
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