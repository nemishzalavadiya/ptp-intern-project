import { useFetch } from "src/hooks/useFetch";

function getAllWatchlistByUserId() {
  const [isComplete, data, error] = useFetch(`/api/watchlist/user`);
  return [isComplete, data, error];
}
function getAllWatchlistEntryByWatchlistId(
  watchlistId,
  searchQuery,
  page,
  size
) {
  const [isComplete, data, error] = useFetch(
    `/api/watchlist/searchWatchlist?assetName=${searchQuery}&watchlistID=${watchlistId}&page=${page}&size=${size}`
  );
  return [isComplete, data, error];
}
export { getAllWatchlistByUserId, getAllWatchlistEntryByWatchlistId };
