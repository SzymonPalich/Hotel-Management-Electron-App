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
            Dodaj pokój
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
              <dt class="text-sm font-medium text-gray-500">Numer pokoju</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
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
                  type="text"
                  required
                  v-model = this.result.roomNumber
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
                            <dt class="text-sm font-medium text-gray-500">Pokój</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <select
                  class="
                    w-full
                    border-2 border-gray-400
                    px-2
                    py-0_1
                    rounded-xl
                    outline-none
                  "
                  @change="selectRoomType($event.target.value)"
                >
                  <option
                    v-for="room in resultRoomTypes.content"
                    :key="room"
                    v-bind:value="room.id"
                    :selected="room.type == this.result.roomType"
                  >
                    {{ room.type }}
                  </option>
                </select>
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
              <dt class="text-sm font-medium text-gray-500">Status</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <select
                  class="
                    w-full
                    border-2 border-gray-400
                    px-2
                    py-0_1
                    rounded-xl
                    outline-none
                  "
                  @change="selectStatus($event.target.value)"
                >
                  <option
                    v-for="status in statuses"
                    :key="status"
                    v-bind:value="status.value"
                    :selected="status.value == this.result.status"
                  >
                    {{ status.text }}
                  </option>
                </select>
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
              @click="this.add()"
            >
              Dodaj
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import RoomTypesServices, { IRoomType } from "../../services/RoomTypesService";
import RoomsServices, { IRoom } from "../../services/RoomsService";
import { defineComponent } from "vue";
import Utils, { IPager, IList } from "../../Utils";

export default defineComponent({
  data() {
    return {
      result: RoomsServices.getBlankRoomTemplate(),
      pager: Utils.getDefaultPager(),
      resultRoomTypes: Utils.getBlankListTemplate<IRoomType>(),
      statuses: [
        { value: 1, text: "Wolny" },
        { value: 2, text: "Zajęty" },
        { value: 3, text: "Rezerwacja" }
      ],
    };
  },

  mounted() {
    this.getRoomTypes().then((data) => (this.resultRoomTypes = data));
    this.getRoomTypes().then((data) => {
      this.result.roomTypeId = JSON.parse(JSON.stringify(data)).content[0].id;
    });
    this.result.status = 1;
  },

  methods: {
    async getRoomTypes(): Promise<IList<IRoomType>> {
      return await RoomTypesServices.getList(this.pager);
    },

    selectRoomType: function (value: number) {
      this.result.roomTypeId = value;
    },

    selectStatus: function (value: number) {
      this.result.status = value;
    },

    async add(): Promise<void> {
      console.log(this.result);
      await RoomsServices.create(this.result);
      this.$router.push({ name: "rooms" });
    },
    back(): void {
      this.$router.push({ name: "rooms" });
    },
  },
});
</script>