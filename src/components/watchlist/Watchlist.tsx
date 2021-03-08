/*
  Component: Watchlist
  props: None
*/
import React from "react";
import { Loader} from "semantic-ui-react";
import { getAllWatchlistByUserId } from "src/services/watchlist";
import WatchlistContent from "src/components/watchlist/WatchlistContent";
import { UserId } from "src/components/Objects";
import { useState } from "react";
import  Tab from "src/components/Tab"
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
        <Tab content={response.content} handleItemClick={handleItemClick} activeItem={activeItem } />      
        <WatchlistContent
          content={content}
          watchlistId={response.content[activeItem].id}
        />
    </>
  ) : (
    <Loader active={!isContentFetchingCompleted}>Loading</Loader>
  );
}
