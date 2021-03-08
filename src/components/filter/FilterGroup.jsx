import { Button, List, Header } from "semantic-ui-react";
import CheckboxFilter from "src/components/filter/CheckboxFilter";
import RangeFilter from "src/components/filter/RangeFilter";
import { filterType } from "src/components/filter/filterType.tsx";
import { useEffect, useState } from "react";

const Filter = (props) => {
	const initialState = {
		selectedFilters: Array(
			...props.details.map((filter) =>
				filter.type == filterType.RANGE ? { minimum: filter.minimum, maximum: filter.maximum } : []
			)
		),
	};

	const [selectedGroupState, setSelectedGroupState] = useState(props.selectedFilters);

	useEffect(() => {
		props.setSelectedState(selectedGroupState);
	}, [selectedGroupState]);

	const addFilter = (filterIndex, checkboxIndex) => {
		setSelectedGroupState([
			...selectedGroupState.map((filter, index) =>
				filterIndex === index
					? [
							...selectedGroupState[filterIndex],
							props.details[filterIndex].filterOptions[checkboxIndex].parameter,
					  ]
					: filter
			),
		]);
	};
	const removeFilter = (filterIndex, checkboxIndex) => {
		setSelectedGroupState([
			...selectedGroupState.map((filter, index) =>
				filterIndex === index
					? [
							...selectedGroupState[filterIndex].filter(
								(option) => option != props.details[filterIndex].filterOptions[checkboxIndex].parameter
							),
					  ]
					: filter
			),
		]);
	};

	const changeRange = (filterIndex, minimum, maximum) => {
		setSelectedGroupState([
			...selectedGroupState.map((filter, index) => (filterIndex === index ? { minimum, maximum } : filter)),
		]);
	};

	const clearFilters = () => {
		props.pageReset();
		setSelectedGroupState(initialState.selectedFilters);
	};

	return (
		<div className="group-main">
			<div className="filter-header">
				<Header as="h3" className="main-title">
					Filters
				</Header>
				<Button basic className="green" content="Clear All" onClick={clearFilters} />
			</div>
			<List>
				{props.details.map((filter, index) => (
					<List.Item key={index}>
						{filter.type === filterType.CHECKBOX ? (
							<CheckboxFilter
								filterDetails={filter}
								filterIndex={index}
								addFilter={addFilter}
								removeFilter={removeFilter}
								selectedFilters={props.selectedFilters}
							/>
						) : (
							<RangeFilter
								filterDetails={filter}
								filterIndex={index}
								selectedFilters={props.selectedFilters}
								changeRange={changeRange}
							/>
						)}
					</List.Item>
				))}
			</List>
		</div>
	);
};

export default Filter;
