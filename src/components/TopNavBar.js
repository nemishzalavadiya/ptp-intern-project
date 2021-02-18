import MainSearch from "./MainSearch";
import { Button } from "semantic-ui-react";
import Link from "next/link";
import styles from "../styles/TopNavBar.module.css";
const TopNavBar = () => {
	return (
		<div className={styles.header}>
			<Link href="/">
				<img className={styles.logo} src="/LOGO.png" alt="PTP LOGO" />
			</Link>
			<MainSearch size={5} />
			<Button className="green">LOGIN</Button>
		</div>
	);
};

export default TopNavBar;
