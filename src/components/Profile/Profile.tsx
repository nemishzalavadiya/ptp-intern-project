import React, { useEffect, useState } from "react";
import {
  Divider,
  Input,
  Image,
  Button,
  Form,
  Grid,
  Segment,
  Label,
  Icon,
  Dropdown,
  Loader,
  Modal,
  Popup,
} from "semantic-ui-react";
import { updateUserDetails, getUser } from "src/services/userUpdate";
import { ToastContainer } from "react-toastify";
import Moment from "moment";
import { useRouter } from "next/router";
import showToast from "src/components/showToast";

export default function Profile() {
  const router = useRouter();
  const [availableCash, setAvailableCash] = useState(0);
  const [isUpdate, setIsUpdate] = useState(false);
  const [open, setOpen] = useState(false);
  const [fund, setFund] = useState(0);
  const [bank, setBank] = useState(null);
  const [isAddFund, setIsAddFund] = useState(true);
  const bankDetail = [
    { name: "Axis", accountNumber: "XXXXXXX10121" },
    { name: "State Bank of India", accountNumber: "XXXXXXX20212" },
    { name: "Bank of Baroda", accountNumber: "XXXXXXX30323" },
  ];
  const genderOption = [
    { key: "MALE", value: "MALE", text: "MALE" },
    { key: "FEMALE", value: "FEMALE", text: "FEMALE" },
  ];
  const [user, setUser] = useState({
    userName: "",
    email: "",
    dpURL: "",
    firstName: "",
    lastName: "",
    dpId: "",
    panCard: "",
    gender: "",
    dateOfBirth: "",
    mobileNo: "",
    kycVerified: "",
  });
  const [isComp, setIsComp] = useState(false);
  useEffect(async () => {
    const user = await getUser();
    setIsComp(true);
    setUser(user);
  }, [isUpdate]);

  const revertChanges = () => {
    setIsUpdate(false);
  };

  const modal = () => {
    return (
      <Modal inverted size="mini" open={open}>
        <Modal.Content>
          <Input
            required
            fluid
            type="number"
            icon="money"
            placeholder="Add amount"
            onChange={(event) => setFund(parseInt(event.target.value))}
          />
          {!isAddFund && (
            <Dropdown
              selection
              onChange={(event, data) => setBank(data.value)}
              placeholder="Select your bank"
              options={bankDetail.map((item) => {
                return { key: item.name, value: item.name, text: item.name };
              })}
            ></Dropdown>
          )}
        </Modal.Content>
        <Modal.Actions>
          <Button
            disabled={
              fund <= 0 ||
              (!isAddFund && fund > availableCash) ||
              (!isAddFund && bank == null)
            }
            color="green"
            inverted
            onClick={() => (isAddFund ? addFund() : withdrawFund())}
          >
            {isAddFund ? "Add Fund" : "Withdraw"}
          </Button>
          <Button color="red" inverted onClick={() => setOpen(false)}>
            Cancel
          </Button>
        </Modal.Actions>
      </Modal>
    );
  };

  const addFund = () => {
    setAvailableCash(parseInt(availableCash) + parseInt(fund));
    setOpen(false);
  };

  const withdrawFund = () => {
    const cashValue = parseInt(availableCash);
    const newValue = parseInt(fund);
    setAvailableCash(cashValue - newValue);
    setOpen(false);
  };

  const saveChanges = () => {
    if (isUpdate == false) {
      setIsUpdate(true);
      return;
    }
    if (user.mobileNo != null && user.mobileNo.length != 10) {
      showToast("Please enter valid phone number",true)
      return;
    }
    updateUserDetails(user)
      .then((res) => {
        setIsUpdate(false);
        showToast("Profile updated successfully");
      })
      .catch((err) => {
        setIsUpdate(false);
        showToast(err.message, true);
      });
  };

  return (
    <>
      {isComp ? (
        <Segment className="profile">
          <Grid divided columns={2}>
            <Divider vertical></Divider>
            <Grid.Row>
              <Grid.Column width={8}>
                <Form inverted>
                  <Grid>
                    <Grid.Row>
                      <Grid.Column></Grid.Column>
                    </Grid.Row>
                    <Grid.Row>
                      <Grid.Column width={8}>
                        {user.dpURL ? (
                          <Image
                            className="profileicon"
                            src="/user.jpg"
                            circular
                          />
                        ) : (
                          <Image
                            className="profileicon"
                            src="/userwhite.jpg"
                            circular
                          />
                        )}
                      </Grid.Column>

                      <Grid.Column width={8}>
                        <Input
                          readOnly={isUpdate}
                          transparent
                          required
                          inverted
                          placeholder="User Name"
                          value={user.firstName + " " + user.lastName}
                          className="username"
                        />
                      </Grid.Column>
                    </Grid.Row>
                    <Grid.Row>
                      <Grid.Column>
                        <h3>Account</h3>
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
                          inverted
                          transparent
                          value={user.dpId}
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
                          placeholder="PAN"
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
                          value={user.panCard}
                          placeholder="Add XXX123"
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
                          placeholder="E-mail"
                          icon="mail"
                          className="textcolor"
                        />
                      </Grid.Column>

                      <Grid.Column width={8}>
                        <Input
                          readOnly={!isUpdate}
                          required
                          inverted
                          transparent
                          value={user.email}
                          className={`textcolor ${
                            isUpdate ? "textborder" : ""
                          }`}
                          onChange={(event) =>
                            setUser({ ...user, email: event.target.value })
                          }
                          icon={isUpdate ? "pencil" : ""}
                          placeholder="abc@gmail.com"
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
                          placeholder="Gender"
                          icon="venus mars"
                          className="textcolor"
                        />
                      </Grid.Column>

                      <Grid.Column width={8}>
                        <Dropdown
                          value={user.gender}
                          disabled={!isUpdate}
                          className={`profiledropdown ${
                            isUpdate ? "textborder" : ""
                          }`}
                          placeholder="Select your gender"
                          options={genderOption}
                          onChange={(event) => {
                            setUser({
                              ...user,
                              gender: event.target.innerText,
                            });
                          }}
                        ></Dropdown>
                      </Grid.Column>
                    </Grid.Row>
                    <Grid.Row>
                      <Grid.Column width={8}>
                        <Input
                          readOnly={true}
                          inverted
                          transparent
                          iconPosition="left"
                          placeholder="Date Of Birth"
                          icon="calendar"
                          className="textcolor"
                        />
                      </Grid.Column>
                      <Grid.Column width={8}>
                        <Input
                          readOnly={!isUpdate}
                          required
                          inverted
                          type="date"
                          transparent
                          id="userdob"
                          value={user.dateOfBirth}
                          placeholder="DOB"
                          onChange={(event) =>
                            setUser({
                              ...user,
                              dateOfBirth: Moment(event.target.value).format(
                                "YYYY-MM-DD"
                              ),
                            })
                          }
                          className={`textcolor ${
                            isUpdate ? "textborder" : ""
                          }`}
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
                          readOnly={!isUpdate}
                          required
                          inverted
                          transparent
                          value={user.mobileNo}
                          placeholder="Add 70165XXXXX"
                          onChange={(event) =>
                            setUser({ ...user, mobileNo: event.target.value })
                          }
                          className={`textcolor ${
                            isUpdate ? "textborder" : ""
                          }`}
                          icon={isUpdate ? "pencil" : ""}
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
                        {user.kycVerified ? (
                          <Label color="green">
                            {"verified "}
                            <Icon name="check circle"></Icon>
                          </Label>
                        ) : (
                          <Button
                            color="red"
                            onClick={() => {
                              router.push("/kyc");
                            }}
                          >
                            KYC Verification
                          </Button>
                        )}
                      </Grid.Column>
                    </Grid.Row>
                  </Grid>
                </Form>
              </Grid.Column>

              <Grid.Column width={8}>
                {isUpdate == true ? (
                  <Button.Group className="save-button">
                    <Button onClick={saveChanges} inverted color="green">
                      <Icon name="edit" />
                      Save
                    </Button>
                    <Button onClick={revertChanges} inverted color="red">
                      <Icon name="cancel" />
                      Cancel
                    </Button>
                  </Button.Group>
                ) : (
                  <Button
                    className="editbutton"
                    inverted
                    color="green"
                    onClick={saveChanges}
                  >
                    <Icon name="edit" />
                    Edit
                  </Button>
                )}
                <Form inverted>
                  <Grid>
                    <h3>Bank accounts</h3>
                    {bankDetail.map((item) => {
                      return (
                        <Grid.Row>
                          <Grid.Column width={4}>
                            <Input
                              readOnly={true}
                              required
                              inverted
                              transparent
                              value={"*" + item.accountNumber.slice(-4)}
                              className="textcolor"
                            />
                          </Grid.Column>
                          <Grid.Column width={12}>
                            <Input
                              readOnly={true}
                              transparent
                              inverted
                              value={item.name}
                              className="textcolor"
                            />
                          </Grid.Column>
                        </Grid.Row>
                      );
                    })}

                    <h3>Funds</h3>
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
                        <Button
                          onClick={() => {
                            setOpen(true);
                            setIsAddFund(true);
                            setFund(0);
                          }}
                          inverted
                          fluid
                          color="green"
                        >
                          Add Fund
                        </Button>
                      </Grid.Column>

                      <Grid.Column width={8}>
                        <Button
                          onClick={() => {
                            setOpen(true);
                            setIsAddFund(false);
                            setFund(0);
                          }}
                          inverted
                          fluid
                          color="blue"
                        >
                          Withdraw
                        </Button>
                      </Grid.Column>
                    </Grid.Row>
                  </Grid>
                </Form>
              </Grid.Column>
            </Grid.Row>
          </Grid>
          {modal()}
        </Segment>
      ) : (
        <Loader active />
      )}
      <ToastContainer/>
    </>
  );
}
