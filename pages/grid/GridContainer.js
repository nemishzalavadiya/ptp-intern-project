/*
  Component: GridContainer
  props: title, header, data
*/
export default function GridContainer(props) {
  function conditionalDisplay(item) {
    if (Number.isInteger(item)) {
      return <i className="rupee sign icon small"></i>;
    }
  }

  return (
    <>
      <div
        className="ui equal width grid"
        style={{ margin: "10px 20px 10px 20px", minWidth: "100vh" }}
      >
        <div className="ui horizontal divider" style={{ color: "#fff" }}>
          {props.title}
        </div>
        {props.data.map((row) => {
          return (
            <div
              className="row ui right floated segment"
              style={{
                minheight: "100vh",
                padding: "5vh 2vh 5vh 2vh",
                backgroundColor: "rgb(33, 33, 33)",
                color: "#fff",
                cursor: "pointer",
              }}
            >
              {row.map((item, index) =>
                index != 0 ? (
                  <div className="column">
                    <div style={{ fontSize: "x-small" }}>
                      {props.header[index]}
                    </div>
                    {item} {conditionalDisplay(item)}
                  </div>
                ) : (
                  <div className="eight wide column">{item}</div>
                )
              )}
            </div>
          );
        })}
      </div>
    </>
  );
}
