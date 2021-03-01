import React, { createContext, useState, useContext, useEffect } from "react";
import Cookies from "js-cookie";
import authenticateUser from "src/services/authenticate";
const AuthContext = createContext({});
import Router from "next/router";
import { Loader } from "semantic-ui-react";

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(null);
  const [isLoading, setLoading] = useState(true);

  useEffect(() => {
    function loadUserTokenFromCookies() {
      const tokenCookie = Cookies.get("token");
      if (tokenCookie) {
        let authorization = `Bearer ${tokenCookie}`;
        setToken(authorization);
      } else {
        if (Router.pathname !== "/login") Router.push("/login");
      }
      setLoading(false);
    }
    loadUserTokenFromCookies();
  }, []);

  const login = (email, password, validate) => {
    const [isCompleted, data, error] = authenticateUser(
      email,
      password,
      validate
    );
    if (isCompleted && data.token) {
      Cookies.set("token", data.token, { expires: 60 });
      let authorization = `Bearer ${data.token}`;
      setToken(authorization);
    }
    return [isCompleted, data, error];
  };

  const logout = () => {
    Cookies.remove("token");
    setToken(null);
    window.location.pathname = "/login";
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated: !!token, token, login, logout, isLoading }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

export const ProtectRoute = ({ children }) => {
  const { isAuthenticated, isLoading } = useAuth();
  if (
    isLoading ||
    (!isAuthenticated && window.location.pathname !== "/login")
  ) {
    return <Loader inverted active>Loading</Loader>;
  }
  return children;
};
