<template>
  <div class="cleaning relative w-full flex flex-col h-screen overflow-hidden">
    <body class="font-family-karla flex">
      <div class="w-full flex-grow p-6">
        <h2 class="text-4xl text-white pb-6 text-center">Sprzątanie</h2>
        <div class="w-full mt-6">
          <div class="overflow-auto rounded-xl">
            <table id="cleaning_table" class="min-w-full">
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
                    Numer Pokoju
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
                    Uzupełnienie minibaru
                  </th>
                </tr>
              </thead>
              <tbody class="text-gray-700">
                <tr
                  v-for="room in results"
                  :key="room.nr"
                  @click="clean(room)"
                  class="bg-white"
                >
                  <td class="w-1/3 text-left py-3 px-4">
                    {{ room.nr }}
                  </td>
                  <td class="w-1/3 text-center py-3 px-4">
                    {{ room.minibar }}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <p class="pt-3 text-gray-600"></p>
        </div>
      </div>
    </body>
    <div class="px-6 mt-auto mb-3">
      <pagination />
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import { IRoom } from "../../services/CleaningService";
import Pagination from "../../components/Pagination.vue";
import SearchBar from "../../components/SearchBar.vue";

let temp_room_results: Array<IRoom> = [
  {
    id: 1,
    nr: 1231,
    minibar: "Tak",
  },
  {
    id: 2,
    nr: 531,
    minibar: "Tak",
  },
  {
    id: 3,
    nr: 21,
    minibar: "Nie",
  },
  {
    id: 4,
    nr: 37,
    minibar: "Tak",
  },
  {
    id: 5,
    nr: 67,
    minibar: "Nie",
  },
  {
    id: 6,
    nr: 701,
    minibar: "Tak",
  },
];

@Options({
  components: {
    Pagination,
    SearchBar,
  },
})
export default class CleaningView extends Vue {
  clean(room: IRoom) {
    if (room.minibar == "Tak") {
      this.$router.push({ name: "cleaning-finish", params: { id: room.nr } });
    } else {
      console.log("czysto");
    }
  }
  data() {
    return {
      results: temp_room_results,
    };
  }
}
</script>