import useAuth from "src/components/contexts/useAuth";
import { Loader } from "semantic-ui-react"
import { useRouter } from "next/router"

const ProtectRoute = ({ children }) => {
  const router = useRouter();
  const { isAuthenticated, isLoading } = useAuth();
  if (!isAuthenticated && router.pathname === "/register"){
    return children;
  }
  if (
    isLoading ||
    (!isAuthenticated && router.pathname !== "/login")
  ) {
    return (
      <div className="site-background">
        <Loader inverted active>Loading</Loader>
      </div>
    );
  }
  
  return children;
};
export default ProtectRoute;