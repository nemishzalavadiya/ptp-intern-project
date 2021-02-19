/*
  Component: WatchlistById
  TODO: 
  1. Handle Error on fetching
*/
import { getAllWatchlistEntryByWatchlistId } from "src/services/watchlist";
import { useState } from "react";
import Loading from "src/components/loader/Loading";
import WatchlistView from "src/components/watchlist/WatchlistView";
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
