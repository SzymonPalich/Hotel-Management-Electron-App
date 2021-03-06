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
            Edytuj: {{ this.result.roomNumber }}
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
                  v-model="this.tempRoomNumber"
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Pokój</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <v-select
                  label="type"
                  v-model="this.roomValue"
                  :options="this.resultRoomTypes.content"
                  :reduce="(option) => option.id"
                  :clearable="false"
                  placeholder="Wybierz typ"
                >
                  <template v-slot:option="option">
                    <span :class="option.icon"></span>
                    {{ option.type }}
                  </template>
                  <template v-slot:no-options="{ search, searching }">
                    <template v-if="searching">
                      Brak wyników dla <em>{{ search }}</em
                      >.
                    </template>
                  </template>
                </v-select>
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
import RoomTypesServices, { IRoomType } from "../../services/RoomTypesService";
import RoomsServices, { IRoom } from "../../services/RoomsService";
import { defineComponent } from "vue";
import Utils, { IPager, IList } from "../../Utils";
import { AxiosError } from "axios";

export default defineComponent({
  data() {
    return {
      result: RoomsServices.getBlankRoomTemplate(),
      pager: Utils.getDefaultPager(),
      resultRoomTypes: Utils.getBlankListTemplate<IRoomType>(),
      roomValue: null as any,
      tempRoomNumber: "",
    };
  },

  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
    this.getRoomTypes().then((data) => (this.resultRoomTypes = data));
    this.getData().then((data) => (this.roomValue = data.roomTypeId));
    this.getData().then((data) => (this.tempRoomNumber = data.roomNumber));
  },

  methods: {
    getId(): string {
      return this.$route.params.id as string;
    },

    async getData(): Promise<IRoom> {
      try {
        return await RoomsServices.fetch(this.getId());
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
        return Promise.reject()
      }
    },

    async getRoomTypes(): Promise<IList<IRoomType>> {
      return await RoomTypesServices.getList(this.pager);
    },

    async save(): Promise<void> {
      this.result.roomTypeId = this.roomValue || 0;
      this.result.roomNumber = this.tempRoomNumber;
      try {
        await RoomsServices.update(this.getId(), this.result);
        Utils.acceptedAlert();
        this.$router.push({ name: "rooms" });
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
      }
    },
    back(): void {
      this.$router.push({ name: "rooms" });
    },
  },
});
</script>