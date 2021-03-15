import { useState, useEffect } from "react";
import React from "react";
import GridContainer from "src/components/grid/GridContainer";
import { Loader } from "semantic-ui-react";
import { getMutualFundPosition } from "src/hooks/mutualFundPosition";
import Sorting from "src/components/Sorting/Sorting";
const mutualFundHeaders = [
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
    header: "Total Amount",
    icon: <i className="rupee sign icon small"> </i>,
    sortable: false
  },
  {
    header: "Average NAV",
    icon: <i className="rupee sign icon small"> </i>,
    sortable: false
  },
  {
    header: "Current NAV",
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
  let intialPatternState = [];
  for(let i=0;i<mutualFundHeaders.length;i++){
    intialPatternState.push(0);
  }
  const [pattern, setPattern] = useState(intialPatternState);
  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let d = [];
    let size = mutualFundHeaders.length;
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
  
  let [isContentFetchingCompleted, totalPage, response] = [false, 0];
  [isContentFetchingCompleted, totalPage, response] = getMutualFundPosition(
    UserId.userId,
    searchString,
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
    // <GridContainer
    //   dashboard={dashboard}
    //   content={dashboard? dashboardPosition : mutualFundHeaders}
    //   pagination={pagination}
    //   data={response}
    // ></GridContainer>
    <>
      {response.length !== 0 ? (
        <Sorting
          content={mutualFundHeaders}
          pattern={pattern}
          onclick={changeArrow}
        />
      ) : null}
      <GridContainer
        content={mutualFundHeaders}
        pagination={pagination}
        data={response}
        showHeaderGrid="disable"
      ></GridContainer>
    </>
  );
}
