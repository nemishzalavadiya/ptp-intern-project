import React from "react";
import { Loader, Menu, Input, Form, Grid } from "semantic-ui-react";
import { useState } from "react";
import { UserId } from "src/components/Objects";
import { getOrdersByDate } from "src/services/orderService";
import GridContainer from "src/components/grid/GridContainer";
import "semantic-ui-css/semantic.min.css";
import styles from "src/styles/Layout.module.scss";
import { AssetClass } from "src/enums/assetClass";

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

  return (
    <div>
      {isContentFetchingCompleted ? (
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
        />
      ) : (
        <Loader active />
      )}
    </div>
  );
}
