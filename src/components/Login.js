import { useState, useEffect } from "react";
import {
  Divider,
  Input,
  Image,
  Button,
  Form,
  Grid,
  Segment,
} from "semantic-ui-react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Router from "next/router";
import { useAuth } from "src/components/contexts/auth";
export default function Login() {
  const { login } = useAuth();
  const [login_detail, setValidate] = useState({
    validate: false,
    email: null,
    password: null,
  });
  const [isCompleted, data, error] = login(
    login_detail.email,
    login_detail.password,
    login_detail.validate
  );
  if (isCompleted && error) {
    toast("Username or Password is incorrect", {
      position: "bottom-right",
      autoClose: 2000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: false,
      draggable: false,
      progress: undefined,
    });
  }
  useEffect(()=>{
    if (isCompleted && data.token) {
      if(Router.query.path===undefined){
        Router.replace("/")
      }
      else{
        Router.replace(Router.query.path);
      }
    } 
  })
  const submitHandler = (event) => {
    event.preventDefault();
    setValidate({
      validate: true,
      email: event.target.email.value,
      password: event.target.password.value,
    });
  };
  return (
    <>
      <Segment id="seg2">
        <Grid textAlign="center">
          <Divider vertical></Divider>
          <Grid.Row verticalAlign="middle">
            <Grid.Column width={8}>
              <Image src="/home.png" size="medium" />
            </Grid.Column>
            <Grid.Column width={8}>
              <Form inverted onSubmit={submitHandler}>
                <Grid>
                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        transparent
                        required
                        placeholder="email"
                        iconPosition="left"
                        icon="user"
                        id="email"
                        name="email"
                      />
                    </Grid.Column>
                  </Grid.Row>

                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        required
                        transparent
                        type="password"
                        iconPosition="left"
                        placeholder="******"
                        icon="lock"
                        id="password"
                        name="password"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={3}></Grid.Column>
                    <Grid.Column width={10}>
                      {" "}
                      <Button type="submit" id="submit" fluid color="grey">
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

// Example: toast
// toast("Username or Password is incorrect", {
//   position: "bottom-right",
//   autoClose: 2000,
//   hideProgressBar: false,
//   closeOnClick: true,
//   pauseOnHover: false,
//   draggable: false,
//   progress: undefined,
// });

//use to show toast: <ToastContainer></ToastContainer>
