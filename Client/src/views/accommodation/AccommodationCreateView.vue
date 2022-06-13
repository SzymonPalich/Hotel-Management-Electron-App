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
            Utwórz rezerwację
          </h1>
        </div>
        <div class="bg-white h-full rounded-b-xl text-black">
          <dl class="p-2">
             <div
              class="bg-gray-50 px-2 py-5 sm:grid sm:grid-cols-3 sm:gap-3 sm:px-6 relative"
            > 
              <div class="float-left">
                <dt class="text-sm font-medium text-gray-500">Początek rezerwacji</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                  <input
                    class="
                      border-2 border-gray-400
                      h-full
                      w-3/4
                      rounded-xl
                      px-2
                      py-0_1
                      outline-none
                      focus:border-2 focus:border-cyan-400 focus:rounded-xl
                    "
                    type="date"
                    min="new Date()"
                    required
                    v-model="this.result.startDate"
                    v-on:change="clearDate"
                  />
                </dd>
              </div>
              <div class="float-left">
                <dt class="text-sm font-medium text-gray-500">Koniec rezerwacji</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                  <input
                    class="
                      border-2 border-gray-400
                      h-full
                      w-3/4
                      rounded-xl
                      px-2
                      py-0_1
                      outline-none
                      focus:border-2 focus:border-cyan-400 focus:rounded-xl
                    "
                    type="date"
                    required
                    v-model="this.result.endDate"
                    v-on:change="clearDate"
                  />
                </dd>
              </div>
              <div class="float-left">
                <dt class="text-sm font-medium text-gray-500">Typ pokoju</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                  <select class="border-2 border-gray-400 h-full w-3/4 rounded-xl px-2 py-0_1 outline-none focus:border-2 focus:border-cyan-400 focus:rounded-xl"
                   @change="selectRoomTypeId($event.target.value)">
                    <option selected disabled>Wybierz typ</option>
                    <option value="1">Ekonomiczny</option>
                    <option value="2">Standardowy</option>
                    <option value="3">Deluxe</option>
                  </select>
                  <div class="float-right bg-gray-400 rounded-2xl w-8 text-center">
                      <i class="material-icons align-middle" @click="filterRooms(this.result.startDate, this.result.endDate, this.roomTypeId)">search</i>
                  </div>
                </dd>
              </div>
            </div>
            <div class="border-2 border-gray-400 px-2 py-0_1 rounded-xl outline-none mx-6">
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2 p-2">
                <select size="5" class="overflow-hidden w-full h-full border-0 outline-none focus:outline-none" @change="selectRoom($event.target.value)" >
                  <option class="hover:bg-gray-200 hover:rounded-xl focus:visible focus:bg-slate-500 focus:ring" v-for="room in resultRooms.content" :key="room" v-bind:value="room.id">
                    {{ room.roomLabel }}
                  </option>  
                </select>
              </dd>
            </div>
            <div
              class="
                bg-gray-50
                w-full
                px-6
                py-5
              "
            >
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2 grid-col">
                <v-select
                  label="clientLabel"
                  :options="this.resultClients"
                  :reduce="(option) => option.id"
                  @option:selected="selectClient()"
                  :clearable="false"
                  placeholder="Wybierz klienta"
                  v-model="this.valueClient"
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
import AccommodationServices from "../../services/AccommodationService";
import { defineComponent } from "vue";
import 'vue-select/dist/vue-select.css';
import Utils, { IPager, IList } from "../../Utils";
import ClientsServices, { IClient, IClientSelect } from "../../services/ClientsService";
import RoomsServices, { IRoom } from "../../services/RoomsService";
import { AxiosError } from "axios";

export default defineComponent({
  data() {
    return {
      result: AccommodationServices.getBlankAccommodationTemplate(),
      pager: Utils.getRoomsPager(),
      valueClient: null,
      resultClients: [ClientsServices.getBlankClientSelectTemplate()],
      resultRooms: Utils.getBlankListTemplate<IRoom>(),
      roomTypeId: 0,
    };
  },

  mounted() {
    this.getClients().then((data) => (this.resultClients = data));
  },

  methods: {
    async getClients(): Promise<Array<IClientSelect>> {
      return await ClientsServices.getSelectList();
    },

    async getAvailableList(startDate: Date, endDate: Date, roomTypeId: string): Promise<Array<IRoom>> {
      return await RoomsServices.getAvailableList(startDate, endDate, roomTypeId);
    },

    selectRoom: function (value: number) {
      this.result.roomId = value;
    },

    selectClient() {
      if(this.valueClient != null){
        this.result.clientId = this.valueClient;
      }
    },

    clearDate() {
      this.resultRooms.content = [];
      this.result.roomId = 0;
      this.$forceUpdate;
    },

    selectRoomTypeId: function(value: number) {
      this.resultRooms.content = [];
      this.result.roomId = 0;
      this.roomTypeId = value;
      this.$forceUpdate;
    },

    filterRooms(startDate: Date, endDate: Date, roomTypeId: string) {
      this.getAvailableList(startDate, endDate, roomTypeId).then((data) => (this.resultRooms.content = data));
      this.$forceUpdate;
    },

    async save(): Promise<void> {
      try {
        await AccommodationServices.create(this.result);
        Utils.acceptedAlert();
        this.$router.push({ name: "accommodation" });
      } catch(error) {
        const err = error as AxiosError
        if (err.response) {
           Utils.errorAlert(err.response.status)
        }
      }
    },
    back(): void {
      // console.log(this.result);
      this.$router.push({ name: "accommodation" });
    },
  },
});
</script>