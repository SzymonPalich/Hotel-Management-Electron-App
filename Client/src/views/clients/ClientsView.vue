<template>
  <div class="flex justify-between flex-col h-screen">
    <div class="mt-4 flex mr-0 ml-auto">
      <div class="flex flex-row pr-4 rounded-xl">
        <div class="flex border-2 border-gray-400 ml-4 rounded-xl">
          <input
            v-on:keyup.enter="this.find()"
            v-model="this.search"
            type="text"
            class="bg-gray-600 px-2 rounded-l-xl outline-none text-lg"
          />
          <div
            @click="this.find()"
            class="flex items-center bg-gray-600 rounded-r-xl"
          >
            <i class="material-icons">search</i>
          </div>
        </div>
      </div>
      <div class="pr-6 flex items-center">
        <i
          class="px-2 py-1 rounded-xl text-white bg-gray-800 material-icons"
          @click="$router.push({ name: 'clients-create' })"
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
                Imię
              </th>
              <th class="text-left py-3 px-4 uppercase font-semibold text-sm">
                Nazwisko
              </th>
              <th class="text-left py-3 px-4 uppercase font-semibold text-sm">
                E-mail
              </th>
              <th
                class="
                  text-left
                  py-3
                  px-4
                  uppercase
                  font-semibold
                  text-sm
                  whitespace-nowrap
                "
              >
                NR Telefonu
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Akcje
              </th>
            </tr>
          </thead>
          <tbody class="text-gray-700">
            <tr v-for="client in result.content" :key="client" class="bg-white">
              <td class="text-left py-2 px-4">
                {{ client.firstName }}
              </td>
              <td class="text-left py-2 px-4">
                {{ client.lastName }}
              </td>
              <td class="text-left py-2 px-4">
                {{ client.email }}
              </td>
              <td class="text-left py-2 px-4">
                {{ client.phoneNumber }}
              </td>
              <td class="text-center py-2 px-4 w-36">
                <router-link
                  :to="{ name: 'clients-fetch', params: { id: client.id } }"
                  ><i class="material-icons align-middle"
                    >description</i
                  ></router-link
                >
                <router-link
                  :to="{ name: 'clients-edit', params: { id: client.id } }"
                >
                  <i class="material-icons align-middle">edit</i>
                </router-link>
                <i
                  class="material-icons align-middle cursor-pointer"
                  @click="alertDisplay(client.id)"
                  >delete</i
                >
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
import ClientsServices, { IClient } from "../../services/ClientsService";
import Pagination from "../../components/Pagination.vue";
import SearchBar from "../../components/SearchBar.vue";
import Utils, { IPager, IList } from "../../Utils";
import { defineComponent } from "vue";

export default defineComponent({
  components: {
    Pagination,
  },
  data() {
    return {
      result: Utils.getBlankListTemplate<IClient>(),
      search: "",
    };
  },
  mounted() {
    this.getData().then((data) => (this.result = data));
  },

  methods: {
    async getData(): Promise<IList<IClient>> {
      return await ClientsServices.getList(this.result.pager);
    },

    async find(): Promise<void> {
      this.result.pager.search = this.search;
      this.result = await ClientsServices.getList(this.result.pager);
    },

    async getPage(page: number) {
      this.result.pager = Utils.getPager(page, "id");
      this.result.pager.search = this.search;
      this.result = await ClientsServices.getList(this.result.pager);
    },

    alertDisplay(id: string): void {
      Utils.alertDisplayDelete("client", id);
    },
  },
});
</script>