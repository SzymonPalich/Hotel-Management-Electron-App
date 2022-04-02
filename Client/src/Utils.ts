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
}
