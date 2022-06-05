import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";
import options from '../../spurvago.config.json';

export default class ClientsServices {
    public static getBlankClientTemplate(): IClient {
        const tempClient: IClient = {
            id: 0,
            firstName: "",
            lastName: "",
            email: "",
            phoneNumber: "",
        };
        return tempClient;
    }

    public static async fetch(id: string): Promise<IClient> {
        return (await axios.get<IClient>(options.apiUrl + `client/${id}`)).data;
    }

    public static async update(id: string, client: IClient): Promise<IClient> {
        return (await axios.put<IClient>(options.apiUrl + `client/${id}`, client)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IClient>> {
        return (await axios.get<IList<IClient>>(options.apiUrl + `client`, { params: pager })).data;
    }

    public static async create(client: IClient): Promise<IClient> {
        return (await axios.post<IClient>(options.apiUrl + `client`, client)).data;
    }

    public static async delete(id: string): Promise<IClient> {
        return (await axios.delete<IClient>(options.apiUrl + `client/${id}`)).data;
    }

}

export interface IClient {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
}