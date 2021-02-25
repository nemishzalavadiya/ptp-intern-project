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
      <div className="about fancy" >
        <Header className="stats">About</Header>
        <div className="info">{about}</div>
        <Header className="stats">Managing Director</Header>
        <div className="info">{managingDirector}</div>
      </div>
    </div>
  );
}
