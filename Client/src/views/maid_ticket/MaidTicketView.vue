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
            <img class="material-icons" src="../../../public/css/fonts/icons8-search-25.png"/>
          </div>
        </div>
      </div>
      <div class="pr-6 flex items-center">
      </div>
    </div>
    <div class="px-6 pb-4 pt-7 w-full h-full">
      <div class="overflow-auto rounded-xl">
        <table class="min-w-full">
          <thead class="bg-gray-800 text-white">
            <tr>
              <th
                class="
                  text-left
                  py-2
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                  w-1/5
                "
              >
                Numer Pokoju
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Sprzątacz
              </th>
                  <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Planowana data sprzątania
              </th>
              <th
                class="
                  text-center
                  py-3
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                  w-36
                "
              >
                Akcje
              </th>
            </tr>
          </thead>
          <tbody class="text-gray-700">
            <tr v-for="maid in result.content" :key="maid" class="bg-white">
              <td class="text-left py-2 px-4">
                {{ maid.roomNumber }}
              </td>
              <td class="text-center py-2 px-4">
                {{ maid.employeeFirstName }} {{ maid.employeeLastName }}
              </td>
              <td class="text-center py-2 px-4">
                {{ maid.finalizationDate }}
              </td>
              <td class="text-center py-2 px-4 w-36">
                <router-link
                  :to="{ name: 'maid-ticket-finalize', params: { id: maid.id} }">
                  <img v-if="maid.employeeId==undefined && (this.loginResult.role=='ROLE_MANAGER' || this.loginResult.role=='ROLE_MAID')" class="material-icons align-middle" src="../../../public/css/fonts/icons8-cocktail-25.png"/>
                  </router-link>
                <router-link
                  :to="{ name: 'maid_ticket-fetch', params: { id: maid.id } }"
                  ><img class="align-middle material-icons"
                    src="../../../public/css/fonts/icons8-document-30.png"
                  /></router-link
                >
                <img @click="alertDisplay(maid.id)" class="material-icons align-middle" src="../../../public/css/fonts/icons8-delete-25.png"/>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="px-6 mt-auto mb-6">
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
import MaidTicketServices, { IMaid } from "../../services/MaidTicketService";
import Pagination from "../../components/Pagination.vue";
import SearchBar from "../../components/SearchBar.vue";
import LoginServices, { ILogin } from "../../services/LoginService";
import Utils, { IPager, IList } from "../../Utils";
import { defineComponent } from "vue";

export default defineComponent({
  components: {
    Pagination,
  },
  data() {
    return {
      result: Utils.getBlankListTemplate<IMaid>(),
      pager: Utils.getDefaultPager(),
      loginResult: LoginServices.getBlankLoginTemplate(),
      search: "",
    };
  },
  mounted() {
    console.log(this.getData());
    this.getRank().then((data) => (this.loginResult = data));
    this.getData().then((data) => (this.result = data));
  },

  methods: {
    async getData(): Promise<IList<IMaid>> {
      return await MaidTicketServices.getList(this.pager);
    },

   async getRank(): Promise<ILogin> {
      return await LoginServices.fetch();
    },

    async find(): Promise<void> {
      this.result.pager.search = this.search;
      this.result = await MaidTicketServices.getList(this.result.pager);
      console.log(this.result.content);
    },

    async getPage(page: number) {
      this.result.pager = Utils.getPager(page, "id");
      this.result.pager.search = this.search;
      this.result = await MaidTicketServices.getList(this.result.pager);
    },

    alertDisplay(id: string) {
      Utils.alertDisplayDelete("maid", id);
    },
  },
});
</script>