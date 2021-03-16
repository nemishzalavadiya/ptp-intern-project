import { useState, useEffect } from "react";
import Head from "next/head";
import Link from "next/link";
import { Loader, Button, Icon } from "semantic-ui-react";
import FilterGroup from "src/components/filter/FilterGroup";
import Layout from "src/components/Layout";
import { stockFilters } from "src/components/filter/filterDetails";
import { filterType } from "src/components/filter/filterType.tsx";
import GridContainer from "src/components/grid/GridContainer";
import useWebSocket from "src/hooks/useWebSocket";
import Sorting from "src/components/Sorting/Sorting";
const stocks = () => {
	const content = [
		{ header: "Company", icon: "" },
		{ header: "Market Price", icon: <i className="rupee sign icon small"></i>, sortable: false },
		{ header: "Close Price", icon: <i className="rupee sign icon small"></i>, sortable: false },
		{ header: "Market Cap", icon: <i className="rupee sign icon small"></i> },
	];

  let initailPattern=[];
  for(let i=0;i<content.length;i++){
    initailPattern.push(0);
  }
  const [pattern, setPattern] = useState(initailPattern);
  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let midPattern = [];
    let size = content.length;
    for (let i = 0; i < size; i++) {
		midPattern.push(0);
    }
    midPattern[index] = 1 - pattern[index];
    setPattern(midPattern);
    if (midPattern[index] == 1) {
      setOrderBy("DESC");
    } else {
      setOrderBy("ASC");
    }
    setSortingField(fieldName);
  }
	const initialState = {
		results: [],
		selectedFilters: Array(
			...stockFilters.map((filter) =>
				filter.type == filterType.RANGE ? { minimum: filter.minimum, maximum: filter.maximum } : []
			)
		),
	};

  const [results, setResults] = useState(initialState.results);
  const [selectedFilters, setSelectedFilters] = useState(
    initialState.selectedFilters
  );

  const [activePage, setActivePage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  let isSubscriptionCompleted = false;
  let subscriptionDataMap = new Map();

  async function requestFiltered(url = "", data = {}) {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    return response.json();
  }

  useEffect(() => {
    let filterBody = {};
    selectedFilters.forEach((filter, index) => {
      if (stockFilters[index].type == filterType.RANGE) {
        filterBody[stockFilters[index].field] = filter;
      }
    });

		requestFiltered(`/api/stocks/filters?page=${activePage}&sortingField=${sortingField}&orderBy=${orderBy}`, filterBody).then((page) => {
			setResults(page.content);
			setTotalPages(page.totalPages);
			subscriptionDataMap.clear();
			setSubscriptionIdList(
				page.content === undefined ? [] : page.content.map((item) => item.stockDetail.assetDetail.id)
			);
		});
	}, [selectedFilters, activePage, orderBy, sortingField]);

  const [subscriptionIdList, setSubscriptionIdList] = useState(
    results === undefined
      ? []
      : results.map((item) => item.stockDetail.assetDetail.id)
  );

  [isSubscriptionCompleted, subscriptionDataMap] = useWebSocket(
    subscriptionIdList
  );

  const pageReset = () => {
    setActivePage(0);
  };

  const setSelectedState = (selectedGroupState) => {
    setSelectedFilters([...selectedGroupState]);
  };

  return (
    <Layout name="STOCK">
      <Head>
        <title>Pirimid Trading Platform</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>

      <div className="filter-grid">
        <FilterGroup
          details={stockFilters}
          selectedFilters={selectedFilters}
          pageReset={pageReset}
          setSelectedState={setSelectedState}
        />
        {isSubscriptionCompleted ? (
          <div className="right-grid">
            <GridContainer
              content={content}
              data={
                results === undefined
                  ? []
                  : results.map((item) => [
                      <Link
                        href={`/details/${item.stockDetail.assetDetail.id}`}
                      >
                        {item.stockDetail.assetDetail.name}
                      </Link>,
                      subscriptionDataMap.get(
                        item.stockDetail.assetDetail.id
                      ) === undefined
                        ? ""
                        : subscriptionDataMap.get(
                            item.stockDetail.assetDetail.id
                          ).marketPrice,
                      subscriptionDataMap.get(
                        item.stockDetail.assetDetail.id
                      ) === undefined
                        ? ""
                        : subscriptionDataMap.get(
                            item.stockDetail.assetDetail.id
                          ).close,
                      item.marketCap,
                      <Button>
                       <i class="heart icon"></i> Add
                      </Button>,
                    ])
              }
              pagination={{
                activePage,
                totalPages,
                handlePaginationChange: setActivePage,
              }}
            />
          </div>
        ) : (
          <Loader active>Loading...</Loader>
        )}
      </div>
    </Layout>
  );
};

export default stocks;
