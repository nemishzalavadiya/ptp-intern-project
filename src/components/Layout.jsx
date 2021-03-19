import NavBar from "src/components/NavBar";
import "semantic-ui-css/semantic.min.css";
import TopNavBar from "src/components/TopNavBar";

const Layout = (props) => {
	return (
		<div className="mainLayout">
			<div className="rightLayout">
				<TopNavBar name={props.name} />
				<div className="innerLayout">
					{props.children}
				</div>
			</div>
		</div>
	);
};

export default Layout;
