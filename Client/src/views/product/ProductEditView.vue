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
          <h1 class="text-2xl leading-6 font-medium text-white text-center mb-2">
            Edytuj: {{ this.result.productName }}
          </h1>
        </div>
        <div class="bg-white h-full rounded-b-xl text-black">
          <dl>
            <div
              class="
                bg-gray-50
                px-4
                py-5
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">Nazwa Produktu</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    border border-gray-300
                    w-full
                    h-full
                    rounded-md
                    px-2
                    py-1_5
                    outline-none
                    focus:border focus:border-cyan-400 focus:rounded-md
                  "
                  type="text"
                  required
                  v-model="this.tempProductName"
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Cena detaliczna</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    border border-gray-300
                    w-full
                    h-full
                    rounded-md
                    px-2
                    py-1_5
                    outline-none
                    focus:border focus:border-cyan-400 focus:rounded-md
                  "
                  type="number"
                  required
                  v-model="this.result.retailPrice"
                />
              </dd>
            </div>
            <div
              class="
                bg-gray-50
                px-4
                py-5
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">Cena hurtowa</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    border border-gray-300
                    w-full
                    h-full
                    rounded-md
                    px-2
                    py-1_5
                    outline-none
                    focus:border focus:border-cyan-400 focus:rounded-md
                  "
                  type="number"
                  required
                  v-model="this.result.wholesalePrice"
                />
              </dd>
            </div>
            <div
              class="
                bg-gray-50
                px-4
                py-5
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">Ilość</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    border border-gray-300
                    w-full
                    h-full
                    rounded-md
                    px-2
                    py-1_5
                    outline-none
                    focus:border focus:border-cyan-400 focus:rounded-md
                  "
                  type="number"
                  required
                  v-model="this.result.productAmount"
                  @keypress="(event) => {
                    if (!event.key.match(/[0-9]/g)) {
                      event.preventDefault();
                    }
                  }"
                  @keyup="this.block()"
                  min="0"
                />
              </dd>
            </div>
          </dl>
          <div class="text-center px-4 py-5">
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
            <button style="margin-left:0.6rem"
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
import ProductServices, { IProduct } from "../../services/ProductService";
import { defineComponent } from "vue";
import Utils, { IPager, IList } from "../../Utils";
import { AxiosError } from "axios";

export default defineComponent({
  data() {
    return {
      result: ProductServices.getBlankProductTemplate(),
      pager: Utils.getDefaultPager(),
      tempProductName: "",
    };
  },

  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
    this.getData().then((data) => (this.tempProductName = data.productName));
  },

  methods: {
    getId(): string {
      return this.$route.params.id as string;
    },

    async getData(): Promise<IProduct> {
      try {
        return await ProductServices.fetch(this.getId());
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
        return Promise.reject()
      }
    },

    async save(): Promise<void> {
      try {
        await ProductServices.update(this.getId(), this.result);
        Utils.acceptedAlert();
        this.$router.push({ name: "product" });
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
      }
    },
    back(): void {
      this.$router.push({ name: "product" });
    },
    block(): void {
      if (this.result.productAmount < 0) {
        this.result.productAmount = 0;
      }
      if (this.result.productAmount > 99) {
        this.result.productAmount = 99;
      }
    }
  },
});
</script>