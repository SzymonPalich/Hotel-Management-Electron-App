<template>
  <div class="relative w-full flex flex-col h-screen overflow-hidden">
    <div class="rounded-xl mx-48 mt-20">
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
            Pokój #{{ this.result.room_number }}
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
              <dt class="text-sm font-medium text-gray-500">Typ pokoju</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.room_type }}
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Status pokoju</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.room_status }}
              </dd>
            </div>
          </dl>
          <div class="text-center px-4 py-5">
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
import { Vue } from "vue-class-component";
import RoomsService, { IRoom } from "../../services/RoomsService";

let temp_room: IRoom = {
  id: 1,
  room_number: 101,
  room_type: "Ekonomiczny",
  room_status: 1,
};

export default class RoomsFetchView extends Vue {
  data() {
    return {
      result: temp_room,
    };
  }

  private setStatus(room_status: number): string {
    return RoomsService.setStatus(room_status);
  }
}
</script>