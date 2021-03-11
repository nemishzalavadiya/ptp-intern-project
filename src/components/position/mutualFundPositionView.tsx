import { useState, useEffect } from "react";
import React from "react";
import GridContainer from "src/components/grid/GridContainer";
import { Loader } from "semantic-ui-react";
import { getMutualFundPosition } from "src/hooks/mutualFundPosition";
import Sorting from "src/components/Sorting/Sorting";
<<<<<<< HEAD
=======
import { MutualfundpositionSortingfield } from "src/components/Sorting/SortingField";
>>>>>>> 6aaf623... rebased, modified structure sorting file
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
  let intialPatternState = [];
<<<<<<< HEAD
  for(let i=0;i<mutualFundHeaders.length;i++){
=======
  for (let i = 0; i < MutualfundpositionSortingfield.length; i++) {
>>>>>>> 6aaf623... rebased, modified structure sorting file
    intialPatternState.push(0);
  }
  const [pattern, setPattern] = useState(intialPatternState);
  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let d = [];
<<<<<<< HEAD
    let size = mutualFundHeaders.length;
=======
    let size = MutualfundpositionSortingfield.length;
>>>>>>> 6aaf623... rebased, modified structure sorting file
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
<<<<<<< HEAD
=======
  const [isContentFetchingCompleted, setStatus] = useState(false);
  const [position, setPosition] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const size = 3;
  async function fetchUrl(Url) {
    let positionList = [];
    const response = await fetch(Url);
    const data = await response.json();
    const content = await data.content;
    setTotalPage(data.totalPages);
    await content.forEach((element) => {
      const {
        quantity,
        totalAmount,
        assetName,
        assetId,
        nav,
        netValue,
        profit,
      } = element;
      positionList.push([
        // <Link href={`/details/${assetId}`}> {assetName} </Link>,
        assetName,
        quantity,
        totalAmount,
        totalAmount / quantity,
        nav,
        netValue,
        (profit * totalAmount) / 100,
        profit,
      ]);
    });
    setPosition(positionList);
    setStatus(true);
  }
  useEffect(() => {
    setStatus(false);
    fetchUrl(
      `/api/mutualfund/position/search/users/${UserId.userId}?name=${searchString}&page=${page}&size=${size}&sortingField=${sortingField}&orderBy=${orderBy}`
    );
  }, [UserId.userId, searchString, page, size, sortingField, orderBy]);

>>>>>>> 6aaf623... rebased, modified structure sorting file
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
<<<<<<< HEAD
      <Sorting
        content={mutualFundHeaders}
        pattern={pattern}
        onclick={changeArrow}
      />
=======
      {position.length !== 0 ? (
        <Sorting
          content={MutualfundpositionSortingfield}
          pattern={pattern}
          onclick={changeArrow}
        />
      ) : null}
>>>>>>> 6aaf623... rebased, modified structure sorting file
      <GridContainer
        content={mutualFundHeaders}
        pagination={pagination}
        data={response}
        showHeaderGrid="disallow"
      ></GridContainer>
    </>
  );
}
