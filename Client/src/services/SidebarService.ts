import Utils, { IList, IPager } from "@/Utils";
import axios, { AxiosResponse, AxiosResponseHeaders } from "axios";


export default class SidebarServices{

    public static getBlankUserTemplate(): IUser {
        const tempClient: IUser = {
            id: 0,
            firstName: "",
            lastName: "",
            email: "",
            role: "",
        };
        return tempClient;
    }

    public static async getList(): Promise<IUser> {    
        const token = localStorage.getItem('token');
        return (await axios.get<IUser>(`http://localhost:8081/api/auth/user`, { headers:{
            "Authorization": `${token}`
        }})).data;
    }

}

export interface IUser {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    role: string;
}