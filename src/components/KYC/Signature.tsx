import React, { useState } from "react";
import { Segment, Icon, Button, Header, Loader } from "semantic-ui-react";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function Signature({ leagleInfo, setLeagleInfo, upload }) {
  const [isExecuting, setStatus] = useState(false);
  const uploadKYCDetails = async () => {
    setStatus(true);
    await upload();
    setStatus(false);
  };

  const onProfileChange = (event) => {
    if (
      event.target.files[0].type === "image/jpeg" ||
      event.target.files[0].type === "image/png"
    ) {
      setLeagleInfo({ ...leagleInfo, profile: event.target.files[0] });
    } else {
      toast.error("Enter in PNG or JPEG Format", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: false,
      });
    }
  };
  const onSignatureChange = (event) => {
    if (
      event.target.files[0].type === "image/jpeg" ||
      event.target.files[0].type === "image/png"
    ) {
      setLeagleInfo({ ...leagleInfo, sign: event.target.files[0] });
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
      <Loader inverted active={isExecuting}>
        Verifing
      </Loader>
      <Segment placeholder className="signature">
        <Segment>
          <Header icon>
            <Icon name="images file outline" />
            {leagleInfo.sign === null
              ? "Upload Signature"
              : leagleInfo.sign.name}
          </Header>
          <label className="custom-file-upload">
            <input type="file" onChange={onSignatureChange} />
            Upload
          </label>
        </Segment>
        <Segment>
          <Header icon>
            <Icon name="pdf file outline" />
            {leagleInfo.profile === null
              ? "Upload Profile Picture"
              : leagleInfo.profile.name}
          </Header>
          <label className="custom-file-upload">
            <input type="file" onChange={onProfileChange} />
            Upload
          </label>
        </Segment>
      </Segment>
      <Button
        className="kycbutton"
        onClick={uploadKYCDetails}
        disabled={isExecuting}
      >
        Upload Now
      </Button>
    </div>
  );
}
