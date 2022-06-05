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
          @click="$router.push({ name: 'product-create' })"
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
                Nazwa Produktu
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Cena detaliczna
              </th>
              <th class="text-center py-3 px-4 uppercase font-semibold text-sm">
                Cena hurtowa
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
              <td class="text-center py-2 px-4 w-44">
                <router-link :to="{ name: 'product-fetch', params: { id: product.id } }">
                  <i class="material-icons align-middle">description</i>
                </router-link>

                <router-link :to="{ name: 'product-edit', params: { id: product.id } }"
                  ><i class="material-icons align-middle">edit</i>
                </router-link>

                <i @click="alertDisplay(product.id)" class="material-icons align-middle">delete</i>
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
import ProductService, { IProduct } from "../../services/ProductService";
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
      result: Utils.getBlankListTemplate<IProduct>(),
      pager: Utils.getDefaultPager(),
    };
  },
  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
  },

  methods: {
    async getData(): Promise<IList<IProduct>> {
      return await ProductService.getList(this.pager);
    },

    alertDisplay(id: string): void {
      Utils.alertDisplayDelete("product",id);
    },
  },
});
</script>