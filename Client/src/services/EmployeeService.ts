import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class EmployeeServices {
    public static getBlankEmployeeTemplate(): IEmployee {
        const tempEmployee: IEmployee = {
            id: 0,
            name: "",
            surname: "",
            position: "",
            salary: 0,
            email: "",
            phone_number: "",
            pesel: "",
            employment_date: new Date("2000-02-02"),
            dismissal_date: undefined,
        };
        return tempEmployee;
    }

    public static async fetch(id: string): Promise<IEmployee> {
        return (await axios.get<IEmployee>(`http://localhost:8081/api/employee/${id}`)).data;
    }

    public static async update(id: string, employee: IEmployee): Promise<IEmployee> {
        return (await axios.put<IEmployee>(`http://localhost:8081/api/employee/${id}`, employee)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IEmployee>> {
        return (await axios.get<IList<IEmployee>>(`http://localhost:8081/api/employee`, { params: pager })).data;
    }  
}

export interface IEmployee {
    id: number;
    name: string;
    surname: string;
    position: string;
    salary: number;
    email: string;
    phone_number: string;
    pesel: string;
    employment_date: Date;
    dismissal_date?: Date;
}