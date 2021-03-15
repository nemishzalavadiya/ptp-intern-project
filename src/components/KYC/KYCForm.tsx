import React, { useState, useEffect } from "react";
import { addKYCDetails, isKycVerified } from "src/services/KYCService";
import {
  Grid,
  Button,
  Icon,
  Divider,
  Image,
  Loader,
  Step,
} from "semantic-ui-react";
import PanDetail from "src/components/KYC/PanDetail";
import PersonalDetail from "src/components/KYC/PersonalDetail";
import Signature from "src/components/KYC/Signature";
import SuccessPage from "src/components/KYC/SuccessPage";
import { KYCStep } from "src/enums/Step";

import { ToastContainer, toast } from "react-toastify";
import { useRouter } from "next/router";

export default function KYC(props) {
  const router = useRouter();
  const [isContentFetchingCompleted, changeFetchStatus] = useState(false);
  const [page, setPage] = useState(KYCStep.PERSONAL_DETAIL);
  const [isExecuting, setStatus] = useState(false);

  const validateFileType = (event) => {
    if (
      event.target.files[0].type === "image/jpeg" ||
      event.target.files[0].type === "image/png"
    ) {
      return true;
    } else {
      toast.error("Please select PNG or JPEG File", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: false,
      });
      return false;
    }
  };
  useEffect(() => {
    isKycVerified().then((res) => {
      if (res) {
        router.push("/profile");
      } else {
        changeFetchStatus(true);
      }
    });
  }, []);

  const [panDetails, setPanDetails] = useState({
    panNumber: "",
    panFile: null,
  });

  const [personalDetails, setPersonalDetails] = useState({
    occupation: "",
    annualIncome: "",
    motherName: "",
    fatherName: "",
  });

  const [leagleInfo, setLeagleInfo] = useState({
    sign: null,
    profile: null,
  });

  const nextClick = () => {
    setPage(page + 1);
  };

  const prevClick = () => {
    setPage(page - 1);
  };
  function createFormData() {
    const formData = new FormData();
    formData.append("pan", panDetails.panFile);
    formData.append("signature", leagleInfo.sign);
    formData.append("profile  ", leagleInfo.profile);
    formData.append("panNumber", panDetails.panNumber);
    formData.append("occupation", personalDetails.occupation);
    formData.append("annualIncome", personalDetails.annualIncome);
    formData.append("motherName", personalDetails.motherName);
    formData.append("fatherName", personalDetails.fatherName);
    formData.append("pab", panDetails.panFile);
    return formData;
  }

  const validatePan = () => {
    var patt = new RegExp("[A-Z]{5}[0-9]{4}[A-Z]{1}");
    if (!patt.test(panDetails.panNumber)) {
      toast.error("Enter Valid PAN Number", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: true,
      });
      return false;
    }
    if (panDetails.panFile === null) {
      toast.error("PAN Card is Required", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: true,
      });
      return false;
    }
    return true;
  };

  const validatePersonalDetails = () => {
    if (
      personalDetails.occupation === "" ||
      personalDetails.annualIncome === "" ||
      personalDetails.motherName === "" ||
      personalDetails.fatherName === ""
    ) {
      toast.error("Enter Valid Personal Details", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: true,
      });
      return false;
    }
    return true;
  };

  const validateLeagleInfo = () => {
    if (leagleInfo.profile === null || leagleInfo.sign === null) {
      toast.error("Signature and Profile are required", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: true,
      });
      return false;
    }
    return true;
  };

  const upload = async () => {
    setStatus(true);
    if (validatePersonalDetails() && validatePan() && validateLeagleInfo()) {
      await addKYCDetails(createFormData())
        .then(() => {
          setStatus(false);
          toast.success("You are KYC Verified", {
            position: "bottom-right",
            autoClose: 2000,
            hideProgressBar: true,
          });
          nextClick();
        })
        .catch((err) => {
          setStatus(false);
          toast.error(err.message, {
            position: "bottom-right",
            autoClose: 2000,
            hideProgressBar: true,
          });
        });
    } else {
      setStatus(false);
    }
  };

  function renderSwitch() {
    switch (page) {
      case KYCStep.PERSONAL_DETAIL:
        return (
          <PersonalDetail
            personalDetails={personalDetails}
            setPersonalDetails={setPersonalDetails}
          />
        );
      case KYCStep.PAN:
        return (
          <PanDetail
            panDetails={panDetails}
            setPanDetails={setPanDetails}
            validateFileType={validateFileType}
          />
        );
      default:
        return (
          <Signature
            leagleInfo={leagleInfo}
            setLeagleInfo={setLeagleInfo}
            upload={upload}
            nextClick={nextClick}
            validateFileType={validateFileType}
          />
        );
    }
  }

  return isContentFetchingCompleted ? (
    page !== 3 ? (
      <div className="kycDiv">
        <ToastContainer />
        <Loader inverted active={isExecuting}>
          Verifying
        </Loader>
        <Grid>
          <Grid.Row>
            <Grid.Column width={7} className="leftSide">
              <Image src="/kyc.svg"></Image>
            </Grid.Column>
            <Grid.Column width={7} verticalAlign="middle" className="rightSide">
              <div>
                <Step.Group size="mini" fluid>
                  <Step
                    active={page == KYCStep.PERSONAL_DETAIL}
                    onClick={() => setPage(0)}
                  >
                    <Step.Content>
                      <Step.Title>Personal Details</Step.Title>
                    </Step.Content>
                  </Step>

                  <Step active={page == KYCStep.PAN} onClick={() => setPage(1)}>
                    <Step.Content>
                      <Step.Title>Pan Detail</Step.Title>
                    </Step.Content>
                  </Step>

                  <Step
                    active={page == KYCStep.DOCUMENT}
                    onClick={() => setPage(2)}
                  >
                    <Step.Content>
                      <Step.Title>Documents</Step.Title>
                    </Step.Content>
                  </Step>
                </Step.Group>
                <Divider></Divider>
                {renderSwitch()}
                <div className="backNext">
                  <Button
                    icon
                    labelPosition="left"
                    className="kycButton"
                    disabled={page === KYCStep.PERSONAL_DETAIL}
                    onClick={prevClick}
                  >
                    Back
                    <Icon name="left arrow" />
                  </Button>
                  {page !== KYCStep.DOCUMENT ? (
                    <Button
                      icon
                      labelPosition="right"
                      className="kycButton"
                      onClick={nextClick}
                    >
                      Next
                      <Icon name="right arrow" />
                    </Button>
                  ) : (
                    <Button
                      className="uploadNow"
                      onClick={upload}
                      disabled={isExecuting}
                    >
                      Submit
                      <Icon name="right arrow" />
                    </Button>
                  )}
                </div>
              </div>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </div>
    ) : (
      <SuccessPage
        panDetails={panDetails}
        personalDetails={personalDetails}
        leagleInfo={leagleInfo}
      ></SuccessPage>
    )
  ) : (
    <Loader active inverted>
      Loading...
    </Loader>
  );
}
