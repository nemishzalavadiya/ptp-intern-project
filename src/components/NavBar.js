import styles from "../styles/NavBar.module.css";
import Link from "next/link";
import { Popup } from "semantic-ui-react";
const NavBar = () => {
	return (
		<div className={styles.sideBar}>
			<ul>
				<li>
					<Link href="/">
						<div>
							<Popup
								trigger={<img src="/icons8-stocks-growth-48.png" alt="Stocks" />}
								position="right center"
								content="STOCKS"
								inverted
							/>
						</div>
					</Link>
				</li>
				<li>
					<Link href="/test">
						<div>
							<Popup
								trigger={<img src="/icons8-coin-in-hand-48.png" alt="Mutual Funds" />}
								position="right center"
								content="MUTUAL FUNDS"
								inverted
							/>
						</div>
					</Link>
				</li>
				<li>
					<Link href="/test">
						<div>
							<Popup
								trigger={<img src="/round_add_shopping_cart_white_48dp.png" alt="Watchlist" />}
								position="right center"
								content="WATCHLIST"
								inverted
							/>
						</div>
					</Link>
				</li>
				<li>
					<Link href="/test">
						<div>
							<Popup
								trigger={<img src="/round_request_page_white_48dp.png" alt="Positions" />}
								position="right center"
								content="POSITIONS"
								inverted
							/>
						</div>
					</Link>
				</li>
			</ul>
		</div>
	);
};

export default NavBar;
