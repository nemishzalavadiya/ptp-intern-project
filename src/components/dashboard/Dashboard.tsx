import { Grid, Icon, Loader } from "semantic-ui-react";
import getTopStocksAndMutualFunds from "src/services/dashboard";
import TopAssetContainer from "src/components/dashboard/TopAssetContainer";
const Dashboard = () => {
    let header = [{
        title: "Popular Stocks",
        link: "/stocks",
        linkTitle: "SEE All Stocks",
        data: {
            sortBy:"peRatio",
            companyIcon: <Icon name="envelope open" />,
            sign: <Icon size="small" name="percent"></Icon>,
            secondaryData: "(3Y)"
        }
    }, {
        title: "Popular MutualFunds",
        link: "/mutualfunds",
        linkTitle: "SEE All MutualFunds",
        data: {
            sortBy:"risk",
            companyIcon: <Icon name="envelope open" />,
            sign: "",
            secondaryData: ""
        }
    }]
    const [isCompleted, response] = getTopStocksAndMutualFunds();
    return isCompleted ?
        <>
            <Grid>
                <Grid.Row>
                    <Grid.Column width="10" className="top-asset">
                        <div className="dashboard-left">
                            <TopAssetContainer header={header[0]} data={response.dashboardStockDTOList} />
                            <TopAssetContainer header={header[1]} data={response.dashboardMutualFundDTOList} />
                        </div>
                    </Grid.Column>
                    <Grid.Column width="6" className="position">
                        <div className="dashboard-right">
                            Position
                </div>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </>
        : <Loader active>Loading</Loader>;
}
export default Dashboard;