/*
  Component: Watchlist
  props: None
  TODO: 
  1. Handle Error on fetching
*/
import React from "react";
import { Tab } from "semantic-ui-react";
import { getAllWatchlistByUserId } from "../api/watchlist";
import Loading from "../loader/Loading";
import WatchlistById from "./WatchlistById";

const Header = [
  "Company_Id",
  "Open",
  "Close",
  "Market Pr.",
  "High",
  "Low",
  "% CHG",
];
const sign = [
  "",
  <i className="rupee sign icon small"></i>,
  <i className="rupee sign icon small"></i>,
  <i className="rupee sign icon small"></i>,
  <i className="rupee sign icon small"></i>,
  <i className="rupee sign icon small"></i>,
  <i className="percent icon small"></i>,
];

const panes = (watchlist) => {
  let paneList = [];
  watchlist.map((item) => {
    paneList.push({
      menuItem: item.name,
      render: () => (
        <Tab.Pane>
          <WatchlistById header={Header} sign={sign} watchlistId={item.id} />
        </Tab.Pane>
      ),
    });
  });
  return paneList;
};

export default function Watchlist() {
  const [isContentFetchingCompleted, response] = getAllWatchlistByUserId(
    "00000000-0000-0000-0000-000000000000"
  );

  //return isContentFetchingCompleted? <Tab defaultActiveIndex={0} panes={panes(response.content)} /> : <Loading/>
  return isContentFetchingCompleted ? (
    <WatchlistById
      header={Header}
      sign={sign}
      watchlistId={response.content[0].id}
    />
  ) : (
    response.error ? <div>{response.error}</div>:<Loading />
  );
}
