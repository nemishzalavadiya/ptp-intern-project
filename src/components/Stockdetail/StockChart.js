import React from "react";
import { Menu, Tab } from "semantic-ui-react";
import CandlestickChart from "src/components/Stockdetail/CandlestickChart";
import LineChart from "src/components/Stockdetail/LineChart";
const panes = [
  {
    menuItem: (
      <Menu.Item className="tab-item" key="candlestick">
        <img src="/icons8-candlestick-chart-20.png" height="28px" />
      </Menu.Item>
    ),
    render: () => <CandlestickChart />,
  },
  {
    menuItem: (
      <Menu.Item className="tab-item" key="line">
        <img src="/icons8-line-chart-201.png" height="28px" />
      </Menu.Item>
    ),
    render: () => <LineChart />,
  },
];
export default function StockChart(props) {
  return (
    <div className="area">
      <Tab
        className="tab-container"
        menu={{ secondary: true, pointing: true, inverted: true }}
        panes={panes}
      />
    </div>
  );
}
