import { Button } from "semantic-ui-react";
import Link from "next/link";
const TopNavBar = () => {
	return (
		<div className="headerTopNavBar">
			<Link href="/">
				<img className="logo" src="/LOGO.png" alt="PTP LOGO" />
			</Link>
			<Button className="green">LOGIN</Button>
		</div>
	);
};

export default TopNavBar;
