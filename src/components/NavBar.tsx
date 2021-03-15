import Link from "next/link";
import { Popup } from "semantic-ui-react";
import { Menu } from "semantic-ui-react";
import { useState } from "react";
const sidebar = [
  {
    item: "STOCK",
    imgLink: "/icons8-stocks-481.png",
    imgAlter: "Stocks",
    content: "Stocks",
    link: "/stocks",
  },
  {
    item: "MUTUAL_FUND",
    imgLink: "/icons8-coin-in-hand-48.png",
    imgAlter: "Mutual Funds",
    content: "Mutual Funds",
    link: "/mutualfunds",
  },
  {
    item: "WATCHLIST",
    imgLink: "/icons8-eye-unchecked-48.png",
    imgAlter: "Watchlist",
    content: "Watchlist",
    link: "/watchlist",
  },
  {
    item: "POSITION",
    imgLink: "/round_add_shopping_cart_white_48dp.png",
    imgAlter: "Positions",
    content: "Positions",
    link: "/position",
  },
  {
    item: "ORDERS",
    imgLink: "/icons8-purchase-order-48.png",
    imgAlter: "Orders",
    content: "Orders",
    link: "/Order",
  },
];

const NavBar = (props) => {
  const [activeItem, setActiveItem] = useState(props.name);
  if (activeItem !== props.name) {
    setActiveItem(props.name);
  }
  return (
    <Menu inverted secondary pointing className="list-container" >
      {sidebar.map((item, index) => {
        return (
          <Menu.Item
            key={index}
            active={activeItem === item.item}
            className="list-item"
          >
            <Link href={item.link}>
              {item.content}
            </Link>
          </Menu.Item>
        );
      })}
    </Menu>
  );
};

export default NavBar;
