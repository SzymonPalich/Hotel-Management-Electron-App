<template>
  <div class="flex justify-between flex-col h-screen">
    <div class="mt-4 flex mr-0 ml-auto">
      <search-bar />
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
      <pagination />
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
    SearchBar,
  },
  data() {
    return {
      result: Utils.getBlankListTemplate<IClient>(),
      pager: Utils.getDefaultPager(),
    };
  },
  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
  },

  methods: {
    async getData(): Promise<IList<IClient>> {
      return await ClientsServices.getList(this.pager);
    },
    
    alertDisplay(id: string): void {
      Utils.alertDisplayDelete('client', id);
    }
  },
});
</script>