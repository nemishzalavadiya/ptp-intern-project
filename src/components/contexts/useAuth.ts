import { useContext } from "react";
import AuthContext from "src/components/contexts/AuthContext"

const useAuth = () => useContext(AuthContext);

export default useAuth;


