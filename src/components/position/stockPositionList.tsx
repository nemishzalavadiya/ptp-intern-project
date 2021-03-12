import React from "react";
import { useState, useEffect } from "react";
import { getPositionByuserAndAsset } from "src/services/position";
import StockPosition from "src/components/position/stockPositionView";
import Link from "next/link";
import { Loader } from "semantic-ui-react";
import { AssetClass } from "src/enums/assetClass";
import {StockpositionField} from "src/components/Sorting/fields";
import Sorting from "src/components/Sorting/Sorting";

export default function StockPositionList({
  searchString,
  page,
  handlePaginationChange,
  dashboard
}) {
  let intialPatternState = [];
  for (let i = 0; i < StockpositionField.length; i++) {
    intialPatternState.push(0);
  }
  const [pattern, setPattern] = useState(intialPatternState);
  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let d = [];
    let size = StockpositionField.length;
    for (let i = 0; i < size; i++) {
      d.push(0);
    }
    d[index] = 1 - pattern[index];
    setPattern(d);
    if (d[index] == 1) {
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
          content={StockpositionField}
          pattern={pattern}
          onclick={changeArrow}
        />
      ) : null}
    <StockPosition
      uuid={companyUuids}
      positionList={positionList}
      pagination={pagination}
      dashboard={dashboard}
      showHeaderGrid="disallow"
    ></StockPosition>
    </>
  );
}
