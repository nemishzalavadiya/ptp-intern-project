/*
  Component: WatchlistById
  props: header*: list, sign: list of icon, watchlistId*: String
  TODO: 
  1. Handle Error on fetching
*/
import { getAllWatchlistEntryByWatchlistId } from "../api/watchlist";
import { useState } from "react";
import Loading from "../loader/Loading";
import WatchlistView from "./WatchlistView";
export default function WatchlistById(props) {
  let companyUuids = [];
  const [page, setPage] = useState(0);
  function handlePaginationChange(page) {
    setPage(page-1);
  }
  const pagination = {
    activePage: page,
    totalPages: 10,
    handlePaginationChange:handlePaginationChange
  }
  const [isContentFetchingCompleted,response] = getAllWatchlistEntryByWatchlistId(props.watchlistId,0,10);
  
  if (isContentFetchingCompleted) {
    let responseData = response.content;
    responseData.map((item) => {
      companyUuids.push(item.assetDetail.id);
    });
  }
  return isContentFetchingCompleted ? 
    <WatchlistView
    content={props.content}
    pagination={pagination}
    companyUuids={companyUuids}
    />
  :<Loading />
}
