import { Grid, Loader, Segment } from "semantic-ui-react";
import Link from "next/link";
import getTopStocksAndMutualFunds from "src/services/dashboard";
import TopAssetContainer from "src/components/dashboard/TopAssetContainer";
import Position from "src/components/position/position";
import CardHeader from "src/components/dashboard/DashboardCardDetails"
const Dashboard = () => {
    const [isCompleted, response] = getTopStocksAndMutualFunds();
    return isCompleted ?
        <>
            <Grid>
                <Grid.Row>
                    <Grid.Column width="10" className="top-asset">
                        <div className="dashboard-left">
                            <TopAssetContainer header={CardHeader[0]} data={response.dashboardStockDTOList} />
                            <TopAssetContainer header={CardHeader[1]} data={response.dashboardMutualFundDTOList} />
                            <TopAssetContainer header={CardHeader[2]} filter={true} data={CardHeader[2].data} />
                        </div>
                    </Grid.Column>
                    <Grid.Column width="6" className="position">
                        <div className="dashboard-right">
                            <Segment className="dashboard-position">
                                <div className="dashboard-position-title">
                                    <h3>Your Investments</h3>
                                </div>
                                <Position dashboard={true} />
                                <div className="dashboard-position-link">
                                    <Link href="/position"><span className="dashboard-position-link-title">VIEW ALL INVESTMENT</span></Link>
                                </div>
                            </Segment>
                        </div>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </>
        : <Loader active>Loading</Loader>;
}
export default Dashboard;