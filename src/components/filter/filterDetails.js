import { filterType } from "src/components/filter/filterType.tsx";
export const mutualFundFilters = [
	{
		title: "Risk",
		field: "risk",
		filterOptions: ["Low", "Moderately Low", "Moderate", "Moderately High", "High"],
		params: ["Low", "Moderately Low", "Moderate", "Moderately High", "High"],
		type: filterType.CHECKBOX,
	},
	{
		title: "Available To Invest",
		field: "sipAllowed",
		filterOptions: ["One-Time", "SIP"],
		params: ["false", "true"],
		type: filterType.CHECKBOX,
	},
	{
		title: "Fund Size (Cr)",
		minField: "openSize",
		maxField: "closeSize",
		lowerLimit: 0,
		upperLimit: 10000,
		type: filterType.RANGE,
	},
];
export const stockFilters = [
	{
		title: "Market Cap (Cr)",
		minField: "marketCapLowerLimit",
		maxField: "marketCapUpperLimit",
		lowerLimit: 0,
		upperLimit: 100000,
		type: filterType.RANGE,
	},
	{
		title: "Closing Price",
		minField: "closingPriceLowerLimit",
		maxField: "closingPriceUpperLimit",
		lowerLimit: 0,
		upperLimit: 100000,
		type: filterType.RANGE,
	},
];
