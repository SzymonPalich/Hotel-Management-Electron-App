<template>
  <div class="relative w-full flex flex-col h-screen overflow-hidden">
    <div class="rounded-xl mx-48 mt-auto mb-auto">
      <div
        class="
          bg-gray-800
          border-4 border-solid border-gray-800
          rounded-xl
          text-white
        "
      >
        <div class="px-4 py-5 sm:px-6 mt-2">
          <h1 class="text-2xl leading-6 font-medium text-white text-center">
            Numer Pokoju: {{ this.result.roomNumber }}
          </h1>
        </div>
        <div class="bg-white h-full rounded-b-xl text-black">
          <dl>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Produkt</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <v-select
                  label="productName"
                  multiple
                  v-model="this.products"
                  :options="this.resultProducts.content"
                  :reduce="(option) => option"
                  @option:deselected="remove"
                  placeholder="Wybierz produkty"
                >
                  <template v-slot:option="option">
                    <span :class="option.icon"></span>
                    {{ option.productName }}
                  </template>
                  <template #selected-option="option">
                    <div style="display: flex; align-items: baseline">
                      {{ option.productName }}
                    </div>
                  </template>
                  <template v-slot:no-options="{ search, searching }">
                    <template v-if="searching">
                      Brak wyników dla <em>{{ search }}</em
                      >.
                    </template>
                  </template>
                </v-select>
              </dd>
            </div>
            <div v-for="product in this.products" :key="product">
            <div
              class="
                bg-gray-50
                px-4
                py-5
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">{{ product.productName }}</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input  
                  :v-bind:id="product.id"
                  class="
                    border-2 border-gray-400
                    w-full
                    h-full
                    rounded-xl
                    text-md
                    px-2
                    py-1
                    outline-none
                    focus:border-2 focus:border-cyan-400 focus:rounded-xl
                  "
                  type="number"
                  required
                  v-model="output[parseInt(product.id)]"
                  @input="change(product.id, output[parseInt(product.id)])"
                />
              </dd>
            </div>
            </div>
          </dl>
          <div class="text-center px-4 py-3 bg-gray-50 rounded-b-xl">
            <button
              class="
                w-1/6
                bg-gray-800
                rounded-xl
                px-6
                mx-2
                py-2
                text-white
                border-2 border-black
                hover:
              "
              @click="this.back()"
            >
              Wróć
            </button>
            <button
              class="
                w-1/6
                bg-gray-800
                rounded-xl
                px-6
                mx-2
                py-2
                text-white
                border-2 border-black
                hover:
              "
              @click="this.save()"
            >
              Zatwierdź
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import MaidTicketServices, { IMaid, IRefill } from "../../services/MaidTicketService";
import { defineComponent } from "vue";
import Utils, { IList, IPager } from "../../Utils";
import ProductServices, {IProduct} from "../../services/ProductService";
import { numberLiteralTypeAnnotation } from "@babel/types";
import { AxiosError } from "axios";

export default defineComponent({
  data() {
    return {
      result: MaidTicketServices.getBlankMaidTicketTemplate(),
      products: null,
      pager: Utils.getDefaultPager(),
      resultProducts: Utils.getBlankListTemplate<IProduct>(),
      output: [] as number[],
      final: new Map<number, number>()
    };
  },

  mounted() {
    this.getData().then((data) => (this.result = data));
    this.getProducts().then((data) => (this.resultProducts = data));
  },

  methods: {
    getId(): string {
      return this.$route.params.id as string;
    },

    change(id: string, val: number) {
      this.final.set(parseInt(id), val)
    },

    remove(drink: any) {
      this.final.delete(parseInt(drink.id))
      this.output[parseInt(drink.id)] = 0
    },

    async getData(): Promise<IMaid> {
      try {
        return await MaidTicketServices.fetch(this.getId());
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
        return Promise.reject()
      }
    },

    async getProducts(): Promise<IList<IProduct>> {
        return await ProductServices.getList(this.pager);
    },

    async save(): Promise<void> {
      this.final.forEach((key: number) => {
        if (this.final.get(key) == 0) {
          this.final.delete(key)
        }
      });

      var refill: IRefill = {products: this.final};
      try {
        await MaidTicketServices.refill(this.getId(), refill);
        Utils.acceptedAlert();
        this.$router.push({ name: "maid_ticket" });
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
      }
    },

    back(): void {
      this.$router.push({ name: "maid_ticket" });
    },
  },
});
</script>