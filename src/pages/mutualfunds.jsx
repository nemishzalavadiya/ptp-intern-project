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
				filter.type == filterType.RANGE
					? { value: { min: filter.lowerLimit, max: filter.upperLimit } }
					: { value: [] }
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
		let filterBody = {};
		selectedFilters.forEach((filter, index) => {
			if (mutualFundFilters[index].type == filterType.RANGE) {
				filterBody[mutualFundFilters[index].minField] = filter.value.min;
				filterBody[mutualFundFilters[index].maxField] = filter.value.max;
			}
			if (mutualFundFilters[index].type == filterType.CHECKBOX) {
				filterBody[mutualFundFilters[index].field] = filter.value;
			}
		});
		requestFiltered(`/api/mutualfunds/filters?page=${activePage}`, filterBody).then((page) => {
			setResults(page.content);
			setTotalPages(page.totalPages);
		});
	}, [selectedFilters, activePage]);

	const addFilter = (filterIndex, checkboxIndex) => {
		setSelectedFilters([
			...selectedFilters.map((filter, index) =>
				filterIndex === index
					? {
							value: [
								...selectedFilters[filterIndex].value,
								mutualFundFilters[filterIndex].params[checkboxIndex],
							],
					  }
					: { ...filter }
			),
		]);
	};
	const removeFilter = (filterIndex, checkboxIndex) => {
		setSelectedFilters([
			...selectedFilters.map((filter, index) =>
				filterIndex === index
					? {
							value: [
								...selectedFilters[filterIndex].value.filter(
									(option) => option != mutualFundFilters[filterIndex].params[checkboxIndex]
								),
							],
					  }
					: { ...filter }
			),
		]);
	};

	const clearFilters = () => {
		setResults(initialState.results);
		setActivePage(0);
		setSelectedFilters(initialState.selectedFilters);
	};

	const changeRange = (filterIndex, minimum, maximum) => {
		setSelectedFilters([
			...selectedFilters.map((filter, index) =>
				filterIndex === index ? { value: { min: minimum, max: maximum } } : { ...filter }
			),
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
