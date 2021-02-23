import { Header } from "semantic-ui-react";
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
      <Header as="h2" className="ui header stats">
        Statistics
      </Header>
      <section>
        <table className="ui inverted table segment">
          <thead>
            <tr>
              <th>Book Value</th>
              <th>Div. Yield</th>
              <th>EPS(TTM)</th>
              <th>Industry P/E</th>
              <th>Market Cap</th>
              <th>P/B Ratio</th>
              <th>P/E Ratio</th>
              <th>ROE</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{bookValue}</td>
              <td>{divYield}</td>
              <td>{earningPerShareTTM}</td>
              <td>{industryPE}</td>
              <td>{marketCap}</td>
              <td>{pbRatio}</td>
              <td>{peRatio}</td>
              <td>{returnOnEquity}</td>
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
          Managing Director
        </Header>
        <Header as="h3" className="ui header stats">
          {managingDirector}
        </Header>
      </div>
    </div>
  );
}
