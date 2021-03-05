import useAuth from "src/components/contexts/useAuth";
import { Loader } from "semantic-ui-react"

const ProtectRoute = ({ children }) => {
  const { isAuthenticated, isLoading } = useAuth();
  if (
    isLoading ||
    (!isAuthenticated && window.location.pathname !== "/login")
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