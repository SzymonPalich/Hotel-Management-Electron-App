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
            Dodaj typ pokoju
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
                  v-model="this.result.type"
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Cena</dt>
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
                  min="0"
                  maxlength="6"
                  required
                  v-model="this.result.price"
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
                px-8
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
              style="margin-left: 0.6rem"
              class="
                w-1/6
                bg-gray-800
                rounded-xl
                px-8
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
import RoomTypesService, { IRoomType } from "../../services/RoomTypesService";
import { defineComponent } from "vue";
import Utils, { IPager, IList } from "../../Utils";
import { AxiosError } from "axios";

export default defineComponent({
  data() {
    return {
      result: RoomTypesService.getBlankRoomTypeTemplate(),
      pager: Utils.getDefaultPager(),
    };
  },

  mounted() {
    this.getRoomTypes();
  },

  methods: {
    async getRoomTypes(): Promise<IList<IRoomType>> {
      return await RoomTypesService.getList(this.pager);
    },

    async add(): Promise<void> {
      try {
        await RoomTypesService.create(this.result);
        this.$router.push({ name: "rooms" });
      } catch (error) {
        const err = error as AxiosError;
        if (err.response) {
          Utils.errorAlert(err.response.status);
        }
      }
    },
    back(): void {
      this.$router.push({ name: "rooms" });
    },
  },
});
</script>