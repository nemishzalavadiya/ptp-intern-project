import { Header, Table, Segment, Container } from "semantic-ui-react";
const headers = [
  "Minimum SIP",
  "Risk",
  "Expense Ratio",
  "NAV",
  "Fund Started",
  "Fund Size",
];

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
      assetDetail: { name, logoUrl, assetClass, about },
    },
  } = props.mfDetail;
  const values = [minSIP, risk, expenseRatio, nav, fundStarted, fundSize];
  return (
    <div className="stats-main">
      <div className="fancy">
      <Header className="stats">Statistics</Header>
      <section>
        <Table color="black" inverted>
          <Table.Header>
            <Table.Row>
              {headers.map((item) => {
                return <Table.HeaderCell>{item}</Table.HeaderCell>;
              })}
            </Table.Row>
          </Table.Header>
          <Table.Body>
            <Table.Row>
              {values.map((item) => {
                return <Table.Cell>{item}</Table.Cell>;
              })}
            </Table.Row>
          </Table.Body>
        </Table>
      </section>
      </div>
      <div className="about fancy">
        <Header className="stats">About</Header>
        <div className="info">{about}</div>
        <Header className="stats">Fund Manager</Header>
        <div className="info">{fundManager}</div>
      </div>
    </div>
  );
}
