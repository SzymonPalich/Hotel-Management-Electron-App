<template>
  <div class="relative w-full flex flex-col h-screen overflow-hidden">
    <h1 class="text-4xl text-white mt-6 text-center">Naprawy</h1>
    <div class="w-full mt-6 p-6">
      <div class="text-right text-white mb-2 rounded-xl">
        
        <i
          class="px-2 py-2 rounded-xl bg-gray-800 material-icons"
          @click="$router.push({ name: 'repairs-create' })"
          >add</i
        >
      </div>
      <div class="overflow-auto rounded-xl">
        <table class="min-w-full">
          <thead class="bg-gray-800 text-white">
            <tr>
              <th
                class="
                  w-1/3
                  text-left
                  py-3
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                "
              >
                Usterka
              </th>
              <th
                class="
                  w-1/3
                  text-center
                  py-3
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                "
              >
                Numer Pokoju
              </th>
              <th class="w-1/3 text-center py-3 px-4">Akcja</th>
            </tr>
          </thead>
          <tbody class="text-gray-700">
            <tr v-for="repair in results"
            :key="repair"
            class="bg-white">

              <td class="w-1/3 text-left py-3 px-4">{{ repair.issue }}</td>
              <td class="w-1/3 text-center py-3 px-4">{{ repair.room_nr }}</td>
              <td class="w-1/3 text-center py-3 px-4">
                <i
                  class="material-icons"
                  @click="preview(repair)"
                  >description</i
                >
                <i class="material-icons text-red-500" @click="alertDisplay"
                  >delete</i
                >
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <p class="pt-3 text-gray-600"></p>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";
import Swal from "sweetalert2";
import { IRepair } from "../../services/RepairService";

let temp_repair_results: Array<IRepair> = [
  {
    id: 1,
    issue: "Telewizor",
    room_nr: 113,
    desc: "Zniszczona matryca"
  },
  {
    id: 2,
    issue: "Cieknący kran",
    room_nr: 53,
    desc: "Woda cieknie z kranu"
  },
  {
    id: 3,
    issue: "Wybita szyba",
    room_nr: 22,
    desc: "Szyba przy balkonie wybita"
  },
  {
    id: 4,
    issue: "Wyrwana klamka",
    room_nr: 9,
    desc: "Klamka do garderoby wyrwana"
  },
];

export default class RepairsView extends Vue {
  alertDisplay() {
    Swal.fire({
      title: "Jesteś pewien?",
      showCancelButton: true,
      confirmButtonText: "Tak",
      confirmButtonColor: "#FF2D00",
      cancelButtonText: "Nie",
      cancelButtonColor: "#187800",
      showLoaderOnConfirm: true,
    }).then((result) => {
      if (result.value) {
        Swal.fire("Usunięte", "Pomyślnie usunąłeś rekord", "success");
      }
    });
  }

  preview(repair: IRepair) {
    this.$router.push({
      name: "repairs-fetch",
      params: { id: repair.id, issue: repair.issue, room_nr: repair.room_nr, desc: repair.desc },
    });
  }

  data(){
    return {
      results: temp_repair_results,
    };
  }
}
</script>