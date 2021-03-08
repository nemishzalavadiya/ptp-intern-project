import Highcharts from "highcharts/highstock";
import React, { useState, useEffect } from "react";
import HighchartsReact from "highcharts-react-official";
import { Loader } from "semantic-ui-react";
import mockData from "src/components/Mutualfund/MutualfundData";
import options from "src/components/Mutualfund/MutualFundChartOptions";
let mockOptions = options;
const transformChartData = (options, array) => {
  const dataLength = array.length;
  console.log(dataLength);
  for (let i = 0; i < dataLength; i++) {
    array[i].x = new Date(array[i].x).getTime();
    options.series[0].data.push(array[i]);
  }
  return options;
};
export default function Chart(props) {
  const [data, setData] = useState({ options: {} });
  const [isLoading, setIsLoading] = useState(false);
  useEffect(() => {
    options.series[0].data = [];
    const fetchData = async () => {
      setIsLoading(true);
      const fetchedData = mockData;
      const newOptions = transformChartData(mockOptions, fetchedData);
      setData({ options: newOptions });
      setIsLoading(false);
    };
    fetchData();
  }, []);
  return (
    <div className="area">
      {" "}
      {isLoading ? (
        <Loader active={!isLoading}></Loader>
      ) : (
        <HighchartsReact highcharts={Highcharts} options={data.options} />
      )}
    </div>
  );
}
