import React from "react";
import useWebSocket from "src/hooks/useWebSocket";
import GridContainer from "src/components/grid/GridContainer";
import { Loader } from "semantic-ui-react";

const stockHeaders = [
  {
    header: "CompanyName",
    icon: "",
  },
  {
    header: "Quantity",
    icon: "",
  },
  {
    header: "Average Price",
    icon: <i className="rupee sign icon small"> </i>,
  },
  {
    header: "Cost",
    icon: <i className="rupee sign icon small"> </i>,
  },
  {
    header: "Current Price",
    icon: <i className="rupee sign icon small"> </i>,
  },
  {
    header: "Current Value",
    icon: <i className="rupee sign icon small"> </i>,
  },
  {
    header: "Profit&Loss(%)",
    icon: <i className="percent icon small"> </i>,
    showColor: true,
  },
  {
    header: "Profit&Loss",
    icon: <i className="rupee icon small"> </i>,
    showColor: true,
  },
];
export default function StockPosition({ uuid, positionList, pagination }) {
  const [isSubscriptionCompleted, myMap] = useWebSocket(uuid);

  if (isSubscriptionCompleted) {
    Array.from(myMap.values()).forEach((row, index) => {
      if (positionList[index] != undefined) {
        let companyData = Object.values(row);
        positionList[index][4] = companyData[5];
        let netValue = companyData[5] * positionList[index][1];
        positionList[index][5] = netValue;
        positionList[index][6] =
          ((netValue - positionList[index][3]) / positionList[index][3]) * 100;
        positionList[index][7] = netValue - positionList[index][3];
      }
    });
  }
  return (
    <div>
      <div> {!isSubscriptionCompleted ? <Loader active /> : null} </div>
      <GridContainer
        content={
          isSubscriptionCompleted ? stockHeaders : stockHeaders.slice(0, 4)
        }
        pagination={pagination}
        data={positionList}
      >
      </GridContainer>
    </div>
  );
}
