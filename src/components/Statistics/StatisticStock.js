import { Header, Table } from "semantic-ui-react";
export default function Statistics(props) {
  const {
    numberOfStackHolders,
    pbRatio,
    peRatio,
    industryPE,
    divYield,
    bookValue,
    marketCap,
    returnOnEquity,
    earningPerShareTTM,
    stockDetail: {
      yearFounded,
      managingDirector,
      assetDetail: { name, logoUrl, assetClass, about },
    },
  } = props.stockDetail;

  return (
    <div>
      <Header as="h2" className="stats">
        Statistics
      </Header>
      <section>
        <Table inverted>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>Book Value</Table.HeaderCell>
              <Table.HeaderCell>Div. Yield</Table.HeaderCell>
              <Table.HeaderCell>EPS(TTM)</Table.HeaderCell>
              <Table.HeaderCell>Industry P/E</Table.HeaderCell>
              <Table.HeaderCell>Market Cap</Table.HeaderCell>
              <Table.HeaderCell>P/B Ratio</Table.HeaderCell>
              <Table.HeaderCell>P/E Ratio</Table.HeaderCell>
              <Table.HeaderCell>ROE</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            <Table.Row>
              <Table.Cell>{bookValue}</Table.Cell>
              <Table.Cell>{divYield}</Table.Cell>
              <Table.Cell>{earningPerShareTTM}</Table.Cell>
              <Table.Cell>{industryPE}</Table.Cell>
              <Table.Cell>{marketCap}</Table.Cell>
              <Table.Cell>{pbRatio}</Table.Cell>
              <Table.Cell>{peRatio}</Table.Cell>
              <Table.Cell>{returnOnEquity}</Table.Cell>
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
          Managing Director
        </Header>
        <Header as="h3" className="stats">
          {managingDirector}
        </Header>
      </div>
    </div>
  );
}
