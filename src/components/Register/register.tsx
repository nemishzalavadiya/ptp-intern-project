import React, { useEffect } from "react";
import { useState } from "react";
import {
  Divider,
  Input,
  Image,
  Label,
  Icon,
  Button,
  Form,
  Grid,
  Segment,
} from "semantic-ui-react";
import Link from "next/link";
import { ToastContainer, toast } from "react-toastify";
import Router from "next/router";
import { userRegistration } from "../../services/userRegistration";
export default function Register() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errors, setError] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");

  const checkPassword = () => {
    const re = new RegExp(
      "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})"
    );
    const isOk = re.test(password);
    if (isOk)
      return true;
    else 
      return false;
  };
  const register = (event) => {
    if (
      firstName.length >= 3 &&
      lastName.length >= 3 &&
      password == confirmPassword &&
      checkPassword(password)
    ) {
      event.preventDefault();
      let data = {
        email: email,
        password: password,
        firstName: firstName,
        lastName: lastName,
      };
      userRegistration(data)
        .then(() => {
          toast("Registration successful", {
            position: "bottom-right",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: false,
            progress: undefined,
          });
          Router.push("/login");
        })
        .catch((err) => {
          toast(err.message, {
            position: "bottom-right",
            autoClose: 1500,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: false,
            progress: undefined,
          });
        });
    } else if (password != confirmPassword) {
      toast("Passwords don't match", {
        position: "bottom-right",
        autoClose: 1500,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: false,
        progress: undefined,
      });
    }
    else if(password.length>=8 && !checkPassword(password)){
      toast("Please add strong password", {
        position: "bottom-right",
        autoClose: 1500,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: false,
        progress: undefined,
      });          
    }
     else {
      toast("Please fill out all fields", {
        position: "bottom-right",
        autoClose: 1500,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: false,
        progress: undefined,
      });
    }
  };

  return (
    <>
      <Segment className="usersignup">
        <Grid textAlign="center">
          <Divider vertical></Divider>
          <Grid.Row verticalAlign="middle">
            <Grid.Column width={8}>
              <Image src="/PTP.png" className="ptpimage" />
            </Grid.Column>

            <Grid.Column width={8}>
              <Form inverted>
                <Grid>
                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        transparent
                        inverted
                        onChange={(event) => setFirstName(event.target.value)}
                        placeholder="Firstname"
                        iconPosition="left"
                        icon="user"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        transparent
                        inverted
                        onChange={(event) => setLastName(event.target.value)}
                        placeholder="Lastname"
                        iconPosition="left"
                        icon="user"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>

                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        transparent
                        inverted
                        value={email}
                        onChange={(event) => setEmail(event.target.value)}
                        placeholder="Email"
                        iconPosition="left"
                        icon="mail"
                        type="email"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>

                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        inverted
                        transparent
                        type="password"
                        iconPosition="left"
                        placeholder="******"
                        value={password}
                        onChange={(event) => {
                          setPassword(event.target.value);
                        }}
                        icon="lock"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        inverted
                        transparent
                        type="password"
                        iconPosition="left"
                        placeholder="******"
                        value={confirmPassword}
                        onChange={(event) =>
                          setConfirmPassword(event.target.value)
                        }
                        icon="lock"
                        className="textcolor"
                      />
                      {errors && (
                        <Label pointing color="red">
                          {errors}
                        </Label>
                      )}
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={3}></Grid.Column>
                    <Grid.Column width={10}>
                      <Button
                        type="submit"
                        className="submitButton"
                        onClick={register}
                        fluid
                      >
                        Register
                      </Button>
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={3}></Grid.Column>
                    <Grid.Column width={10}>
                      <Link href="/login">Already Registered? Login</Link>
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
