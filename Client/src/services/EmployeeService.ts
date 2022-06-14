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

    public static getBlankEmployeeSelectTemplate(): IEmployeeSelect {
        const tempEmployee: IEmployeeSelect = {
            id: 0,
            label: ""
        };
        return tempEmployee;
    }

    public static async fetch(id: string): Promise<IEmployee> {
        const token = localStorage.getItem('token');
        return (await axios.get<IEmployee>(options.apiUrl + `employee/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async update(id: string, employee: IEmployee): Promise<IEmployee> {
        const token = localStorage.getItem('token');
        return (await axios.put<IEmployee>(options.apiUrl + `employee/${id}`, employee, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async getList(pager: IPager): Promise<IList<IEmployee>> {
        const token = localStorage.getItem('token');
        return (await axios.get<IList<IEmployee>>(options.apiUrl + `employee`, { params: pager, headers:{
            "Authorization": `${token}`
        }})).data;
    }

    public static async getSelectList(): Promise<Array<IEmployeeSelect>> {
        const token = localStorage.getItem('token');
        return (await axios.get<Array<IEmployeeSelect>>(options.apiUrl + `employee/select-list`, { headers:{
            "Authorization": `${token}`
        }})).data;
    }

    public static async getEmployeesByPosition(pager: IPager, position: string): Promise<IList<IEmployee>> {
        const token = localStorage.getItem('token');
        return (await axios.get<IList<IEmployee>>(options.apiUrl + `employee/position?position=${position}`, { params: pager , headers:{
            "Authorization": `${token}`
        }})).data;
    }  

    public static async getEmployeesByPositionSelect(position: string): Promise<Array<IEmployeeSelect>> {
        const token = localStorage.getItem('token');
        return (await axios.get<Array<IEmployeeSelect>>(options.apiUrl + `employee/select-list/position?position=${position}`, { headers:{
            "Authorization": `${token}`
        }})).data;
    }

    public static async delete(id: string): Promise<IEmployee> {
        const token = localStorage.getItem('token');
        return (await axios.delete(options.apiUrl + `employee/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async create(employee: IEmployee): Promise<IEmployee> {
        const token = localStorage.getItem('token');
        return (await axios.post(options.apiUrl + `employee`, employee, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
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

export interface IEmployeeSelect {
    id: number;
    label: string;
}