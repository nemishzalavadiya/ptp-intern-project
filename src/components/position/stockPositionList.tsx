import React from "react";
import { useState, useEffect } from "react";
import { getPositionByuserAndAsset } from "src/services/position";
import StockPosition from "src/components/position/stockPositionView";
import Link from "next/link";
import { Loader } from "semantic-ui-react";
import { AssetClass } from "src/enums/assetClass";
import Sorting from "src/components/Sorting/Sorting";
const stockHeaders = [
  {
    header: "Company",
    icon: "",
    sortable: false
  },
  {
    header: "Quantity",
    icon: "",
    sortable: false
  },
  {
    header: "Average Price",
    icon: <i className="rupee sign icon small"> </i>,
    sortable: false
  },
  {
    header: "Cost",
    icon: <i className="rupee sign icon small"> </i>,
    sortable: false
  },
  {
    header: "Current Price",
    icon: <i className="rupee sign icon small"> </i>,
    sortable: false
  },
  {
    header: "Current Value",
    icon: <i className="rupee sign icon small"> </i>,
    sortable: false
  },
  {
    header: "Profit/Loss",
    icon: <i className="rupee icon small"> </i>,
    showColor: true,
    sortable: false
  },
  {
    header: "Profit/Loss(%)",
    icon: <i className="percent icon small"> </i>,
    showColor: true,
    sortable: false
  },
];
export default function StockPositionList({
  searchString,
  page,
  handlePaginationChange,
  dashboard
}) {
  let intialPatternState = [];
  for (let i = 0; i < stockHeaders.length; i++) {
    intialPatternState.push(0);
  }
  const [pattern, setPattern] = useState(intialPatternState);
  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let midPattern = [];
    let size = stockHeaders.length;
    for (let i = 0; i < size; i++) {
      midPattern.push(0);
    }
    midPattern[index] = 1 - pattern[index];
    setPattern(midPattern);
    if (midPattern[index] == 1) {
      setOrderBy("DESC");
    } else {
      setOrderBy("ASC");
    }
    setSortingField(fieldName);
  }
  let positionList = [];
  let companyUuids = [];
  let [isContentFetchingCompleted, response] = [false];
  [isContentFetchingCompleted, response] = getPositionByuserAndAsset(
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
        element.price.toFixed(2),
        (element.price * element.volume).toFixed(2),
      ]);
    });
  }
  return !isContentFetchingCompleted ? (
    <Loader active />
  ) : (
    <>
    {positionList.length !== 0 ? (
        <Sorting
          content={stockHeaders}
          pattern={pattern}
          onclick={changeArrow}
        />
      ) : null}
    <StockPosition
      stockHeaders={stockHeaders}
      uuid={companyUuids}
      positionList={positionList}
      pagination={pagination}
      dashboard={dashboard}
      showHeaderGrid="disallow"
    ></StockPosition>
    </>
  );
}
