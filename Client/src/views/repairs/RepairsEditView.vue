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
            Edytuj #{{ this.result.id }}
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
                  v-model="this.result.name"
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Numer Pokóju</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <v-select
                  label="roomLabel"
                  v-model="this.value"
                  :options="this.resultRooms"
                  :reduce="(option) => option.id"
                  placeholder="Wybierz pokój"
                  :clearable="false"
                >
                  <template v-slot:option="option">
                    <span :class="option.icon"></span>
                    {{ option.roomLabel }}
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
                     border border-gray-300
                    w-full
                    h-full
                    rounded-md
                    px-2
                    py-1
                    outline-none
                    focus:border focus:border-cyan-400 focus:rounded-md
                    resize-none
                  "
                  required
                  v-model="this.result.description"
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
                px-8
                py-2
                text-white
                border-2 border-black
                hover:
                mx-4
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
                py-2
                text-white
                border-2 border-black
                hover:
                mx-4
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
import RepairServices, { IRepair } from "../../services/RepairService";
import RoomsServices, { IRoom, IRoomSelect } from "../../services/RoomsService";
import { defineComponent } from "vue";
import Utils, { IPager, IList } from "../../Utils";
import { AxiosError } from "axios";

export default defineComponent({
  data() {
    return {
      result: RepairServices.getBlankRepairTemplate(),
      value: null as any,
      pager: Utils.getMaxPager(),
      resultRooms: [RoomsServices.getBlankRoomSelectTemplate()],
    };
  },

  mounted() {
    console.log(this.getData());
    this.getData().then((data) => (this.result = data));
    this.getRooms().then((data) => (this.resultRooms = data));
    this.getData().then((data) => (this.value = data.roomId))
  },

  methods: {
    getId(): string {
      return this.$route.params.id as string;
    },

    async getData(): Promise<IRepair> {
      try {
        return await RepairServices.fetch(this.getId());
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
        return Promise.reject()
      }
    },

    async getRooms(): Promise<Array<IRoomSelect>> {
      return await RoomsServices.getSelectList();
    },

    async save(): Promise<void> {
      if(this.value != null){ this.result.roomId = this.value }
      try {
        await RepairServices.update(this.getId(), this.result);
        Utils.acceptedAlert();
        this.$router.push({ name: "repairs" });
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
      }
    },
    
    back(): void {
      this.$router.push({ name: "repairs" });
    },
  },
});
</script>