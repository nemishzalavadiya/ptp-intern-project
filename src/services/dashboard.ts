import {useFetch} from "src/hooks/useFetch"
const getTopStocksAndMutualFunds = ()=>{
    return useFetch("/api/assets/dashboard");
}
export default getTopStocksAndMutualFunds;