import Head from "next/head";
import Layout from "src/components/Layout";
import Position from 'src/components/position/position.tsx';


export default function Home() {
  return (
    <Layout name="POSITION">
      <Head>
        <title>Pirimid Trading Platform</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>
      <Position />
    </Layout>
  );
}
