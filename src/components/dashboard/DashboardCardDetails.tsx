import { Icon } from "semantic-ui-react";

const CardHeader = [{
    title: "Popular Stocks",
    link: "/stocks",
    linkTitle: "SEE ALL STOCKS",
    data: {
        id: "assetDetailId",
        sortBy: "peRatio",
        companyIcon: <Icon name="envelope open" />,
        sign: <Icon size="small" name="percent"></Icon>,
        secondaryData: "(P/E)"
    }
}, {
    title: "Popular Funds",
    link: "/mutualfunds",
    linkTitle: "SEE ALL MUTUAL FUNDS",
    data: {
        id: "assetDetailId",
        sortBy: "risk",
        companyIcon: <Icon name="envelope open" />,
        sign: "",
        secondaryData: ""
    }
},{
    title: "Handpicked Collections"
}];

export default CardHeader;