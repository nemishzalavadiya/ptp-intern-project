import style from "src/styles/globals.scss";
import "semantic-ui-css/semantic.min.css";
import AuthProvider from "src/components/Authentication/AuthProvider";
import ProtectRoute from "src/components/Authentication/ProtectRoute";

function MyApp({ Component, pageProps }) {
  return (
    <div className="component-background">
      <AuthProvider>
        <ProtectRoute>
          <style jsx>{`
            ${style}
          `}</style>
          <Component {...pageProps} />
        </ProtectRoute>
      </AuthProvider>
    </div>
  );
}

export default MyApp;
