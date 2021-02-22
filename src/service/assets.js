import { useFetch } from "../hook/useFetch";

function getAllAssets() {
  const [isComplete, data] = useFetch("/api/assets?page=0&size=10");
  return [isComplete, data];
}
function getAssetById(id) {
  const [isComplete, data] = useFetch("/api/assets/" + id);
  return [isComplete, data];
}
function getStockByAssetId(id) {
  const [isStockFetched, stockDetail] = useFetch("/api/assets/stocks/" + id);
  console.log("stock : ", stockDetail);
  return [isStockFetched, stockDetail];
}
function getMfByAssetId(id) {
  const [isMfFetched, mfDetail] = useFetch("/api/assets/mutualfunds/" + id);
  return [isMfFetched, mfDetail];
}
export { getAllAssets, getAssetById, getStockByAssetId, getMfByAssetId };
