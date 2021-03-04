import React from "react";
import { getPositionByuserAndAsset } from "src/services/position";
import StockPosition from "src/components/position/stockPositionView";
import { UserId } from "src/components/Objects";
import Link from "next/link";
import { Loader } from "semantic-ui-react";
import { AssetClass } from "src/enums/assetClass";

export default function StockPositionList({
  searchString,
  page,
  handlePaginationChange,
}) {
  let positionList = [];
  let companyUuids = [];
  let [isContentFetchingCompleted, response] = [false];
  [isContentFetchingCompleted, response] = getPositionByuserAndAsset(
    UserId.userId,
    AssetClass.STOCK.toLowerCase(),
    searchString,
    page,
    5
  );

  const pagination = {
    activePage: page,
    totalPages: response.totalPages,
    handlePaginationChange: handlePaginationChange,
  };

  if (isContentFetchingCompleted) {
    response.content.forEach((element) => {
      companyUuids.push(element.assetDetail.id);
      positionList.push([
        <Link href={`/details/${element.assetDetail.id}`}>
          {element.assetDetail.name}
        </Link>,
        element.volume,
        element.price,
        element.price * element.volume,
      ]);
    });
  }
  return !isContentFetchingCompleted ? (
    <Loader active />
  ) : (
    <StockPosition
      uuid={companyUuids}
      positionList={positionList}
      pagination={pagination}
    ></StockPosition>
  );
}
