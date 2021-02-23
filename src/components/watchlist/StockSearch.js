import { useEffect, useState } from "react";
import { Loader, Search } from "semantic-ui-react";
import WatchlistView from "src/components/watchlist/WatchlistView";
import WatchlistById from "src/components/watchlist/WatchlistById";
import { useFetch } from "src/hooks/useFetch";
import debounce from "src/services/debounce";

export default function StockSearch(props) {
  const [value, setValue] = useState("");
  let lists = [];
  let [isCompleted, data] = [false];

  let url = `/api/watchlist/searchWatchlist?AssetName=${value}&watchlistID=${props.watchlistId}`;
  [isCompleted, data] = useFetch(url);
  const pagination = {
    activePage: 0,
    totalPages: 20,
    //handlePaginationChange: handlePaginationChange,
  };
  function handleSearchChange(e, data) {
    setValue(data.value);
  }
  if (isCompleted) {
    lists = [];
    data.forEach((item) => {
      lists.push(item.assetDetail.id);
    });
  }
  return (
    <>
      <Search
        fluid
        size="big"
        onSearchChange={(e, data) => debounce(handleSearchChange(e, data), 100)}
      />{" "}
      {isCompleted && value != "" ? (
        <div>
          <WatchlistView
            content={props.content}
            companyUuids={lists}
            pagination={pagination}
            tabId={props.watchlistId}
          />
        </div>
      ) : isCompleted ? (
        <WatchlistById
          content={props.content}
          watchlistId={props.watchlistId}
        />
      ) : <Loader active>Loading</Loader>}
    </>
  );
}
