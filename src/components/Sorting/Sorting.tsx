import { Icon } from "semantic-ui-react";
import GridHeader from "src/components/grid/GridHeader";
export default function Sorting(props) {
  return (
    <GridHeader content={props.content} onclick={props.onclick} pattern={props.pattern}></GridHeader>
    // <div className="sorting">
    //   {props.content.map((item, index) => {
    //     return (
    //       <div key={index} className="childSorting">
    //         <div
    //           onClick={() => props.onclick(index, item.title)}
    //           className={props.pattern[index] == 0 ? "dropdown" : "triangle up"}
    //         >
    //           {item.title}
    //           <Icon
    //             className={
    //               props.pattern[index] == 0 ? "dropdown" : "triangle up"
    //             }
    //           />
    //         </div>
    //       </div>
    //     );
    //   })}
    // </div>
  );
}
