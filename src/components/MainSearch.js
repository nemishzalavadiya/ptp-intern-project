import { useEffect, useState } from "react";
import { Search } from "semantic-ui-react";

const MainSearch = (props) => {
	const [value, setValue] = useState("");
	const [results, setResults] = useState([]);
	const [loading, setLoading] = useState(false);

	useEffect(() => {
		if (value !== "") {
			setLoading(true);
			fetch(`/assets/search/${value}?page=0&size=${props.size}`)
				.then((res) => res.json())
				.then((data) => {
					setResults({
						...data.content.map((item) => {
							return { title: item.name, id: item.id };
						}),
					});
					setLoading(false);
				});
		}
	}, [value]);
	return (
		<Search
			fluid
			size="big"
			loading={loading}
			onResultSelect={(event, data) => console.log(data.value)}
			onSearchChange={(event, data) => setValue(data.value)}
			results={results}
			value={value}
		/>
	);
};

export default MainSearch;
