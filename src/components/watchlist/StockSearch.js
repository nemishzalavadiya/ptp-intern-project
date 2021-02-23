import { useEffect, useState } from "react";
import { Search } from "semantic-ui-react";
import WatchlistView from "src/components/watchlist/WatchlistView";
import { useFetch } from "src/hooks/useFetch";

const StockSearch = (props) => {

const content = [
  { header: "Company_Id", icon: "" },
  { header: "Open", icon: <i className="rupee sign icon small"></i> },
  { header: "Close", icon: <i className="rupee sign icon small"></i> },
  { header: "last", icon: <i className="rupee sign icon small"></i> },
  { header: "High", icon: <i className="rupee sign icon small"></i> },
  { header: "Low", icon: <i className="rupee sign icon small"></i> },
  { header: "% CHG", icon: <i className="percent icon small"></i> },
];

    const [value1, setValue1] = useState("");
    const [results, setResults] = useState([]);
    const [loading, setLoading] = useState(false);
    const [list,setList] = useState([]);
    const lists = [];

    const pagination = {
        activePage: 0,
        totalPages: 20,
       // handlePaginationChange: handlePaginationChange,
    };

    const handleSearchChange = (e, data) => {
        setValue1(data.value);
    }

    const [isCompleted,data] = useFetch(`/api/watchlist/searchWatchlist?AssetName=${value1}&watchlistID=00000000-0000-0000-0000-000000000001`);
    if(isCompleted){
        data.forEach((item) => {
            lists.push(item.assetDetail.id);
        })
    } 
     /*useEffect(() => {
        if (value1 !== "") {
            setLoading(true);
             fetch(`/api/watchlist/searchWatchlist?AssetName=${value1}&watchlistID=00000000-0000-0000-0000-000000000001`)
                .then((res) => res.json())
                .then((data) => {
                    setResults({
                        ...data.map((item) => {
                            lists.push(item.assetDetail.id);  
                            return { title: item.assetDetail.name , id:item.assetDetail.id , asset:item.assetDetail.assetClass};
                        }),
                    }) 
                    setLoading(false);
                });
        }
    }, [value1]);
    */
    console.log('fromStockSearch'); 
    console.log(isCompleted===true)
    return (
        isCompleted? <div>
        <Search
            fluid
            size="big"
            loading={loading}
            //onResultSelect={(e,data) => handleSearchChange(e,data)}
            onSearchChange={(e,data) => handleSearchChange(e,data)}
            results={results}
            value={value1}
        />
        <WatchlistView
            content={content}
            companyUuids={lists}
            pagination={pagination}
            tabId={"00000000-0000-0000-0000-000000000001"}
        />
        </div>:null
    );
};
export default StockSearch;