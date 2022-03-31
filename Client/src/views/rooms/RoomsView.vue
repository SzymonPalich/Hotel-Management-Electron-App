<template>
  <div class="flex justify-between flex-col h-screen">
    <div class="mt-4 flex mr-0 ml-auto">
      <search-bar />
      <div class="pr-6 flex items-center">
        <i class="px-2 py-1 rounded-xl text-white bg-gray-800 material-icons"
          >add</i
        >
      </div>
    </div>
    <div class="px-6 pb-4 pt-3 w-full h-full">
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
                Typ
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
                Status
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
                Akcje
              </th>
            </tr>
          </thead>
          <tbody class="text-gray-700">
            <tr v-for="room in results" :key="room.id" class="bg-white">
              <td class="w-1/3 text-left py-3 px-4">
                {{ room.room_number }}
              </td>
              <td class="w-1/3 text-center py-3 px-4">
                {{ room.room_type }}
              </td>
              <td class="w-1/3 text-center py-3 px-4">
                {{ this.setStatus(room.room_status) }}
              </td>
              <td class="w-1/3 text-center py-3 px-4">temp</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="px-6 mb-3">
      <pagination />
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import { IRoom } from "../../services/RoomsService";
import Pagination from "../../components/Pagination.vue";
import SearchBar from "../../components/SearchBar.vue";

let temp_room_results: Array<IRoom> = [
  {
    id: 1,
    room_number: 101,
    room_type: "Ekonomiczny",
    room_status: 1,
  },
  {
    id: 2,
    room_number: 102,
    room_type: "Ekonomiczny",
    room_status: 1,
  },
  {
    id: 3,
    room_number: 103,
    room_type: "Ekonomiczny",
    room_status: 2,
  },
  {
    id: 4,
    room_number: 201,
    room_type: "Ekonomiczny",
    room_status: 1,
  },
  {
    id: 5,
    room_number: 202,
    room_type: "Ekonomiczny",
    room_status: 3,
  },
  {
    id: 6,
    room_number: 401,
    room_type: "Ekonomiczny",
    room_status: 1,
  },
  {
    id: 6,
    room_number: 402,
    room_type: "Ekonomiczny",
    room_status: 4,
  },
  {
    id: 6,
    room_number: 403,
    room_type: "Ekonomiczny",
    room_status: 5,
  },
  {
    id: 7,
    room_number: 403,
    room_type: "Ekonomiczny",
    room_status: 1,
  },
  {
    id: 7,
    room_number: 403,
    room_type: "Ekonomiczny",
    room_status: 1,
  },
];

@Options({
  components: {
    Pagination,
    SearchBar,
  },
})
export default class RoomsView extends Vue {
  data() {
    return {
      results: temp_room_results,
    };
  }

  private setStatus(room_status: number): string {
    switch (room_status) {
      case 1: {
        return "Gotowy";
      }
      case 2: {
        return "Rezerwacja";
      }
      case 3: {
        return "Sprzątanie";
      }
      case 4: {
        return "Usterka";
      }
      case 5: {
        return "Zablokowany";
      }
      default: {
        return "Błąd";
      }
    }
  }
}
</script>