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
        const token = localStorage.getItem('token');
        return (await axios.get<IProduct>(options.apiUrl + `product/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async update(id: string, repair: IProduct): Promise<IProduct> {
        const token = localStorage.getItem('token');
        return (await axios.put<IProduct>(options.apiUrl + `repairs/product/${id}`, repair, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async getList(pager: IPager): Promise<IList<IProduct>> {
        const token = localStorage.getItem('token');
        return (await axios.get<IList<IProduct>>(options.apiUrl + `product`, { params: pager , headers:{
            "Authorization": `${token}`
        }})).data;
    }  
    
    public static async delete(id: string): Promise<IProduct> {
        const token = localStorage.getItem('token');
        return (await axios.delete(options.apiUrl + `product/${id}`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async create(product: IProduct): Promise<AxiosResponse> {
         const token = localStorage.getItem('token');
        return (await axios.post<IProduct>(options.apiUrl + `product`, product, {headers: {
            'Authorization': `${token}`
        }}).then((response) => (response)));
    }

    public static async getList2(): Promise<Array<IProduct>> {
        const token = localStorage.getItem('token');
        return (await axios.get<Array<IProduct>>(options.apiUrl + `product/select`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }

    public static async getAll(): Promise<Array<IProduct>> {
        const token = localStorage.getItem('token');
        return (await axios.get<Array<IProduct>>(options.apiUrl+`product/select`, {
            headers: {
              'Authorization': `${token}` 
            }
          })).data;
    }
}

export interface IProduct {
    id: number;
    productName: string;
    retailPrice: number;
    wholesalePrice: number;
    productAmount: number;
}