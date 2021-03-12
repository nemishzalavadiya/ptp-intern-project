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

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRouter } from "next/router";

export default function KYC(props) {
  const router = useRouter();
  const [isContentFetchingCompleted, changeFetchStatus] = useState(false);
  const [page, setPage] = useState(0);

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

  const headers = [
    {
      header: "Enter Your Personal Details",
    },
    {
      header: "Enter Your PAN Details",
    },
    {
      header: "Upload Your Signature and Profile",
    },
  ];

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
        hideProgressBar: false,
      });
      return false;
    }
    if (panDetails.panFile === null) {
      toast.error("PAN Card is Required", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: false,
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
        hideProgressBar: false,
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
        hideProgressBar: false,
      });
      return false;
    }
    return true;
  };

  const upload = async () => {
    if (validatePersonalDetails() && validatePan() && validateLeagleInfo()) {
      await addKYCDetails(createFormData())
        .then(() => {
          toast.success("Verification Done", {
            position: "bottom-right",
            autoClose: 2000,
            hideProgressBar: false,
          });
          nextClick();
        })
        .catch((err) => {
          toast.error(err.message, {
            position: "bottom-right",
            autoClose: 2000,
            hideProgressBar: false,
          });
        });
    }
  };

  function renderSwitch() {
    switch (page) {
      case 0:
        return (
          <PersonalDetail
            personalDetails={personalDetails}
            setPersonalDetails={setPersonalDetails}
          />
        );
      case 1:
        return (
          <PanDetail panDetails={panDetails} setPanDetails={setPanDetails} />
        );
      default:
        return (
          <Signature
            leagleInfo={leagleInfo}
            setLeagleInfo={setLeagleInfo}
            upload={upload}
            nextClick={nextClick}
          />
        );
    }
  }

  return isContentFetchingCompleted ? (
    page !== 3 ? (
      <div className="kycdiv">
        <ToastContainer />
        <Grid textAlign="center">
          <Grid.Row>
            <Grid.Column width={7} className="leftSide">
              <Image src="/kyc.png"></Image>
            </Grid.Column>
            <Grid.Column width={7} verticalAlign="top" className="rightSide">
              <div>
                <Step.Group size="mini" fluid>
                  <Step active={page == 0} onClick={() => setPage(0)}>
                    <Step.Content>
                      <Step.Title>Personal Details</Step.Title>
                    </Step.Content>
                  </Step>

                  <Step active={page == 1} onClick={() => setPage(1)}>
                    <Step.Content>
                      <Step.Title>Pan Detail</Step.Title>
                    </Step.Content>
                  </Step>

                  <Step active={page == 2} onClick={() => setPage(2)}>
                    <Step.Content>
                      <Step.Title>Documents</Step.Title>
                    </Step.Content>
                  </Step>
                </Step.Group>
                <h3>{headers[page].header}</h3>
                <Divider></Divider>
                {renderSwitch()}
                <div className="backnext">
                  <Button
                    icon
                    labelPosition="left"
                    className="kycbutton"
                    disabled={page === 0}
                    onClick={prevClick}
                  >
                    Back
                    <Icon name="left arrow" />
                  </Button>
                  <Button
                    icon
                    labelPosition="right"
                    className="kycbutton"
                    disabled={page === 2}
                    onClick={nextClick}
                  >
                    Next
                    <Icon name="right arrow" />
                  </Button>
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
