import { useState, useEffect } from "react";
import Head from "next/head";
import Link from "next/link";
import FilterGroup from "src/components/filter/FilterGroup";
import Layout from "src/components/Layout";
import { mutualFundFilters } from "src/components/filter/filterDetails";
import { filterType } from "src/components/filter/filterType.tsx";
import GridContainer from "src/components/grid/GridContainer";
import { useRouter } from "next/router";

const mutualfunds = () => {
	const router = useRouter();
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
				filter.type == filterType.RANGE ? { minimum: filter.minimum, maximum: filter.maximum } : []
			)
		),
	};

	const [results, setResults] = useState(initialState.results);
	const [selectedFilters, setSelectedFilters] = useState(initialState.selectedFilters);

	const [activePage, setActivePage] = useState(0);
	const [totalPages, setTotalPages] = useState(0);

	//method to get index by field from mutualFundFilter
	function getIndexFromMutualFundFilterByField(field) {
		for (let [index, filter] of mutualFundFilters.entries()) {
			if (filter.field === field) {
				return index;
			}
		}
		return null;
	}

	//adding dashboard filters
	if (Object.keys(router.query).length !== 0) {
		for (let filters in router.query) {
			let filtersData = JSON.parse(router.query[filters].toString());
			for (let field in filtersData) {
				let index = getIndexFromMutualFundFilterByField(field.toString());
				if (index != null) {
					if (mutualFundFilters[index].type === filterType.CHECKBOX) {
						initialState.selectedFilters[index].push(filtersData[field])
					} else if (mutualFundFilters[index].type === filterType.RANGE) {
						for (let key in filtersData[field]) {
							initialState.selectedFilters[index][key] = filtersData[field][key];
						}
					}
				}
			}
		}
		router.replace("/mutualfunds", undefined, { shallow: true });
	}
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
			filterBody[mutualFundFilters[index].field] = filter;
		});
		requestFiltered(`/api/mutualfunds/filters?page=${activePage}`, filterBody).then((page) => {
			setResults(page.content);
			setTotalPages(page.totalPages);
		});
	}, [selectedFilters, activePage]);

	const pageReset = () => {
		setActivePage(0);
	};

	const setSelectedState = (selectedGroupState) => {
		setSelectedFilters(selectedGroupState);
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
					pageReset={pageReset}
					selectedFilters={selectedFilters}
					setSelectedState={setSelectedState}
				/>
				<div className="right-grid">
					<GridContainer
						content={content}
						data={
							results === undefined
								? []
								: results.map((item) => [
									<Link href={`/details/${item.mutualFundDetail.assetDetail.id}`}>
										{item.mutualFundDetail.assetDetail.name}
									</Link>,
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
