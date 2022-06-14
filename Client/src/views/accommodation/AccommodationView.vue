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
        <img
         class="px-2 py-1 rounded-xl text-white bg-gray-800"
         src="../../../public/css/fonts/icons8-plus-25.png"
          @click="$router.push({ name: 'accommodation-create' })"
          />
      </div>
    </div>
    <div class="px-6 pb-4 pt-7 w-full h-full">
      <div class="overflow-auto rounded-xl">
        <table class="min-w-full">
          <thead class="bg-gray-800 text-white">
            <tr>
              <th class="text-left py-3 px-4 uppercase font-semibold text-sm">
                Klient
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Pokój
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Początek rezerwacji
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Koniec rezerwacji
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
            <tr
              v-for="accommodation in result.content"
              :key="accommodation"
              class="bg-white"
            >
              <td class="text-left py-2 px-4">
                  {{ accommodation.clientFirstName }} {{ accommodation.clientLastName }}
              </td>
              <td class="text-center py-2 px-4">
                  {{ accommodation.roomNumber }} {{ accommodation.roomType }}
              </td>
              <td class="text-center py-2 px-4">
                {{ accommodation.startDate }}
              </td>
              <td class="text-center py-2 px-4">
                {{ accommodation.endDate }}
              </td>
              <td class="text-center py-2 px-4 w-44">
                <router-link :to="{ name: 'accommodation-fetch', params: { id: accommodation.id } }">
                  <img class="align-middle material-icons"
                    src="../../../public/css/fonts/icons8-document-30.png"
                  />
                </router-link>

                <img class="align-middle material-icons"
                    src="../../../public/css/fonts/icons8-delete-25.png"
                    />
                <i class="material-icons align-middle">delete</i>
                <i class="material-icons align-middle" @click="invoice(accommodation.id)">description</i>
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
import AccommodationServices, { IAccommodation } from "../../services/AccommodationService";
import Pagination from "../../components/Pagination.vue";
import SearchBar from "../../components/SearchBar.vue";
import Utils, { IPager, IList } from "../../Utils";
import { defineComponent } from "vue";
import { AxiosError } from "axios";

export default defineComponent({
  components: {
    Pagination,
  },
  data() {
    return {
      result: Utils.getBlankListTemplate<IAccommodation>(),
      pager: Utils.getDefaultPager(),
      search: "",
    };
  },
  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
  },

  methods: {
    async getData(): Promise<IList<IAccommodation>> {
      return await AccommodationServices.getList(this.pager);
    },

    async invoice(id: string): Promise<void> {
      return await AccommodationServices.invoice(id);
    },

    async find(): Promise<void> {
      this.result.pager.search = this.search;
      this.result = await AccommodationServices.getList(this.result.pager);
    },

    async getPage(page: number) {
      this.result.pager = Utils.getPager(page, "id");
      this.result.pager.search = this.search;
      this.result = await AccommodationServices.getList(this.result.pager);
    },
    alertDisplay(id: string): void {
      try {
        Utils.alertDisplayDelete("accommodation",id);
      } catch(error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
      }
    },
  },
});
</script>