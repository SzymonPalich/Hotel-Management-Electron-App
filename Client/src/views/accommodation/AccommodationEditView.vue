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
            Edytuj rezerwacje dla pokoju {{ this.result.roomNumber }} {{ this.result.roomType }}
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
              <dt class="text-sm font-medium text-gray-500">Klient</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <v-select
                  label="clientLabel"
                  v-model="this.selectedClient"
                  :options="this.resultClients"
                  :reduce="(option) => option.id"
                  :clearable="false"
                  placeholder="Wybierz klienta"
                >
                  <template v-slot:option="option">
                    <span :class="option.icon"></span>
                    {{ option.clientLabel }}
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
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Pokój</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <v-select
                  label="roomLabel"
                  v-model="this.selectedRoom"
                  :options="this.resultRooms"
                  :reduce="(option) => option.id"
                  :clearable="false"
                  placeholder="Wybierz typ"
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
              <dt class="text-sm font-medium text-gray-500">Początek rezerwacji</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    border-2 border-gray-400
                    w-full
                    h-full
                    rounded-xl
                    px-2
                    py-0_1
                    outline-none
                    focus:border-2 focus:border-cyan-400 focus:rounded-xl
                  "
                  type="date"
                  required
                  v-model="this.result.startDate"
                />
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
              <dt class="text-sm font-medium text-gray-500">Koniec rezerwacji</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    border-2 border-gray-400
                    w-full
                    h-full
                    rounded-xl
                    px-2
                    py-0_1
                    outline-none
                    focus:border-2 focus:border-cyan-400 focus:rounded-xl
                  "
                  type="date"
                  required
                  v-model="this.result.endDate"
                />
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
              <dt class="text-sm font-medium text-gray-500">Dostępność</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <v-select
                  label="text"
                  v-model="this.selectedAccessibility"
                  :options="this.accessibility"
                  :reduce="(option) => option.onlyreserv"
                  :clearable="false"
                  placeholder="Wybierz dostępność"
                >
                  <template v-slot:option="option">
                    <span :class="option.icon"></span>
                    {{ option.text }}
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
import AccommodationServices, { IAccommodation } from "../../services/AccommodationService";
import { defineComponent } from "vue";
import Utils, { IPager, IList } from "../../Utils";
import ClientsServices, { IClient, IClientSelect } from "../../services/ClientsService";
import RoomsServices, { IRoom, IRoomSelect } from "../../services/RoomsService";
import { Axios, AxiosError } from "axios";

export default defineComponent({
  data() {
    return {
      result: AccommodationServices.getBlankAccommodationTemplate(),
      pager: Utils.getMaxPager(),
      resultClients: [ClientsServices.getBlankClientSelectTemplate()],
      resultRooms: [RoomsServices.getBlankRoomSelectTemplate()],
      accessibility: [
        { onlyreserv: true, text:"Tylko za rezerwacją" },
        { onlyreserv: false, text:"Dowolna" }
      ],
      selectedRoom: null as any,
      selectedClient: null as any,
      selectedAccessibility: null as any
    };
  },

  mounted() {
    this.getData().then((data) => (this.result = data));
    this.getClients().then((data) => (this.resultClients = data));
    this.getRooms().then((data) => (this.resultRooms = data));
    this.getData().then((data) => (this.selectedClient = data.clientId))
    this.getData().then((data) => (this.selectedRoom = data.roomId))
    this.getData().then((data) => (this.selectedAccessibility = data.reservationOnly))
  },

  methods: {
    getId(): string {
      return this.$route.params.id as string;
    },

    async getData(): Promise<IAccommodation> {
      try {
        return await AccommodationServices.fetch(this.getId());
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
        return Promise.reject()
      }
    },

    async getClients(): Promise<Array<IClientSelect>> {
      return await ClientsServices.getSelectList();
    },

    async getRooms(): Promise<Array<IRoomSelect>> {
      return await RoomsServices.getSelectList();
    },

    async save(): Promise<void> {
      this.result.roomId = this.selectedRoom || 0;
      this.result.clientId = this.selectedClient || 0;
      this.result.reservationOnly = this.selectedAccessibility || false;
      try {
        await AccommodationServices.update(this.getId(), this.result);
        Utils.acceptedAlert();
        this.$router.push({ name: "accommodation" });
      } catch (error) {
        const err = error as AxiosError
        if (err.response) {
          Utils.errorAlert(err.response.status)
        }
      }
    },
    back(): void {
      this.$router.push({ name: "accommodation" });
    },
  },
});
</script>