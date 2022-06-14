import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";
import options from '../../spurvago.config.json';

export default class RepairServices {
    public static getBlankRepairTemplate(): IRepair {
        const tempRepair: IRepair = {
            id: 0,
            roomId: 0,
            roomNumber: 0,
            roomType: "",
            employeeId: undefined,
            employeeFristName: "",
            employeeLastName: "",
            name: "",
            description: "",
            partsPrice: undefined,
            technicianReport: undefined,
            finalizationDate: undefined,
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
        return (await axios.put<IRepair>(options.apiUrl + `maintenance_ticket/${id}`, repair, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async delete(id: string): Promise<IRepair> {
        const token = localStorage.getItem('token');
        return (await axios.delete(options.apiUrl + `maintenance_ticket/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async create(repair: IRepair): Promise<IRepair> {
        const token = localStorage.getItem('token');
        return (await axios.post<IRepair>(options.apiUrl + `maintenance_ticket`, repair, {
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
    roomId: number;
    roomNumber: number;
    roomType: string;
    employeeId?: number;
    employeeFristName: string;
    employeeLastName: string;
    name: string;
    description: string;
    partsPrice?: number;
    technicianReport?: string;
    finalizationDate?: Date;
}