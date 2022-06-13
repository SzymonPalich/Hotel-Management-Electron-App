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
                <select
                  class="
                    w-full
                    border-2 border-gray-400
                    px-2
                    py-0_1
                    rounded-xl
                    outline-none
                  "
                  @change="selectClient($event.target.value)"
                >
                  <option
                    v-for="client in resultClients.content"
                    :key="client"
                    v-bind:value="client.id"
                    :selected="client.id == this.result.clientId"
                  >
                    {{ client.firstName }} {{ client.lastName }}
                  </option>
                </select>
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
                  @change="selectRoom($event.target.value)"
                >
                  <option
                    v-for="room in resultRooms.content"
                    :key="room"
                    v-bind:value="room.id"
                    :selected="room.id == this.result.roomId"
                  >
                    {{ room.roomNumber }} {{ room.roomType }}
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
                <select
                  class="
                    w-full
                    border-2 border-gray-400
                    px-2
                    py-0_1
                    rounded-xl
                    outline-none
                  "
                  @change="selectAccess($event.target.value)"
                >
                  <option
                    v-for="access in accessibility"
                    :key="access"
                    v-bind:value="access.onlyreserv"
                    :selected="access.onlyreserv == this.result.reservationOnly"
                  >
                    {{ access.text }}
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
import ClientsServices, { IClient } from "../../services/ClientsService";
import RoomsServices, { IRoom } from "../../services/RoomsService";
import { AxiosError } from "axios";

export default defineComponent({
  data() {
    return {
      result: AccommodationServices.getBlankAccommodationTemplate(),
      pager: Utils.getMaxPager(),
      resultClients: Utils.getBlankListTemplate<IClient>(),
      resultRooms: Utils.getBlankListTemplate<IRoom>(),
      accessibility: [
        { onlyreserv: true, text:"Tylko za rezerwacją" },
        { onlyreserv: false, text:"Dowolna" }
      ],
    };
  },

  mounted() {
    console.log(this.getData());
    console.log(this.getClients());
    this.getData().then((data) => (this.result = data));
    this.getClients().then((data) => (this.resultClients = data));
    this.getRooms().then((data) => (this.resultRooms = data));
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

    async getClients(): Promise<IList<IClient>> {
      return await ClientsServices.getList(this.pager);
    },

    async getRooms(): Promise<IList<IRoom>> {
      return await RoomsServices.getList(this.pager);
    },

    selectRoom: function (value: number) {
      this.result.roomId = value;
    },

    selectClient: function (value: number) {
      this.result.clientId = value;
    },

    selectAccess: function (value: boolean) {
      this.result.reservationOnly = value;
    },

    async save(): Promise<void> {
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