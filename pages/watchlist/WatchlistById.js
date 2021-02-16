import { getAllWatchlistEntryByWatchlistId } from "../api/watchlist";
import Loading from "../loader/Loading";
import WatchlistView from "./WatchlistView";
export default function WatchlistById(props) {
  let companyUuids = [];
  const [
    isContentFetchingCompleted,
    response,
  ] = getAllWatchlistEntryByWatchlistId(props.watchlistId);

  if (isContentFetchingCompleted) {
    let responseData = response.content;
    responseData.map((item) => {
      companyUuids.push(item.assetDetail.id);
    });
  }

  return isContentFetchingCompleted ? (
    <WatchlistView
      header={props.header}
      sign={props.sign}
      companyUuids={companyUuids}
    />
  ) : (
    <Loading />
  );
}
