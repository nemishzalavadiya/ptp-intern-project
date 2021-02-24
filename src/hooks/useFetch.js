/*
  hook: useFetch
  argument: url
*/
import { useState, useEffect } from "react";
function useFetch(url) {
  const [content, setContent] = useState({
    data: [],
    error: false,
    isComplete: false,
  });
  async function fetchUrl(Url) {
    try {
      await fetch(Url)
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error("Something went wrong. check network connection");
          }
        })
        .then((responseJson) => {
          setContent({ data: responseJson, isComplete: true });
        })
        .catch((error) => {
          throw new Error(error);
        });
    } catch (error) {
      //handle errors
      setContent({ data: [], error: error, isComplete: false });
    }
  }
  useEffect(() => {
    fetchUrl(url);
    return ()=>{
      setContent({
        data: [],
        error: false,
        isComplete: false,
      });
    }
  }, [url]);
  return [content["isComplete"], content["data"]];
}
export { useFetch };