import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";

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
        const token = localStorage.getItem('token');
        return (await axios.get<IClient>(`http://localhost:8081/api/client/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
        }

        public static async update(id: string, client: IClient): Promise<IClient> {
            const token = localStorage.getItem('token');
            return (await axios.put<IClient>(`http://localhost:8081/api/client/${id}`, client, {
                headers: {
                  'Authorization': `${token}` 
                }
              })).data;
        }

        public static async getList(pager: IPager): Promise<IList<IClient>> {    
            const token = localStorage.getItem('token');
            return (await axios.get<IList<IClient>>(`http://localhost:8081/api/client`, { params: pager, headers:{
                "Authorization": `${token}`
            }})).data;
        }

        public static async create(client: IClient): Promise<IClient> {
            const token = localStorage.getItem('token');
        return (await axios.post<IClient>(`http://localhost:8081/api/client`, client, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async delete(id: string): Promise<IClient> {
        const token = localStorage.getItem('token');
        return (await axios.delete<IClient>(`http://localhost:8081/api/client/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

}

export interface IClient {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
}