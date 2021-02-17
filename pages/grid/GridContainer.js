/*
  Component: GridContainer
  props: title: String, header*: List, data*: List of List, icon: list of <i></i>

  TODO:
    1. No data present
*/
export default function GridContainer(props) {
  return (
    <>
      <div
        className="ui equal width grid"
        style={{ margin: "10px 20px 10px 20px", minWidth: "1000px" }}
      >
        {props.title ? (
          <div className="ui horizontal divider" style={{ color: "#fff" }}>
            {props.title}
          </div>
        ) : null}

        {props.data.map((row,outerIndex) => {
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
                      {props.header[innerIndex]}
                    </div>
                   {props.icon ? props.icon[innerIndex]:null}{item}
                  </div>
                ) : (
                  <div className="six wide column" key={`${outerIndex} ${innerIndex}`}>{item}</div>
                )
              )}
            </div>
          );
        })}
      </div>
    </>
  );
}
