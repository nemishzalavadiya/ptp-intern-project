import showToast from "src/components/showToast";

interface IsessionService {
    login: (user: any) => Promise<string>;
    logout: () => void;
    user: () => Promise<string>;
}

let sessionService: IsessionService = {
    user: async () => {
        let response = await fetch("/api/user");
        let userInfo = await response.json();
        if (response.ok) {
            return userInfo;
        }
        return null;
    },
    login: async (userDetail) => {
        let options = {
            method: "POST",
            body: JSON.stringify(userDetail),
            headers: {
                "Content-Type": "application/json",
            },
        };
        let userInfo = null;
        const response = await fetch("api/login", options);
        if (response.ok) {
            userInfo = await sessionService.user();
            showToast("Logged in successfully");
        } else {
            showToast("Username or Password is incorrect",true);
        }
        return userInfo;
    },

    logout: async () => {
        let response = await fetch("/api/logout", { method: "POST" });
        if (response.ok) {
            showToast("Logged out successfully");
        } else {
            showToast("Something went wrong, try again later");
        }
    }
}

export default sessionService;



