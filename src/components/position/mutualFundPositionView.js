import React from "react";
import GridContainer from "src/components/grid/GridContainer";
import { getPositionByuserAndAsset } from "src/services/position";
import { Loader } from "semantic-ui-react";
import { UserId } from "src/components/Objects";
import Link from "next/link";
import { AssetClass } from "src/enums/assetClass";

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
  let positionList = [];
  let [isCompleted, response] = [false];
  let assetclass = (
    AssetClass.MUTUAL_FUND.slice(0, 6) + AssetClass.MUTUAL_FUND.slice(7)
  ).toLowerCase();
  [isCompleted, response] = getPositionByuserAndAsset(
    UserId.userId,
    assetclass,
    value,
    page,
    5
  );

  const pagination = {
    activePage: page,
    totalPages: response.totalPages,
    handlePaginationChange: handlePaginationChange,
  };

  if (isCompleted) {
    response.content.forEach((element) => {
      console.log(element);
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
    });
  }

  return !isCompleted ? (
    <Loader active />
  ) : (
    <GridContainer
      content={mutualFundHeaders}
      pagination={pagination}
      data={positionList}
    ></GridContainer>
  );
}
