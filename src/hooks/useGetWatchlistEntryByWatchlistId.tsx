import { useFetch } from "src/hooks/useFetch";
import { useEffect, useState } from "react";

const useWatchlist = (watchlistId, searchQuery, page, size) => {
    const [content, setContent] = useState({
        data: {},
        error: false,
        isComplete: false,
    });

    async function getAllWatchlistEntryByWatchlistId() {
        const response = await fetch(`/api/watchlist/searchWatchlist?assetName=${searchQuery}&watchlistID=${watchlistId}&page=${page}&size=${size}`);
        const data = await response.json();
        if (response.ok) {
            let companyUuids = [];
            data.content.forEach((item) => {
                companyUuids.push(item.assetDetail.id);
            });
            setContent({ data: { companyUuids: companyUuids, totalPages: data.totalPages }, error: false, isComplete: true });
        }
        else {
            setContent({ data: {}, error: true, isComplete: true });
        }
    }

    useEffect(() => {
        getAllWatchlistEntryByWatchlistId();
    }, [watchlistId, searchQuery, page, size]);

    return [content.isComplete, content.data, content.error];
}

export default useWatchlist;