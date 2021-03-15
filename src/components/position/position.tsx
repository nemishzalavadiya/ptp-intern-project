import { useState } from "react";
import Tab from "src/components/Tab";
import Search from "src/components/Search";
import MutualFundPosition from "src/components/position/mutualFundPositionView";
import StockPositionList from "src/components/position/stockPositionList";
import { AssetClass } from "src/enums/assetClass";
export default function Position(props) {
  const [searchString, setSearchString] = useState("");
  const [assetClass, setAssetClass] = useState(AssetClass.STOCK);
  const [activeItem, setActiveItem] = useState(0);
  const [page, setPage] = useState(0);

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
  return (
    <div>
      <Tab
        content={tabs}
        handleItemClick={handleItemClick}
        activeItem={activeItem}
      ></Tab>
      {
        !props.dashboard && <Search
          handleSearchChange={handleSearchChange}
          placeholder={`Search In ${tabs[activeItem].name} Position...`}
        />
      }
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
