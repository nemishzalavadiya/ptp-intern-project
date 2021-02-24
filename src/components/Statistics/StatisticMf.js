import { Header, Table } from "semantic-ui-react";
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
        <Table inverted>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>Minimum SIP</Table.HeaderCell>
              <Table.HeaderCell>Risk</Table.HeaderCell>
              <Table.HeaderCell>Expense Ratio</Table.HeaderCell>
              <Table.HeaderCell>NAV</Table.HeaderCell>
              <Table.HeaderCell>Fund Started</Table.HeaderCell>
              <Table.HeaderCell>Fund Size</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            <Table.Row>
              <Table.Cell>{minSIP}</Table.Cell>
              <Table.Cell>{risk}</Table.Cell>
              <Table.Cell>{expenseRatio}</Table.Cell>
              <Table.Cell>{nav}</Table.Cell>
              <Table.Cell>{fundStarted}</Table.Cell>
              <Table.Cell>{fundSize}</Table.Cell>
            </Table.Row>
          </Table.Body>
        </Table>
      </section>
      <br />
      <div className="about">
        <Header as="h2" className="stats">
          About
        </Header>
        <Header as="h3" className="stats">
          {about}
        </Header>
      </div>
      <br />
      <div className="about">
        <Header as="h2" className="stats">
          Fund Manager
        </Header>
        <Header as="h3" className="stats">
          {fundManager}
        </Header>
      </div>
    </div>
  );
}
