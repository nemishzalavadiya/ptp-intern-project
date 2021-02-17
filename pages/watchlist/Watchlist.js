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
import WatchlistRenderer from "./WatchlistRenderer";

const content = [
  {header:"Company_Id",icon:""},
  {header:"Open",icon:<i className="rupee sign icon small"></i>},
  {header:"Close",icon:<i className="rupee sign icon small"></i>},
  {header:"last",icon:<i className="rupee sign icon small"></i>},
  {header:"High",icon:<i className="rupee sign icon small"></i>},
  {header:"Low",icon:<i className="rupee sign icon small"></i>},
  {header:"% CHG",icon:<i className="percent icon small"></i>}
];
const panes = (watchlist) => {
  let paneList = [];
  watchlist.map((item) => {
    paneList.push({
      menuItem: item.name,
      render: () => (
        <Tab.Pane>
          <WatchlistById content={content} watchlistId={item.id} />
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

  return (isContentFetchingCompleted?<Tab renderActiveOnly={true} menu={{pointing:true}} defaultActiveIndex={0} panes={panes(response.content)} /> : <Loading/>)
}
