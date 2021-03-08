import React from "react";
import AssetOrder from "src/components/Order/AssetOrder";
import Tab from "src/components/Tab";
import { useState } from "react";
import { Loader, Menu, Input, Form, Grid } from "semantic-ui-react";
import Moment from "moment";

export default function Order(props) {
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
  function handleItemClick(index) {
    setActiveItem(index);
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
            <Grid.Column width={3}>
              <label>Start Date</label>
              <Form.Input
                value={startDate}
                onChange={handleStartDateChange}
                type="date"
              />
            </Grid.Column>
            <Grid.Column width={3}>
              <label>End Date</label>
              <Form.Input
                value={endDate}
                onChange={handleEndDateChange}
                type="date"
              />
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Form>
      <AssetOrder
        startDate={startDate}
        endDate={endDate}
        activeItem={activeItem}
      />
    </div>
  );
}
