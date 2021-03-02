const options = {
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
    height: (9 / 16) * 100 + "%",
    events: { load: function () {} },
  },
  credits: {
    enabled: false,
  },
  exporting: {
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
      name: "stock",
    },
    {
      type: "column",
      data: [],
      yAxis: 1,
      turboThresold: 0,
    },
  ],
};

export default options;
