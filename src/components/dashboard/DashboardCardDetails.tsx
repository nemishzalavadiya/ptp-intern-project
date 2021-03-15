import { Icon, Image } from "semantic-ui-react";

const fundSizeRange = {
    fundSizeRange: {
        minimum: 100,
        maximum: 10000
    }
}
const sipAllowed = {
    sipAllowed: "true"
}
const riskLow = {
    risk: "Low"
}
const riskHigh = {
    risk: "High"
}
const CardHeader = [{
    title: "Popular Stocks",
    type: 'asset',
    link: "/stocks",
    linkTitle: "SEE ALL STOCKS",
    data: {
        id: "assetDetailId",
        sortBy: "peRatio",
        sign: <Icon size="small" name="percent"></Icon>,
        secondaryData: "(P/E)",
        objectField: 'dashboardStockDTOList'
    }
}, {
    title: "Popular Funds",
    type: 'asset',
    link: "/mutualfunds",
    linkTitle: "SEE ALL MUTUAL FUNDS",
    data: {
        id: "assetDetailId",
        sortBy: "risk",
        sign: "",
        secondaryData: "",
        objectField: 'dashboardMutualFundDTOList'
    }
}, {
    title: "Handpicked Collections",
    type: 'filter',
    iconList: [
        {
            icon: <img src="/risk/icons8-low-risk-48.png" alt="Low Risk" />,
            message: "Low Risk",
            link: `/mutualfunds?filter=${JSON.stringify(riskLow)}`
        },
        {
            icon: <img src="/sip/icons8-sip-48.png" alt="SIP" />,
            message: "SIP Only",
            link: `/mutualfunds?filter=${JSON.stringify(sipAllowed)}`
        }, {
            icon: <img src="/mf/icons8-mf-48.png" alt="Top MutualFunds" />,
            message: "Top Funds",
            link: `/mutualfunds?filter=${JSON.stringify(fundSizeRange)}`
        }, {
            icon: <img src="/risk/icons8-high-risk-48.png" alt="High Risk" />,
            message: "High Risk",
            link: `/mutualfunds?filter=${JSON.stringify(riskHigh)}`
        }
    ]
}];

export default CardHeader;