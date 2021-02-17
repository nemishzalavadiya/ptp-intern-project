/*
  Component: GridContainer
  props:  content*: [ {header*: string,icon:<i></i>} ],
          data* : data
          pagination: Object {
            activePage*:number,totalPages*:number,
            handlePaginationChange(pageNumber): method
          }
  TODO:
    1. No data present
*/
import PaginationContainer from "./PaginationContainer";
export default function GridContainer(props) {
  return (
    <div className="ui grid" style={{ margin: "40px 60px 20px 60px",justifyContent:"center"}}>
      <div
        className="ui equal width grid"
        style={{ margin: "40px 60px 20px 60px", minWidth: "1000px" }}
      >

        {props.data.map((row, outerIndex) => {
          return (
            <div
              key={outerIndex}
              className="row ui right floated segment"
              style={{
                minheight: "100vh",
                padding: "5vh 2vh 5vh 2vh",
                backgroundColor: "rgb(33, 33, 33)",
                color: "#fff",
                cursor: "pointer",
              }}
            >
              {row.map((item, innerIndex) =>
                innerIndex != 0 ? (
                  <div className="column" key={`${outerIndex} ${innerIndex}`}>
                    <div style={{ fontSize: "x-small" }}>
                      {props.content[innerIndex].header}
                    </div>
                    {props.content[innerIndex].icon ? props.content[innerIndex].icon : null}
                    {item}
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
      </div>
      <div>
        <PaginationContainer pagination={props.pagination} />
      </div>
    </div>
  );
}
