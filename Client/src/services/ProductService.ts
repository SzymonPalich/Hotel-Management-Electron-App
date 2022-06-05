import Utils, { IList, IPager } from "@/Utils";
import axios from "axios";
import options from '../../spurvago.config.json';

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
        return (await axios.get<IProduct>(options.apiUrl + `product/${id}`)).data;
    }

    public static async update(id: string, repair: IProduct): Promise<IProduct> {
        return (await axios.put<IProduct>(options.apiUrl + `product/${id}`, repair)).data;
    }

    public static async getList(pager: IPager): Promise<IList<IProduct>> {
        return (await axios.get<IList<IProduct>>(options.apiUrl + `product`, { params: pager })).data;
    }
    
    public static async delete(id: string): Promise<IProduct> {
        return (await axios.delete(options.apiUrl + `product/${id}`)).data;
    }

    public static async create(product: IProduct): Promise<IProduct> {
        return (await axios.post<IProduct>(options.apiUrl + `product`, product)).data;
    }
}

export interface IProduct {
    id: number;
    product_name: string;
    retail_price: number;
    wholesale_price: number;
}