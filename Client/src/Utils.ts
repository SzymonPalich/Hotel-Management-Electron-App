import Swal from "sweetalert2";
import EmployeeServices, { IEmployee } from "./services/EmployeeService";
import MaidTicketServices, { IMaid } from "./services/MaidTicketService";

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
              } else if (table == "employee") {
                this.deleteEmployee(id);
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

    public static errorAlert(): void {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
          })
    }

    public static getDefaultPager(): IPager {
        return {
            index: 0,
            size: 10,
            sort: "id"
        };
    }
    public static getDefaultPagerWithSort(sort: string): IPager {
        return {
            index: 0,
            size: 10,
            sort: sort
        };
    }

    public static getBlankListTemplate<T>(): IList<T> {
        return {
            content: [],
            pager: this.getDefaultPager(),
            totalElements: 0,
            totalPages: 0
        }

    }

    static async deleteMaid(id: string): Promise<IMaid> {
        return await MaidTicketServices.delete(id);
    }

    static async deleteEmployee(id: string): Promise<IEmployee> {
        return await EmployeeServices.delete(id);
    }
}

export interface IPager {
    index: number,
    size: number,
    sort: string
}

export interface IList<T> {
    content: Array<T>,
    pager: IPager,
    totalElements: number,
    totalPages: number
}
