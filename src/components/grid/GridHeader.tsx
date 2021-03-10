import { Grid, Icon } from "semantic-ui-react";
export default function GridHeader(props) {
  return (
    <Grid columns="equal" className="grid-container">
      <Grid.Row className="ui segment grid-row ">
        {props.content.map((item, index) => {
          return index == 0 ? (
            <Grid.Column width="6">
              <div
                className={
                  props.pattern[index] == 0 ? "dropdown" : "triangle up"
                }
                onClick={() => props.onclick(index, item.title)}
              >
                {item.title}
                <Icon
                  className={
                    props.pattern[index] == 0 ? "dropdown" : "triangle up"
                  }
                ></Icon>
              </div>
            </Grid.Column>
          ) : (
            <Grid.Column>
              <div
                className={`grid-header-item-sorting ${
                  props.pattern[index] == 0 ? "dropdown" : "triangle up"
                }`}
                onClick={() => props.onclick(index, item.title)}
              >
                {item.title}
                <Icon
                  className={
                    props.pattern[index] == 0 ? "dropdown" : "triangle up"
                  }
                ></Icon>
              </div>
            </Grid.Column>
          );
        })}
      </Grid.Row>
    </Grid>
  );
}
