import { Menu } from "semantic-ui-react";
export default function Tab(props){
    return (
        <Menu pointing inverted secondary className="tab-menu">
        {props.content.map((item, index) => {
          return (
            <Menu.Item
              key={index}
              name={item.name}
              active={props.activeItem === index}
              onClick={() => props.handleItemClick(index)}
            />
          );
        })}
        </Menu>
    );
}