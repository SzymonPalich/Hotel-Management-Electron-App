<template>
  <div class="relative w-full flex flex-col h-screen overflow-hidden">
    <h1 class="text-4xl text-white mt-6 text-center">Klienci</h1>
<div class="flex justify-between flex-col h-screen">
    <div class="mt-6 flex mr-0 ml-auto">
      <search-bar />
      <div class="pr-6 flex items-center">
        <i class="px-2 py-1 rounded-xl text-white bg-gray-800 material-icons"
          @click="$router.push({ name: 'rooms-create' })"
          >add</i
        >
      </div>
    </div>
      
      <div class="overflow-auto rounded-xl">
        <table class="min-w-full">
          <thead class="bg-gray-800 text-white">
            <tr>
              <th
                class="
                  w-1/5
                  text-center
                  py-5
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                "
              >
                Imię
              </th>
              <th
                class="
                  w-1/5
                  text-center
                  py-5
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                "
              >
                Nazwisko
              </th>
              <th
                class="
                  w-1/5
                  text-center
                  py-5
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                "
              >
                Dane kontaktowe
              </th>
              <th
                class="
                  w-2/5
                  text-center
                  py-5
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                  whitespace-nowrap
                "
              >
                NR Telefonu
              </th>
            </tr>
          </thead>
          <tbody class="text-gray-700">
            <tr v-for="client in results" :key="client" class="bg-white">
              <td class="w-1/5 text-left py-2 px-4">{{ client.first_name }}</td>
              <td class="w-1/5 text-center py-2 px-4">
                {{ client.last_name }}
              </td>
              <td class="w-1/5 text-center py-2 px-4">{{ client.email }}</td>
              <td class="w-2/5 text-center py-2 px-4">
                {{ client.phone_number }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <p class="pt-3 text-gray-600"></p>
    </div>
    <div class="px-6 mb-3">
      <pagination />
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import { IClients } from "../../services/ClientsServices";
import Pagination from "../../components/Pagination.vue";
import SearchBar from "../../components/SearchBar.vue";
let temp_clients_results: Array<IClients> = [
  {
    id: 1,
    first_name: "Jan",
    last_name: "Nowak",
    email: "jkowalski2137@gmail.com",
    phone_number: "+48 667 444 321",
  },
  {
    id: 2,
    first_name: "Dariusz",
    last_name: "Kowalski",
    email: "dkowalski3@gmail.com",
    phone_number: "+48 550 684 531",
  },
  {
    id: 3,
    first_name: "Heronim",
    last_name: "Wróblewski",
    email: "hwroblewski52@gmail.com",
    phone_number: "+48 663 548 522",
  },
  {
    id: 4,
    first_name: "Cyprian",
    last_name: "Kołodziej",
    email: "ckolodziej8@gmail.com",
    phone_number: "+48 611 333 156",
  },
  {
    id: 5,
    first_name: "Dominik",
    last_name: "Wójcik",
    email: "dwojzik999@gmail.com",
    phone_number: "+48 574 433 111",
  },
  {
    id: 6,
    first_name: "Oskar",
    last_name: "Mazurek",
    email: "omazurek601@gmail.com",
    phone_number: "+48 401 215 116",
  },
  {
    id: 7,
    first_name: "Ksawery",
    last_name: "Kwiatkowski",
    email: "kkwiatkowski4@gmail.com",
    phone_number: "+48 607 277 888",
  },
  {
    id: 8,
    first_name: "Fryderyk",
    last_name: "Ostrowski",
    email: "fostrowski111@gmail.com",
    phone_number: "+48 648 156 113",
  },
  //   {
  //   id: 9,
  //   first_name: "Kacper",
  //   last_name: "Jakubowski",
  //   email: "kjakubowski13gmail.com",
  //   phone_number: "+48 652 425 635",
  // },
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
      results: temp_clients_results,
    };
  }
}
</script>