import { Input, Label, Accordion, Menu } from "semantic-ui-react";
import { useState } from "react";
import SliderView from "semantic-ui-react-slider";
const RangeFilter = (props) => {
	const [invalid, setInvalid] = useState(false);
	const [active, setActive] = useState(true);

	const RangeFilter = (
		<div>
			<SliderView
				onSliderValuesChange={(minValue, maxValue) => props.changeRange(props.filterIndex, minValue, maxValue)}
				sliderMinValue={props.filterDetails.lowerLimit}
				sliderMaxValue={props.filterDetails.upperLimit}
				selectedMinValue={props.selectedFilters[props.filterIndex][0]}
				selectedMaxValue={props.selectedFilters[props.filterIndex][1]}
			/>
			<div className="input-to">
				<Input
					focus
					type="number"
					placeholder={`Min (${props.filterDetails.lowerLimit})`}
					value={props.selectedFilters[props.filterIndex][0]}
					onChange={(event, data) => {
						if (data.value > props.selectedFilters[props.filterIndex][1] || data.value < 0)
							setInvalid(true);
						else setInvalid(false);
						return props.changeRange(
							props.filterIndex,
							parseInt(data.value),
							props.selectedFilters[props.filterIndex][1]
						);
					}}
					size="mini"
				/>
				<Label className="grey"> to </Label>
				<Input
					focus
					type="number"
					placeholder={`Max (${props.filterDetails.upperLimit})`}
					value={props.selectedFilters[props.filterIndex][1]}
					onChange={(event, data) => {
						if (data.value < props.selectedFilters[props.filterIndex][0] || data.value < 0)
							setInvalid(true);
						else setInvalid(false);
						return props.changeRange(
							props.filterIndex,
							props.selectedFilters[props.filterIndex][0],
							parseInt(data.value)
						);
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
