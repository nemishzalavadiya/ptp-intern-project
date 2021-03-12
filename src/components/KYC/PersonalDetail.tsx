import React from "react";
import {
  Grid,
  Input,
} from "semantic-ui-react";

export default function PersonalDetail({
  personalDetails,
  setPersonalDetails,
}) {
  return (
    <div className="midSection">
    <Grid>
      <Grid.Row>
        <Grid.Column width={5}><label>Occupation</label></Grid.Column>
        <Grid.Column width={11}><Input
        placeholder="Enter Occupation"
        value={personalDetails.occupation}
        className="kycinput"
        onChange={(event) => {
          setPersonalDetails({
            ...personalDetails,
            occupation: event.target.value,
          });
        }}
      /></Grid.Column>
      </Grid.Row>
      <Grid.Row>
        <Grid.Column width={5}><label>Annual Income</label></Grid.Column>
        <Grid.Column width={11}><Input
        placeholder="Enter Youe Annual Income"
        type="number"
        value={personalDetails.annualIncome}
        onChange={(event) => {
          setPersonalDetails({
            ...personalDetails,
            annualIncome: event.target.value,
          });
        }}
        className="kycinput"
      /></Grid.Column>
      </Grid.Row>
      <Grid.Row>
        <Grid.Column width={5}><label>Mother Name</label></Grid.Column>
        <Grid.Column width={11}><Input
        placeholder="Enter Your Mother's Name"
        value={personalDetails.motherName}
        onChange={(event) => {
          setPersonalDetails({
            ...personalDetails,
            motherName: event.target.value,
          });
        }}
        className="kycinput"
      /></Grid.Column>
      </Grid.Row>
      <Grid.Row>
        <Grid.Column width={5}><label>Father Name</label></Grid.Column>
        <Grid.Column width={11}><Input
        placeholder="Enter Your Father's Name"
        value={personalDetails.fatherName}
        onChange={(event) => {
          setPersonalDetails({
            ...personalDetails,
            fatherName: event.target.value,
          });
        }}
        className="kycinput"
      /></Grid.Column>
      </Grid.Row>

    </Grid>
    </div>
  );
}
