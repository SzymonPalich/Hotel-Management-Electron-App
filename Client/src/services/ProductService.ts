import Utils, {IList, IPager} from "@/Utils";
import axios from "axios";

export default class RepairService {
    public static getBlankProductTemplate(): IProduct {
        const tempProduct: IProduct = {
            id: 0,
            product_name: "",
            retail_price: 0,
            wholesale_price: 0,
        };
        return tempProduct;
    }
    
    public static async fetch(id: string): Promise<IProduct> {
        return (await axios.get<IProduct>(`http://localhost:8081/api/product/${id}`)).data;
    }

    public static async update(id: string, repair: IProduct): Promise<IProduct> {
        return (await axios.put<IProduct>(`http://localhost:8081/api/repairs/product/${id}`, repair)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IProduct>> {
        return (await axios.get<IList<IProduct>>(`http://localhost:8081/api/product`, { params: pager })).data;
    }  
}

export interface IProduct {
    id: number;
    product_name: string;
    retail_price: number;
    wholesale_price: number;
}