import Head from "next/head";
import Layout from "src/components/Layout";
import SIP from "src/components/SipStatus/SIP";
export default function Home() {
  return (
    <Layout name="POSITION">
      <Head>
        <title>SIP-Status</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>

      <SIP />
    </Layout>
  );
}
