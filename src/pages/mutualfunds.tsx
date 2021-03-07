import Head from "next/head";
import Layout from "src/components/Layout";
import Watchlist from "src/components/watchlist/Watchlist";
export default function Home() {
  return (
    <Layout name="MUTUAL_FUND">
      <Head>
        <title>Mutual fund</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>
    </Layout>
  );
}
