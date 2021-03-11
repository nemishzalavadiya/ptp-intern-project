import {
  Divider,
  Input,
  Image,
  Button,
  Form,
  Grid,
  Segment,
  Icon,
} from "semantic-ui-react";
import Link from "next/link";
import { ToastContainer } from "react-toastify";
import useAuth from "src/components/contexts/useAuth";
import { useState } from "react";
import { useRouter } from "next/router";
import "react-toastify/dist/ReactToastify.css";
export default function Login() {
  const [showPassword, setShowPassword] = useState(false);  
  const { login } = useAuth();
  const router = useRouter();
  const submitHandler = async (event) => {
    event.preventDefault();
    let user = {
      email: event.target.email.value,
      password: event.target.password.value,
    };
    let userInfo = await login(user);
    if (userInfo) {
      if (router.query.path === undefined) {
        router.replace("/");
      } else {
        router.replace(router.query.path.toString());
      }
    }
  };
  const iconClickHandler = () => {
    setShowPassword(!showPassword);
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
                        type={showPassword ? "text" : "password"}
                        iconPosition="left"
                        placeholder="******"
                        name="password"
                        icon={
                          <Icon
                            name={showPassword ? "lock open" : "lock"}
                            link
                            onClick={iconClickHandler}
                          />
                        }
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
                  <Grid.Row>
                    <Grid.Column width={3}></Grid.Column>
                    <Grid.Column width={10} >
                      <Link href="/register">                     Not Registered?   Create Account
                      </Link>

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
