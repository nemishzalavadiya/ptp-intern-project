import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import NavBar from "src/components/NavBar";
import { Image, Popup, Grid, Icon, Divider } from "semantic-ui-react";
import Link from "next/link";
import useAuth from "src/components/contexts/useAuth";
import { getUser } from "src/services/userUpdate";
const TopNavBar = (props) => {
  const [userName, setUserName] = useState("");
  const [dpUrl, setDpUrl] = useState("");
  let { logout } = useAuth();
  const router = useRouter();

  useEffect(async () => {
    const user = await getUser();
    setUserName(user.firstName);
    setDpUrl(null);
  }, []);
  const userLogout = async () => {
    await logout();
    router.push({ pathname: "/login", query: { path: router.asPath } });
  };
  return (
    <div className="top-nav-container">
      <div className="headerTopNavBar">
        <div>
          <Link href="/">
            <img className="logo" src="/LOGO.png" alt="PTP LOGO" />
          </Link>
        </div>
        <div id="navigation" className="top-nav-bar-component">
          <NavBar name={props.name} />
        </div>
        <div>
          <Popup
            position="bottom right"
            trigger={
              <div>
                {dpUrl ? (
                  <Image src="/user.jpg" className="usericon" circular />
                ) : (
                  <Image src="/userwhite.jpg" className="usericon" circular />
                )}
              </div>
            }
            content={
              <>
                {dpUrl ? (
                  <div className="profilebox">
                    <Image
                      src="/user.jpg"
                      className="popupusericon"
                      circular
                      bordered
                    />
                  </div>
                ) : (
                  <div className="profilebox">
                    <Image
                      src="/userwhite.jpg"
                      className="popupusericon"
                      circular
                      bordered
                    />
                  </div>
                )}

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
      </div>
    </div>
  );
};

export default TopNavBar;
