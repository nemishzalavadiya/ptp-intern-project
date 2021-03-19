import {useFetch} from "src/hooks/useFetch"
const getTopStocksAndMutualFunds = ()=>{
    return useFetch("/api/dashboard/assets");
}
export default getTopStocksAndMutualFunds;