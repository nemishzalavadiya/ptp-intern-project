import styles from "../../styles/Assetcards.module.css";
import { Card, Loader } from "semantic-ui-react";
import { getAllAssets } from "../../services/assets";
import Link from "next/link";
export default function Assetcards() {
  console.log("before...");
  const [isComplete, response] = getAllAssets();
  console.log("after, ", response);
  return isComplete ? (
    <div className={styles.main}>
      <Card.Group centered itemsPerRow={1} stackable={true} doubling={true}>
        {response.content.map((card) => (
          <Link as={`/details/${card.id}`} href="/details/[id]" key={card.id}>
            <Card
              key={card.id}
              style={{
                backgroundColor: "#272727",
                padding: "20px",
                borderRadius: "30px",
              }}
            >
              <Card.Content>
                <Card.Header style={{ color: "white" }}>
                  {card.name}
                </Card.Header>
              </Card.Content>
              <Card.Content extra style={{ color: "white" }}>
                {card.about} {card.assetClass}
              </Card.Content>
            </Card>
          </Link>
        ))}
      </Card.Group>
    </div>
  ) : (
    <Loader active={!isComplete}>Loading</Loader>
  );
}
