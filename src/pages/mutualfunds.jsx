import { useState, useEffect } from "react";
import Head from "next/head";
import FilterGroup from "src/components/filter/FilterGroup";
import Layout from "src/components/Layout";
import { mutualFundFilters } from "src/components/filter/filterDetails";
import { filterType } from "src/components/filter/filterType.tsx";
import GridContainer from "src/components/grid/GridContainer";

const mutualfunds = () => {
	const content = [
		{ header: "Company_Id", icon: "" },
		{ header: "Risk", icon: "" },
		{ header: "Min Sip", icon: <i className="rupee sign icon small"></i> },
		{ header: "Fund Size", icon: <i className="rupee sign icon small"></i> },
	];

	const initialState = {
		results: [],
		selectedFilters: Array(
			...mutualFundFilters.map((filter) =>
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
			risk: selectedFilters[0].map((i) => mutualFundFilters[0].filterOptions[i]),
			closeSize: selectedFilters[2][1],
			openSize: selectedFilters[2][0],
		};
		if (selectedFilters[1].length === 1)
			filterBody = {
				...filterBody,
				sipAllowed: selectedFilters[1][0] === 0 ? false : true,
			};
		requestFiltered(`/api/mutualfunds/filters?page=${activePage}`, filterBody).then((page) => {
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
		<Layout name="MUTUAL_FUND">
			<Head>
				<title>Pirimid Trading Platform</title>
				<link rel="icon" href="/favicon.svg" />
			</Head>
			<div className="filter-grid">
				<FilterGroup
					details={mutualFundFilters}
					addFilter={addFilter}
					removeFilter={removeFilter}
					selectedFilters={selectedFilters}
					clearFilters={clearFilters}
					changeRange={changeRange}
				/>
				<div className="right-grid">
					<GridContainer
						content={content}
						data={
							results === undefined
								? []
								: results.map((item) => [
										item.mutualFundDetail.assetDetail.name,
										item.risk,
										item.minSIP,
										item.fundSize,
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

export default mutualfunds;
