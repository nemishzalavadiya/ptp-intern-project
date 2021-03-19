import React from "react";
import { Grid, Input } from "semantic-ui-react";

export default function PersonalDetail({
  personalDetails,
  setPersonalDetails,
}) {
  return (
    <div className="midSection">
      <div className="personalInfo">
        <Grid textAlign="left">
          <Grid.Row>
            <Grid.Column width={6}>
              <label>Occupation</label>
            </Grid.Column>
            <Grid.Column width={9}>
              <Input
                placeholder="Enter Occupation"
                value={personalDetails.occupation}
                className="kycInput"
                onChange={(event) => {
                  setPersonalDetails({
                    ...personalDetails,
                    occupation: event.target.value,
                  });
                }}
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={6}>
              <label>Annual Income</label>
            </Grid.Column>
            <Grid.Column width={9}>
              <Input
                placeholder="Enter Your Annual Income"
                type="number"
                value={personalDetails.annualIncome}
                onChange={(event) => {
                  setPersonalDetails({
                    ...personalDetails,
                    annualIncome: event.target.value,
                  });
                }}
                className="kycInput"
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={6}>
              <label>Mother Name</label>
            </Grid.Column>
            <Grid.Column width={9}>
              <Input
                placeholder="Enter Your Mother's Name"
                value={personalDetails.motherName}
                onChange={(event) => {
                  setPersonalDetails({
                    ...personalDetails,
                    motherName: event.target.value,
                  });
                }}
                className="kycInput"
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={6}>
              <label>Father Name</label>
            </Grid.Column>
            <Grid.Column width={9}>
              <Input
                placeholder="Enter Your Father's Name"
                value={personalDetails.fatherName}
                onChange={(event) => {
                  setPersonalDetails({
                    ...personalDetails,
                    fatherName: event.target.value,
                  });
                }}
                className="kycInput"
              />
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </div>
    </div>
  );
}
