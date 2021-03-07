import style from "src/styles/globals.scss";
import "semantic-ui-css/semantic.min.css";
import "react-toastify/dist/ReactToastify.css";

function MyApp({ Component, pageProps }) {
  return (
    <>
      <style jsx>{`${style}`}</style>
      <Component {...pageProps} />
    </>
  );
}

export default MyApp;
