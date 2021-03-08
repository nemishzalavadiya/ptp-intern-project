import { useEffect, useState } from "react";
import { Image, Popup, Grid } from "semantic-ui-react";
import Link from "next/link";
import Router from "next/router";

const TopNavBar = () => {
	const [userEmail, setUserEmail] = useState("");
	useEffect(() => {
		var user = JSON.parse(localStorage.getItem("user"));
		setUserEmail(user.email);
	}, []);

	const logout = () => {
		localStorage.removeItem("user");
		Router.push("/login");
	};

	return (
		<div className="headerTopNavBar">
			<Link href="/">
				<img className="logo" src="/LOGO.png" alt="PTP LOGO" />
			</Link>

			<Popup
				trigger={<Image src="/user.jpg" className="usericon" circular />}
				content={
					<>
						<div className="profilebox">
							<Image src="/user.jpg" className="popupusericon image-border" circular bordered />
						</div>

						<Grid textAlign="center">
							<Grid.Row>user-name</Grid.Row>
							<Grid.Row>{userEmail}</Grid.Row>

							<Grid.Row
								className="cardbutton"
								onClick={() => {
									Router.push("/profile");
								}}
							>
								Profile
							</Grid.Row>
							<Grid.Row className="cardbutton" onClick={logout}>
								Logout
							</Grid.Row>
						</Grid>
					</>
				}
				on="click"
				hideOnScroll
			/>
		</div>
	);
};

export default TopNavBar;
