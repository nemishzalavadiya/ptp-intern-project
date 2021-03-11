import React from "react";
import AssetOrder from "src/components/Order/AssetOrder";
import Tab from "src/components/Tab";
import { useState,useEffect } from "react";
import { Loader, Menu, Input, Form, Grid } from "semantic-ui-react";
import Moment from "moment";

export default function Order(props) {
  const stockHeader = [
    { header: "Company", icon: "", sortable: false },
    { header: "Asset Class", icon: "", sortable: false  },
    { header: "Price", icon: <i className="rupee sign icon small"></i>, sortable: false  },
    { header: "Date", icon: "", sortable: false  },
    { header: "Time", icon: "", sortable: false  },
    { header: "Order Type", icon: "", sortable: false  },
    { header: "Price Type", icon: "", sortable: false  },
    { header: "Status", icon: "", sortable: false  },
  ];
  const mutualFundHeader = [
    { header: "Company", icon: "", sortable: false  },
    { header: "Asset Class", icon: "", sortable: false  },
    { header: "Amount", icon: <i className="rupee sign icon small"></i>, sortable: false  },
    { header: "Start Date", icon: "" , sortable: false },
    { header: "Frequency", icon: "", sortable: false  },
    { header: "SIP Status", icon: "" , sortable: false },
  ];
  const [activeItem, setActiveItem] = useState(0);
  const [startDate, setStartDate] = useState(Moment().format("YYYY-MM-DD"));
  const [endDate, setEndDate] = useState(Moment().format("YYYY-MM-DD"));
  const tabs = [{ name: "Stock" }, { name: "MutualFund" }];
  function handleStartDateChange(e, data) {
    setStartDate(data.value);
  }
  function handleEndDateChange(e, data) {
    setEndDate(data.value);
  }
  const [pattern, setPattern] = useState([]);
  const length = activeItem == 0 ? stockHeader.length : mutualFundHeader.length;;
  useEffect(() => {
     let initialPattern=[];
     for(let i=0;i<length;i++){
     initialPattern.push(0);
     }
     setPattern(initialPattern);
    }, [activeItem]);

  function handleItemClick(index) {
    setActiveItem(index);
  }
  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let midPattern = [];
    let size = length;
    for (let i = 0; i < size; i++) {
      midPattern.push(0);
    }
    midPattern[index] = 1 - pattern[index];
    if (midPattern[index] == 1) {
      setOrderBy("DESC");
    } else {
      setOrderBy("ASC");
    }
    setSortingField(fieldName);
    setPattern(midPattern);
  }
  return (
    <div>
      <Tab
        content={tabs}
        handleItemClick={handleItemClick}
        activeItem={activeItem}
      />
      <Form>
        <Grid>
          <Grid.Row>
            <Grid.Column width={3} className="date-picker">
              <label className="date-picker-label">Start Date</label>
              <Form.Input
                value={startDate}
                onChange={handleStartDateChange}
                type="date"
                max = {endDate}
              />
            </Grid.Column>
            <Grid.Column width={3} className="date-picker">
              <label className="date-picker-label">End Date</label>
              <Form.Input
                value={endDate}
                onChange={handleEndDateChange}
                type="date"
                min = {startDate}
                max = {endDate}
              />
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Form>
      <AssetOrder
        startDate={startDate}
        endDate={endDate}
        activeItem={activeItem}
        onclick={changeArrow}
        pattern={pattern}
        stockHeader={stockHeader}
        mutualFundHeader={mutualFundHeader}
      />
    </div>
  );
}
