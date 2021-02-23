/*
  Component: Watchlist
  props: None
*/
import React from "react";
import { Loader, Menu } from "semantic-ui-react";
import { getAllWatchlistByUserId } from "src/services/watchlist";
import WatchlistById from "src/components/watchlist/WatchlistById";
import StockSearch from "src/components/watchlist/StockSearch";
import { UserId } from "src/components/Objects";
import { useState } from "react";
const content = [
  { header: "Company_Id", icon: "" },
  { header: "Open", icon: <i className="rupee sign icon small"></i> },
  { header: "Close", icon: <i className="rupee sign icon small"></i> },
  { header: "last", icon: <i className="rupee sign icon small"></i> },
  { header: "High", icon: <i className="rupee sign icon small"></i> },
  { header: "Low", icon: <i className="rupee sign icon small"></i> },
  { header: "% CHG", icon: <i className="percent icon small"></i> },
];

export default function Watchlist() {
  const [activeItem, setActiveItem] = useState(0);
  const [isContentFetchingCompleted, response] = getAllWatchlistByUserId(
    UserId.userId
  );
  function handleItemClick(index) {
    setActiveItem(index);
  }
  return isContentFetchingCompleted ? (
    <>
      <Menu pointing inverted secondary className="tab-menu">
        {response.content.map((item, index) => {
          return (
            <Menu.Item
              key={index}
              name={item.name}
              active={activeItem === index}
              onClick={() => handleItemClick(index)}
            />
          );
        })}
      </Menu>
      <div style={{padding:10}}>
      <StockSearch/>
      </div>
    </>
  ) : (
    <Loader active={!isContentFetchingCompleted}>Loading</Loader>
  );
}
