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
    <Grid
      columns="equal"
      style={{ minWidth: "800px", margin: "3px", justifyContent: "center" }}
    >
      {props.data.map((row, outerIndex) => {
        return (
          <div
            key={outerIndex}
            className="row ui segment"
            style={{
              padding: "5vh 2vh",
              backgroundColor: "rgb(33, 33, 33)",
              color: "#fff",
              cursor: "pointer",
            }}
          >
            {row.map((item, innerIndex) =>
              innerIndex != 0 ? (
                <div className="column" key={`${outerIndex} ${innerIndex}`}>
                  <div style={{ fontSize: "0.7rem", minWidth: "90px" }}>
                    {props.content[innerIndex].header}
                  </div>
                  <div style={{ fontSize: "0.9rem", minWidth: "90px" }}>
                    {props.content[innerIndex].icon
                      ? props.content[innerIndex].icon
                      : null}
                    {isFinite(item) ? item.toLocaleString() : item}
                  </div>
                </div>
              ) : (
                <div
                  className="six wide column"
                  key={`${outerIndex} ${innerIndex}`}
                >
                  {item}
                </div>
              )
            )}
          </div>
        );
      })}
      <div style={{ marginTop: "30px" }}>
        <PaginationContainer
          pagination={props.pagination}
          tabId={props.tabId}
        />
      </div>
    </Grid>
  ) : (
    <div
      style={{
        height: "300px",
        position: "relative",
      }}
    >
      <div
        style={{
          margin: "0",
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
        }}
      >
        <Icon name="box" />
        <div style={{ transform: "translateX(-25%)" }}>No Data</div>
      </div>
    </div>
  );
}
