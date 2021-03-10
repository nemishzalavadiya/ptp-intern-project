import React from "react";
import GridContainer from "src/components/grid/GridContainer";
import { Loader } from "semantic-ui-react";
import { getMutualFundPosition } from "src/hooks/mutualFundPosition";

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
const dashboardPosition = [mutualFundHeaders[0],mutualFundHeaders[4],mutualFundHeaders[5]]
export default function MutualFundPosition({
  searchString,
  page,
  handlePaginationChange,
  dashboard
}) {
  let [isContentFetchingCompleted, totalPage, response] = [false, 0];
  [isContentFetchingCompleted, totalPage, response] = getMutualFundPosition(
    searchString,
    page,
    5,
    dashboard
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
      dashboard={dashboard}
      content={dashboard? dashboardPosition : mutualFundHeaders}
      pagination={pagination}
      data={response}
    ></GridContainer>
  );
}
