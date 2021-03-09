import { Card, Grid, Segment } from "semantic-ui-react";
import Link from 'next/link';
const TopAssetContainer = (props) => {
    console.log(props)
    return <div className="dashboard-grid">
        <Grid inverted>
            <Grid.Row className="dashboard-container">
                <Grid.Column floated='left' width={5}>
                    <h3>{props.content.title}</h3>
                </Grid.Column>
                <Grid.Column floated='right' width={5} className="dashboard-assetlist-link">
                    <Link href={props.content.link}>{props.content.linkTitle}</Link>
                </Grid.Column>
            </Grid.Row>
            <Grid.Row columns="equal" className="dashboard-container">
                {
                    props.content.data.map((item, index) => {
                        return <Grid.Column>
                            <Segment key={index} inverted color="grey" className="dashboard-asset-container">
                                <div className="dashboard dashboard-company-icon">{item.companyIcon}</div>
                                <div className="dashboard dashboard-company-name">{item.name}</div>
                                <div className="dashboard dashboard-description"><span>{item.perChange}</span><span>{item.sign}</span>{" "}<span className="dashboard-secondary-data">{item.secondaryData}</span></div>
                            </Segment>
                        </Grid.Column>
                    })
                }
            </Grid.Row>
        </Grid>
    </div>
}
export default TopAssetContainer;