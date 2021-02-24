import { Header, Table } from "semantic-ui-react";

const headers = [
  "Book Value",
  "Div. Yield",
  "EPS(TTM)",
  "Industry P/E",
  "Market Cap",
  "P/B Ratio",
  "P/E Ratio",
  "ROE",
];

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
  const values = [
    bookValue,
    divYield,
    earningPerShareTTM,
    industryPE,
    marketCap,
    pbRatio,
    peRatio,
    returnOnEquity,
  ];
  return (
    <div>
      <Header as="h2" className="stats">
        Statistics
      </Header>
      <section>
        <Table inverted>
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
