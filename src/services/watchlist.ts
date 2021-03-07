import { useFetch } from "src/hooks/useFetch";

function getAllWatchlistByUserId(userId) {
  const [isComplete, data] = useFetch(`/api/watchlist/users/${userId}`);
  return [isComplete, data];
}
function getAllWatchlistEntryByWatchlistId(watchlistId, value, page, size) {
  const [isComplete, data] = useFetch(
    `/api/watchlist/searchWatchlist?assetName=${value}&watchlistID=${watchlistId}&page=${page}&size=${size}`
  );

  return [isComplete, data];
}
export { getAllWatchlistByUserId, getAllWatchlistEntryByWatchlistId };
