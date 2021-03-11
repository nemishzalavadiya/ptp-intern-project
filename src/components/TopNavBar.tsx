import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { Image, Popup, Grid, Icon } from "semantic-ui-react";
import Link from "next/link";
import useAuth from "src/components/contexts/useAuth";
import { getUser } from "src/services/userUpdate";
const TopNavBar = () => {
  const [userName, setUserName] = useState("");
  const [dpUrl, setDpUrl] = useState("");
  let { logout } = useAuth();
  const router = useRouter();
  useEffect(async () => {
    const user = await getUser();
    setUserName(user.firstName);
    setDpUrl(user.dpURL);
  }, []);
  const userLogout = async () => {
    await logout();
    router.push({ pathname: "/login", query: { path: router.asPath } });
  };
  return (
    <div className="headerTopNavBar">
      <Link href="/">
        <img className="logo" src="/LOGO.png" alt="PTP LOGO" />
      </Link>

      <Popup
        position="bottom right"
        trigger={
          <div>
            {dpUrl ? (
              <Image src="/user.jpg" className="usericon" circular />
            ) : (
              <Icon name="user" size="big"></Icon>
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
              <div className="emptyprofilebox">
                <Icon
                  name="user"
                  className="popusericon"
                  size={"big"}
                  color="black"
                  circular
                ></Icon>
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
  );
};

export default TopNavBar;
