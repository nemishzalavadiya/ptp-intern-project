import {
  Divider,
  Input,
  Image,
  Button,
  Form,
  Grid,
  Segment,
} from "semantic-ui-react";
import { ToastContainer } from "react-toastify";
import { useAuth } from "src/components/contexts/auth";

import "react-toastify/dist/ReactToastify.css";
export default function Login() {
  const { login } = useAuth();
  const submitHandler = (event) => {
    event.preventDefault();
    let user = {
      email: event.target.email.value,
      password: event.target.password.value,
    };
    login(user);
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
              <Form inverted onSubmit={submitHandler}>
                <Grid>
                  <Grid.Row>
                    <Grid.Column width={11}>
                      <Input
                        transparent
                        required
                        inverted
                        name="email"
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
                        name="password"
                        icon="lock"
                        className="textcolor"
                      />
                    </Grid.Column>
                  </Grid.Row>
                  <Grid.Row>
                    <Grid.Column width={3}></Grid.Column>
                    <Grid.Column width={10}>
                      <Button type="submit" className="submitButton" fluid>
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
