import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";
import options from "../../spurvago.config.json";

export default class RoomTypesServices {

    public static getBlankRoomTypeTemplate(): IRoomType {
        const tempRoom: IRoomType = {
            id: 0,
            roomType: "",
            price: 0,
        };
        return tempRoom;
    }

    public static async fetch(id: number): Promise<IRoomType> {
        return (await axios.get<IRoomType>(options.apiUrl + `room_type/${id}`)).data;
    }

    public static async update(id: string, room: IRoomType): Promise<IRoomType> {
        return (await axios.put<IRoomType>(options.apiUrl + `room_type/${id}`, room)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IRoomType>> {
        return (await axios.get<IList<IRoomType>>(options.apiUrl + `room_type`, { params: pager })).data;
    }  
}

export interface IRoomType {
    id: number;
    roomType: string;
    price: number;
}