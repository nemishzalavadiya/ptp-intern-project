import Head from "next/head";
import Login from "src/components/Login/Login"

export default function HomePage() {
  return (
      <div  className="mainLayout">
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