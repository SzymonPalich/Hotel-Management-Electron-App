import Utils, { IList, IPager } from "@/Utils";
import axios, { AxiosResponse, AxiosResponseHeaders } from "axios";
import options from '../../spurvago.config.json';
export default class LoginServices {
    public static getBlankLoginTemplate(): ILogin {
        const tempLogin: ILogin = {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            role: ""
        };
        return tempLogin;
    }

    public static async login(login: ILogin): Promise<AxiosResponse> {
        let responseRequest;
        if((responseRequest = await axios.post<ILogin>(options.apiUrl + `login`, login))!= undefined){
            localStorage.setItem('token', responseRequest.headers.authorization);
            return responseRequest;
        }
        return responseRequest;
    }

    public static async fetch(): Promise<ILogin> {
        const token = localStorage.getItem('token');
        return (await axios.get<ILogin>(options.apiUrl + `auth/user`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

}

export interface ILogin {
    firstName: string;
    lastName: string
    email: string;
    password: string;
    role: string;
}