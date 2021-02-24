import NavBar from "src/components/NavBar";
import "semantic-ui-css/semantic.min.css";
import styles from "src/styles/Layout.module.scss";
import TopNavBar from "src/components/TopNavBar";

const Layout = (props) => {
	return (
		<div className={styles.main}>
			<div className={styles.bar}>
				<NavBar name={props.name} />
			</div>
			<div className={styles.right}>
				<TopNavBar />
				<div className={styles.innerChild}>
				{props.children}
				</div>
			</div>
		</div>
	);
};


export default Layout;
