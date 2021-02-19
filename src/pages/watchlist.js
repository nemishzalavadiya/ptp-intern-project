import Head from "next/head";
import Layout from "src/components/Layout";
import Watchlist from "src/components/watchlist/Watchlist";
export default function Home() {
  return (
    <div style={{overflow:"scroll"}}>
    <Layout>
      <Head>
        <title>Pirimid Trading Platform</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>
      <div style={{margin:"2% 9% 2% 6%"}}>
        <Watchlist />
      </div>
    </Layout>
    </div>
  );
}
