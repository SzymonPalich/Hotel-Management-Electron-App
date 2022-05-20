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
        return (await axios.get<IMaid>(`http://localhost:8081/api/maid_ticket/${id}`)).data;
    }

    public static async update(id: string, maid: IMaid): Promise<IMaid> {
        return (await axios.put<IMaid>(`http://localhost:8081/api/maid_ticket/${id}`, maid)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IMaid>> {
        return (await axios.get<IList<IMaid>>(`http://localhost:8081/api/maid_ticket`, { params: pager })).data;
    }  
}

export interface IMaid {
    id: number;
    roomId: number;
    roomNumber: number;
    finalization_date?: Date;
    employeeId: number;
    employeeFirstName: string;
    employeeLastName: string;
}