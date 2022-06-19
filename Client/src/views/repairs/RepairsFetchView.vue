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
          <h1
            class="text-2xl leading-6 font-medium text-white text-center mb-2"
          >
            Podgląd
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
              <dt class="text-sm font-medium text-gray-500">Usterka</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.name }}
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Numer pokoju</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.roomNumber }}
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
              <dt class="text-sm font-medium text-gray-500">Opis</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.description }}
              </dd>
            </div>
            <div
              v-if="this.result.technicianReport !== ''"
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Raport technika</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.technicianReport }}
              </dd>
            </div>
            <div
              v-if="this.result.technicianReport !== ''"
              class="
                bg-gray-50
                px-4
                py-5
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">
                Data finalizacji
              </dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.finalizationDate }}
              </dd>
            </div>
            <div
              v-if="this.result.technicianReport !== ''"
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Cena części</dt>
              <dd
                v-if="this.result.partsPrice > 0"
                class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2"
              >
                {{ this.currencyFormat(this.result.partsPrice) }}
              </dd>
              <dd
                v-else
                class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2"
              >
                Brak Kosztów
              </dd>
            </div>
          </dl>
          <div class="text-center px-4 py-5 bg-gray-50 rounded-b-xl">
            <button
              class="
                bg-gray-800
                rounded-xl
                px-8
                py-2
                text-white
                border-2 border-black
                hover:
              "
              @click="$router.push({ name: 'repairs' })"
            >
              Wróć
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import RepairServices, { IRepair } from "../../services/RepairService";
import { defineComponent } from "vue";
import { AxiosError } from "axios";
import Utils from "@/Utils";

export default defineComponent({
  data() {
    return {
      result: RepairServices.getBlankRepairTemplate(),
    };
  },

  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
  },

  methods: {
    getId(): string {
      return this.$route.params.id as string;
    },

    currencyFormat(value: number): string {
      return Utils.currencyFormat(value);
    },

    async getData(): Promise<IRepair> {
      try {
        return await RepairServices.fetch(this.getId());
      } catch (error) {
        const err = error as AxiosError;
        if (err.response) {
          Utils.errorAlert(err.response.status);
        }
        return Promise.reject();
      }
    },
  },
});
</script>