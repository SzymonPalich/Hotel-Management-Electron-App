export default class EmployeeService {

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