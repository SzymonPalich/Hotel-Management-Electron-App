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
            startDate: new Date(),
            endDate: new Date(),
            reservationOnly: false,
        };
        return tempClient;
    }

    public static async fetch(id: string): Promise<IAccommodation> {
        const token = localStorage.getItem('token');
        return (await axios.get<IAccommodation>(options.apiUrl + `accommodation/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async update(id: string, accommodation: IAccommodation): Promise<IAccommodation> {
        const token = localStorage.getItem('token');
        return (await axios.put<IAccommodation>(options.apiUrl + `accommodation/${id}`, accommodation, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async getList(pager: IPager): Promise<IList<IAccommodation>> {
        const token = localStorage.getItem('token');
        return (await axios.get<IList<IAccommodation>>(options.apiUrl + `accommodation`, { params: pager, headers:{
            "Authorization": `${token}`
        }})).data;
    }

    public static async create(accommodation: IAccommodation): Promise<IAccommodation> {
        const token = localStorage.getItem('token');
        return (await axios.post<IAccommodation>(options.apiUrl + `accommodation`, accommodation, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async delete(id: string): Promise<IAccommodation> {
        const token = localStorage.getItem('token');
        return (await axios.delete<IAccommodation>(options.apiUrl + `accommodation/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async invoice(id: string): Promise<void> {
    const token = localStorage.getItem('token');
      return (await axios.put<void>(options.apiUrl + `accommodation/${id}/invoice`,{}, {
        headers: {
          'Authorization': `${token}` 
        }
      })).data;
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
    startDate: Date;
    endDate: Date;
    reservationOnly: boolean;
}