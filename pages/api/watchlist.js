import { useFetch } from "../hook/useFetch";

function getAllWatchlistByUserId(userId){
    const [data,isComplete] = useFetch('/api/watchlist/users/'+userId);
    console.log(data);
}
export { watchlist };