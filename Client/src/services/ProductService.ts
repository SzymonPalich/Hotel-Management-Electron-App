import Utils, { IList, IPager } from "@/Utils";
import axios, { AxiosResponse } from "axios";
import options from '../../spurvago.config.json';

export default class ProductServices {
    public static getBlankProductTemplate(): IProduct {
        const tempProduct: IProduct = {
            id: 0,
            productName: "",
            retailPrice: 0,
            wholesalePrice: 0,
            productAmount: 0
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

    public static async create(product: IProduct): Promise<AxiosResponse> {
        return (await axios.post<IProduct>(options.apiUrl + `product`, product).then((response) => (response)));
    }

    public static async getList2(): Promise<Array<IProduct>> {
        return (await axios.get<Array<IProduct>>(options.apiUrl + `product/select`)).data;
    }

    public static async getAll(): Promise<Array<IProduct>> {
        return (await axios.get<Array<IProduct>>(options.apiUrl+`product/select`)).data;
    }
}

export interface IProduct {
    id: number;
    productName: string;
    retailPrice: number;
    wholesalePrice: number;
    productAmount: number;
}