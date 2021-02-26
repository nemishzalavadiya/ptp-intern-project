function userLogin(email,password){
    let options = {
        method:"POST",
        body : JSON.stringify({
            email:email,
            password:password
        }),
        header:{
            'Content-Type':'application/json;charset=utf-8',
        }
    }
    [isCompleted,data] = useFetch("/api/login",options);
    return [isCompleted,data]
}