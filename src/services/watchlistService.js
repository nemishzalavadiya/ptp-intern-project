import { useFetch } from "src/hooks/useFetch";

function getAllWatchlistByUserId() {
  const [isComplete, data, error] = useFetch(`/api/watchlist/user`);
  return [isComplete, data, error];
}

async function addToWatchlist(watchlistEntry) {
  const response = await fetch("/api/watchlist/add-watchlistentry", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(watchlistEntry),
  });
  const body = await response.json();
  if (response.ok) {
    return body;
  } else {
    throw new Error(body.message);
  }
}

async function removeFromWatchlist(assetDetailId) {
  const response = await fetch("/api/watchlist/watchlistentry?assetDetailId="+assetDetailId, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(assetDetailId),
  });
}

export { getAllWatchlistByUserId, addToWatchlist, removeFromWatchlist };
