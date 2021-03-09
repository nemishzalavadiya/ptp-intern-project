import { useState } from "react";
import { Loader } from "semantic-ui-react";
import WatchlistView from "src/components/watchlist/WatchlistView";
import useGetWatchlistEntryByWatchlistId from "src/hooks/useGetWatchlistEntryByWatchlistId";
import Search from "src/components/Search";

export default function WatchlistContent(props) {
  const [content, setContent] = useState({
    pages:0,
    searchQuery:"",
    watchlistId:props.watchlistId
  });
  let companyUuids = [];
  let [isWatchlistEntryFetchingCompleted, response, error] = [false, null, false];
  [isWatchlistEntryFetchingCompleted, response, error] = useGetWatchlistEntryByWatchlistId(
    props.watchlistId,
    content.searchQuery,
    content.pages,
    5
  );
  function handlePaginationChange(pageNo) {
    setContent({ pages: pageNo, watchlistId: content.watchlistId, searchQuery: content.searchQuery });
  }
  const pagination = {
    activePage: content.pages,
    totalPages: 2,
    handlePaginationChange: handlePaginationChange,
  };

  if (content.watchlistId !== props.watchlistId) {
    companyUuids.length = 0;
    setContent({ pages: 0, watchlistId: props.watchlistId, searchQuery: "" });
  }

  function handleSearchChange(e, data) {
    setContent({ pages: 0, watchlistId: props.watchlistId, searchQuery: data.value });
  }
  if (isWatchlistEntryFetchingCompleted) {
    companyUuids.length = 0;
    companyUuids = response.companyUuids;
    pagination.totalPages = response.totalPages;
  }
  return (
    <>
      <Search
        handleSearchChange={handleSearchChange}
        placeholder={"Search In Watchlist..."}
      />
      {isWatchlistEntryFetchingCompleted && !error ? (
        <WatchlistView
          content={props.content}
          companyUuids={companyUuids}
          pagination={pagination}
          tabId={props.watchlistId}
        />
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
        )}
    </>
  );
}
