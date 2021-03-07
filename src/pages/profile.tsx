import Head from "next/head";
import Profile from "src/components/Profile/Profile"
import Layout from "src/components/Layout";
export default function HomePage() {
  return (

    <Layout>
      <Head>
        <title>Pirimid Trading Platform</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>
      <div>
        <Profile/>
      </div>
    </Layout>
      
  )
}
