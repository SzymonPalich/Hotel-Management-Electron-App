<template>
  <div class="relative w-full flex flex-col h-screen overflow-hidden">
    <div class="rounded-xl mx-24 mt-20">
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
            Pokój #{{ this.result.roomNumber }}
          </h1>
        </div>
        <div class="bg-white h-full rounded-b-xl text-black">
          <dl>
            <div
              class="
                bg-white
                px-4
                py-5
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">Typ pokoju</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.roomType }}
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
              <dt class="text-sm font-medium text-gray-500">Cena za pokój</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                450.00 zł
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Status pokoju</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.setStatus(this.result.status) }}
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
              <dt class="text-sm font-medium text-gray-500">
                Osoba wynajmująca
              </dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                Brak
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
              <dt class="text-sm font-medium text-gray-500">
                Czas wynajmu
              </dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                03.04.2022 - 22.04.2022
              </dd>
            </div>
          </dl>
          <div class="text-center px-4 py-5 bg-gray-50 rounded-b-xl">
            <button
              class="
                bg-gray-800
                rounded-xl
                px-2
                py-2
                text-white
                border-2 border-black
                hover:
              "
              @click="$router.push({ name: 'rooms' })"
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
import RoomsServices, { IRoom } from "../../services/RoomsService";
import { defineComponent } from "vue";

export default defineComponent({
  data() {
    return {
      result: RoomsServices.getBlankRoomTemplate(),
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

    setStatus(status: number): string {
      return RoomsServices.setStatus(status);
    },

    async getData(): Promise<IRoom> {
      return await RoomsServices.fetch(this.getId());
    },
  },
});
</script>