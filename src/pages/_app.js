import style from "src/styles/globals.scss";

function MyApp({ Component, pageProps }) {
  return (
    <>
      <style jsx>{`${style}`}</style>
      <Component {...pageProps} />
    </>
  );
}

export default MyApp;
