import { useFetch } from "../hook/useFetch";

function getAllWatchlistByUserId(userId){
    const [isComplete,data] = useFetch('/api/watchlist/users/'+userId);
    return [isComplete,data];
}
function getAllWatchlistEntryByWatchlistId(watchlistId){
    const [isComplete,data] = useFetch('/api/watchlist/'+watchlistId);
    return [isComplete,data];
}
export { getAllWatchlistByUserId, getAllWatchlistEntryByWatchlistId };