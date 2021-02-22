import { Jumbotron, Container } from "react-bootstrap";
import styles from "../../styles/Chart.module.css";
export default function Chart(props) {
  return (
    <div style={{ backgroundColor: "#e6e6e6", width: "60%" }}>
      <Jumbotron fluid className={styles.jumbo}>
        <div className="ui active inverted dimmer">
          <div className="ui medium text loader">Loading</div>
        </div>
      </Jumbotron>
    </div>
  );
}
