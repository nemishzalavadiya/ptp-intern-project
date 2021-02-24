import styles from "src/styles/NavBar.module.scss";
import Link from "next/link";
import { Popup } from "semantic-ui-react";
import { List } from "semantic-ui-react";
const NavBar = () => {
  return (
    <div className={styles.sideBar}>
      <List className="list-container">
          <List.Item className="list-item">
            <Link href="/">
              <div>
                <Popup
                  size="small"
                  trigger={<img src="/icons8-stocks-481.png" alt="Stocks" />}
                  position="right center"
                  content="STOCKS"
                  inverted
                />
              </div>
            </Link>
          </List.Item>

        <List.Item className="list-item">
          <Link href="/test">
            <div>
              <Popup
                size="small"
                trigger={
                  <img src="/icons8-coin-in-hand-48.png" alt="Mutual Funds" />
                }
                position="right center"
                content="MUTUAL FUNDS"
                inverted
              />
            </div>
          </Link>
        </List.Item>
        <List.Item className="list-item">
          <Link href="/watchlist">
            <div>
              <Popup
                size="small"
                trigger={
                  <img src="/icons8-eye-unchecked-48.png" alt="Watchlist" />
                }
                position="right center"
                content="WATCHLIST"
                inverted
              />
            </div>
          </Link>
        </List.Item>
        <List.Item className="list-item">
          <Link href="/test">
            <div>
              <Popup
                size="small"
                trigger={
                  <img
                    src="/round_add_shopping_cart_white_48dp.png"
                    alt="Positions"
                  />
                }
                position="right center"
                content="POSITIONS"
                inverted
              />
            </div>
          </Link>
        </List.Item>
      </List>
    </div>
  );
};

export default NavBar;
