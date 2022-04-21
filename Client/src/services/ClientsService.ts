import axios from "axios";

export default class ClientsServices {
    public static getTempClient(): IClient {
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
        return (await axios.get<IClient>(`http://localhost:8081/api/client/${id}`)).data;
    }
}

export interface IClient {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string; 
}