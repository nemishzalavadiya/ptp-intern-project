import React, { useState } from "react";
import { Segment, Icon, Button, Header, Loader } from "semantic-ui-react";

import { ToastContainer, toast } from "react-toastify";

export default function Signature({
  leagleInfo,
  setLeagleInfo,
  validateFileType,
}) {
  const onProfileChange = (event) => {
    if (validateFileType(event)) {
      setLeagleInfo({ ...leagleInfo, profile: event.target.files[0] });
    }
  };
  const onSignatureChange = (event) => {
    if (validateFileType(event)) {
      setLeagleInfo({ ...leagleInfo, sign: event.target.files[0] });
    }
  };
  return (
    <div className="midSection">
      <div className="signature">
        <div className="fileUpload">
          <Header icon>
            <Icon name="images file outline" />
            <div className="cutText">
              {leagleInfo.sign === null
                ? "Upload Signature"
                : leagleInfo.sign.name}
            </div>
          </Header>
          <label className="customFileUpload">
            <input type="file" onChange={onSignatureChange} />
            Upload
          </label>
        </div>
        <div className="fileUpload">
          <Header icon>
            <Icon name="images file outline" />
            <div className="cutText">
              {leagleInfo.profile === null
                ? "Upload Picture"
                : leagleInfo.profile.name}
            </div>
          </Header>
          <label className="customFileUpload">
            <input type="file" onChange={onProfileChange} />
            Upload
          </label>
        </div>
      </div>
    </div>
  );
}
