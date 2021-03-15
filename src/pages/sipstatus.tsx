import Head from "next/head";
import Layout from "src/components/Layout";
import SipStatus from "src/components/SipStatus/SipStatus";
export default function Home() {
  return (
    <Layout name="POSITION">
      <Head>
        <title>SIP-Status</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>

      <SipStatus />
    </Layout>
  );
}
