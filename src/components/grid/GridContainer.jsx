/*
  Component: GridContainer
  props:  content*: [ {header*: string,icon:<i></i>} ],
          data* : data
          pagination: Object {
            activePage*:number,totalPages*:number,
            handlePaginationChange(pageNumber): method
          }
          tabId: string 
*/
import PaginationContainer from "src/components/grid/PaginationContainer";
import { Grid, Icon } from "semantic-ui-react";
export default function GridContainer(props) {
  return props.data.length !== 0 ? (
    <Grid columns="equal" className={props.dashboard?"dashboard-grid-container":"grid-container"}>
      {props.data.map((row, outerIndex) => {
        return (
          <Grid.Row key={outerIndex} className={props.dashboard?"ui segment dashboard-grid-row":"ui segment grid-row"}>
            {row.map((item, innerIndex) =>
              innerIndex != 0 ? (
                <Grid.Column key={`${outerIndex} ${innerIndex}`}>
                  {props.showHeaderGrid !== "disable"? (
                    <div className="grid-header-item">
                      {props.content[innerIndex].header}
                    </div>
                  ) : null}
                  <div
                    className={`grid-item ${props.content[innerIndex] && props.content[innerIndex].showColor &&
                      (item >= 0 ? "profit" : "loss")
                      }`}
                  >
                    {props.content[innerIndex]&&props.content[innerIndex].icon
                      ? props.content[innerIndex].icon
                      : null}
                    {isFinite(item) ? item.toLocaleString() : item}
                  </div>
                </Grid.Column>
              ) : (
                <Grid.Column width="6" key={`${outerIndex} ${innerIndex}`} className={props.dashboard && "dashboard-companyName"}>
                  {item}
                </Grid.Column>
              )
            )}
          </Grid.Row>
        );
      })}
      <div>
        { !props.dashboard && props.pagination.totalPages > 1 && (
          <PaginationContainer
            pagination={props.pagination}
            tabId={props.tabId}
          />
        )}
      </div>
    </Grid>
  ) : (
    <div className="outer-div">
      <div className="inner-div">
        <Icon name="box" />
        <div className="icon-content">No Data</div>
      </div>
    </div>
  );
}
