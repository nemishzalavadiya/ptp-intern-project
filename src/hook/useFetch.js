/*
  hook: useFetch
  argument: url
  TODO:
      1. manage throw errors response
*/
import { useState, useEffect } from "react";
function useFetch(url) {
  const [content, setContent] = useState({data:[],error:false,isComplete:false});
  async function fetchUrl(Url) {
    try {
      const response = await fetch(url);
      if(!response.ok){
        throw new Error("Can't fetch the data, ",response);
      }
      const json = await response.json();
      setContent({data:json,isComplete:true})
    } catch (error) {
      //handle errors
      setContent({data:[],error:error,isComplete:false})
    }
  }
  useEffect(() => {
    fetchUrl(url);
  }, []);
  return [content['isComplete'],content['data']];
}
export { useFetch };
