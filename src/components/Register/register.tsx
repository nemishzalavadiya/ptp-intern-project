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
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [errors, setError] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const iconPasswordClickHandler = () => {
    setShowPassword(!showPassword);
  };
  const iconconfirmPasswordClickHandler = () => {
    setShowConfirmPassword(!showConfirmPassword);
  };
  const checkPassword = () => {
    const re = new RegExp(
      "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})"
    );
    return re.test(password);
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
          toast.dark("Registration successful", {
            position: "bottom-right",
            autoClose: 2000,
            hideProgressBar: true,
          });
          Router.push("/login");
        })
        .catch((err) => {
          toast.error(err.message, {
            position: "bottom-right",
            autoClose: 2000,
            hideProgressBar: true,
          });
        });
    } else if (password != confirmPassword) {
      toast.error("Passwords don't match", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: true,
      });
    } else if (password.length >= 8 && !checkPassword(password)) {
      toast.error("Please add strong password", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: true,
      });
    } else {
      toast.error("Please fill out all fields", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: true,
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
                        autoComplete="new-password"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        transparent
                        inverted
                        autoComplete="new-password"
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
                        autoComplete="new-password"
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
                        type={showPassword ? "text" : "password"}
                        iconPosition="left"
                        placeholder="********"
                        value={password}
                        onChange={(event) => {
                          setPassword(event.target.value);
                        }}
                        icon={
                          <Icon
                            name={showPassword ? "lock open" : "lock"}
                            link
                            onClick={iconPasswordClickHandler}
                          />
                        }
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        inverted
                        transparent
                        type={showConfirmPassword ? "text" : "password"}
                        iconPosition="left"
                        placeholder="********"
                        value={confirmPassword}
                        onChange={(event) =>
                          setConfirmPassword(event.target.value)
                        }
                        icon={
                          <Icon
                            name={showConfirmPassword ? "lock open" : "lock"}
                            link
                            onClick={iconconfirmPasswordClickHandler}
                          />
                        }
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
