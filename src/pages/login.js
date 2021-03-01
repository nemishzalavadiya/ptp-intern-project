import Head from "next/head";
import Login from "src/components/Login/Login.js"
import styles from "src/styles/Layout.module.scss";

export default function HomePage() {
  return (
      <div  className={styles.mainLayout} style={{ background:'black'}}>
      <Head>
        <title>Pirimid Trading Platform</title> 
        <link rel="icon" href="/favicon.svg" />
      </Head>
      <div className="loginBox">
        <Login/>
      </div>
    </div>
  )
}