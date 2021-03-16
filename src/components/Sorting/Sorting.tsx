import { Icon } from "semantic-ui-react";
import GridHeader from "src/components/grid/GridHeader";
export default function Sorting(props) {
  return (
    <GridHeader
      content={props.content}
      onclick={props.onclick}
      pattern={props.pattern}
      showHeaderGrid={props.showHeaderGrid}
      dashboard={props.dashboard}
    ></GridHeader>
  );
}
