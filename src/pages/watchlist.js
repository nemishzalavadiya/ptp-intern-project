import Head from "next/head";
import Layout from "src/components/Layout";
import Watchlist from "src/components/watchlist/Watchlist";
export default function Home() {
  return (
    <Layout>
      <Head>
        <title>My Watchlist</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>
      <div className="watchlist">
        <Watchlist />
      </div>
    </Layout>
  );
}
