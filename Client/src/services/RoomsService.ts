import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class RoomsServices {
    public static setStatus(room_status: number): string {
        switch (room_status) {
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
            room_status: 0,
        };
        return tempRoom;
    }

    public static async fetch(id: string): Promise<IRoom> {
        return (await axios.get<IRoom>(`http://localhost:8081/api/rooms/${id}`)).data;
    }

    public static async update(id: string, room: IRoom): Promise<IRoom> {
        return (await axios.put<IRoom>(`http://localhost:8081/api/rooms/${id}`, room)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IRoom>> {
        return (await axios.get<IList<IRoom>>(`http://localhost:8081/api/rooms`, { params: pager })).data;
    }  
}

export interface IRoom {
    id: number;
    room_number: number;
    room_type: string;
    room_status: number;
}