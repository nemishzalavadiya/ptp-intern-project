import { useState, useEffect } from "react";
import Head from "next/head";
import FilterGroup from "src/components/filter/FilterGroup";
import Layout from "src/components/Layout";
import { stockFilters } from "src/components/filter/filterDetails";
import { filterType } from "src/components/filter/filterType.ts";
import GridContainer from "src/components/grid/GridContainer";

const stocks = () => {
	const content = [
		{ header: "Company_Id", icon: "" },
		{ header: "Market Cap (Cr)", icon: <i className="rupee sign icon small"></i> },
	];

	const initialState = {
		results: [],
		selectedFilters: Array(
			...stockFilters.map((filter) =>
				filter.type == filterType.RANGE ? [filter.lowerLimit, filter.upperLimit] : []
			)
		),
	};

	const [results, setResults] = useState(initialState.results);
	const [selectedFilters, setSelectedFilters] = useState(initialState.selectedFilters);

	const [activePage, setActivePage] = useState(0);
	const [totalPages, setTotalPages] = useState(0);

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
		let filterBody = {
			marketCapLowerLimit: selectedFilters[0][0],
			marketCapUpperLimit: selectedFilters[0][1],
			closingPriceLowerLimit: selectedFilters[1][0],
			closingPriceUpperLimit: selectedFilters[1][1],
		};

		requestFiltered(`/api/stocks/filters?page=${activePage}`, filterBody).then((page) => {
			setResults(page.content);
			setTotalPages(page.totalPages);
		});
	}, [selectedFilters, activePage]);

	const addFilter = (filterIndex, checkboxIndex) => {
		setSelectedFilters([
			...selectedFilters.map((arr, index) => (filterIndex === index ? [...arr, checkboxIndex] : [...arr])),
		]);
	};

	const removeFilter = (filterIndex, checkboxIndex) => {
		setSelectedFilters([
			...selectedFilters.map((arr, index) =>
				filterIndex === index ? [...arr].filter((item, i) => item != checkboxIndex) : [...arr]
			),
		]);
	};

	const clearFilters = () => {
		setResults(initialState.results);
		setSelectedFilters(initialState.selectedFilters);
		setActivePage(0);
	};

	const changeRange = (filterIndex, minimum, maximum) => {
		setSelectedFilters([
			...selectedFilters.map((item, index) => (index === filterIndex ? [minimum, maximum] : [...item])),
		]);
	};

	return (
		<Layout>
			<Head>
				<title>Pirimid Trading Platform</title>
				<link rel="icon" href="/favicon.svg" />
			</Head>
			<div className="filter-grid">
				<FilterGroup
					details={stockFilters}
					addFilter={addFilter}
					removeFilter={removeFilter}
					selectedFilters={selectedFilters}
					clearFilters={clearFilters}
					changeRange={changeRange}
				/>
				<div className="right-grid">
					<GridContainer
						content={content}
						data={results.map((item) => [item.stockDetail.assetDetail.name, item.marketCap])}
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
