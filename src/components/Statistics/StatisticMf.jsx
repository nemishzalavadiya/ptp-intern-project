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
  let {
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
  fundStarted = fundStarted.substring(0,10);
  const values = [minSIP, risk, expenseRatio, nav, fundStarted, fundSize];
  return (
    <div className="stats-main">
      <div className="div-style">
      <Header className="stats">Statistics</Header>
      <section>
        <Table color="black" inverted>
          <Table.Header>
            <Table.Row>
              {headers.map((item,index) => {
                return <Table.HeaderCell key={index}>{item}</Table.HeaderCell>;
              })}
            </Table.Row>
          </Table.Header>
          <Table.Body>
            <Table.Row>
              {values.map((item,index) => {
                return <Table.Cell key={index}>{item}</Table.Cell>;
              })}
            </Table.Row>
          </Table.Body>
        </Table>
      </section>
      </div>
      <div className="about div-style">
        <Header className="stats">About</Header>
        <div className="info">{about}</div>
        <Header className="stats">Fund Manager</Header>
        <div className="info">{fundManager}</div>
      </div>
    </div>
  );
}
