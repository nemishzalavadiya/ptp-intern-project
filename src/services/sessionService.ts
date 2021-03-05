import showToast from "src/components/showToast";
import Cookies from "js-cookie";

interface IsessionService {
    login: (user: any) => Promise<string>;
    logout: () => void;
    user: () => Promise<string>;
}

let sessionService: IsessionService = {
    user: async () => {
        let response = await fetch("/api/user");
        let userId = await response.text();
        if (response.ok) {
            //will expire in half a day
            Cookies.set("userId", userId, { expires: 0.5 });
        }
        return userId;
    },
    login: async (userDetail) => {
        let options = {
            method: "POST",
            body: JSON.stringify(userDetail),
            headers: {
                "Content-Type": "application/json",
            },
        };
        let userId = null;
        const response = await fetch("api/login", options);
        if (response.ok) {
            userId = await sessionService.user();
            showToast("Logged in successfully");
        } else {
            showToast("Username or Password is incorrect");
        }
        return userId;
    },

    logout: async () => {
        Cookies.remove("userId");
        let response = await fetch("/api/logout", { method: "POST" });
        if (response.ok) {
            showToast("Logged out successfully");
        } else {
            showToast("Something went wrong, try again later");
        }
    }
}

export default sessionService;



