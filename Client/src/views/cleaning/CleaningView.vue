<template>
  <div class="cleaning">
    <body class="font-family-karla flex">
      <div class="relative w-full flex flex-col h-screen overflow-y-hidden">
        <div class="w-full h-screen overflow-x-hidden flex flex-col">
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
        </div>
      </div>
    </body>
  </div>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";
import { IRoom } from "../../services/CleaningService";

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

<style scoped>
tbody tr:hover {
  background-color: rgb(100, 100, 100);
  color: white;
}
</style>