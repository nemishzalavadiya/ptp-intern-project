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
      await fetch(Url).then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Something went wrong');
        }
      })
      .then((responseJson) => {
        setContent({data:responseJson,isComplete:true})
      })
      .catch((error) => {
        throw new Error(error);
      });
      
    } catch (error) {
      //handle errors
      setContent({data:[],error:error,isComplete:false})
    }
  }
  useEffect(() => {
    fetchUrl(url);
  }, [url]);
  return [content['isComplete'],content['data']];
}
export { useFetch };
