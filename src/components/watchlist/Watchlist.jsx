
/*
  Component: Watchlist
  props: None
*/
import React from "react";
import { Loader } from "semantic-ui-react";
import { getAllWatchlistByUserId } from "src/services/watchlistService";
import WatchlistContent from "src/components/watchlist/WatchlistContent";
import MutualFundWatchlistContent from "src/components/watchlist/mutualFundWatchList";
import { useState, useEffect } from "react";
import Tab from "src/components/Tab";
import {
  addToWatchlist,
  removeFromWatchlist,
} from "src/services/watchlistService";

const content = [
  [
    { header: "Company", icon: "", sortable: false },
    {
      header: "Open",
      icon: <i className="rupee sign icon small"></i>,
      sortable: false,
    },
    {
      header: "Close",
      icon: <i className="rupee sign icon small"></i>,
      sortable: false,
    },
    {
      header: "Last",
      icon: <i className="rupee sign icon small"></i>,
      sortable: false,
    },
    {
      header: "High",
      icon: <i className="rupee sign icon small"></i>,
      sortable: false,
    },
    {
      header: "Low",
      icon: <i className="rupee sign icon small"></i>,
      sortable: false,
    },
    {
      header: "% CHG",
      icon: <i className="percent icon small"></i>,
      sortable: false,
    },
    { header: "", icon: "", sortable: false },
  ],
  [
    { header: "Company", icon: "", sortable: false },
    { header: "Risk", icon: "", sortable: false },
    {
      header: "Expense Ratio",
      icon: <i className="percent sign icon small"></i>,
      sortable: false,
    },
    {
      header: "Nav",
      icon: <i className="rupee sign icon small"></i>,
      sortable: false,
    },
    { header: "Fund Size", icon: "", sortable: false },
    { header: "", icon: "", sortable: false },
  ],
];

export default function Watchlist() {
  const [activeItem, setActiveItem] = useState(0);
  const [
    isWatchlistIdFetchingCompleted,
    response,
    error,
  ] = getAllWatchlistByUserId();

  const [pattern, setPattern] = useState([]);
  const length = content[activeItem].length;
  useEffect(() => {
    let initialPattern = [];
    for (let i = 0; i < length; i++) {
      initialPattern.push(0);
    }
    setPattern(initialPattern);
  }, [activeItem]);

  function handleItemClick(index) {
    setActiveItem(index);
  }

  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let midPattern = [];
    let size = length;
    for (let i = 0; i < size; i++) {
      midPattern.push(0);
    }
    midPattern[index] = 1 - pattern[index];
    if (midPattern[index] == 1) {
      setOrderBy("DESC");
    } else {
      setOrderBy("ASC");
    }
    setSortingField(fieldName);
    setPattern(midPattern);
  }
  const tabs = [
    {
      name: "Stock",
    },
    {
      name: "MutualFund",
    },
  ];

  return isWatchlistIdFetchingCompleted && !error ? (
    <>
      <Tab
        content={tabs}s
        handleItemClick={handleItemClick}
        activeItem={activeItem}
      />
      {activeItem==0?<WatchlistContent
        content={content[0]}
        watchlistId={response.content[0].id}
        onclick={changeArrow}
        pattern={pattern}
      />:<MutualFundWatchlistContent
      content={content[1]}
      watchlistId={response.content[1].id}
      onclick={changeArrow}
      pattern={pattern}
    />
  }
    </>
  ) : (
    <Loader active>
      Loading...
      {!!error && (
        <>
          <br />
          Something Went Wrong, Try Refresing
        </>
      )}
    </Loader>
  );
}