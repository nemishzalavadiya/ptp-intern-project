import HighStock from "highcharts/highstock";
import React, { useState, useEffect } from "react";
import HighchartsReact from "highcharts-react-official";
import { Loader } from "semantic-ui-react";
import mockData from "src/components/Stockdetail/StockData";

let mockOptions = {
  chart: {
    backgroundColor: "#272727",
    borderRadius: "15px",
    type: "candlestick",
    style: {
      color: "#fffdfd",
    },
    animation: {
      duration: 1500,
    },
    zoomType: "x",
  },
  credits: {
    enabled: false,
  },

  yAxis: [
    {
      gridLineWidth: 0,
      title: {
        text: "",
      },
      height: "100%",
      lineWidth: 1,
      resize: {
        enabled: true,
      },
    },
    {
      gridLineWidth: 0,
      title: {
        text: "",
      },
      top: "65%",
      height: "16%",
      offset: 0,
      lineWidth: 1,
    },
  ],
  tooltip: {
    split: true,
    backgroundColor: "#747474",
    borderRadius: 20,
    style: {
      color: "#ffffff",
    },
    headerShape: "square",
  },
  plotOptions: {
    candlestick: {
      color: "#f55742",
      upColor: "#50d688",
    },
    column: {
      color: "#50d688",
    },
  },
  series: [
    {
      type: "candlestick",
      data: [],
      name: "stock"
    },
    {
      type: "column",
      data: [],
      yAxis: 1,
      turboThresold: 0,
    },
  ],
};
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
          highcharts={HighStock}
          constructorType={"stockChart"}
          options={data.options}
        />
      )}
    </div>
  );
}
