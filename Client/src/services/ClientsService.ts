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
        const token = localStorage.getItem('token');
        return (await axios.get<IClient>(options.apiUrl + `client/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
        }

        public static async update(id: string, client: IClient): Promise<IClient> {
            const token = localStorage.getItem('token');
            return (await axios.put<IClient>(options.apiUrl + `client/${id}`, client, {
                headers: {
                  'Authorization': `${token}` 
                }
              })).data;
        }

        public static async getList(pager: IPager): Promise<IList<IClient>> {    
            const token = localStorage.getItem('token');
            return (await axios.get<IList<IClient>>(options.apiUrl + `client`, { params: pager, headers:{
                "Authorization": `${token}`
            }})).data;
        }

        public static async create(client: IClient): Promise<IClient> {
            const token = localStorage.getItem('token');
        return (await axios.post<IClient>(options.apiUrl + `client`, client, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async delete(id: string): Promise<IClient> {
        const token = localStorage.getItem('token');
        return (await axios.delete<IClient>(options.apiUrl + `client/${id}`, {
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