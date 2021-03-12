import { Image, Grid, Popup, Segment } from "semantic-ui-react";
import Link from 'next/link';
const TopAssetContainer = (props) => {

    const imageProvider = (firstLowerCharacter) => {
        let charCode = firstLowerCharacter.charCodeAt();
        //"m" ascii 109 [ img available from a-m ]
        if (charCode > 109) {
            return String.fromCharCode(charCode - 12);
        }else if(charCode < 97){
            // 49 means remove "0" and add "a" to get in range [a-m]
            return String.fromCharCode(charCode + 49 );
        }
        return firstLowerCharacter;
    }
    String.prototype.capitalize = function () {
        return this.charAt(0).toUpperCase() + this.slice(1);
    }
    return <div className="dashboard-grid">
        <Grid inverted>
            <Grid.Row className="dashboard-container">
                <Grid.Column floated='left' width={5}>
                    <h3>{props.header.title}</h3>
                </Grid.Column>
                {
                    !props.filter && <Grid.Column floated='right' width={5} className="dashboard-assetlist-link">
                        <Link href={props.header.link}><div className="dashboard-populer-assets-link">{props.header.linkTitle}</div></Link>
                    </Grid.Column>
                }
            </Grid.Row>
            <Grid.Row columns="equal" className="dashboard-container">
                {
                    props.data.map((item, index) => {
                        return <Grid.Column key={index} className="dashboard-company-card">
                            {
                                !props.filter && <Link href={`/details/${item[props.header.data.id]}`}>
                                    <Segment key={index} inverted className="dashboard-asset-container">
                                        <div className="dashboard dashboard-company-icon">
                                            <Image width="30px" height="30px" src={`/company/${imageProvider(item.name[0].toLowerCase())}.png`} />
                                        </div>
                                        <div className="dashboard dashboard-company-name">
                                            {item.name}
                                        </div>
                                        <div className="dashboard dashboard-description">
                                            <span>
                                                <Popup content={props.header.data.sortBy.capitalize()}
                                                    trigger={<span>{item[props.header.data.sortBy]}</span>}
                                                    position="bottom center"
                                                    size="tiny"
                                                >
                                                </Popup>
                                            </span>
                                            <span>{props.header.data.sign}</span>{" "}
                                            <span className="dashboard-secondary-data">
                                                {props.header.data.secondaryData}
                                            </span>
                                        </div>
                                    </Segment>
                                </Link>
                            }
                            {
                                props.filter &&
                                <div className="dashboard-filter-container">
                                    <Link href={item.link}>
                                        <div className="dashboard-filter-icon">
                                            {item.icon}
                                            <div className="dashboard-filter-description">
                                                {item.message}
                                            </div>
                                        </div>
                                    </Link>
                                </div>
                            }
                        </Grid.Column>
                    })
                }
            </Grid.Row>
        </Grid>
    </div>
}
export default TopAssetContainer;