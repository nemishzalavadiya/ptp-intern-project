import { useState, useEffect } from "react";
import { AssetClass } from "src/enums/assetClass";
import Moment from "moment";
import {
  underscoreToCapitalize,
  capitalize,
} from "src/services/underscoreToCapitalize";

function getOrdersByDate(
  userId,
  startDate = Moment().format("YYYY-MM-DD"),
  EndDate = Moment().format("YYYY-MM-DD"),
  page,
  size,
  assetClass
) {
  const [results, setResults] = useState([]);
  const [content, setContent] = useState(false);
  const [totalPage, setTotalPage] = useState(0);

  async function fetchUrl(Url) {
    let dataFetched = [];
    const response = await fetch(Url);
    const data = await response.json();
    const content = await data.content;
    setTotalPage(data.totalPages);
    if (assetClass === AssetClass.STOCK) {
      await content.forEach((item) => {
        dataFetched.push([
          item.stockDetail.assetDetail.name,
          item.stockDetail.assetDetail.assetClass,
          item.price,
          item.timestamp.substr(0, 10),
          item.timestamp.substr(11, 12).substr(0, 5),
          item.orderType,
          item.productCode,
          item.status,
        ]); 
      });
    } else {
      await content.forEach((item) => {
        dataFetched.push([
          item.mutualFundDetail.assetDetail.name,
          underscoreToCapitalize(item.mutualFundDetail.assetDetail.assetClass),
          item.price,
          item.price / item.nav,
          item.timestamp.substr(0, 10),
          underscoreToCapitalize(item.investmentType),
          item.sipStatus,
        ]);
      });
    }
    setResults(dataFetched);
    setContent(true);
  }
  useEffect(() => {
    setContent(false);
    if (assetClass === AssetClass.STOCK) {
      fetchUrl(
        `/api/stock/orders/filter-by-date?userId=${userId}&startDate=${startDate}&endDate=${EndDate}&page=${page}&size=${size}`
      );
    } else {
      fetchUrl(
        `/api/mutualfund/orders/filter-by-date?userId=${userId}&startDate=${startDate}&endDate=${EndDate}&page=${page}&size=${size}`
      );
    }
  }, [userId, startDate, EndDate, page, size, assetClass]);
  return [content, totalPage, results];
}

export { getOrdersByDate };
