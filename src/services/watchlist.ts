import { useFetch } from "src/hooks/useFetch";
import { useAuth } from "src/components/contexts/auth";

function getAllWatchlistByUserId(userId) {
  let { token } = useAuth();
  let options = {
    headers: {
      Authentication: token,
    },
  };
  const [isComplete, data] = useFetch(
    "/api/watchlist/users/" + userId,
    options
  );
  return [isComplete, data];
}
function getAllWatchlistEntryByWatchlistId(watchlistId, page, size) {
  let { token } = useAuth();
  let options = {
    headers: {
      Authentication: token,
    },
  };
  const [isComplete, data] = useFetch(
    "/api/watchlist/" + watchlistId + "?page=" + page + "&size=" + size,
    options
  );

  return [isComplete, data];
}
export { getAllWatchlistByUserId, getAllWatchlistEntryByWatchlistId };
