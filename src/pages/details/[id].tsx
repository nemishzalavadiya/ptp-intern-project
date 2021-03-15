import Head from "next/head";
import { useRouter } from "next/router";
import { getAssetById } from "src/services/assets";
import Stockdetail from "src/components/Stockdetail/Stockdetail";
import MutualFundDetail from "src/components/Mutualfund/MutualfundDetail";
import Layout from "src/components/Layout";
import { Loader } from "semantic-ui-react";
import { AssetClass } from "src/enums/assetClass";

export default function details() {
  const router = useRouter();
  const { id } = router.query;
  const [isComplete, response] = getAssetById(id);
  return isComplete ? (
    response.assetClass == AssetClass.STOCK ? (
      <Layout name="STOCK">
        <Head>
          <title>Stock</title>
          <link rel="icon" href="/favicon.svg" />
        </Head>
        <Stockdetail stockId={id} />
      </Layout>
    ) : (
      <Layout name="MUTUAL_FUND">
        <Head>
          <title>Mutual Fund</title>
          <link rel="icon" href="/favicon.svg" />
        </Head>
        <MutualFundDetail mfId={id} />
      </Layout>
    )
  ) : (
    <Layout>
      <Loader active>Loading</Loader>
    </Layout>
  );
}
