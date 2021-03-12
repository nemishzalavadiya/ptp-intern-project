import React from "react";
import { Loader, Menu, Input, Form, Grid } from "semantic-ui-react";
import { useState } from "react";
import { UserId } from "src/components/Objects";
import { getOrdersByDate } from "src/services/orderService";
import GridContainer from "src/components/grid/GridContainer";
import "semantic-ui-css/semantic.min.css";
import styles from "src/styles/Layout.module.scss";
import { AssetClass } from "src/enums/assetClass";
import Sorting from "src/components/Sorting/Sorting";
import { stockOrders, mfOrders } from "src/components/Sorting/fields"; 
export default function AssetOrder(props) {
  const stockHeader = [
    { header: "Company Name", icon: "" },
    { header: "Asset Class", icon: "" },
    { header: "Price", icon: <i className="rupee sign icon small"></i> },
    { header: "Date", icon: "" },
    { header: "Time", icon: "" },
    { header: "Order Type", icon: "" },
    { header: "Price Type", icon: "" },
    { header: "Status", icon: "" },
  ];
  const mutualFundHeader = [
    { header: "Company Name", icon: "" },
    { header: "Asset Class", icon: "" },
    { header: "Amount", icon: <i className="rupee sign icon small"></i> },
    { header: "Start Date", icon: "" },
    { header: "Frequency", icon: "" },
    { header: "SIP Status", icon: "" },
  ];
  let [isContentFetchingCompleted, totalPage, response] = [false, 0];
  const [page, setPage] = useState({
    pages: 0,
  });

  const assetClass =
    props.activeItem == 0 ? AssetClass.STOCK : AssetClass.MUTUAL_FUND;

  [isContentFetchingCompleted, totalPage, response] = getOrdersByDate(
    UserId.userId,
    props.startDate,
    props.endDate,
    page.pages,
    5,
    assetClass
  );
  function handlePaginationChange(pageNo) {
    setPage({ pages: pageNo });
  }

  const pagination = {
    activePage: page.pages,
    totalPages: totalPage,
    handlePaginationChange: handlePaginationChange,
  };

  function handleItemClick(index) {
    setActiveItem(index);
  }
  let initailPatternStock=[];
  let totalLength1 = assetClass === AssetClass.STOCK ? stockOrders.length : mfOrders.length;
  for(let i=0;i<totalLength1;i++){
    initailPatternStock.push(0);
  }
  const [patternStock, setPatternStock] = useState(initailPatternStock);
  const [StockOrderBy, setStockOrderBy] = useState("");
  const [sortingFieldStock, setSortingFieldStock] = useState("");
  function changeArrowStock(index, fieldName) {
    let d = [];
    let size = totalLength1;
    for (let i = 0; i < size; i++) {
      d.push(0);
    }
    d[index] = 1 - patternStock[index];
    setPatternStock(d);
    if (d[index] == 1) {
      setStockOrderBy("DESC");
    } else {
      setStockOrderBy("ASC");
    }
    setSortingFieldStock(fieldName);
  }

  let initailPatternMf=[];
  let totalLength2 = assetClass === AssetClass.STOCK ? stockOrders.length : mfOrders.length;
  for(let i=0;i<totalLength2;i++){
    initailPatternMf.push(0);
  }
  const [patternMf, setPatternMf] = useState(initailPatternMf);
  const [MfOrderBy, setMfOrderBy] = useState("");
  const [sortingFieldMf, setSortingFieldMf] = useState("");
  function changeArrowMf(index, fieldName) {
    let d = [];
    let size = totalLength2;
    for (let i = 0; i < size; i++) {
      d.push(0);
    }
    d[index] = 1 - patternMf[index];
    setPatternMf(d);
    if (d[index] == 1) {
      setMfOrderBy("DESC");
    } else {
      setMfOrderBy("ASC");
    }
    setSortingFieldMf(fieldName);
  }

  return (
    <div>
      {isContentFetchingCompleted ? (
        <>
        {response.length !== 0 ? <Sorting content={assetClass === AssetClass.STOCK ? stockOrders : mfOrders} pattern={assetClass === AssetClass.STOCK ? patternStock : patternMf} onclick={assetClass === AssetClass.STOCK ? changeArrowStock :changeArrowMf} /> : null}
        <GridContainer
          content={
            response.length === 0
              ? null
              : response[0][1] === AssetClass.STOCK
              ? stockHeader
              : mutualFundHeader
          }
          data={response}
          pagination={pagination}
          showHeaderGrid="disable"
        />
        </>
      ) : (
        <Loader active />
      )}
    </div>
  );
}
