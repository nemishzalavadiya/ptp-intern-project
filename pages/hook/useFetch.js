import { useState, useEffect } from "react";
function useFetch(url) {
  const [content, setContent] = useState({data:[],isComplete:false});

  async function fetchUrl(Url) {
    try {
      const response = await fetch(Url);
      const json = await response.json();
      setContent({data:json,isComplete:true})
    } catch (error) {
      //handle errors here
    }
  }
  useEffect(() => {
    fetchUrl(url);
  }, []);
  return [content['isComplete'],content['data']];
}
export { useFetch };
