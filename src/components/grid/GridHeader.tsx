import { Grid, Icon } from "semantic-ui-react";
export default function GridHeader(props) {
  return props.dashboard !== true ? (
    <Grid columns="equal" className="grid-container">
      <Grid.Row className="ui segment grid-row-sorting-header">
        {props.content.map((item, index) => {
          return index == 0 ? (
            <Grid.Column width="6">
              {item.sortable !== false ? (
                <div
                  className={
                    props.pattern[index] == 0 ? "dropdown" : "triangle up"
                  }
                  onClick={() => props.onclick(index, item.header)}
                >
                  {item.header}
                  <Icon
                    className={
                      props.pattern[index] == 0 ? "dropdown" : "triangle up"
                    }
                  ></Icon>
                </div>
              ) : (
                <div>{item.header}</div>
              )}
            </Grid.Column>
          ) : (
            <Grid.Column>
              {item.sortable !== false ? (
                <div
                  className={`grid-header-item-sorting ${
                    props.pattern[index] == 0 ? "dropdown" : "triangle up"
                  }`}
                  onClick={() => props.onclick(index, item.header)}
                >
                  {item.header}
                  {item.sortable === false ? null : (
                    <Icon
                      className={
                        props.pattern[index] == 0 ? "dropdown" : "triangle up"
                      }
                    ></Icon>
                  )}
                </div>
              ) : (
                <div className="grid-header-item-sorting">{item.header}</div>
              )}
            </Grid.Column>
          );
        })}
      </Grid.Row>
    </Grid>
  ) : null;
}
