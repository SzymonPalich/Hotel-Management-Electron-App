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
}
