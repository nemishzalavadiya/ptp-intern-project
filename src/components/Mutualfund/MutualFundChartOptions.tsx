const options = {
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
    height: (9 / 16) * 100 + "%",
    events: { load: function () {}, redraw: function () {} },
  },
  legend: {
    enabled: false,
  },
  exporting: {
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
    ordinal: false 
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
    },
  ],
};

export default options;
