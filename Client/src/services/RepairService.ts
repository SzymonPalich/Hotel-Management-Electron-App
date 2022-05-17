import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class RepairService {
    public static getBlankRepairTemplate(): IRepair {
        const tempRepair: IRepair = {
            id: 0,
            room_number: 0,
            room_type: "",
            employee_fristName: "",
            employee_lastName: "",
            name: "",
            description: "",
            parts_price: 0,
            technician_report: "",
            finalization_date: undefined,
        };
        return tempRepair;
    }
    
    public static async fetch(id: string): Promise<IRepair> {
        return (await axios.get<IRepair>(`http://localhost:8081/api/maintenance_ticket/${id}`)).data;
    }

    public static async update(id: string, repair: IRepair): Promise<IRepair> {
        return (await axios.put<IRepair>(`http://localhost:8081/api/repairs/maintenance_ticket/${id}`, repair)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IRepair>> {
        return (await axios.get<IList<IRepair>>(`http://localhost:8081/api/maintenance_ticket`, { params: pager })).data;
    }  
}

export interface IRepair {
    id: number;
    room_number: number;
    room_type: string;
    employee_fristName: string;
    employee_lastName: string;
    name: string;
    description: string;
    parts_price?: number;
    technician_report?: string;
    finalization_date?: Date;
}