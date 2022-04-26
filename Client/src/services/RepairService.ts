import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class RepairService {
    public static getBlankRepairTemplate(): IRepair {
        const tempRepair: IRepair = {
            id: 0,
            issue: "",
            room_nr: 0,
            desc: "",
            report: "",
            parts_price: 0,
            finalization_date: undefined,
        };
        return tempRepair;
    }
    
    public static async fetch(id: string): Promise<IRepair> {
        return (await axios.get<IRepair>(`http://localhost:8081/api/repairs/${id}`)).data;
    }

    public static async update(id: string, repair: IRepair): Promise<IRepair> {
        return (await axios.put<IRepair>(`http://localhost:8081/api/repairs/${id}`, repair)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IRepair>> {
        return (await axios.get<IList<IRepair>>(`http://localhost:8081/api/repairs`, { params: pager })).data;
    }  
}

export interface IRepair {
    id: number;
    issue: string;
    room_nr: number;
    desc: string;
    report?: string;
    parts_price?: number;
    finalization_date?: Date;
}