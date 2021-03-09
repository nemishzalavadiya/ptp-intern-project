import { Grid, Icon } from "semantic-ui-react"
import TopAssetContainer from "src/components/dashboard/TopAssetContainer"
const Dashboard = () => {
    let content = [{
        title: "Popular Stocks",
        link: "/stocks",
        linkTitle: "SEE All Stocks",
        data: [{
            name: "Tata Birala Consultancy Limited",
            perChange: "25",
            companyIcon: <Icon name="envelope open" />,
            sign: <Icon size="small" name="percent"></Icon>,
            secondaryData: "(3Y)"
        }, {
            name: "Infosys Private Limited",
            companyIcon: <Icon name="envelope open" />,
            perChange: "15",
            sign: <Icon size="small" name="percent"></Icon>,
            secondaryData: "(3Y)"

        }, {
            name: "Google Security Private Limited",
            companyIcon: <Icon name="envelope open" />,
            perChange: "27",
            sign: <Icon size="small" name="percent"></Icon>,
            secondaryData: "(3Y)"
        }, {
            name: "Apple SoftTech Private Limited",
            companyIcon: <Icon name="envelope open" />,
            perChange: "20",
            sign: <Icon size="small" name="percent"></Icon>,
            secondaryData: "(3Y)"
        }]
    }, {
        title: "Popular MutualFunds",
        link: "/mutualfunds",
        linkTitle: "SEE All MutualFunds",
        data: [{
            name: "Tata Birala Consultancy Limited",
            perChange: "25",
            companyIcon: <Icon name="envelope open" />,
            sign: <Icon size="small" name="percent"></Icon>,
            secondaryData: "(3Y)"
        }, {
            name: "Infosys Private Limited",
            companyIcon: <Icon name="envelope open" />,
            perChange: "15",
            sign: <Icon size="small" name="percent"></Icon>,
            secondaryData: "(3Y)"

        }, {
            name: "Google Security Private Limited",
            companyIcon: <Icon name="envelope open" />,
            perChange: "27",
            sign: <Icon size="small" name="percent"></Icon>,
            secondaryData: "(3Y)"
        }, {
            name: "Apple SoftTech Private Limited",
            companyIcon: <Icon name="envelope open" />,
            perChange: "20",
            sign: <Icon size="small" name="percent"></Icon>,
            secondaryData: "(3Y)"
        }]
    }]
    return <>
        <Grid>
            <Grid.Row>
                <Grid.Column width="10" className="top-asset">
                <div className="dashboard-left">
                    <TopAssetContainer content={content[0]} />
                    <TopAssetContainer content={content[1]} />
                </div>
                </Grid.Column>
                <Grid.Column width="6" className="position">
                <div className="dashboard-right">
                    Position
                </div>
                </Grid.Column>
            </Grid.Row>
        </Grid>
    </>;
}
export default Dashboard;