import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";
import options from "../../spurvago.config.json";

export default class RoomTypesServices {

    public static getBlankRoomTypeTemplate(): IRoomType {
        const tempRoom: IRoomType = {
            id: 0,
            type: "",
            price: 0,
        };
        return tempRoom;
    }

    public static async fetch(id: number): Promise<IRoomType> {
        const token = localStorage.getItem('token');
        return (await axios.get<IRoomType>(options.apiUrl + `room_type/${id}`, {
            headers: {
                'Authorization': `${token}`
            }
        })).data;
    }

    public static async create(room: IRoomType): Promise<IRoomType> {
        const token = localStorage.getItem('token');
        return (await axios.post(options.apiUrl + `room_type`, room, {
            headers: {
                'Authorization': `${token}`
            }
        })).data;
    }

    public static async update(id: string, room: IRoomType): Promise<IRoomType> {
        const token = localStorage.getItem('token');
        return (await axios.put<IRoomType>(options.apiUrl + `room_type/${id}`, room, {
            headers: {
                'Authorization': `${token}`
            }
        })).data;
    }

    public static async getList(pager: IPager): Promise<IList<IRoomType>> {
        const token = localStorage.getItem('token');
        return (await axios.get<IList<IRoomType>>(options.apiUrl + `room_type`, {
            params: pager, headers: {
                "Authorization": `${token}`
            }
        })).data;
    }

    public static async delete(id: string): Promise<IRoomType> {
        const token = localStorage.getItem('token');
        return (await axios.delete(options.apiUrl + `room_type/${id}`, {
            headers: {
                "Authorization": `${token}`
            }
        })).data;
    }
}

export interface IRoomType {
    id: number;
    type: string;
    price: number;
}