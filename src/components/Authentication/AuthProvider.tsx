import React, { useState, useEffect } from "react";
import AuthContext from "src/components/contexts/AuthContext";
import sessionService from "src/services/sessionService";
import Router from "next/router";
import Cookies from "js-cookie";

const AuthProvider = ({ children }) => {
  let toggle = true;
  const [user, setUser] = useState(null);
  const [isLoading, setLoading] = useState(true);

  function loadUserTokenFromCookies() {
    if (!user && Router.pathname !== "/login") {
      let userId = Cookies.get("userId");
      if (userId) {
        setUser(userId);
      } else {
        toggle = false;
        Router.push({ pathname: "/login", query: { path: Router.asPath } });
      }
    }
    else if (Router.pathname === "/login") {
      let userId = Cookies.get("userId");
      if (userId) {
        toggle = false;
        if (Router.query.path === undefined) {
          Router.replace("/");
        }
        else {
          Router.replace(Router.query.path.toString());
        }
      }
    }
    if (toggle) {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadUserTokenFromCookies();
  });

  const login = async (userDetail) => {
    let userId = await sessionService.login(userDetail);
    setUser(userId);
  }
  const logout = async () => {
    await sessionService.logout();
    setUser(null);
  }

  return (
    <AuthContext.Provider
      value={{ isAuthenticated: !!user, user, login, logout, isLoading }}
    >
      {children}
    </AuthContext.Provider>
  );
};
export default AuthProvider;