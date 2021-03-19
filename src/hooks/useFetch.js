/*
  hook: useFetch
  argument: url
*/
import { useState, useEffect } from "react";
function useFetch(url, options, type) {
	const [content, setContent] = useState({
		data: [],
		error: false,
		isComplete: false,
	});
	async function fetchUrl(Url, Options) {
		try {
			await fetch(Url, Options)
				.then((response) => {
					if (response.ok) {
						if (type === undefined) {
							return response.json();
						} else if (type == "text") {
							return response.text();
						}
					} else {
						throw new Error(response.status);
					}
				})
				.then((responseData) => {
					setContent({ data: responseData, isComplete: true, error: false });
				})
				.catch((error) => {
					throw new Error(error);
				});
		} catch (error) {
			//handle errors
			setContent({ data: [], error: error, isComplete: true });
		}
	}
	useEffect(() => {
		if (url != null) {
			fetchUrl(url, options);
		}
	}, [url, JSON.stringify(options)]);
	return [content["isComplete"], content["data"], content["error"]];
}
export { useFetch };
