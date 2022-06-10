import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";
import options from '../../spurvago.config.json';

export default class AccommodationServices {
    public static getBlankAccommodationTemplate(): IAccommodation {
        const tempClient: IAccommodation = {
            id: 0,
            clientId: 0,
            clientFirstName: "",
            clientLastName: "",
            roomId: 0,
            roomNumber: 0,
            roomType: "",
            roomStatus: 0,
            startDate: new Date(),
            endDate: new Date(),
            reservationOnly: false,
        };
        return tempClient;
    }

    public static async fetch(id: string): Promise<IAccommodation> {
        return (await axios.get<IAccommodation>(options.apiUrl + `accommodation/${id}`)).data;
    }

    public static async update(id: string, accommodation: IAccommodation): Promise<IAccommodation> {
        return (await axios.put<IAccommodation>(options.apiUrl + `accommodation/${id}`, accommodation)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IAccommodation>> {
        return (await axios.get<IList<IAccommodation>>(options.apiUrl + `accommodation`, { params: pager })).data;
    }

    public static async create(accommodation: IAccommodation): Promise<IAccommodation> {
        return (await axios.post<IAccommodation>(options.apiUrl + `accommodation`, accommodation)).data;
    }

    public static async delete(id: string): Promise<IAccommodation> {
        return (await axios.delete<IAccommodation>(options.apiUrl + `accommodation/${id}`)).data;
    }

}

export interface IAccommodation {
    id: number;
    clientId: number;
    clientFirstName: string;
    clientLastName: string;
    roomId: number;
    roomNumber: number;
    roomType: string;
    roomStatus: number;
    startDate: Date;
    endDate: Date;
    reservationOnly: boolean;
}