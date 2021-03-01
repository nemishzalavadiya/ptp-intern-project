import { useFetch } from "src/hooks/useFetch";
function authenticateUser(email, password,validate) {
  let options = {
    method: "POST",
    body: JSON.stringify({
      email: email,
      password: password,
    }),
    headers:{
        "Content-Type":"application/json"
    }
  };
  const [isCompleted, data,error] = useFetch(validate?"/api/login":null, options);
  return [isCompleted, data,error];
}

export default authenticateUser;
