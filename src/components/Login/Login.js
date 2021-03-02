import React, { useEffect } from "react";
import { useState } from "react";
import { Divider, Input, Image, Icon, Button, Form, Grid, Segment } from "semantic-ui-react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Router from "next/router";
export default function Login() {
  const userList = [
    {
      email: "user@gmail.com",
      password: "password",
    },
    {
      email: "dummy@gmail.com",
      password: "dummy",
    },
    {
      email: "root@gmail.com",
      password: "root",
    },
    {
      email: "harshcerti@gmail.com",
      password: "harsh1868",
    },
  ];

  useEffect(() => {
    var User = JSON.parse(localStorage.getItem("user"));
    if(!User)
      return;
    if (new Date().getTime() <= User.time) {
      userList.forEach((user) => {
        if (user.email == User.email && user.password == User.password) {
          Router.push("/");
          return;
        }
      });
    }
  }, []);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const verifyUser = async () => {
    var User = {
      email: email,
      password: password,
    };

    let isExist = false;
    userList.forEach((user) => {
      if (user.email == User.email && user.password == User.password) {
        isExist = true;
        return;
      }
    });

    if (isExist == false) {
      toast("Username or Password is incorrect", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: false,
        progress: undefined,
      });
    } else {
      var User = {
        email: email,
        password: password,
        time: new Date().getTime() + 300000,
      };
      User = JSON.stringify(User);
      localStorage.setItem("user", User);
      toast("Login Successfully", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: false,
        progress: undefined,
      });
      Router.push("/");
    }
  };
  return (
    <>
      <Segment className="userlogin">
        <Grid textAlign="center">
          <Divider vertical></Divider>
          <Grid.Row verticalAlign="middle">
            <Grid.Column width={8}>
              <Image src="/PTP.png" size="medium" />
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
                        placeholder="Email"
                        iconPosition="left"
                        icon="mail"
                        className ="textcolor"
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
                        className ="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={3}></Grid.Column>
                    <Grid.Column width={10}>
          
                      <Button
                        type="submit"
                        className="submitButton"
                        onClick={verifyUser}
                        fluid
                        
                     >
                        Login
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
