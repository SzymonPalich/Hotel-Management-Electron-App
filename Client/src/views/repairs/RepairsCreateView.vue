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
            Dodaj naprawę
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
                  v-model = this.result.name
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Pokój</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <v-select
                  class="
                    w-full
                    border-2 border-gray-400
                    px-2
                    py-0_1
                    rounded-xl
                    outline-none
                  "
                  label="roomNumber"
                  v-model="this.value"
                  :options="this.resultRooms.content"
                  :reduce="(option) => option.id"
                  placeholder="Wybierz pokój"
                >
                  <template v-slot:option="option">
                    <span :class="option.icon"></span>
                    {{ option.roomNumber }} {{ option.roomType }}
                  </template>
                </v-select>
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
                <textarea
                  rows="5"
                  maxlength="400"
                  class="
                    scroll
                    border-2 border-gray-400
                    w-full
                    h-full
                    rounded-xl
                    text-md
                    px-2
                    py-1
                    outline-none
                    focus:border-2 focus:border-cyan-400 focus:rounded-xl
                    resize-none
                  "
                  required
                  v-model = this.result.description
                ></textarea>
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
import RepairServices, { IRepair } from "../../services/RepairService";
import 'vue-select/dist/vue-select.css'
import RoomsServices, { IRoom } from "../../services/RoomsService";
import { defineComponent } from "vue";
import Utils, { IList, IPager } from "../../Utils";

export default defineComponent({
  data() {
    return {
      result: RepairServices.getBlankRepairTemplate(),
      value: null,
      pager: Utils.getDefaultPager(),
      resultRooms: Utils.getBlankListTemplate<IRoom>(),
    };
  },

  mounted() {
    console.log(this.getRooms());
    this.getRooms().then((data) => (this.resultRooms = data));
  },

  methods: {
    async getRooms(): Promise<IList<IRoom>> {
      return await RoomsServices.getList(this.pager);
    },

    async add(): Promise<void> {
      if(this.value != null){ this.result.roomId = this.value }
      console.log(this.result);
      // await RepairServices.create(this.result);
      // this.$router.push({ name: "repairs" });
    },

    back(): void {
      this.$router.push({ name: "repairs" });
    },
  },
});
</script>