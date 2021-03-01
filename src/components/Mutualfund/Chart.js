import HighStock from "highcharts/highstock";
import React, { useState, useEffect } from "react";
import HighchartsReact from "highcharts-react-official";
import { Loader } from "semantic-ui-react";
import mockData from "src/components/Mutualfund/MutualfundData";
let mockOptions = {
  chart: {
    backgroundColor: "#272727",
    borderRadius: "15px",
    type: "spline",
    style: {
      color: "#fffdfd",
    },
    animation: {
      duration: 1500,
    },
    zoomType: "x",
  },
  legend: {
    enabled: false,
  },
  title: {
    text: "",
  },
  credits: {
    enabled: false,
  },
  tooltip: {
    split: false,
    backgroundColor: "#747474",
    borderRadius: 20,
    style: {
      color: "#ffffff",
    },
    headerShape: "square",
  },
  yAxis: {
    gridLineWidth: 0,
    title: {
      text: "",
    },
  },
  xAxis: {
    type: "datetime",
    title: {
      text: "",
    },
  },
  plotOptions: {
    line: {
      color: "#50d688",
    },
  },
  series: [
    {
      type: "line",
      name: "nav",
      data: [],
      animation: {
        duration: 2000,
      },
    },
  ],
};
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
        <HighchartsReact highcharts={HighStock} options={data.options} />
      )}
    </div>
  );
}
