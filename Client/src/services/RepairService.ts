import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";
import options from '../../spurvago.config.json';

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
        const token = localStorage.getItem('token');
        return (await axios.get<IRepair>(options.apiUrl + `maintenance_ticket/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async update(id: string, repair: IRepair): Promise<IRepair> {
        const token = localStorage.getItem('token');
        return (await axios.put<IRepair>(options.apiUrl + `repairs/maintenance_ticket/${id}`, repair, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async getList(pager: IPager): Promise<IList<IRepair>> {
        const token = localStorage.getItem('token');
        return (await axios.get<IList<IRepair>>(options.apiUrl + `maintenance_ticket`, { params: pager, headers:{
            "Authorization": `${token}`
        }})).data;
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