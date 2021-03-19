import "semantic-ui-css/semantic.min.css";
import KYC from "src/components/KYC/KYCForm";
import Layout from "src/components/Layout";
import Head from "next/head";

const KycPage = (props) => {
	return (
        <Layout name="KYC">
      <Head>
        <title>Pirimid Trading Platform</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>
      <KYC/>
    </Layout>
	);
};


export default KycPage;
