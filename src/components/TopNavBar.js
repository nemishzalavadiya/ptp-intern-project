import {useEffect, useState} from "react";
import { Button, Grid, GridRow, Image } from "semantic-ui-react";
import Link from "next/link";
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
			<div className="userlogo">
			
		
			<Image src="/logo.png"  circular width='70px' height='70px' />
			{userName}
			</div>
				
		</div>
	);
};

export default TopNavBar;
