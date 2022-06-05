import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";
import options from '../../spurvago.config.json';

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
        return (await axios.get<IMaid>(options.apiUrl + `maid_ticket/${id}`)).data;
    }

    public static async update(id: string, maid: IMaid): Promise<IMaid> {
        return (await axios.put<IMaid>(options.apiUrl + `maid_ticket/${id}`, maid)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IMaid>> {
        return (await axios.get<IList<IMaid>>(options.apiUrl + `maid_ticket`, { params: pager })).data;
    }

    public static async delete(id: string): Promise<IMaid> {
        return (await axios.delete(options.apiUrl + `maid_ticket/${id}`)).data;
    }

    public static async create(maid: IMaid): Promise<IMaid> {
        return (await axios.post<IMaid>(options.apiUrl + `maid_ticket`, maid)).data;
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