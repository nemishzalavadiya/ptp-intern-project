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
  const [page, setPage] = useState({
    pages: 0,
    watchlistId: props.watchlistId,
  });
  const pagination = {
    activePage: page,
    totalPages: 2,
    handlePaginationChange: handlePaginationChange,
  };

  const [
    isContentFetchingCompleted,
    response,
  ] = getAllWatchlistEntryByWatchlistId(props.watchlistId, page.pages, 5);

  function handlePaginationChange(page) {
    setPage({ page: page - 1, watchlistId: page.watchlistId });
  }

  if (page.watchlistId !== props.watchlistId) {
    setPage({ pages: 0, watchlistId: props.watchlistId });
  }

  if (isContentFetchingCompleted) {
    let responseData = response.content;
    responseData.map((item) => {
      companyUuids.push(item.assetDetail.id);
    });
    pagination.totalPages = response.totalPages;
  }
  return isContentFetchingCompleted ? (
    <WatchlistView
      content={props.content}
      pagination={pagination}
      companyUuids={companyUuids}
      tabId={props.watchlistId}
    />
  ) : (
    <Loading />
  );
}
