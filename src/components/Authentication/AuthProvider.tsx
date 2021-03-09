import React, { useState, useEffect } from "react";
import AuthContext from "src/components/contexts/AuthContext";
import sessionService from "src/services/sessionService";
import { useRouter } from "next/router";

const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const router = useRouter();

  async function loadUser() {
    if (
      !user &&
      router.pathname !== "/login" &&
      router.pathname !== "/register"
    ) {
      let userInfo = await sessionService.user();
      if (userInfo) {
        setUser(userInfo);
      } else {
        window.location.href = "/login?path=" + router.asPath
        //router.push({ pathname: "/login", query: { path: router.asPath } });
      }
    } else if (
      router.pathname === "/login" ||
      router.pathname === "/register"
    ) {
      let userInfo = await sessionService.user();
      if (userInfo) {
        setUser(userInfo);
        if (router.query.path === undefined) {
          window.location.href = "/"
          //router.replace("/");
        } else {
          window.location.href = router.query.path.toString()
          //router.replace(router.query.path.toString());
        }
      }
    }
    setLoading(false);
  }

  useEffect(() => {
    loadUser();
  }, []);

  const login = async (userDetail) => {
    let userInfo = await sessionService.login(userDetail);
    if (userInfo) {
      setUser(userInfo);
    }
    return userInfo;
  };
  const logout = async () => {
    await sessionService.logout();
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated: !!user, user, login, logout, isLoading }}
    >
      {children}
    </AuthContext.Provider>
  );
};
export default AuthProvider;