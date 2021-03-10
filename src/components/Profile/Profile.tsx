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
  Select,
  Dropdown,
  Loader,
} from "semantic-ui-react";
import { editUserDetails, getUser } from "../../services/userUpdate";
import { ToastContainer, toast } from "react-toastify";
import Moment from "moment";

export default function Profile() {
  const [bankName, setBankName] = useState("Axis");
  const [accountNumber, setAccountNumber] = useState("109");
  const [availableCash, setAvailableCash] = useState("40000");
  const [isUpdate, setIsUpdate] = useState(false);
  const [oldUser, setOldUser] = useState({});
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
  useEffect( async () => {
    const user = await getUser();
    setIsComp(true);
    setOldUser(user);
    setUser(user);
  }, [isUpdate]);

  const revertChanges = () => {
    setIsUpdate(false);
  };
  const saveChanges = () => {
    if (isUpdate == false) {
      setIsUpdate(true);
      return;
    }
    editUserDetails(user)
      .then((res) => {
        setIsUpdate(false);
        toast("Profile updated successfully", {
          position: "bottom-right",
          autoClose: 1500,
        });
      })
      .catch((err) => {
        setIsUpdate(false);
        setUser(oldUser);
        toast.error(err.message, {
          position: "bottom-right",
          autoClose: 1500,
        });
      });
  };

  return (
    <>
      {isComp ? (
        <Segment className="profile">
          <Grid divided columns={2}>
            <Divider vertical></Divider>
            <Grid.Row verticalAlign="middle">
              <Grid.Column width={8}>
                <Form inverted>
                  <Grid>
                    <Grid.Row>
                      <Grid.Column width={8}>
                        {user.dpURL ? (
                          <Image
                            className="profileicon"
                            src="/user.jpg"
                            circular
                          />
                        ) : (
                          <Icon name="user" size={"huge"} circular></Icon>
                        )}
                      </Grid.Column>

                      <Grid.Column width={8}>
                        <Input
                          readOnly={true}
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
                      <Grid.Column width={8}>
                        <Input
                          readOnly={!isUpdate}
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
                          onChange={(event) =>
                            setUser({ ...user, email: event.target.value })
                          }
                          placeholder="abc@gmail.com"
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
                          placeholder="Gender"
                          icon="venus mars"
                          className="textcolor"
                        />
                      </Grid.Column>

                      <Grid.Column width={8}>
                        {isUpdate == false ? (
                          <Input
                            readOnly={!isUpdate}
                            required
                            inverted
                            type="text"
                            transparent
                            value={user.gender}
                            placeholder="Select your gender"
                            className="textcolor"
                          />
                        ) : (
                          <Dropdown
                            value={user.gender}
                            placeholder="Select your gender"
                            options={genderOption}
                            onChange={(event) => {
                              setUser({
                                ...user,
                                gender: event.target.innerText,
                              });
                            }}
                          ></Dropdown>
                        )}
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
                          readOnly={!isUpdate}
                          required
                          inverted
                          transparent
                          value={user.mobileNo}
                          placeholder="+91 7016..."
                          onChange={(event) =>
                            setUser({ ...user, mobileNo: event.target.value })
                          }
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
                        {user.kycVerified ? (
                          <>
                            {" "}
                            You are KYC verified
                            <Icon name="check circle"></Icon>
                          </>
                        ) : (
                          <Button>Please Do kyc</Button>
                        )}
                      </Grid.Column>
                    </Grid.Row>
                  </Grid>
                </Form>
              </Grid.Column>

              <Grid.Column width={8}>
                {isUpdate == true ? (
                  <div className="editbutton">
                    {" "}
                    <Label onClick={saveChanges}>
                      <Icon name="edit" />
                      Save
                    </Label>
                    <Label onClick={revertChanges}>
                      <Icon name="cancel" />
                      Cancel
                    </Label>
                  </div>
                ) : (
                  <Label className="editbutton" onClick={saveChanges}>
                    <Icon name="edit" />
                    Edit
                  </Label>
                )}
                <Form inverted>
                  <Grid>
                    <Grid.Row>
                      <Grid.Column width={8}>
                        <Input
                          readOnly={true}
                          inverted
                          transparent
                          iconPosition="left"
                          placeholder="Bank Name"
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
      ) : (
        <Loader active />
      )}
      <ToastContainer></ToastContainer>
    </>
  );
}
