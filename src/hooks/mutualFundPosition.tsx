import { useState, useEffect } from "react";
import Link from "next/link";

function getMutualFundPosition(searchText, page, size,dashboard) {
  const [isContentFetchingCompleted, setStatus] = useState(false);
  const [position, setPosition] = useState([]);
  const [totalPage, setTotalPage] = useState(0);

  async function fetchUrl(Url) {
    let positionList = [];
    const response = await fetch(Url);
    const data = await response.json();
    const content = await data.content;
    setTotalPage(data.totalPages);
    content.forEach((element) => {
      const {
          assetName,
          assetId,
          quantity,
          totalAmount,
          nav,
          netValue,
          profitPercentage,
          avgNav,
          profit
      } = element;
      if (dashboard) {
        positionList.push([
          <Link href={`/details/${assetId}`}>{assetName}</Link>,
          nav,
          netValue
        ]);
      } 
      else{
      positionList.push([
        <Link href={`/details/${assetId}`}>{assetName}</Link>,
        quantity,
        totalAmount,
        avgNav,
        nav,
        netValue,
        profit,
        profitPercentage,
      ]);
    }
    });
    if (dashboard) {
      positionList.sort((positionA, positionB) => positionB[1] - positionA[1])
    }
    setPosition(positionList);
    setStatus(true);
  }
  useEffect(() => {
    setStatus(false);
    fetchUrl(
      `/api/mutualfund/position/search/?name=${searchText}&page=${page}&size=${size}`
    );
  }, [searchText, page, size]);

  return [isContentFetchingCompleted, totalPage, position];
}
export { getMutualFundPosition };