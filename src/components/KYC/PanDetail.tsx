import React from "react";
import { Segment, Icon, Header, Input } from "semantic-ui-react";

import { toast } from "react-toastify";

export default function PanDetail({
  panDetails,
  setPanDetails,
  validateFileType,
}) {
  const onFileChange = (event) => {
    if (validateFileType(event)) {
      setPanDetails({ ...panDetails, panFile: event.target.files[0] });
    }
  };

  return (
    <div className="midSection">
      <Input
        placeholder="Enter PAN"
        value={panDetails.panNumber}
        onChange={(event) =>
          setPanDetails({ ...panDetails, panNumber: event.target.value })
        }
        className="kycInput"
      />
      <div className="fileUpload">
        <Header icon>
          <Icon name="images file outline" />
          {panDetails.panFile === null ? "Upload PAN" : panDetails.panFile.name}
        </Header>
        <label className="customFileUpload">
          <input type="file" onChange={onFileChange} />
          Upload
        </label>
      </div>
      <br />
    </div>
  );
}
