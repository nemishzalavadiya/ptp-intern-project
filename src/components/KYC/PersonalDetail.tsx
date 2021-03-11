import React from "react";
import {
  Segment,
  Step,
  Grid,
  Form,
  Icon,
  Button,
  Divider,
  Image,
  Input,
} from "semantic-ui-react";

export default function PersonalDetail({
  personalDetails,
  setPersonalDetails,
}) {
  return (
    <div>
      <Input
        placeholder="Enter Occupation"
        value={personalDetails.occupation}
        className="kycinput"
        onChange={(event) => {
          setPersonalDetails({
            ...personalDetails,
            occupation: event.target.value,
          });
        }}
      />
      <br />
      <Input
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
      />
      <br />
      <Input
        placeholder="Enter Your Mother's Name"
        value={personalDetails.motherName}
        onChange={(event) => {
          setPersonalDetails({
            ...personalDetails,
            motherName: event.target.value,
          });
        }}
        className="kycinput"
      />
      <br />
      <Input
        placeholder="Enter Your Father's Name"
        value={personalDetails.fatherName}
        onChange={(event) => {
          setPersonalDetails({
            ...personalDetails,
            fatherName: event.target.value,
          });
        }}
        className="kycinput"
      />
      <br />
    </div>
  );
}
