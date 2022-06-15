import Swal from "sweetalert2";
import EmployeeServices, { IEmployee } from "./services/EmployeeService";
import MaidTicketServices, { IMaid } from "./services/MaidTicketService";
import ClientsServices, { IClient } from "./services/ClientsService";
import RepairServices, { IRepair } from "./services/RepairService";
import RoomsServices, { IRoom } from "./services/RoomsService";
import ProductServices, { IProduct } from "./services/ProductService";
import AccommodationServices, { IAccommodation } from "./services/AccommodationService";
import { AxiosError } from "axios";

export default class Utils {
    public static alertDisplay() {
        Swal.fire({
            title: "Jesteś pewien?",
            showCancelButton: true,
            confirmButtonText: "Tak",
            confirmButtonColor: "#992c2c",
            cancelButtonText: "Nie",
            cancelButtonColor: "#374151",
            showLoaderOnConfirm: true,
        })
    }

    public static alertDisplayDelete(table: string, id: string) {
        Swal.fire({
            title: "Jesteś pewien?",
            showCancelButton: true,
            confirmButtonText: "Tak",
            confirmButtonColor: "#992c2c",
            cancelButtonText: "Nie",
            cancelButtonColor: "#374151",
            showLoaderOnConfirm: true,
        }).then((result) => {
            if (result.isConfirmed) {
                if (table == "maid") {
                    this.deleteMaid(id);
                    window.location.reload();
                } else if (table == "employee") {
                    this.deleteEmployee(id);
                    window.location.reload();
                } else if (table == "client") {
                    this.deleteClient(id);
                    window.location.reload();
                } else if (table == "technician") {
                    this.deleteTechnician(id);
                    window.location.reload();
                } else if (table == "rooms") {
                    this.deleteRoom(id);
                    window.location.reload();
                } else if (table == "product") {
                    this.deleteProduct(id);
                    window.location.reload();
                } else if (table == "accommodation") {
                    this.deleteAccommodation(id);
                    window.location.reload();
                }
            } else if (result.isDenied) {
                return false
            }
        })
    }

    public static acceptedAlert(): void {
        Swal.fire({
            icon: 'success',
            title: "Pomyślnie zapisano rekord!",
            showConfirmButton: true,
            confirmButtonText: "Ok",
            confirmButtonColor: "#3f5137",
            timer: 1500
        });
    }

    public static errorAlert(err: number): void {
        let text = ""
        if (err == 422) {
            text = "Niepoprawne dane"
        } else if (err == 404){
            text = "Brak podanego rekordu"
        }
        Swal.fire({
            icon: 'error',
            title: 'Błąd',
            text: text,
        })
    }

    public static getDefaultPager(): IPager {
        return {
            index: 1,
            size: 10,
            sort: "id",
            search: ""
        };
    }

    public static getMaxPager(): IPager {
        return {
            index: 1,
            size: 999999,
            sort: "id",
            search: ""
        };
    }

    public static getRoomsPager(): IPager {
        return {
            index: 1,
            size: 5,
            sort: "id",
            search: ""
        };        
    }

    public static getPager(index: number, sort: string): IPager {
        return {
            index: index,
            size: 10,
            sort: sort,
            search: ""
        };
    }

    public static getDefaultPagerWithSort(sort: string): IPager {
        return {
            index: 1,
            size: 10,
            sort: sort,
            search: "",
        };
    }

    public static getBlankListTemplate<T>(): IList<T> {
        return {
            content: [],
            pager: this.getDefaultPager(),
            totalElements: 0,
            totalPages: 1
        }
    }

    static async deleteMaid(id: string): Promise<IMaid> {
        try{
            return await MaidTicketServices.delete(id);
        } catch (error) {
            const err = error as AxiosError
            if (err.response) {
                Utils.errorAlert(err.response.status)
            }
            return Promise.reject()
        }
    }

    static async deleteEmployee(id: string): Promise<IEmployee> {
        try{
            return await EmployeeServices.delete(id);
        } catch (error) {
            const err = error as AxiosError
            if (err.response) {
                Utils.errorAlert(err.response.status)
            }
            return Promise.reject()
        }
    }

    static async deleteClient(id: string): Promise<IClient> {
        try{
            return await ClientsServices.delete(id);
        } catch (error) {
            const err = error as AxiosError
            if (err.response) {
                Utils.errorAlert(err.response.status)
            }
            return Promise.reject()
        }
    }

    static async deleteTechnician(id: string): Promise<IRepair> {
        try{
            return await RepairServices.delete(id);
        } catch (error) {
            const err = error as AxiosError
            if (err.response) {
                Utils.errorAlert(err.response.status)
            }
            return Promise.reject()
        }
    }

    static async deleteRoom(id: string): Promise<IRoom> {
        try{
            return await RoomsServices.delete(id);
        } catch (error) {
            const err = error as AxiosError
            if (err.response) {
                Utils.errorAlert(err.response.status)
            }
            return Promise.reject()
        }
    }

    static async deleteProduct(id: string): Promise<IProduct> {
        try{
            return await ProductServices.delete(id);
        } catch (error) {
            const err = error as AxiosError
            if (err.response) {
                Utils.errorAlert(err.response.status)
            }
            return Promise.reject()
        }
    }

    static async deleteAccommodation(id: string): Promise<IAccommodation> {
        try{
            return await AccommodationServices.delete(id);
        } catch (error) {
            const err = error as AxiosError
            if (err.response) {
                Utils.errorAlert(err.response.status)
            }
            return Promise.reject()
        }
    }
}

export interface IPager {
    index: number,
    size: number,
    sort: string,
    search: string
}

export interface IList<T> {
    content: Array<T>,
    pager: IPager,
    totalElements: number,
    totalPages: number
}
