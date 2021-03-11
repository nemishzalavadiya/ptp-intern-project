import React from "react";
import { Segment, Icon, Header, Input } from "semantic-ui-react";

import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function PanDetail({ panDetails, setPanDetails }) {
  const onFileChange = (event) => {
    if (
      event.target.files[0].type === "image/jpeg" ||
      event.target.files[0].type === "image/png"
    ) {
      setPanDetails({ ...panDetails, panFile: event.target.files[0] });
    } else {
      toast.error("Enter in PNG or JPEG Format", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: false,
      });
    }
  };

  return (
    <div>
      <Input
        placeholder="Enter PAN"
        value={panDetails.panNumber}
        onChange={(event) =>
          setPanDetails({ ...panDetails, panNumber: event.target.value })
        }
        className="kycinput"
      />
      <Segment placeholder className="fileupload">
        <Header icon>
          <Icon name="pdf file outline" />
          {panDetails.panFile === null ? "Upload PAN" : panDetails.panFile.name}
        </Header>
        <label className="custom-file-upload">
          <input type="file" onChange={onFileChange} />
          Upload
        </label>
      </Segment>
      <br />
    </div>
  );
}
