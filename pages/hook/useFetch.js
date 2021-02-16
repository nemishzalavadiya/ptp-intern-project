import { useState, useEffect } from "react";
function useFetch(url) {
  const [data, setData] = useState([]);
  const [isComplete, setCompleted] = useState(false);

  async function fetchUrl(Url) {
    try {
      const response = await fetch(Url);
      const json = await response.json();
      setData(json);
      setCompleted(true);
    } catch (error) {
      //handle errors here
    }
  }
  useEffect(() => {
    fetchUrl(url);
  }, []);
  return [data, isComplete];
}
export { useFetch };
