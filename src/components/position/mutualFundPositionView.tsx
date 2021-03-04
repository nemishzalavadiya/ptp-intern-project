import React from "react";
import GridContainer from "src/components/grid/GridContainer";
import { Loader } from "semantic-ui-react";
import { UserId } from "src/components/Objects";
import { getMutualFundPosition } from "src/hooks/mutualFundPosition.ts";

const mutualFundHeaders = [
  {
    header: "CompanyName",
    icon: "",
  },
  {
    header: "Quantity",
    icon: "",
  },
  {
    header: "Total Amount",
    icon: <i className="rupee sign icon small"> </i>,
  },
  {
    header: "Average NAV",
    icon: <i className="rupee sign icon small"> </i>,
  },
  {
    header: "Current NAV",
    icon: <i className="rupee sign icon small"> </i>,
  },
  {
    header: "Current Value",
    icon: <i className="rupee sign icon small"> </i>,
  },
  {
    header: "Profit & Loss",
    icon: <i className="rupee icon small"> </i>,
    showColor: true,
  },
  {
    header: "Profit & Loss(%)",
    icon: <i className="percent icon small"> </i>,
    showColor: true,
  },
];

export default function MutualFundPosition({
  value,
  page,
  handlePaginationChange,
}) {
  let [isContentFetchingCompleted, totalPage, response] = [false, 0];
  [isContentFetchingCompleted, totalPage, response] = getMutualFundPosition(
    UserId.userId,
    value,
    page,
    5
  );

  const pagination = {
    activePage: page,
    totalPages: totalPage,
    handlePaginationChange: handlePaginationChange,
  };

  return !isContentFetchingCompleted ? (
    <Loader active />
  ) : (
    <GridContainer
      content={mutualFundHeaders}
      pagination={pagination}
      data={response}
    ></GridContainer>
  );
}
