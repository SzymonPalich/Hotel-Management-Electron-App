import Swal from "sweetalert2";

export default class Utils {
    public static alertDisplay(): void {
        Swal.fire({
            title: "Jesteś pewien?",
            showCancelButton: true,
            confirmButtonText: "Tak",
            confirmButtonColor: "#992c2c",
            cancelButtonText: "Nie",
            cancelButtonColor: "#374151",
            showLoaderOnConfirm: true,
        });
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
