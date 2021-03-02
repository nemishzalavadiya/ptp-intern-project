import style from "src/styles/globals.scss";
import "semantic-ui-css/semantic.min.css";
import { ToastContainer } from "react-toastify";
import { AuthProvider, ProtectRoute } from "src/components/contexts/auth";
function MyApp({ Component, pageProps }) {
  return (
    <div className="component-background">
      <AuthProvider>
        <ProtectRoute>
          <style jsx>{`
            ${style}
          `}</style>
          <Component {...pageProps} />
          <ToastContainer></ToastContainer>
        </ProtectRoute>
      </AuthProvider>
    </div>
  );
}

export default MyApp;
