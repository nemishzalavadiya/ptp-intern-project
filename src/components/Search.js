import debounce from "src/services/debounce";
import { Input } from "semantic-ui-react";

export default function Search(props) {
  let debounceResult = debounce(props.handleSearchChange, 300);
  return (
      <Input
        className="input-search"
        size="large"
        inverted
        onChange={(e, data) => debounceResult(e, data)}
        placeholder={props.placeholder}
        icon="search"
      />
  );
}
