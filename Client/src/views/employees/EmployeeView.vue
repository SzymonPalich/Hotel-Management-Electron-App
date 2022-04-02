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
              <td class="text-center py-2 px-4">
                <router-link :to="{ name: 'employee-info', params: { id: emp.id }}"
                  ><i class="material-icons align-middle"
                    >description</i
                  ></router-link
                >
                <i class="material-icons align-middle">person</i>
                <i class="material-icons align-middle">edit</i>
                <i class="material-icons align-middle">delete</i>
              </td>
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
        email: "AKowalski@spr.com",
        phone_number: "190-921-291",
        pesel: "98721093802",
        employment_date: new Date(2001, 11, 9)
    },
    {
        id: 2,
        name: "Leonardo",
        surname: "Przybylski",
        position: "Technik",
        salary: 3502,
        email: "LPrzybylski@spr.com",
        phone_number: "190-921-291",
        pesel: "98721093802",
        employment_date: new Date(2002, 12, 2)
    },
    {
        id: 3,
        name: "Elwira",
        surname: "Kowalska",
        position: "Recepcjonistka",
        salary: 20200,
        email: "EKowalska@spr.com",
        phone_number: "190-921-291",
        pesel: "98721093802",
        employment_date: new Date(2003, 5, 20)
    },
    {
        id: 4,
        name: "Patrycja",
        surname: "Nowak",
        position: "Pokojówka",
        salary: 4208,
        email: "PNowak@spr.com",
        phone_number: "190-921-291",
        pesel: "98721093802",
        employment_date: new Date(2001, 11, 7)
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