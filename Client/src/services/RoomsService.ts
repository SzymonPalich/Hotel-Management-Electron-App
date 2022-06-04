import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

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

    public static async fetch(id: string): Promise<IRoom> {
        return (await axios.get<IRoom>(`http://localhost:8081/api/room/${id}`)).data;
    }

    public static async update(id: string, room: IRoom): Promise<IRoom> {
        return (await axios.put<IRoom>(`http://localhost:8081/api/room/${id}`, room)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IRoom>> {
        return (await axios.get<IList<IRoom>>(`http://localhost:8081/api/room`, { params: pager })).data;
    } 

    public static async delete(id: string): Promise<IRoom> {
        return (await axios.delete(`http://localhost:8081/api/room/${id}`)).data;
    }

    public static async create(room: IRoom): Promise<IRoom> {
        return (await axios.post<IRoom>(`http://localhost:8081/api/room`, room)).data;
    }
}

export interface IRoom {
    id: number;
    roomNumber: string;
    roomTypeId: number;
    status: number;
}