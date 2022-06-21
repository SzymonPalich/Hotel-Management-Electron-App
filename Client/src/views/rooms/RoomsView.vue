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
        <img v-if="this.loginResult.role !='ROLE_TECHNICIAN'"
          class="px-2 py-1 rounded-xl text-white bg-gray-800"
          src="../../../public/css/fonts/icons8-plus-25.png"
          @click="$router.push({ name: 'rooms-create' })"
          />
      </div>
    </div>
    <div class="px-6 pb-4 pt-7 w-full h-full">
      <div class="overflow-auto rounded-xl">
        <table class="min-w-full">
          <thead class="bg-gray-800 text-white">
            <tr>
              <th class="text-left py-3 px-4 uppercase font-semibold text-sm">
                Numer Pokoju
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Typ
              </th>
              <th
                class="
                  text-center
                  py-3
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                  w-38
                "
              >
                Akcje
              </th>
            </tr>
          </thead>
          <tbody class="text-gray-700">
            <tr v-for="room in result.content" :key="room" class="bg-white">
              <td class="text-left py-2 px-4">
                {{ room.roomNumber }}
              </td>
              <td class="text-center py-2 px-4">
                {{ room.roomType }}
              </td>
              <td class="text-center py-2 px-4 w-36">
                <router-link
                  :to="{ name: 'rooms-fetch', params: { id: room.id } }"
                  ><img class="align-middle material-icons"
                    src="../../../public/css/fonts/icons8-document-30.png"
                  /></router-link
                >
                <router-link
                  :to="{ name: 'rooms-edit', params: { id: room.id } }"
                  ><img class="material-icons align-middle"
                  v-if="this.loginResult.role !='ROLE_TECHNICIAN'"
                    src="../../../public/css/fonts/icons8-edit-25.png"
                  />
                </router-link>
                <img @click="alertDisplay(room.id)" 
                v-if="this.loginResult.role !='ROLE_TECHNICIAN'"
                class="material-icons align-middle" src="../../../public/css/fonts/icons8-delete-25.png"/>
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
import RoomsServices, { IRoom } from "../../services/RoomsService";
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
      result: Utils.getBlankListTemplate<IRoom>(),
      pager: Utils.getDefaultPager(),
      search: "",
      loginResult: LoginServices.getBlankLoginTemplate(),
    };
  },
  mounted() {
    console.log(this.getData());
    this.getRank().then((data) => (this.loginResult = data));
    this.getData().then((data) => (this.result = data));
    console.log(this.loginResult.role !='ROLE_TECHNICIAN');
  },

  methods: {
    async getData(): Promise<IList<IRoom>> {
      return await RoomsServices.getList(this.pager);
    },

      async find(): Promise<void> {
      this.result.pager.search = this.search;
      this.result = await RoomsServices.getList(this.result.pager);
    },

    async getPage(page: number) {
      this.result.pager = Utils.getPager(page, "id");
      this.result.pager.search = this.search;
      this.result = await RoomsServices.getList(this.result.pager);
    },

    
   async getRank(): Promise<ILogin> {
      return await LoginServices.fetch();
    },

    alertDisplay(id: string) {
      Utils.alertDisplayDelete("rooms", id);
    },

  }
});
</script>