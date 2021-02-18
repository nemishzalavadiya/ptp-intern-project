import styles from "../../../styles/Loading.module.css";
export default function Loading() {
  return (
    <div className={styles.loading}>
      <img src="/loader.svg" alt="Loading..."></img>
      <div className={styles['dots-span-container']}>
        Loading<span className={styles.dot1}>.</span>
        <span className={styles.dot2}>.</span>
        <span className={styles.dot3}>.</span>
      </div>
    </div>
  );
}
