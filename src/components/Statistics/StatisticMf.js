import { Header } from "semantic-ui-react";
export default function StatisticMf(props) {
  const {
    risk,
    minSIP,
    expenseRatio,
    nav,
    fundStarted,
    fundSize,
    mutualFundDetail: {
      fundManager,
      assetDetail: { name, about, assetClass },
    },
  } = props.mfDetail;
  return (
    <div>
      <Header as="h2" className="ui header stats">
        Statistics
      </Header>
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
              <td>{minSIP}</td>
              <td>{risk}</td>
              <td>{expenseRatio}</td>
              <td>{nav}</td>
              <td>{fundStarted}</td>
              <td>{fundSize}</td>
            </tr>
          </tbody>
        </table>
      </section>
      <br />
      <div className="about">
        <Header as="h2" className="ui header stats">
          About
        </Header>
        <Header as="h3" className="ui header stats">
          {about}
        </Header>
      </div>
      <br />
      <div className="about">
        <Header as="h2" className="ui header stats">
          Fund Manager
        </Header>
        <Header as="h3" className="ui header stats">
          {fundManager}
        </Header>
      </div>
    </div>
  );
}
