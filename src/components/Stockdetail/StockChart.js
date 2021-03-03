import React, { useState } from "react";
import { Menu } from "semantic-ui-react";
import CandlestickChart from "src/components/Stockdetail/CandlestickChart";
import LineChart from "src/components/Stockdetail/LineChart";
import Tab from "src/components/Tab";
const tabs = [
  {
    name: "candlestick",
  },
  {
    name: "line",
  },
];
export default function StockChart(props) {
  const [activeIndex, setActiveIndex] = useState(0);
  function handleTabChange(activeIndex) {
    setActiveIndex(activeIndex);
  }
  return (
    <div className="area">
      <Tab
        content={tabs}
        activeItem={activeIndex}
        handleItemClick={handleTabChange}
      />
      {activeIndex === 0 ? (
        <CandlestickChart activeIndex={activeIndex} />
      ) : (
        <LineChart activeIndex={activeIndex} />
      )}
    </div>
  );
}
