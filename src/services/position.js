import { useFetch } from "src/hooks/useFetch";

function getPositionByuserAndAsset(assetClass, searchText, page, size) {
  const url = `/api/${assetClass}/position/search/?name=${searchText}&page=${page}&size=${size}`;
  const [isCompleted, response] = useFetch(url);
  return [isCompleted, response];
}

export { getPositionByuserAndAsset };
