import style from "src/styles/globals.scss";
import "semantic-ui-css/semantic.min.css";

function MyApp({ Component, pageProps }) {
  return (
    <>
      <style jsx>{`${style}`}</style>
      <Component {...pageProps} />
    </>
  );
}

export default MyApp;
