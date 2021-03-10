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
let dashboardHeader = [];
let dashboardPositionList = [];
export default function StockPosition({ uuid, positionList, pagination, dashboard }) {
  if(dashboard){
    dashboardHeader.length=0
    dashboardHeader.push(stockHeaders[0]);
    dashboardHeader.push(stockHeaders[4]);
    dashboardHeader.push(stockHeaders[5]);
  }
  const [isSubscriptionCompleted, myMap] = useWebSocket(uuid);

  if (isSubscriptionCompleted) {
    dashboardPositionList.length=0
    Array.from(myMap.values()).forEach((row, index) => {
      if (positionList[index] != undefined) {
        let companyData = Object.values(row);
        positionList[index][4] = companyData[5];
        let netValue = companyData[5] * positionList[index][1];
        positionList[index][5] = netValue;
        positionList[index][6] = netValue - positionList[index][3];
        positionList[index][7] =
          ((netValue - positionList[index][3]) / positionList[index][3]) * 100;
        if(dashboard){
          let headerPositionList=[];
          headerPositionList.push(positionList[index][0]);
          headerPositionList.push(positionList[index][4]);
          headerPositionList.push(positionList[index][5]);
          dashboardPositionList.push(headerPositionList);
        }
      }
    });
  }
  return (
    <div>
      <div> {!isSubscriptionCompleted ? <Loader active /> : null} </div>
      <GridContainer
        dashboard={dashboard}
        content={
          isSubscriptionCompleted ? dashboard ? dashboardHeader : stockHeaders : stockHeaders.slice(0, 4)
        }
        pagination={pagination}
        data={dashboard ? dashboardPositionList : positionList}
      ></GridContainer>
    </div>
  );
}
