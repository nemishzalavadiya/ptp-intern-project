import debounce from "src/services/debounce";
import { Input } from "semantic-ui-react";

export default function Search(props) {
  return (
      <Input
        className="input-search"
        size="large"
        inverted
        onChange={(e, data) => debounce(props.handleSearchChange(e, data), 100)}
        placeholder={props.placeholder}
        icon="search"
      />
  );
}
