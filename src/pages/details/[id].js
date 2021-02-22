import { useRouter } from "next/router";
import { getAssetById } from "src/services/assets";
import Stockdetail from "src/components/Stockdetail/Stockdetail";
import MutualFundDetail from "src/components/Mutualfund/MutualfundDetail";
import Layout from "src/components/Layout";
import { Loader } from "semantic-ui-react";
export default function details() {
  const router = useRouter();
  const { id } = router.query;
  const [isComplete, response] = getAssetById(id);
  return isComplete ? (
    response.assetClass == "STOCK" ? (
      <Layout>
        <Stockdetail stockId={router.query.id} />
      </Layout>
    ) : (
      <Layout>
        <MutualFundDetail mfId={router.query.id} />
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
