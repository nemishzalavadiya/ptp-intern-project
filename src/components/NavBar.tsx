import Link from "next/link";
import { Popup } from "semantic-ui-react";
import { Menu } from "semantic-ui-react";
import { useState } from "react";
const sidebar = [
	{
		item: "STOCK",
		imgLink: "/icons8-stocks-481.png",
		imgAlter: "Stocks",
		content: "STOCKS",
		link: "/stocks",
	},
	{
		item: "MUTUAL_FUND",
		imgLink: "/icons8-coin-in-hand-48.png",
		imgAlter: "Mutual Funds",
		content: "MUTUAL FUNDS",
		link: "/mutualfunds",
	},
	{
		item: "WATCHLIST",
		imgLink: "/icons8-eye-unchecked-48.png",
		imgAlter: "Watchlist",
		content: "WATCHLIST",
		link: "/watchlist",
	},
	{
		item: "POSITION",
		imgLink: "/round_add_shopping_cart_white_48dp.png",
		imgAlter: "Positions",
		content: "POSITIONS",
		link: "/",
	},
];

const NavBar = (props) => {
	const [activeItem, setActiveItem] = useState(props.name);
	if (activeItem !== props.name) {
		setActiveItem(props.name);
	}
	return (
		<div className="sideBar">
			<Menu inverted vertical borderless className="list-container">
				{sidebar.map((item, index) => {
					return (
						<Menu.Item key={index} active={activeItem === item.item} className="list-item">
							<Link href={item.link}>
								<div>
									<Popup
										size="mini"
										trigger={<img src={item.imgLink} alt={item.imgAlter} />}
										position="right center"
										content={item.content}
										inverted
									/>
								</div>
							</Link>
						</Menu.Item>
					);
				})}
			</Menu>
		</div>
	);
};

export default NavBar;
