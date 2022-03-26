<template>
<div class="relative w-full flex flex-col h-full overflow-hidden">
    <h1 class="text-4xl text-white mt-6 text-center">Naprawy</h1>
    <div class="w-full mt-6 p-6">
      <div class="text-right text-white mb-2 rounded-xl">
          <i class="px-2 py-2 rounded-xl bg-gray-800 material-icons" @click="$router.push({name:'add_repairs'})">add</i>
      </div>
        <div class="overflow-auto rounded-xl">
            <table class="min-w-full">
                <thead class="bg-gray-800 text-white">
                    <tr>
                        <th class="w-1/3 text-left py-3 px-4 uppercase font-semibold text-sm">Usterka</th>
                        <th class="w-1/3 text-center py-3 px-4 uppercase font-semibold text-sm">Numer Pokoju</th>
                        <th class="w-1/3 text-center py-3 px-4">Akcja</th>
                    </tr>
                </thead>
                <tbody class="text-gray-700">
                    <tr class="bg-white">
                        <td class="w-1/3 text-left py-3 px-4">Telewizor</td>
                        <td class="w-1/3 text-center py-3 px-4">113</td>
                        <td class="w-1/3 text-center py-3 px-4">
                            <i class="material-icons" @click="przejdz(1,'Telewizor',113, 'Zniszczona matryca')">description</i>
                            <i class="material-icons text-red-500" @click="alertDisplay">delete</i>
                        </td>
                    </tr>
                    <tr class="bg-gray-200">
                        <td class="w-1/3 text-left py-3 px-4">Cieknący kran</td>
                        <td class="w-1/3 text-center py-3 px-4">53</td>
                        <td class="w-1/3 text-center py-3 px-4">
                            <i class="material-icons" @click="przejdz(2,'Cieknący kran',53, 'Woda cieknie z kranu')">description</i>
                            <i class="material-icons text-red-500" @click="alertDisplay">delete</i>
                        </td>
                    </tr>
                    <tr id="3" class="bg-white">
                        <td class="w-1/3 text-left py-3 px-4">Wybita szyba</td>
                        <td class="w-1/3 text-center py-3 px-4">22</td>
                        <td class="w-1/3 text-center py-3 px-4">
                            <i class="material-icons" @click="przejdz(3,'Wybita szyba',22,'Szyba przy balkonie wybita')">description</i>
                            <i class="material-icons text-red-500" @click="alertDisplay">delete</i>
                        </td>
                    </tr>
                    <tr class="bg-gray-200">
                        <td class="w-1/3 text-left py-3 px-4">Wyrwana klamka</td>
                        <td class="w-1/3 text-center py-3 px-4">9</td>
                        <td class="w-1/3 text-center py-3 px-4">
                            <i class="material-icons" @click="przejdz(4,'Wyrwana klamka',9, 'Klamka do garderoby wyrwana')">description</i>
                            <i class="material-icons text-red-500" @click="alertDisplay">delete</i>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <p class="pt-3 text-gray-600">
        </p>
    </div>
  </div>
</template>

<script lang="ts">
  import { Vue } from "vue-class-component";
  import Swal from "sweetalert2"

  export default class RepairsView extends Vue {
  alertDisplay() {
    Swal.fire({
      title: "Jesteś pewien?",
      showCancelButton: true,
      confirmButtonText: "Tak",
      confirmButtonColor: "#FF2D00",
      cancelButtonText: "Nie",
      cancelButtonColor: "#187800",
      showLoaderOnConfirm: true
    }).then((result) => {
      if (result.value) {
        Swal.fire("Usunięte", "Pomyślnie usunąłeś rekord", "success");
      }
    });
  }

  przejdz(arg1: number, arg2: string, arg3: number, arg4: string) {
    this.$router.push({
      name: "preview_repair",
      params: { id: arg1, issue: arg2, room_nr: arg3, desc: arg4 },
    });
  }
}
</script>