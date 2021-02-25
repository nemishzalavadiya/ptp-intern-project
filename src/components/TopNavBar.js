import { Button } from "semantic-ui-react";
import Link from "next/link";
import styles from "src/styles/TopNavBar.module.scss";
const TopNavBar = () => {
	return (
		<div className={styles.header}>
			<Link href="/">
				<img className={styles.logo} src="/LOGO.png" alt="PTP LOGO" />
			</Link>
			<Button className="green">LOGIN</Button>
		</div>
	);
};

export default TopNavBar;
