import { Checkbox, Accordion, Menu, List } from "semantic-ui-react";
import { useState } from "react";
const CheckboxFilter = (props) => {
	const [active, setActive] = useState(true);

	const CheckboxFilter = (
		<List className="checkbox-list">
			{props.filterDetails.filterOptions.map((option, index) => (
				<List.Item key={index}>
					<Checkbox
						className="check-box"
						checked={props.selectedFilters[props.filterIndex].includes(index)}
						onChange={(event, data) =>
							data.checked
								? props.addFilter(props.filterIndex, index)
								: props.removeFilter(props.filterIndex, index)
						}
						label={option}
					/>
				</List.Item>
			))}
		</List>
	);
	return (
		<div>
			<Accordion as={Menu} className="filter-collapse" vertical>
				<Menu.Item>
					<Accordion.Title
						className="acc"
						content={props.filterDetails.title}
						active={active}
						onClick={() => setActive(!active)}
					/>
					<Accordion.Content content={CheckboxFilter} active={active} />
				</Menu.Item>
			</Accordion>
		</div>
	);
};

export default CheckboxFilter;
