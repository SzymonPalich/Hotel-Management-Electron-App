import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class EmployeeServices {
    public static setPosition(position: number): string {
        switch (position) {
            case 1: {
                return "Manager";
            }
            case 2: {
                return "Sprzątacz";
            }
            case 3: {
                return "Technik";
            }
            case 4: {
                return "Recepcjonista";
            }
            default: {
                return "Błąd";
            }
        }
    }

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

    public static async getEmployeesByPosition(pager: IPager, position: string): Promise<IList<IEmployee>> {
        return (await axios.get<IList<IEmployee>>(`http://localhost:8081/api/employee/position?position=${position}`, { params: pager })).data;
    }  

    public static async delete(id: string): Promise<IEmployee> {
        return (await axios.delete(`http://localhost:8081/api/employee/${id}`)).data;
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