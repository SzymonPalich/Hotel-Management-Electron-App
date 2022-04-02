<template>
  <div class="flex justify-between flex-col h-screen">
    <div class="mt-4 flex mr-0 ml-auto">
      <search-bar />
      <div class="pr-6 flex items-center">
        <i class="px-2 py-1 rounded-xl text-white bg-gray-800 material-icons"
          @click="$router.push({ name: 'employees-create' })"
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
                Imie
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
                Nazwisko
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
                Stanowisko
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
            <tr v-for="emp in results" :key="emp.id" class="bg-white">
              <td class="w-1/3 text-left py-3 px-4">
                {{ emp.name }}
              </td>
              <td class="w-1/3 text-center py-3 px-4">
                {{ emp.surname }}
              </td>
              <td class="w-1/3 text-center py-3 px-4">
                {{ emp.position }}
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
import { IEmployee } from "../../services/EmployeeService";
import Pagination from "../../components/Pagination.vue";
import SearchBar from "../../components/SearchBar.vue";

let temp_emp_results: Array<IEmployee> = [
    {
        id: 1,
        name: "Andrzej",
        surname: "Kowalski",
        position: "Manager",
        salary: 21308,
        e_mail: "AKowalski@spr.com",
        phone_number: "190-921-291",
        pesel: "98721093802"
    },
    {
        id: 2,
        name: "Leonardo",
        surname: "Przybylski",
        position: "Technik",
        salary: 3502,
        e_mail: "LPrzybylski@spr.com",
        phone_number: "190-921-291",
        pesel: "98721093802"
    },
    {
        id: 3,
        name: "Elwira",
        surname: "Kowalska",
        position: "Recepcjonistka",
        salary: 20200,
        e_mail: "EKowalska@spr.com",
        phone_number: "190-921-291",
        pesel: "98721093802"
    },
    {
        id: 4,
        name: "Patrycja",
        surname: "Nowak",
        position: "Pokojówka",
        salary: 4208,
        e_mail: "PNowak@spr.com",
        phone_number: "190-921-291",
        pesel: "98721093802"
    },
];

@Options({
  components: {
    Pagination,
    SearchBar,
  },
})
export default class EmployeeView extends Vue {
  data() {
    return {
      results: temp_emp_results,
    };
  }

}
</script>