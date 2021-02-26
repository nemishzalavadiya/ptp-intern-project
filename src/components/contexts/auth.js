import React, { createContext, useState, useContext, useEffect } from "react";
import Cookies from "js-cookie";
import { userLogin } from "src/services/authentication";
const AuthContext = createContext({});
import Router from "next/router";
import { Loader } from "semantic-ui-react";


export const AuthProvider = ({ children }) => {

  const [token, setToken] = useState(null);
  const [isLoading,setLoading] = useState(true);

  useEffect(() => {
    function loadUserTokenFromCookies() {
      const tokenCookie = Cookies.get("token");
     
      if (tokenCookie) {
        console.log("Got a token in the cookies, let's see if it is valid");
        authorization = `Bearer ${tokenCookie}`;
        setToken(authorization);
      } else {
        if (Router.pathname !== "/login") Router.push("/login");
      }
      setLoading(false)
    }
    loadUserTokenFromCookies();
  }, []);

  const login = (email, password) => {
    const [isCompleted, data] = userLogin(email, password);
    if (isCompleted && data.token) {
      console.log("Got token");
      Cookies.set("token", token, { expires: 60 });
      authorization = `Bearer ${token.token}`;
      setToken(authorization);
    }
  };

  const logout = () => {
    Cookies.remove("token");
    setToken(null);
    window.location.pathname = "/login";
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated: !!token, token, login, logout ,isLoading}}
    >{children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

export const ProtectRoute = ({ children }) => {
  const { isAuthenticated, isLoading } = useAuth();
  if (
    isLoading || (!isAuthenticated &&
    window.location.pathname !== "/login")
  ) {
    return <Loader active>Loading</Loader>;
  }
  return children;
};
