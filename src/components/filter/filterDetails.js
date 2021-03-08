import { filterType } from "src/components/filter/filterType.ts";
export const mutualFundFilters = [
	{
		title: "Risk",
		filterOptions: ["Low", "Moderately Low", "Moderate", "Moderately High", "High"],
		type: filterType.CHECKBOX,
	},
	{
		title: "Available To Invest",
		filterOptions: ["One-Time", "SIP"],
		type: filterType.CHECKBOX,
	},
	{
		title: "Fund Size (Cr)",
		lowerLimit: 0,
		upperLimit: 10000,
		type: filterType.RANGE,
	},
];
export const stockFilters = [
	{
		title: "Market Cap (Cr)",
		lowerLimit: 0,
		upperLimit: 100000,
		type: filterType.RANGE,
	},
	{
		title: "Closing Price",
		lowerLimit: 0,
		upperLimit: 100000,
		type: filterType.RANGE,
	},
];
