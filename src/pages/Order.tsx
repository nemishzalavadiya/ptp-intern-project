import Head from "next/head";
import Layout from "src/components/Layout";
import Order from "src/components/Order/Order";
export default function Home() {
  return (
    <Layout name="ORDERS">
      <Head>
        <title>Orders</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>

      <Order />
    </Layout>
  );
}
