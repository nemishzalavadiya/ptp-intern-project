import React, { useState, useEffect } from "react";
import AuthContext from "src/components/contexts/AuthContext";
import sessionService from "src/services/sessionService";
import { useRouter } from "next/router";

const AuthProvider = ({ children }) => {
  let toggle = true;
  const [user, setUser] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const router = useRouter();

  async function loadUser() {
    if (!user && router.pathname !== "/login") {
      let userInfo = await sessionService.user();
      if (userInfo) {
        setUser(userInfo);
      } else {
        toggle = false;
        router.push({ pathname: "/login", query: { path: router.asPath } });
      }
    } else if (router.pathname === "/login") {
      let userInfo = await sessionService.user();
      if (userInfo) {
        toggle = false;
        if (router.query.path === undefined) {
          router.replace("/");
        } else {
          router.replace(router.query.path.toString());
        }
      }
    }
    if (toggle) {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadUser();
  });

  const login = async (userDetail) => {
    let userInfo = await sessionService.login(userDetail);
    setUser(userInfo);
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