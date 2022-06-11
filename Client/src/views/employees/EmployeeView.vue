<template>
  <div class="flex justify-between flex-col h-screen">
    <div class="mt-4 flex mr-0 ml-auto">
      <search-bar />
      <div class="pr-6 flex items-center">
        <i
          class="
            px-2
            py-1
            rounded-xl
            text-white
            bg-gray-800
            material-icons
            cursor-pointer
          "
          @click="$router.push({ name: 'employees-create' })"
          >add</i
        >
      </div>
    </div>
    <div class="px-6 pb-4 pt-7 w-full h-full">
      <div class="overflow-auto rounded-xl">
        <table class="min-w-full">
          <thead class="bg-gray-800 text-white">
            <tr>
              <th class="text-left py-3 px-4 uppercase font-semibold text-sm">
                Imie
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Nazwisko
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Stanowisko
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Akcje
              </th>
            </tr>
          </thead>
          <tbody class="text-gray-700">
            <tr v-for="emp in result.content" :key="emp" class="bg-white">
              <td class="text-left py-2 px-4">
                {{ emp.firstName }}
              </td>
              <td class="text-center py-2 px-4">
                {{ emp.lastName }}
              </td>
              <td class="text-center py-2 px-4">
                {{ this.setPosition(emp.position) }}
              </td>
              <td class="text-center py-2 px-4 w-36">
                <router-link
                  :to="{ name: 'employee-info', params: { id: emp.id } }"
                  ><i class="material-icons align-middle"
                    >description</i
                  ></router-link
                >
                <router-link
                  :to="{ name: 'employees-edit', params: { id: emp.id } }"
                >
                  <i class="material-icons align-middle">edit</i>
                </router-link>
                <i
                  class="material-icons align-middle cursor-pointer"
                  @click="alertDisplay(emp.id)"
                  >delete</i
                >
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="px-6 mb-6">
      <pagination
        :index="this.result.pager.index"
        :size="this.result.pager.size"
        :totalElements="this.result.totalElements"
        :totalPages="this.result.totalPages"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import EmployeeServices, { IEmployee } from "../../services/EmployeeService";
import Pagination from "../../components/Pagination.vue";
import SearchBar from "../../components/SearchBar.vue";
import Utils, { IPager, IList } from "../../Utils";
import { defineComponent } from "vue";

export default defineComponent({
  components: {
    Pagination,
    SearchBar,
  },
  data() {
    return {
      result: Utils.getBlankListTemplate<IEmployee>(),
      pager: Utils.getDefaultPager(),
    };
  },
  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
  },

  setPosition(position: string): string {
    return EmployeeServices.setPosition(position);
  },

  methods: {
    async getData(): Promise<IList<IEmployee>> {
      return await EmployeeServices.getList(this.pager);
    },

    setPosition(position: string): string {
      return EmployeeServices.setPosition(position);
    },

    alertDisplay(id: string): void {
      Utils.alertDisplayDelete("employee", id);
    },
  },
});
</script>