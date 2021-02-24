import { useRouter } from "next/router";
import { getAssetById } from "src/services/assets";
import Stockdetail from "src/components/Stockdetail/Stockdetail";
import MutualFundDetail from "src/components/Mutualfund/MutualfundDetail";
import Layout from "src/components/Layout";
import { Loader } from "semantic-ui-react";
import {AssetClass} from "src/components/AssetClass.ts";
export default function details() {
  const router = useRouter();
  const { id } = router.query;
  const [isComplete, response] = getAssetById(id);
  return isComplete ? (
    response.assetClass == AssetClass.STOCK ? (
      <Layout>
        <Stockdetail stockId={id} />
      </Layout>
    ) : (
      <Layout>
        <MutualFundDetail mfId={id} />
      </Layout>
    )
  ) : (
    <Loader active={!isComplete}>Loading</Loader>
  );
}
export async function getServerSideProps(context) {
  return {
    props: {},
  };
}
