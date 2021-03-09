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
import { userEdit, getUserById } from "../../services/userEdit";
import { ToastContainer, toast } from "react-toastify";
import Moment from "moment";

export default function Profile() {
  const [userName, setUserName] = useState("user.userName");
  const [dpID, setDpID] = useState("#123456");
  const [panCard, setPanCard] = useState("ABCDE7549613");
  const [email, setEmail] = useState("user@gmail.com");
  const [mobile, setMobile] = useState("+91 7016...");
  const [kyc, setKyc] = useState("Please Do KYC");
  const [bankName, setBankName] = useState("Axis");
  const [dob, setDob] = useState("");
  const [accountNumber, setAccountNumber] = useState("109");
  const [availableCash, setAvailableCash] = useState("40000");
  const [isUpdate, setIsUpdate] = useState(false);
  const [gender,setGender] = useState("");
  const [oldUser,setOldUser] = useState({});
  let prevEmail = email;
  const genderOption = [ { key: 'MALE', value: 'MALE', text: 'MALE' },
  { key: 'FEMALE', value: 'FEMALE', text: 'FEMALE' },]
  const [user, setUser] = useState({});
  useEffect(async () => {
    const user = await getUserById("00000000-0000-0000-0000-000000000000");
    setOldUser(user);
    setUser(user);
    console.log("prev user" , user);
    prevEmail = email;
    // setUser(user)
    // setEmail(user.email)
    // setGender(user.gender)
    // setUserName(user.userName)
    // setDpID(user.dpID)
    // setPanCard(user.panCard)
    // setKyc(user.kyc)  
    // setDob(Moment(user.dateOfBirth).format("YYYY-MM-DD"))
    // setMobile(user.mobileNo)
    
  }, [isUpdate]);

  const revertChanges = ()=>{
    setUser(oldUser);
    setIsUpdate(false);
  }
  const saveChanges = () => {
    if (isUpdate == false) {
      console.log(user)
      setIsUpdate(true);
      return;
    }
    console.log(user);
    var oldUser = user;
    // oldUser.email = email;
    // oldUser.dateOfBirth = dob;
    console.log(oldUser);
    userEdit(oldUser)
      .then((res) => {
        console.log(res);
        setEmail(oldUser.email);
        prevEmail = email;
      })
      .catch((err) => {
        setEmail(prevEmail);
        toast.error(err.message, {
          position: "bottom-right",
          autoClose: 2000,
          hideProgressBar: false,
        });
      });
    setIsUpdate(false);
  };

  return (
    <>
      <Segment className="profile">
        <Grid  divided columns={2}>
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
                        value={user.userName}
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
                        value={user.dpID}
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
                        value={user.panCard}
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
                        readOnly={!isUpdate}
                        required
                        inverted
                        transparent
                        value={user.email}
                        onChange={(event) => setUser({...user,email:event.target.value})}
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
                        placeholder="Gender"
                        icon="venus mars"
                        className="textcolor"
                      />
                    </Grid.Column>

                    <Grid.Column width={8}>
                      {isUpdate==false? <Input
                        readOnly={!isUpdate}
                        required
                        inverted
                        type="text"
                        transparent
                        value={user.gender}
                        placeholder="Select your gender"
                        className="textcolor"
                      />:

                      <Dropdown    value={user.gender} placeholder="Select your gender"  options={genderOption} onChange={(event) =>{ setUser({...user,gender:event.target.innerText});}} ></Dropdown>
                      }</Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={8}>
                      <Input
                        readOnly={true}
                        inverted
                        transparent
                        iconPosition="left"
                        placeholder="Date of birth"
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
                        value={dob}
                        placeholder="DOB"
                        onChange={(event) => setDob(event.target.value)}
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
                        onChange={(event) => setMobile(event.target.value)}
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
                      {kyc?<> You are  KYC verified 
                      <Icon name="check circle">
                       </Icon>
                       </>:  
                    
                      <Button>
                        Please Do kyc</Button>
                        }
                    </Grid.Column>
                  </Grid.Row>
                </Grid>
              </Form>
            </Grid.Column>

            <Grid.Column width={8}>
             
              {isUpdate==true? ( 
              <div className = "editbutton">   <Label  onClick={saveChanges}>
                <Icon name="edit"/>
                Save
              </Label> 
                <Label  onClick={revertChanges}>
                <Icon name="cancel"/>
                Cancel
              </Label> 
              </div>
              ):
              <Label className="editbutton" onClick={saveChanges}>
                <Icon name= "edit" />
                Edit
              </Label>
            }
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
      <ToastContainer></ToastContainer>
    </>
  );
}
