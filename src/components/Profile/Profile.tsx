import React, { useEffect, useState } from "react";
import {
  Divider,
  Input,
  Image,
  Button,
  Form,
  Grid,
  Segment,
} from "semantic-ui-react";

export default function Profile() {
  useEffect(() => {
    var User = JSON.parse(localStorage.getItem("user"));
    setEmail(User.email);
    setKyc("KYC verified");
  }, []);

  const [userName, setUserName] = useState("user-name");
  const [dpID, setDpID] = useState("#123456");
  const [panCard, setPanCard] = useState("ABCDE7549613");
  const [email, setEmail] = useState("user@gmail.com");
  const [mobile, setMobile] = useState("+91 7016...");
  const [kyc, setKyc] = useState("Please Do KYC");
  const [bankName, setBankName] = useState("Axis");
  const [accountNumber, setAccountNumber] = useState("109");
  const [availableCash, setAvailableCash] = useState("40000");

  return (
    <>
      <Segment className="profile">
        <Grid textAlign="center" divided columns={2}>
          <Divider vertical></Divider>
          <Grid.Row verticalAlign="middle">
            <Grid.Column width={8}>
              <Form inverted>
                <Grid>
                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Image className="profileicon" src="/user.jpg" circular />
                    </Grid.Column>

                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        transparent
                        required
                        inverted
                        placeholder="username"
                        value={userName}
                        className="username"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        iconPosition="left"
                        placeholder="DP ID"
                        icon="lock"
                        className="textcolor"
                      />
                    </Grid.Column>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        required
                        inverted
                        transparent
                        value={dpID}
                        placeholder="DP ID"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>

                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        iconPosition="left"
                        placeholder="Pan-card"
                        icon="address card"
                        className="textcolor"
                      />
                    </Grid.Column>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        required
                        inverted
                        transparent
                        value={panCard}
                        placeholder="Pancard"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        iconPosition="left"
                        placeholder="Email id"
                        icon="mail"
                        className="textcolor"
                      />
                    </Grid.Column>

                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        required
                        inverted
                        transparent
                        value={email}
                        placeholder="hash@gmail.com"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>

                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        iconPosition="left"
                        placeholder="Mobile No"
                        icon="mobile"
                        className="textcolor"
                      />
                    </Grid.Column>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        required
                        inverted
                        transparent
                        value={mobile}
                        placeholder="+91 7016..."
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>

                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        iconPosition="left"
                        placeholder="KYC"
                        icon="podcast"
                        className="textcolor"
                      />
                    </Grid.Column>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        placeholder="KYC"
                        value={kyc}
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>
                </Grid>
              </Form>
            </Grid.Column>

            <Grid.Column width={8}>
              <Form inverted>
                <Grid>
                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        iconPosition="left"
                        placeholder="Bank name"
                        icon="home"
                        className="textcolor"
                      />
                    </Grid.Column>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        transparent
                        inverted
                        placeholder="Bank Name"
                        value={bankName}
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>

                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        iconPosition="left"
                        placeholder="Account ID"
                        icon="lock"
                        className="textcolor"
                      />
                    </Grid.Column>

                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        required
                        inverted
                        transparent
                        placeholder="Account number"
                        value={accountNumber}
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>

                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        iconPosition="left"
                        placeholder="Available Cash"
                        icon="rupee"
                        className="textcolor"
                      />
                    </Grid.Column>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        required
                        inverted
                        transparent
                        value={availableCash}
                        placeholder="Rs."
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Button type="submit" id="submit" fluid color="grey">
                        Add Fund
                      </Button>
                    </Grid.Column>

                    <Grid.Column width={8}>
                      <Button type="submit" id="submit" fluid color="grey">
                        Withdraw
                      </Button>
                    </Grid.Column>
                  </Grid.Row>
                </Grid>
              </Form>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Segment>
    </>
  );
}
