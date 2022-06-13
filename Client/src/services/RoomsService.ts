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
            roomNumber: "",
            roomTypeId: 0,
            status: 0,
        };
        return tempRoom;
    }

    public static getBlankRoomSelectTemplate(): IRoomSelect {
        const tempRoomSelect: IRoomSelect = {
            id: 0,
            roomLabel: "",
        };
        return tempRoomSelect
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
    public static async delete(id: string): Promise<IRoom> {
        const token = localStorage.getItem('token');
        return (await axios.delete(options.apiUrl + `room/${id}`,{ headers:{
            "Authorization": `${token}`
        }})).data;
    }

    public static async create(room: IRoom): Promise<IRoom> {
        const token = localStorage.getItem('token');
        return (await axios.post<IRoom>(options.apiUrl + `room`, room, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async getSelectList(): Promise<Array<IRoomSelect>> {
        const token = localStorage.getItem('token');
        return (await axios.get<Array<IRoomSelect>>(options.apiUrl + `room/select-list`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async getAvailableList(startDate: Date, endDate: Date, roomTypeId: string) {
        return (await axios.get<Array<IRoom>>(options.apiUrl + `room/available?endDate=${endDate}&roomTypeId=${roomTypeId}&startDate=${startDate}`)).data;
    }
}

export interface IRoom {
    id: number;
    roomNumber: string;
    roomTypeId: number;
    status: number;
}

export interface IRoomSelect {
    id: number;
    roomLabel: string;
}