import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class MaidTicketServices {
    public static getBlankMaidTicketTemplate(): IMaid {
        const tempMaid: IMaid = {
            id: 0,
            room_number: 0,
            employee_id: 0,
            employee_firstName: "",
            employee_lastName: "",
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
    room_number: number;
    finalization_date?: Date;
    employee_id: number;
    employee_firstName: string;
    employee_lastName: string;
}