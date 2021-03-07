import React, { useState } from "react";
import CandlestickChart from "src/components/Stockdetail/CandlestickChart";
import LineChart from "src/components/Stockdetail/LineChart";
import { Menu } from "semantic-ui-react";
const tabs = [
  {
    name: "candlestick",
    imgSrc: "/icons8-candlestick-chart-20.png"
  },
  {
    name: "line",
    imgSrc: "/icons8-line-chart-201.png"
  },
];
export default function StockChart(props) {
  const [activeIndex, setActiveIndex] = useState(0);
  function handleTabChange(activeIndex) {
    setActiveIndex(activeIndex);
  }
  return (
    <div className="area">
      <Menu pointing inverted secondary className="tab-menu">
        {
          tabs.map((item,index) => {
            return (
              <Menu.Item
                key={index}
                active={activeIndex === index}
                onClick={() => handleTabChange(index)}
              >
                <img src={item.imgSrc} className="tab-toggle-image" />
              </Menu.Item>
            );
          })
        }
      </Menu>
      {activeIndex === 0 ? (
        <CandlestickChart activeIndex={activeIndex} />
      ) : (
        <LineChart activeIndex={activeIndex} />
      )}
    </div>
  );
}
