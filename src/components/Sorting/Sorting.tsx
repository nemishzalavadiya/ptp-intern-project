import { Grid, Icon } from "semantic-ui-react";

export default function Sorting(props) {
  return (
    <Grid columns="equal" className="grid-container">
      <Grid.Row>
        {props.content.map((item, index) => {
          return (
            <Grid.Column key={index}>
              {item}
              <Icon
                className={
                  props.pattern[index] == 0 ? "dropdown" : "triangle up"
                }
                onClick={() => props.onclick(index, item)}
              />
            </Grid.Column>
          );
        })}
      </Grid.Row>
    </Grid>
  );
}
