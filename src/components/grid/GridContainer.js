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
    <div className="ui grid" style={{ margin: "2% 2%",justifyContent:'center'}}>
      <div style={{ marginBottom:'30px'}}>
        <PaginationContainer pagination={props.pagination} />
      </div>
      <div
        className="ui equal width grid"
        style={{  minWidth: "1000px" }}
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
                    <div style={{ fontSize: "0.7rem", minWidth:"90px" }}>
                      {props.content[innerIndex].header}
                    </div>
                    <div style={{fontSize: "0.9rem", minWidth:"90px"}}>
                    {props.content[innerIndex].icon ? props.content[innerIndex].icon : null}
                    {item}
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
      </div>
    </div>
  );
}
