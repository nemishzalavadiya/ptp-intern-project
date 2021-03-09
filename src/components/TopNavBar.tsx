import { useState } from "react";
import { useRouter } from "next/router";
import { Image, Popup, Grid } from "semantic-ui-react";
import Link from "next/link";
import useAuth from "src/components/contexts/useAuth";
const TopNavBar = () => {
  const [userEmail, setUserEmail] = useState("");
  let { logout } = useAuth();
  const router = useRouter();
  const userLogout=async()=>{
    await logout();
    router.push("/login");
  }
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
              <Image
                src="/user.jpg"
                className="popupusericon image-border"
                circular
                bordered
              />
            </div>

            <Grid textAlign="center">
              <Grid.Row>user-name</Grid.Row>
              <Grid.Row>{userEmail}</Grid.Row>

              <Grid.Row
                className="cardbutton"
                onClick={() => {
                  router.push("/profile");
                }}
              >
                Profile
              </Grid.Row>
              <Grid.Row className="cardbutton" onClick={userLogout}>
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
