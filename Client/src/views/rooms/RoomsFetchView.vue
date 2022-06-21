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
            Pokój #{{ this.result.roomNumber }}
          </h1>
        </div>
        <div class="bg-white h-full rounded-b-xl text-black">
          <dl>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
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
                {{ this.currencyFormat(this.resultRoomTypes.price) }}
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
import RoomTypesServices, { IRoomType } from "@/services/RoomTypesService";
import Utils from '@/Utils'

export default defineComponent({
  data() {
    return {
      result: RoomsServices.getBlankRoomTemplate(),
      resultRoomTypes: RoomTypesServices.getBlankRoomTypeTemplate(),
    };
  },
  mounted() {
    this.getData().then((data) => (this.result = data));
    this.getRoomTypes().then((data) => (this.resultRoomTypes = data));
  },

  methods: {
    getId(): string {
      return this.$route.params.id as string;
    },

    currencyFormat(value: number): string {
      return Utils.currencyFormat(value);
    },

    async getData(): Promise<IRoom> {
      return await RoomsServices.fetch(this.getId());
    },

    async getRoomTypes(): Promise<IRoomType> {
      console.log(this.result);
      var id = (await this.getData()).roomTypeId as unknown as string;
      return await RoomTypesServices.fetch(id);
    },
  },
});
</script>