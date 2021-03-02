import React, { createContext, useState, useContext, useEffect } from "react";
import toastBody from "src/components/toast";
const AuthContext = createContext({});
import Router from "next/router";
import { Loader } from "semantic-ui-react";
export const AuthProvider = ({ children }) => {
  let toggle = true;
  const [user, setUser] = useState(null);
  const [isLoading, setLoading] = useState(true);

  useEffect(() => {
    async function loadUserTokenFromCookies() {
      if (!user && Router.pathname !== "/login") {
        let response = await fetch("/api/user");
        let responseData = await response.text();
        if (response.ok) {
          setUser(responseData);
        } else {
          toggle = false;
          Router.push({ pathname: "/login", query: { path: Router.pathname } });
        }
      }
      if (Router.pathname === "/login") {
        let response = await fetch("/api/user");
        if (response.ok) {
          toggle = false;
          Router.replace("/");
        }
      }
      if (toggle) {
        setLoading(false);
      }
    }

    loadUserTokenFromCookies();
  });

  const login = async (user) => {
    let options = {
      method: "POST",
      body: JSON.stringify(user),
      headers: {
        "Content-Type": "application/json",
      },
    };
    const response = await fetch("api/login", options);
    if (response.ok) {
      toastBody("Logged in successfully");
      if (Router.query.path === undefined) {
        Router.replace("/");
      } else {
        Router.replace(Router.query.path);
      }
    } else {
      toastBody("Username or Password is incorrect");
    }
  };

  const logout = async () => {
    let response = await fetch("/api/logout", { method: "POST" });
    if (response.ok) {
      toastBody("Logged out successfully");
      setUser(null);
    } else {
      toastBody("Something went wrong, try again later");
      setUser(null);
    }
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated: !!user, user, login, logout, isLoading }}
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
    return <Loader active>Loading</Loader>;
  }
  return children;
};
