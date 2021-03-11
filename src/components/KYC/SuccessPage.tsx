import React, { useState } from "react";
import { Button, Grid, Modal, Divider, Image } from "semantic-ui-react";
import { useRouter } from "next/router";

export default function SuccessPage({
  panDetails,
  personalDetails,
  leagleInfo,
}) {
  const router = useRouter();
  const [open, setOpen] = useState(false);
  const [image, setImage] = useState(URL.createObjectURL(panDetails.panFile));
  function getModel() {
    return (
      <Modal
        basic
        size="small"
        open={open}
        closeIcon
        onClose={() => setOpen(false)}
      >
        <Modal.Content>
          <Image src={image} />
        </Modal.Content>
      </Modal>
    );
  }

  return (
    <div className="kycdiv">
      <Grid className="kycProfile">
        <Grid.Row>
          <Grid.Column width={4} verticalAlign="middle" className="leftSide">
            <h1>Congratulations!</h1>
            <h1>Your Setup complete</h1>
            <h3>
              We are your new financial advisors to recommed the best
              investments for you
            </h3>
            <Divider></Divider>

            <Image src="/verified.png"></Image>
            <Button className="getStart" onClick={() => router.push("/")}>
              Gets Started
            </Button>
          </Grid.Column>

          <Grid.Column width={6} verticalAlign="top">
            <h1>KYC Details</h1>
            <Divider></Divider>
            <Grid textAlign="center" className="profile">
              <Grid.Row>
                <Grid.Column width={6}>
                  <Image
                    circular
                    size="small"
                    className="profilePicture"
                    src={URL.createObjectURL(leagleInfo.profile)}
                  />
                </Grid.Column>
              </Grid.Row>
              <Grid.Row>
                <Grid.Column width={5}>Pan Number</Grid.Column>
                <Grid.Column width={8}>{panDetails.panNumber}</Grid.Column>
              </Grid.Row>

              <Grid.Row>
                <Grid.Column width={5}>Occupation</Grid.Column>
                <Grid.Column width={8}>
                  {personalDetails.occupation}
                </Grid.Column>
              </Grid.Row>

              <Grid.Row>
                <Grid.Column width={5}>Annual Income</Grid.Column>
                <Grid.Column width={8}>
                  {personalDetails.annualIncome}
                </Grid.Column>
              </Grid.Row>

              <Grid.Row>
                <Grid.Column width={5}>Mother Name</Grid.Column>
                <Grid.Column width={8}>
                  {personalDetails.motherName}
                </Grid.Column>
              </Grid.Row>

              <Grid.Row>
                <Grid.Column width={5}>Father Name</Grid.Column>
                <Grid.Column width={8}>
                  {personalDetails.fatherName}
                </Grid.Column>
              </Grid.Row>

              <Grid.Row className="buttonGrid">
                <Grid.Column width={5}>
                  <Button
                    onClick={() => {
                      setOpen(true);
                      setImage(URL.createObjectURL(panDetails.panFile));
                    }}
                  >
                    Show PAN
                  </Button>
                </Grid.Column>
                <Grid.Column width={8}>
                  <Button
                    onClick={() => {
                      setOpen(true);
                      setImage(URL.createObjectURL(leagleInfo.sign));
                    }}
                  >
                    Show Sign
                  </Button>
                </Grid.Column>
              </Grid.Row>
            </Grid>
          </Grid.Column>
        </Grid.Row>
      </Grid>
      {getModel()}
    </div>
  );
}
