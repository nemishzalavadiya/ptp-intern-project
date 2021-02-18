/*
  Component: WatchlistById
  TODO: 
  1. Handle Error on fetching
*/
import { getAllWatchlistEntryByWatchlistId } from "../../services/watchlist";
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
    totalPages: 2,
    handlePaginationChange:handlePaginationChange
  }
  const [isContentFetchingCompleted,response] = getAllWatchlistEntryByWatchlistId(props.watchlistId,page,5);
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
