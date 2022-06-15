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
        <img
          class="px-2 py-1 rounded-xl text-white bg-gray-800"
          src="../../../public/css/fonts/icons8-plus-25.png"
          @click="$router.push({ name: 'product-create' })"
          />
      </div>
    </div>
    <div class="px-6 pb-4 pt-7 w-full h-full">
      <div class="overflow-auto rounded-xl">
        <table class="min-w-full">
          <thead class="bg-gray-800 text-white">
            <tr>
              <th class="text-left py-3 px-4 uppercase font-semibold text-sm">
                Nazwa Produktu
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Cena detaliczna
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Cena hurtowa
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Ilość
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
              v-for="product in result.content"
              :key="product"
              class="bg-white"
            >
              <td class="text-left py-2 px-4">{{ product.productName }}</td>
              <td class="text-center py-2 px-4">{{ product.retailPrice }} zł</td>
              <td class="text-center py-2 px-4">
                {{ product.wholesalePrice }} zł
              </td>
              <td class="text-center py-2 px-4">{{ product.productAmount }}</td>
              <td class="text-center py-2 px-4 w-44">
                <router-link :to="{ name: 'product-fetch', params: { id: product.id } }">
                  <img class="align-middle material-icons"
                    src="../../../public/css/fonts/icons8-document-30.png"
                  />
                </router-link>

                <router-link :to="{ name: 'product-edit', params: { id: product.id } }"
                  ><img class="align-middle material-icons"
                    src="../../../public/css/fonts/icons8-edit-25.png"
                  />
                </router-link>

                <img @click="alertDisplay(product.id)" class="material-icons align-middle" src="../../../public/css/fonts/icons8-delete-25.png"/>
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
import ProductServices, { IProduct } from "../../services/ProductService";
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
      result: Utils.getBlankListTemplate<IProduct>(),
      pager: Utils.getDefaultPager(),
      search: "",
    };
  },
  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
  },

  methods: {
    async getData(): Promise<IList<IProduct>> {
      return await ProductServices.getList(this.pager);
    },

        async find(): Promise<void> {
      this.result.pager.search = this.search;
      this.result = await ProductServices.getList(this.result.pager);
    },

    async getPage(page: number) {
      this.result.pager = Utils.getPager(page, "id");
      this.result.pager.search = this.search;
      this.result = await ProductServices.getList(this.result.pager);
    },

    alertDisplay(id: string): void {
      Utils.alertDisplayDelete("product",id);
    },
  },
});
</script>