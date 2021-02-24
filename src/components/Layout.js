import NavBar from "src/components/NavBar";
import "semantic-ui-css/semantic.min.css";
import styles from "src/styles/Layout.module.css";
import TopNavBar from "src/components/TopNavBar";

const Layout = ({ children }) => {
	return (
		<div className={styles.main}>
			<div className={styles.bar}>
				<NavBar />
			</div>
			<div className={styles.right}>
				<TopNavBar />
				<div className={styles.innerChild}>
				{children}
				</div>
			</div>
		</div>
	);
};

export default Layout;
