import Head from "next/head";
import Layout from "src/components/Layout";
import Dashboard from "src/components/dashboard/Dashboard";

export default function Home() {
  return (
    <Layout name="DASHBOARD">
      <Head>
        <title>Invest in Stocks & MutualFunds</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>
      <Dashboard />
    </Layout>
  );
}
