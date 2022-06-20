<template>
  <div class="flex justify-between flex-col h-screen">
    <div class="mt-4 flex mr-0 ml-auto">
      <div class="flex flex-row pr-4 rounded-xl">
        <div class="flex border-gray-400 ml-4 rounded-xl">
          <input
            v-on:keyup.enter="this.find()"
            v-model="this.search"
            type="text"
            class="bg-custom-gray px-2 rounded-l-xl outline-none text-lg"
          />
          <div
            @click="this.find()"
            class="flex items-center bg-custom-gray rounded-r-xl"
          >
            <img
              class="material-icons"
              src="../../../public/css/fonts/icons8-search-25.png"
            />
          </div>
        </div>
      </div>
      <div class="pr-6 flex items-center">
        <img
          class="px-2 py-1 rounded-xl text-white bg-gray-800"
          src="../../../public/css/fonts/icons8-plus-25.png"
          @click="$router.push({ name: 'employees-create' })"
        />
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
                  ><img
                    class="align-middle material-icons"
                    src="../../../public/css/fonts/icons8-document-30.png"
                /></router-link>
                <router-link
                  :to="{ name: 'employees-edit', params: { id: emp.id } }"
                >
                  <img
                    class="align-middle material-icons"
                    src="../../../public/css/fonts/icons8-edit-25.png"
                  />
                </router-link>
                <img v-if="emp.email != this.loginResult.email"
                  class="align-middle material-icons"
                  src="../../../public/css/fonts/icons8-delete-25.png"
                  @click="alertDisplay(emp.id)"
                />
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
import LoginServices, { ILogin } from "../../services/LoginService";
import SearchBar from "../../components/SearchBar.vue";
import Utils, { IPager, IList } from "../../Utils";
import { defineComponent } from "vue";

export default defineComponent({
  components: {
    Pagination,
  },
  data() {
    return {
      result: Utils.getBlankListTemplate<IEmployee>(),
      pager: Utils.getDefaultPager(),
      loginResult: LoginServices.getBlankLoginTemplate(),
      search: "",
    };
  },
  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
    this.getRank().then((data) => (this.loginResult = data));
  },

  setPosition(position: string): string {
    return EmployeeServices.setPosition(position);
  },

  methods: {
    async getData(): Promise<IList<IEmployee>> {
      return await EmployeeServices.getList(this.pager);
    },

    async find(): Promise<void> {
      this.result.pager.search = this.search;
      this.result = await EmployeeServices.getList(this.result.pager);
    },

    async getPage(page: number) {
      this.result.pager = Utils.getPager(page, "id");
      this.result.pager.search = this.search;
      this.result = await EmployeeServices.getList(this.result.pager);
    },

    async getRank(): Promise<ILogin> {
      return await LoginServices.fetch();
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