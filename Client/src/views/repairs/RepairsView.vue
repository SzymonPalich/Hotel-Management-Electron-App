<template>
  <div class="flex justify-between flex-col h-screen">
    <div class="mt-4 flex mr-0 ml-auto">
      <search-bar />
      <div class="pr-6 flex items-center">
        <i class="px-2 py-1 rounded-xl text-white bg-gray-800 material-icons cursor-pointer"
          @click="$router.push({ name: 'repairs-create' })"
          >add</i
        >
      </div>
    </div>
    <div class="px-6 pb-4 pt-7 w-full h-full">
      <div class="overflow-auto rounded-xl">
        <table class="min-w-full">
          <thead class="bg-gray-800 text-white">
            <tr>
              <th
                class="text-left py-3 px-4 uppercase font-semibold text-sm"
              >
                Usterka
              </th>
              <th
                class="text-center py-3 px-4 uppercase font-semibold text-sm w-1/2"
              >
                Numer Pokoju
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm w-36">Akcje</th>
            </tr>
          </thead>
          <tbody class="text-gray-700">
            <tr v-for="repair in result.content"
            :key="repair"
            class="bg-white">

              <td class="text-left py-2 px-4">{{ repair.issue }}</td>
              <td class="text-center py-2 px-4">{{ repair.room_nr }}</td>
              <td class="text-center py-2 px-4 w-44">
                <router-link :to="{ name: 'repairs-finalization', params: { id: '1'} }">
                  <i class="material-icons align-middle">build</i>
                </router-link>

                <router-link :to="{ name: 'repairs-fetch', params: { id: '1' } }">
                  <i class="material-icons align-middle">description</i>
                </router-link>

                <router-link :to="{ name: 'repairs-edit', params: { id: '1' } }"
                  ><i class="material-icons align-middle">edit</i>
                </router-link>

                <router-link :to="{ name: 'repairs' }" @click="alertDisplay()">
                  <i class="material-icons align-middle">delete</i>
                </router-link>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="px-6 mb-6">
      <pagination />
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import RepairServices, { IRepair } from "../../services/RepairService";
import Pagination from "../../components/Pagination.vue";
import SearchBar from "../../components/SearchBar.vue";
import Utils, { IPager, IList } from "../../Utils";
import { defineComponent } from "vue";

export default defineComponent({
  components: {
    Pagination,
    SearchBar,
  },
  data(){
    return {
      result: Utils.getBlankListTemplate<IRepair>(),
      pager: Utils.getDefaultPager(),
    };
  },
  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
  },

  methods: {
    async getData(): Promise<IList<IRepair>> {
      return await RepairServices.getList(this.pager);
    },

    alertDisplay(): void {
      Utils.alertDisplay();
    }
  }
});
</script>