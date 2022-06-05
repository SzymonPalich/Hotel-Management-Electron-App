import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";
import options from '../../spurvago.config.json';

export default class EmployeeServices {
    public static setPosition(position: string): string {
        switch (position) {
            case "ROLE_MANAGER": {
                return "Manager";
            }
            case "ROLE_MAID": {
                return "Sprzątacz";
            }
            case "ROLE_TECHNICIAN": {
                return "Technik";
            }
            case "ROLE_RECEPTIONIST": {
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
            firstName: "",
            lastName: "",
            password: "",
            position: "",
            salary: 0,
            email: "",
            phoneNumber: "",
            pesel: "",
            employmentDate: new Date("2000-02-02"),
            dismissalDate: undefined,
        };
        return tempEmployee;
    }

    public static async fetch(id: string): Promise<IEmployee> {
        return (await axios.get<IEmployee>(options.apiUrl + `employee/${id}`)).data;
    }

    public static async update(id: string, employee: IEmployee): Promise<IEmployee> {
        return (await axios.put<IEmployee>(options.apiUrl + `employee/${id}`, employee)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IEmployee>> {
        return (await axios.get<IList<IEmployee>>(options.apiUrl + `employee`, { params: pager })).data;
    }

    public static async getEmployeesByPosition(pager: IPager, position: string): Promise<IList<IEmployee>> {
        return (await axios.get<IList<IEmployee>>(options.apiUrl + `employee/position?position=${position}`, { params: pager })).data;
    }

    public static async delete(id: string): Promise<IEmployee> {
        return (await axios.delete(options.apiUrl + `employee/${id}`)).data;
    }

    public static async create(employee: IEmployee): Promise<IEmployee> {
        return (await axios.post(options.apiUrl + `employee`, employee)).data;
    }
}

export interface IEmployee {
    id: number;
    firstName: string;
    lastName: string;
    password: string;
    position: string;
    salary: number;
    email: string;
    phoneNumber: string;
    pesel: string;
    employmentDate: Date;
    dismissalDate?: Date;
}