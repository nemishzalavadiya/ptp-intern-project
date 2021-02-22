import styles from "src/styles/Statistics.module.scss";
export default function Statistics(props) {
  return props.assetClass == "STOCK" ? (
    <div>
      <h2 className="ui header" style={{ color: "white" }}>
        Statistics
      </h2>
      <section>
        <table className="ui inverted table segment">
          <thead>
            <tr>
              <th>Book Value</th>
              <th>Div. Yield</th>
              <th>EPS(TTM)</th>
              <th>Industry P/E</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>{props.stockDetail.bookValue}</th>
              <th>{props.stockDetail.divYield}</th>
              <th>{props.stockDetail.earningPerShareTTM}</th>
              <th>{props.stockDetail.industryPE}</th>
            </tr>
          </tbody>
        </table>
        <table className="ui inverted table segment">
          <thead>
            <tr>
              <th>Market Cap</th>
              <th>P/B Ratio</th>
              <th>P/E Ratio</th>
              <th>ROE</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>{props.stockDetail.marketCap}</th>
              <th>{props.stockDetail.pbRatio}</th>
              <th>{props.stockDetail.peRatio}</th>
              <th>{props.stockDetail.returnOnEquity}</th>
            </tr>
          </tbody>
        </table>
      </section>
      <br />
      <div className={styles.about}>
        <h2 className="ui header" style={{ color: "white" }}>
          About
        </h2>
        <h3 className="ui header" style={{ color: "white" }}>
          Students services is a read only operation from server end and no new
          insertion can take place from client. But we need to cache the data
          locally.
        </h3>
      </div>
      <br />
      <div className={styles.about}>
        <h2 className="ui header" style={{ color: "white" }}>
          Managing Director
        </h2>
        <h3 className="ui header" style={{ color: "white" }}>
          {props.stockDetail.stockDetail.managingDirector}
        </h3>
      </div>
    </div>
  ) : (
    <div>
      <h2 className="ui header" style={{ color: "white" }}>
        Statistics
      </h2>
      <section>
        <table className="ui inverted table segment">
          <thead>
            <tr>
              <th>Minimum SIP</th>
              <th>Risk</th>
              <th>Expense Ratio</th>
              <th>NAV</th>
              <th>Fund Started</th>
              <th>Fund Size</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>{props.mfDetail.minSIP}</th>
              <th>{props.mfDetail.risk}</th>
              <th>{props.mfDetail.expenseRatio}</th>
              <th>{props.mfDetail.nav}</th>
              <th>{props.mfDetail.fundStarted}</th>
              <th>{props.mfDetail.fundSize}</th>
            </tr>
          </tbody>
        </table>
      </section>
      <br />
      <div className={styles.about}>
        <h2 className="ui header" style={{ color: "white" }}>
          About
        </h2>
        <h3 className="ui header" style={{ color: "white" }}>
          Students services is a read only operation from server end and no new
          insertion can take place from client. But we need to cache the data
          locally.
        </h3>
      </div>
      <br />
      <div className={styles.about}>
        <h2 className="ui header" style={{ color: "white" }}>
          Fund Manager
        </h2>
        <h3 className="ui header" style={{ color: "white" }}>
          {props.mfDetail.mutualFundDetail.fundManager}
        </h3>
      </div>
    </div>
  );
}
