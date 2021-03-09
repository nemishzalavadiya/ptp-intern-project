import { useState, useEffect } from "react";
import Head from "next/head";
import Link from "next/link";
import FilterGroup from "src/components/filter/FilterGroup";
import Layout from "src/components/Layout";
import { stockFilters } from "src/components/filter/filterDetails";
import { filterType } from "src/components/filter/filterType.tsx";
import GridContainer from "src/components/grid/GridContainer";
import useWebSocket from "src/hooks/useWebSocket";
import Sorting from "src/components/Sorting/Sorting";
const content1 = ["marketCap"];
const stocks = () => {
	const content = [
		{ header: "Company_Id", icon: "" },
		{ header: "Market Price", icon: <i className="rupee sign icon small"></i> },
		{ header: "Close Price", icon: <i className="rupee sign icon small"></i> },
		{ header: "Market Cap (Cr)", icon: <i className="rupee sign icon small"></i> },
	];
  const [pattern, setPattern] = useState([0]);
  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let d = [];
    let size = content1.length;
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
    console.log(fieldName);
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
	const [selectedFilters, setSelectedFilters] = useState(initialState.selectedFilters);

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

		requestFiltered(`/api/stocks/filters?page=${activePage}`, filterBody).then((page) => {
			setResults(page.content);
			setTotalPages(page.totalPages);
		});
	}, [selectedFilters, activePage]);

	useEffect(() => {
		subscriptionDataMap.clear();
		setSubscriptionIdList(results === undefined ? [] : results.map((item) => item.stockDetail.assetDetail.id));
	}, [results]);

	const [subscriptionIdList, setSubscriptionIdList] = useState(
		results === undefined ? [] : results.map((item) => item.stockDetail.assetDetail.id)
	);

	[isSubscriptionCompleted, subscriptionDataMap] = useWebSocket(subscriptionIdList);

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
				<div className="right-grid">
        <Sorting content={content1} pattern={pattern} onclick={changeArrow} />
					<GridContainer
						content={content}
						data={
							results === undefined
								? []
								: results.map((item) => [
										<Link href={`/details/${item.stockDetail.assetDetail.id}`}>
											{item.stockDetail.assetDetail.name}
										</Link>,
										subscriptionDataMap.get(item.stockDetail.assetDetail.id) === undefined
											? ""
											: subscriptionDataMap.get(item.stockDetail.assetDetail.id).marketPrice,
										subscriptionDataMap.get(item.stockDetail.assetDetail.id) === undefined
											? ""
											: subscriptionDataMap.get(item.stockDetail.assetDetail.id).close,
										item.marketCap,
								  ])
						}
						pagination={{
							activePage,
							totalPages,
							handlePaginationChange: setActivePage,
						}}
					/>
				</div>
			</div>
		</Layout>
	);
};

export default stocks;
