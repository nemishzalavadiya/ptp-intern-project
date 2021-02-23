import { useEffect, useState } from "react";
import { Search } from "semantic-ui-react";
import WatchlistView from "src/components/watchlist/WatchlistView";
import WatchlistById from "src/components/watchlist/WatchlistById";
import { useFetch } from "src/hooks/useFetch";
import debounce from "src/services/debounce";

export default function StockSearch(props) {
  const [value, setValue] = useState("");
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  let lists = [];
  let [isCompleted, data] = [false];

  const pagination = {
    activePage: 0,
    totalPages: 20,
    // handlePaginationChange: handlePaginationChange,
  };

  let url = `/api/watchlist/searchWatchlist?AssetName=${value}&watchlistID=${props.watchlistId}`;
  [isCompleted, data] = useFetch(url);

  function handleSearchChange(e, data) {
    setValue(data.value);
  }
  if (isCompleted) {
    lists = [];
    data.forEach((item) => {
      lists.push(item.assetDetail.id);
    });
  }
  console.log('SC-value',value);
  console.log('SC-lists',lists);
  return (
    <>
      <Search
        fluid
        size="big"
        onSearchChange={(e, data) => debounce(handleSearchChange(e, data),100)}
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
      ) : (
        <WatchlistById
          content={props.content}
          watchlistId={props.watchlistId}
        />
      )}
    </>
  );
}
