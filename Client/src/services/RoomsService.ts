import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";
import options from '../../spurvago.config.json';

export default class RoomsServices {
    public static setStatus(status: number): string {
        switch (status) {
            case 1: {
                return "Wolny";
            }
            case 2: {
                return "Zajęty";
            }
            case 3: {
                return "Rezerwacja";
            }
            case 4: {
                return "Sprzątanie";
            }
            case 5: {
                return "Usterka";
            }
            case 6: {
                return "Zablokowany";
            }
            default: {
                return "Błąd";
            }
        }
    }

    public static getBlankRoomTemplate(): IRoom {
        const tempRoom: IRoom = {
            id: 0,
            room_number: 0,
            room_type: "",
            status: 0,
        };
        return tempRoom;
    }

    public static async fetch(id: string): Promise<IRoom> {
        const token = localStorage.getItem('token');
        return (await axios.get<IRoom>(options.apiUrl + `room/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async update(id: string, room: IRoom): Promise<IRoom> {
        const token = localStorage.getItem('token');
        return (await axios.put<IRoom>(options.apiUrl + `room/${id}`, room, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async getList(pager: IPager): Promise<IList<IRoom>> {
        const token = localStorage.getItem('token');
        return (await axios.get<IList<IRoom>>(options.apiUrl + `room`, { params: pager, headers:{
            "Authorization": `${token}`
        }})).data;
    }  
}

export interface IRoom {
    id: number;
    room_number: number;
    room_type: string;
    status: number;
}