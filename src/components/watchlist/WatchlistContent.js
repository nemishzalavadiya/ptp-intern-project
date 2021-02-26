import { useState } from "react";
import { Loader } from "semantic-ui-react";
import WatchlistView from "src/components/watchlist/WatchlistView";
import { getAllWatchlistEntryByWatchlistId } from "src/services/watchlist";
import Search from "src/components/Search";

export default function WatchlistContent(props) {
  const [searchQuery, setSearchQuery] = useState("");
  const [page, setPage] = useState({
    pages: 0,
    watchlistId: props.watchlistId,
  });
  let companyUuids = [];
  let [isCompleted, response] = [false];

  [isCompleted, response] = getAllWatchlistEntryByWatchlistId(
    props.watchlistId,
    searchQuery,
    page.pages,
    5
  );

  function handlePaginationChange(pageNo) {
    setPage({ pages: pageNo, watchlistId: page.watchlistId });
  }
  const pagination = {
    activePage: page.pages,
    totalPages: 2,
    handlePaginationChange: handlePaginationChange,
  };

  if (page.watchlistId !== props.watchlistId) {
    companyUuids.length = 0;
    setPage({ pages: 0, watchlistId: props.watchlistId });
  }

  function handleSearchChange(e, data) {
    setSearchQuery(data.value);
  }
  if (isCompleted) {
    companyUuids.length = 0;
    response.content.forEach((item) => {
      companyUuids.push(item.assetDetail.id);
    });
    pagination.totalPages = response.totalPages;
  }
  return (
    <>
      <Search
        handleSearchChange={handleSearchChange}
        placeholder={"Search In Watchlist..."}
      />
      {isCompleted ? (
        <WatchlistView
          content={props.content}
          companyUuids={companyUuids}
          pagination={pagination}
          tabId={props.watchlistId}
        />
      ) : (
        <Loader active>Loading</Loader>
      )}
    </>
  );
}
