import { Button, List, Header } from "semantic-ui-react";
import CheckboxFilter from "src/components/filter/CheckboxFilter";
import RangeFilter from "src/components/filter/RangeFilter";
import { filterType } from "src/components/filter/filterType.ts";

const Filter = (props) => {
	return (
		<div className="group-main">
			<div className="filter-header">
				<Header as="h3" className="main-title">
					Filters
				</Header>
				<Button basic className="green" content="Clear All" onClick={props.clearFilters} />
			</div>
			<List>
				{props.details.map((filter, index) => (
					<List.Item key={index}>
						{filter.type === filterType.CHECKBOX ? (
							<CheckboxFilter
								filterDetails={filter}
								filterIndex={index}
								addFilter={props.addFilter}
								removeFilter={props.removeFilter}
								selectedFilters={props.selectedFilters}
							/>
						) : (
							<RangeFilter
								filterDetails={filter}
								filterIndex={index}
								selectedFilters={props.selectedFilters}
								changeRange={props.changeRange}
							/>
						)}
					</List.Item>
				))}
			</List>
		</div>
	);
};

export default Filter;
