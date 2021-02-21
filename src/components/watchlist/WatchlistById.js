/*
  Component: WatchlistById
  props: watchlistId: string, content: Object
*/
import { getAllWatchlistEntryByWatchlistId } from "src/services/watchlist";
import { useState } from "react";
import { Loader } from "semantic-ui-react";
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

  function handlePaginationChange(pageNo) {
    setPage({ pages: pageNo, watchlistId: page.watchlistId });
  }

  if (page.watchlistId !== props.watchlistId) {
    setPage({ pages: 0, watchlistId: props.watchlistId });
  }

  if (isContentFetchingCompleted) {
    let responseData = response.content;
    responseData.forEach((item) => {
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
      <Loader active={!isContentFetchingCompleted}>Loading</Loader>
  );
}
