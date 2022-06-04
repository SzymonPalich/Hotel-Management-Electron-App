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
            Dodaj usługę sprzątania
          </h1>
        </div>
        <div class="bg-white h-full rounded-b-xl text-black">
          <dl>
            <div
              class="
                bg-gray-50
                px-4
                py-3
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">Sprzątacz</dt>
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
                  @change="selectEmployee($event.target.value)"
                >
                  <option
                    v-for="emp in resultEmployee"
                    :key="emp"
                    v-bind:value="emp.id"
                    :selected="emp.id == this.result.employeeId"
                  >
                    {{ emp.firstName }} {{ emp.lastName }}
                  </option>
                </select>
              </dd>
            </div>
            <div
              class="bg-white px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
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
          </dl>
          <div class="text-center px-4 py-3 bg-gray-50 rounded-b-xl">
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
import MaidTicketServices, { IMaid } from "../../services/MaidTicketService";
import { defineComponent } from "vue";
import Utils, { IList, IPager } from "../../Utils";
import EmployeeServices, { IEmployee } from "../../services/EmployeeService";
import RoomsServices, { IRoom } from "../../services/RoomsService";

export default defineComponent({
  data() {
    return {
      result: MaidTicketServices.getBlankMaidTicketTemplate(),
      pager: Utils.getDefaultPager(),
      resultEmployee: Utils.getBlankListTemplate<IEmployee>(),
      resultRooms: Utils.getBlankListTemplate<IRoom>(),
    };
  },

  mounted() {
    console.log(this.getEmployees());
    console.log(this.getRooms());
    this.getEmployees().then((data) => (this.resultEmployee = data));
    this.getRooms().then((data) => (this.resultRooms = data));
    this.getEmployees().then((data) => {
      this.result.employeeId = JSON.parse(JSON.stringify(data))[0].id;
    });
    this.getRooms().then((data) => {
      this.result.roomId = JSON.parse(JSON.stringify(data)).content[0].id;
    });
  },

  methods: {
    async getEmployees(): Promise<IList<IEmployee>> {
      return await EmployeeServices.getEmployeesByPosition(this.pager, "2");
    },

    async getRooms(): Promise<IList<IRoom>> {
      return await RoomsServices.getList(this.pager);
    },

    selectRoom: function (value: number) {
      this.result.roomId = value;
    },

    selectEmployee: function (value: number) {
      this.result.employeeId = value;
    },

    async add(): Promise<void> {
      console.log(this.result.employeeId);
      console.log(this.result.roomId);
      console.log(this.result);
      await MaidTicketServices.create(this.result);
      this.$router.push({ name: "maid_ticket" });
    },

    back(): void {
      this.$router.push({ name: "maid_ticket" });
    },
  },
});
</script>