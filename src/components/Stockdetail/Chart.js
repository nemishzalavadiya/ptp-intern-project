import Highcharts from "highcharts/highstock";
import React, { useState, useEffect } from "react";
import HighchartsReact from "highcharts-react-official";
import { Loader } from "semantic-ui-react";
import mockData from "src/components/Stockdetail/StockData";
import options from "src/components/Stockdetail/StockChartOptions";
let mockOptions = options;
const transformChartData = (options, array) => {
  const dataLength = array.length;

  for (var i = 0; i < dataLength; i += 1) {
    options.series[0].data.push([
      array[i][0], //date
      array[i][1], //open
      array[i][2], //high
      array[i][3], //low
      array[i][4], //close
    ]);

    options.series[1].data.push([
      array[i][0], //date
      array[i][5], //volume
    ]);
  }
  return options;
};
export default function Chart(props) {
  const [data, setData] = useState({ options: {} });
  const [isLoading, setIsLoading] = useState(false);
  useEffect(() => {
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
        <HighchartsReact
          highcharts={Highcharts}
          constructorType={"stockChart"}
          options={data.options}
        />
      )}
    </div>
  );
}
