import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class RepairService {
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
            technicianReport: "",
            finalizationDate: undefined,
        };
        return tempRepair;
    }
    
    public static async fetch(id: string): Promise<IRepair> {
        return (await axios.get<IRepair>(`http://localhost:8081/api/maintenance_ticket/${id}`)).data;
    }

    public static async update(id: string, repair: IRepair): Promise<IRepair> {
        return (await axios.put<IRepair>(`http://localhost:8081/api/maintenance_ticket/${id}`, repair)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IRepair>> {
        return (await axios.get<IList<IRepair>>(`http://localhost:8081/api/maintenance_ticket`, { params: pager })).data;
    } 

    public static async delete(id: string): Promise<IRepair> {
        return (await axios.delete(`http://localhost:8081/api/maintenance_ticket/${id}`)).data;
    }

    public static async create(repair: IRepair): Promise<IRepair> {
        return (await axios.post<IRepair>(`http://localhost:8081/api/maintenance_ticket`, repair)).data;
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