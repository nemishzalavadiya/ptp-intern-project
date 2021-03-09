import { Input, Label, Accordion, Menu, Header } from "semantic-ui-react";
import { useState, useEffect } from "react";
import SliderView from "semantic-ui-react-slider";

const RangeFilter = (props) => {
	const [invalid, setInvalid] = useState(false);
	const [active, setActive] = useState(true);
	const [minimum, setMinimum] = useState(props.filterDetails.minimum);
	const [maximum, setMaximum] = useState(props.filterDetails.maximum);

	useEffect(() => {
		props.changeRange(props.filterIndex, minimum, maximum);
	}, [minimum, maximum]);

	const RangeFilter = (
		<div>
			<SliderView
				onSliderValuesChange={(min, max) => {
					setMaximum(max);
					setMinimum(min);
				}}
				sliderMinValue={props.filterDetails.minimum}
				sliderMaxValue={props.filterDetails.maximum}
				selectedMinValue={props.selectedFilters[props.filterIndex].minimum}
				selectedMaxValue={props.selectedFilters[props.filterIndex].maximum}
			/>
			<div className="input-to">
				<Input
					focus
					type="number"
					placeholder={props.filterDetails.minimum}
					value={
						props.selectedFilters[props.filterIndex].minimum == 0
							? ""
							: props.selectedFilters[props.filterIndex].minimum
					}
					onChange={(event, data) => {
						setInvalid(data.value > props.selectedFilters[props.filterIndex].maximum || data.value < 0);
						setMinimum(data.value == "" ? 0 : parseInt(data.value));
					}}
					size="mini"
				/>
				<Header as="h4" className="to">
					to
				</Header>
				<Input
					focus
					type="number"
					placeholder={props.filterDetails.maximum}
					value={
						props.selectedFilters[props.filterIndex].maximum == 0
							? ""
							: props.selectedFilters[props.filterIndex].maximum
					}
					onChange={(event, data) => {
						setInvalid(data.value < props.selectedFilters[props.filterIndex].minimum || data.value < 0);
						setMaximum(data.value == "" ? 0 : parseInt(data.value));
					}}
					size="mini"
				/>
				{invalid ? (
					<Label pointing prompt className="grey">
						Invalid Values
					</Label>
				) : (
					<></>
				)}
			</div>
		</div>
	);
	return (
		<div>
			<Accordion as={Menu} vertical>
				<Menu.Item>
					<Accordion.Title
						className="acc"
						content={props.filterDetails.title}
						active={active}
						onClick={() => setActive(!active)}
					/>
					<Accordion.Content content={RangeFilter} active={active} />
				</Menu.Item>
			</Accordion>
		</div>
	);
};

export default RangeFilter;
