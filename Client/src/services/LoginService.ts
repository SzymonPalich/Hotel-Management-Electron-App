import Utils, { IList, IPager } from "@/Utils";
import axios, { AxiosResponse, AxiosResponseHeaders } from "axios";

export default class LoginServices {
    public static getBlankLoginTemplate(): ILogin {
        const tempLogin: ILogin = {
            email: "",
            password: "",
        };
        return tempLogin;
    }

    public static async fetch(login: ILogin): Promise<AxiosResponse> {
        let responseRequest;
        if((responseRequest = await axios.post<ILogin>(`http://localhost:8081/api/auth/login`, login))!= undefined){
            localStorage.setItem('token', responseRequest.headers.authorization);
            return responseRequest;
        }
        return responseRequest;
    }

}

export interface ILogin {
    email: string;
    password: string;
}