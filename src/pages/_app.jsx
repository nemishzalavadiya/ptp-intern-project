import style from "src/styles/globals.scss";
import "semantic-ui-css/semantic.min.css";
import "react-toastify/dist/ReactToastify.css";
import AuthProvider from "src/components/Authentication/AuthProvider";
import ProtectRoute from "src/components/Authentication/ProtectRoute";
import Router from 'next/router';
import React,{useState} from 'react';
import { Loader } from "semantic-ui-react";
function MyApp({ Component, pageProps }) {
  const [isLoading,setIsLoading]=useState(false);  
  Router.onRouteChangeStart = (url) => {
    setIsLoading(true);
  };
  Router.onRouteChangeComplete = (url) => {
    setIsLoading(false);
};
  return (
    <div className="component-background">
      {
        isLoading?<div className="loader-div"> 
        <Loader inverted active>Loading...</Loader></div>:<AuthProvider>
        <ProtectRoute>
          <style jsx>{`
            ${style}
          `}</style>
          <Component {...pageProps} />
        </ProtectRoute>
      </AuthProvider>
    }
    </div>
  );
}
export default MyApp;