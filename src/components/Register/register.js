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
import { ToastContainer, toast } from "react-toastify";
import Router from "next/router";
import { userRegistration } from "../../services/userRegistration";
export default function Register() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errors, setError] = useState("");
  const [userName,setUserName] = useState("");
  useEffect(() => {
    if (password != confirmPassword) {
      setError("Password and Confirm Password are not same");
      return;
    } else {
      setError("");
    }
  }, [confirmPassword]);

  const register = (event) => {
    if (errors == "") {
      {
        event.preventDefault();
        let data = {
          email: email,
          password: password,
          userName:userName
        };
        userRegistration(data)
          .then(() => {
            toast("User Registered successfully", {
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
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: false,
              draggable: false,
              progress: undefined,
            });
          });
      }
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
                        required
                        inverted
                        onChange={(event) => setEmail(event.target.value)}
                        placeholder="Username"
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
                        required
                        inverted
                        onChange={(event) => setEmail(event.target.value)}
                        placeholder="Email"
                        iconPosition="left"
                        icon="mail"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>

                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        required
                        inverted
                        transparent
                        type="password"
                        iconPosition="left"
                        placeholder="******"
                        onChange={(event) => setPassword(event.target.value)}
                        icon="lock"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        required
                        inverted
                        transparent
                        type="password"
                        iconPosition="left"
                        placeholder="******"
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
