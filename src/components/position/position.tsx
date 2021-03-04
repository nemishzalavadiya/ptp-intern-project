import { useState } from "react";
import Tab from "src/components/Tab";
import Search from "src/components/Search";
import MutualFundPosition from "src/components/position/mutualFundPositionView.tsx";
import StockPositionList from "src/components/position/stockPositionList.tsx";
import { AssetClass } from "src/enums/assetClass.ts";
export default function Position() {
  const [value, setValue] = useState("");
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
    setValue(data.value);
    setPage(0);
  }
  return (
    <div>
      <Tab
        content={tabs}
        handleItemClick={handleItemClick}
        activeItem={activeItem}
      ></Tab>
      <Search
        handleSearchChange={handleSearchChange}
        placeholder={`Search In ${tabs[activeItem].name} Position`}
      />
      {activeItem === 0 ? (
        <StockPositionList
          value={value}
          page={page}
          handlePaginationChange={handlePaginationChange}
        ></StockPositionList>
      ) : (
        <MutualFundPosition
          value={value}
          page={page}
          handlePaginationChange={handlePaginationChange}
        />
      )}
    </div>
  );
}
