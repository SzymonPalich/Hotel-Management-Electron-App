import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";

export default class LoginServices {
    public static getBlankLoginTemplate(): ILogin {
        const tempLogin: ILogin = {
            email: "",
            password: "",
        };
        return tempLogin;
    }

    public static async fetch(login: ILogin): Promise<ILogin> {
        return (await axios.post<ILogin>(`http://localhost:8081/api/auth/login`, login)).data;
    }

}

export interface ILogin {
    email: string;
    password: string;
}