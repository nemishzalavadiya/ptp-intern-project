import styles from "../styles/NavBar.module.css";
import Link from "next/link";
const NavBar = () => {
	return (
		<div className={styles.sideBar}>
			<ul>
				<li>
					<Link href="/">
						<div>
							<img src="/icons8-stocks-growth-48.png" alt="Stocks" />
							<h6>STOCKS</h6>
						</div>
					</Link>
				</li>
				<li>
					<Link href="/">
						<div>
							<img src="/icons8-coin-in-hand-48.png" alt="Mutual Funds" />
							<h6>MUTUAL FUNDS</h6>
						</div>
					</Link>
				</li>
				<li>
					<Link href="/">
						<div>
							<img src="/round_add_shopping_cart_white_48dp.png" alt="Watchlist" />
							<h6>WATCHLIST</h6>
						</div>
					</Link>
				</li>
				<li>
					<Link href="/">
						<div>
							<img src="/round_request_page_white_48dp.png" alt="Positions" />
							<h6>POSITIONS</h6>
						</div>
					</Link>
				</li>
			</ul>
		</div>
	);
};

export default NavBar;
