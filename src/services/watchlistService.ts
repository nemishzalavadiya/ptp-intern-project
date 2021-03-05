import { useFetch } from "src/hooks/useFetch";

function getAllWatchlistByUserId() {
  const [isComplete, data, error] = useFetch(`/api/watchlist/user`);
  return [isComplete, data, error];
}

export { getAllWatchlistByUserId };
