/*
  Component: Watchlist
  props: None
*/
import React from "react";
import { Loader } from "semantic-ui-react";
import { getAllWatchlistByUserId } from "src/services/watchlistService";
import WatchlistContent from "src/components/watchlist/WatchlistContent";
import { useState } from "react";
import Tab from "src/components/Tab";

const content = [[
  { header: "Company_Id", icon: "" },
  { header: "Open", icon: <i className="rupee sign icon small"></i> },
  { header: "Close", icon: <i className="rupee sign icon small"></i> },
  { header: "Last", icon: <i className="rupee sign icon small"></i> },
  { header: "High", icon: <i className="rupee sign icon small"></i> },
  { header: "Low", icon: <i className="rupee sign icon small"></i> },
  { header: "% CHG", icon: <i className="percent icon small"></i> },
],
[
  { header: "Company_Id", icon: "" },
  { header: "Risk", icon: "" },
  { header: "Expense Ratio", icon: <i className="percent sign icon small"></i> },
  { header: "Nav", icon: <i className="rupee sign icon small"></i> },
  { header: "Fund Size", icon: "" }
]
];

export default function Watchlist() {
  const [activeItem, setActiveItem] = useState(0);
  const [
    isWatchlistIdFetchingCompleted,
    response,
    error,
  ] = getAllWatchlistByUserId();
  function handleItemClick(index) {
    setActiveItem(index);
  }
  return isWatchlistIdFetchingCompleted && !error ? (
    <>
      <Tab
        content={response.content}
        handleItemClick={handleItemClick}
        activeItem={activeItem}
      />
      <WatchlistContent
        content={content[activeItem]}
        watchlistId={response.content[activeItem].id}
      />
    </>
  ) : (
    <Loader active>
      Loading
      {!!error && (
        <>
          <br />
          Something Went Wrong, Try Refresing
        </>
      )}
    </Loader>
  );
}
