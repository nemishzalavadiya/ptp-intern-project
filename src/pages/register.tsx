import Head from "next/head";
import Register from "src/components/Register/Register"

export default function HomePage() {
  return (
      <div  className="mainLayout">
      <Head>
        <title>Pirimid Trading Platform</title> 
        <link rel="icon" href="/favicon.svg" />
      </Head>
      <div className="registerBox">
        <Register/>
      </div>
    </div>
  )
}