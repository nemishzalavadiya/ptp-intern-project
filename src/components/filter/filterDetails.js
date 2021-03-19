import { filterType } from "src/components/filter/filterType";
export const mutualFundFilters = [
	{
		title: "Risk",
		field: "risk",
		filterOptions: [
			{ value: "Low", parameter: "Low" },
			{ value: "Moderately Low", parameter: "Moderately Low" },
			{ value: "Moderate", parameter: "Moderate" },
			{ value: "Moderately High", parameter: "Moderately High" },
			{ value: "High", parameter: "High" },
		],
		type: filterType.CHECKBOX,
	},
	{
		title: "Available To Invest",
		field: "sipAllowed",
		filterOptions: [
			{ value: "One-Time", parameter: "false" },
			{ value: "SIP", parameter: "true" },
		],
		type: filterType.CHECKBOX,
	},
	{
		title: "Fund Size (Cr)",
		field: "fundSizeRange",
		minimum: 0,
		maximum: 10000,
		type: filterType.RANGE,
	},
];
export const stockFilters = [
	{
		title: "Market Cap (Cr)",
		field: "marketCapRange",
		minimum: 0,
		maximum: 100000,
		type: filterType.RANGE,
	},
	{
		title: "Closing Price",
		field: "closingRange",
		minimum: 0,
		maximum: 100000,
		type: filterType.RANGE,
	},
];
