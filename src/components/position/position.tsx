import { useState } from "react";
import Router from "next/router";
import Tab from "src/components/Tab";
import { Button } from "semantic-ui-react";
import Search from "src/components/Search";
import MutualFundPosition from "src/components/position/mutualFundPositionView";
import StockPositionList from "src/components/position/stockPositionList";
import { AssetClass } from "src/enums/assetClass";
import { getMutualFundOrdersCountBySipStatus } from "src/services/sipstatus";
import { UserId } from "src/components/Objects";

export default function Position(props) {
  const [searchString, setSearchString] = useState("");
  const [assetClass, setAssetClass] = useState(AssetClass.STOCK);
  const [activeItem, setActiveItem] = useState(0);
  const [page, setPage] = useState(0);
  const [totalSIPs,setTotalSIPs] = useState(0);
  const totalPages = 500;

  function handlePaginationChange(pageNo) {
    setPage(pageNo);
  }

  function handleItemClick(index) {
    setActiveItem(index);
    setPage(0);
    setAssetClass(index === 0 ? AssetClass.STOCK : AssetClass.MUTUAL_FUND);
  }

  const tabs = [
    {
      name: "Stock",
    },
    {
      name: "MutualFund",
    },
  ];

  function handleSearchChange(e, data) {
    setSearchString(data.value);
    setPage(0);
  }
  getMutualFundOrdersCountBySipStatus(UserId.userId, 0, totalPages).then((res) => {
    setTotalSIPs(res);
  })
  return (
    <div>
      <Tab
        content={tabs}
        handleItemClick={handleItemClick}
        activeItem={activeItem}
      ></Tab>
      <Search
        handleSearchChange={handleSearchChange}
        placeholder={`Search In ${tabs[activeItem].name} Position...`}
      />
      {assetClass === AssetClass.MUTUAL_FUND ? (
        <Button
          disabled = {!totalSIPs?true:false}
          inverted
          color="green"
          size="large"
          onClick={() => Router.push("/sipstatus")}
        >
          Active SIPs : {totalSIPs}
        </Button>
      ) : (
        ""
      )}
      {activeItem === 0 ? (
        <StockPositionList
          searchString={searchString}
          page={page}
          dashboard={props.dashboard}
          handlePaginationChange={handlePaginationChange}
        ></StockPositionList>
      ) : (
        <MutualFundPosition
          searchString={searchString}
          dashboard={props.dashboard}
          page={page}
          handlePaginationChange={handlePaginationChange}
        />
      )}
    </div>
  );
}
