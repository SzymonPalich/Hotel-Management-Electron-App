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
            Nazwa: {{ this.result.type }}
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
              <dt class="text-sm font-medium text-gray-500">Nazwa</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.type }}
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
              <dt class="text-sm font-medium text-gray-500">Cena</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.price }} zł
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
              @click="$router.push({ name: 'room_types' })"
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
import { defineComponent } from "vue";
import RoomTypesServices, { IRoomType } from "@/services/RoomTypesService";
import { AxiosError } from "axios";
import Utils from "@/Utils";

export default defineComponent({
  data() {
    return {
      result: RoomTypesServices.getBlankRoomTypeTemplate(),
    };
  },
  mounted() {
    this.getData().then((data) => (this.result = data));
  },

  methods: {
    getId(): string {
      return this.$route.params.id as string;
    },

    async getData(): Promise<IRoomType> {
      try {
        return await RoomTypesServices.fetch(this.getId());
      } catch(error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
        return Promise.reject()
      }
    }
  },
});
</script>