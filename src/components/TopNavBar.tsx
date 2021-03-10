import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { Image, Popup, Grid } from "semantic-ui-react";
import Link from "next/link";
import useAuth from "src/components/contexts/useAuth";
import { userEdit, getUserById } from "src/services/userEdit";
const TopNavBar = () => {
  const [userName, setUserName] = useState("");
  let { logout } = useAuth();
  const router = useRouter();
  // useEffect(async()=>{
  //     const user = await getUserById();
  //     setUserName(user.userName)

  // })
  const userLogout=async()=>{
    await logout();
    router.push({ pathname: "/login", query: { path: router.asPath } });
  }
  return (
    <div className="headerTopNavBar">
      <Link href="/">
        <img className="logo" src="/LOGO.png" alt="PTP LOGO" />
      </Link>

      <Popup
        position='bottom right'
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
              <Grid.Row>{userName}</Grid.Row>
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
