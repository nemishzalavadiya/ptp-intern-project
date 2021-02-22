import { useRouter } from "next/router";
import { getAssetById } from "../../service/assets";
import Stockdetail from "../../components/Stockdetail/Stockdetail";
import MutualFundDetail from "../../components/Mutualfund/MutualfundDetail";
import Layout from "../../components/Layout";
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
  ) : response.error ? (
    <div>{response.error}</div>
  ) : (
    <Loader active={!isComplete}>Loading</Loader>
  );
}
