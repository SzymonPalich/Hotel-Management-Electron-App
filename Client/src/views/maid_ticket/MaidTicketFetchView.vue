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
            Numer Pokoju: {{ this.result.roomNumber }} {{ this.result.roomType }}
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
              <dt class="text-sm font-medium text-gray-500">Imię sprzątacza</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.employeeFirstName }}
              </dd>
            </div>
            <div
              class="
                bg-white
                px-4
                py-5
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">Nazwisko sprzątacza</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.employeeLastName }}
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
              <dt class="text-sm font-medium text-gray-500">Planowana data sprzątania</dt>
              <dd v-if="this.result.finalizationDate == null" class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                Nie zakończono
              </dd>
              <dd v-if="this.result.finalizationDate != null" class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.finalizationDate }}
              </dd>
            </div>
          </dl>
          <div class="text-center px-4 py-5">
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
              @click="$router.push({ name: 'maid_ticket' })"
            >
              Powrót
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import MaidTicketServices, { IMaid } from "../../services/MaidTicketService";
import { defineComponent } from "vue";
import { AxiosError } from "axios";
import Utils from "@/Utils";

export default defineComponent({
  data() {
    return {
      result: MaidTicketServices.getBlankMaidTicketTemplate(),
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
  },
});
</script>