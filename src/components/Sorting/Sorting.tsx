import { Icon } from "semantic-ui-react";

export default function Sorting(props) {
  return (
    <div className="sorting">
      {props.content.map((item, index) => {
        return (
          <div key={index} className="childSorting">
            <div
              onClick={() => props.onclick(index, item)}
              className={props.pattern[index] == 0 ? "dropdown" : "triangle up"}
            >
              {item}
              <Icon
                className={
                  props.pattern[index] == 0 ? "dropdown" : "triangle up"
                }
              />
            </div>
          </div>
        );
      })}
    </div>
  );
}
