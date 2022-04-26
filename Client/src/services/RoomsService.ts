import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class RoomsService {
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
}

export interface IRoom {
    id: number;
    room_number: number;
    room_type: string;
    room_status: number;
}