import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class MaidTicketServices {
    public static getBlankMaidTicketTemplate(): IMaid {
        const tempMaid: IMaid = {
            id: 0,
            roomId: 0,
            roomNumber: 0,
            employeeId: 0,
            employeeFirstName: "",
            employeeLastName: "",
        };
        return tempMaid;
    }


    public static async fetch(id: string): Promise<IMaid> {
        const token = localStorage.getItem('token');
        return (await axios.get<IMaid>(`http://localhost:8081/api/maid_ticket/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async update(id: string, maid: IMaid): Promise<IMaid> {
        const token = localStorage.getItem('token');
        return (await axios.put<IMaid>(`http://localhost:8081/api/maid_ticket/${id}`, maid, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async getList(pager: IPager): Promise<IList<IMaid>> {
        const token = localStorage.getItem('token');
        return (await axios.get<IList<IMaid>>(`http://localhost:8081/api/maid_ticket`, { params: pager , headers:{
            "Authorization": `${token}`
        }})).data;
    }

    public static async delete(id: string): Promise<IMaid> {
        const token = localStorage.getItem('token');
        return (await axios.delete(`http://localhost:8081/api/maid_ticket/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async create(maid: IMaid): Promise<IMaid> {
        const token = localStorage.getItem('token');
        return (await axios.post<IMaid>(`http://localhost:8081/api/maid_ticket`, maid, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }
}

export interface IMaid {
    id: number;
    roomId: number;
    roomNumber: number;
    finalizationDate?: Date;
    employeeId: number;
    employeeFirstName?: string;
    employeeLastName?: string;
}