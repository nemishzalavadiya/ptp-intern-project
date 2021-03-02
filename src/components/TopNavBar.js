import {useEffect, useState} from "react";
import Link from "next/link";
import { Button } from "semantic-ui-react";

const TopNavBar = () => {
	const [userName,setUserName] = useState("");		
	useEffect(() => {
		var user = JSON.parse(localStorage.getItem("user"));
		setUserName(user.email);
	  }, []);
	return (
		<div className="headerTopNavBar">
			<Link href="/">
				<img className="logo" src="/LOGO.png" alt="PTP LOGO" />
			</Link>		
			<Button className="green">{userName}</Button>
		</div>
	);
};

export default TopNavBar;
