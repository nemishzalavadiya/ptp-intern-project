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
    <div className="midSection">
      <Loader inverted active={isExecuting}>
        Verifing
      </Loader>
      <div className="signature">
        <div className="fileupload">
          <Header icon>
            <Icon name="images file outline" />
            <div className="cut-text">
              {leagleInfo.sign === null
                ? "Upload Signature"
                : leagleInfo.sign.name}
            </div>
          </Header>
          <label className="custom-file-upload">
            <input type="file" onChange={onSignatureChange} />
            Upload
          </label>
        </div>
        <div className="fileupload">
          <Header icon>
            <Icon name="images file outline" />
            <div className="cut-text">
              {leagleInfo.profile === null
                ? "Upload Picture"
                : leagleInfo.profile.name}
            </div>
          </Header>
          <label className="custom-file-upload">
            <input type="file" onChange={onProfileChange} />
            Upload
          </label>
        </div>
      </div>
      <Button
        className="uploadnow"
        onClick={uploadKYCDetails}
        disabled={isExecuting}
      >
        Upload Now
      </Button>
    </div>
  );
}
