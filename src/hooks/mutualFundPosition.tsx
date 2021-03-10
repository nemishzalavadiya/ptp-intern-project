import { useState, useEffect } from "react";
import Link from "next/link";

function getMutualFundPosition(userId, searchText, page, size, dashboard) {
  const [isContentFetchingCompleted, setStatus] = useState(false);
  const [position, setPosition] = useState([]);
  const [totalPage, setTotalPage] = useState(0);

  async function fetchUrl(Url) {
    let positionList = [];
    const response = await fetch(Url);
    const data = await response.json();
    const content = await data.content;
    setTotalPage(data.totalPages);
    //processing
    content.forEach((element) => {
      const {
        position: {
          volume,
          price,
          assetDetail: { id, name },
        },
        nav,
        netValue,
        profit,
      } = element;
      if (dashboard) {
        positionList.push([
          <Link href={`/details/${id}`}>{name}</Link>,
          nav,
          netValue
        ]);
      } else {
        positionList.push([
          <Link href={`/details/${id}`}>{name}</Link>,
          volume,
          price,
          price / volume,
          nav,
          netValue,
          (profit * price) / 100,
          profit,
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
      `/api/mutualfund/position/search/users/${userId}?name=${searchText}&page=${page}&size=${size}`
    );
  }, [userId, searchText, page, size]);

  return [isContentFetchingCompleted, totalPage, position];
}
export { getMutualFundPosition };