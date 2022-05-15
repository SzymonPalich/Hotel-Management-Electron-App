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
            ID Pokoju: {{ this.result.roomId }}
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
                <select v-model="selected" required>
                    <option v-for="emp in resultEmployee.content" :key="emp">
                        {{ emp.firstName }} {{ emp.lastName }}
                    </option>
                </select>
              </dd>
            </div>
            <div
              class="
                bg-gray-50
                px-4
                py-3
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">Id Pokoju</dt>
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
                  type="email"
                  required
                  v-model="this.result.roomId"
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
              <dt class="text-sm font-medium text-gray-500">Data zakończenia</dt>
              <dd v-if="this.result.finalizationDate == null" class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                Nie zakończono
              </dd>
              <dd v-if="this.result.finalizationDate != null" class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.finalizationDate }}
              </dd>
            </div>
          </dl>
          <div class="text-center px-4 py-3 bg-gray-50 rounded-b-xl">
            <button
              class="
                bg-gray-800
                rounded-xl
                px-6
                py-2
                text-white
                border-2 border-black
                hover:
                mx-4
              "
              @click="$router.push({ name: 'clients' })"
            >
              Powrót
            </button>
            <button
              class="
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
import MaidTicketServices, { IMaid } from "../../services/MaidTicketService";
import { defineComponent } from "vue";
import Utils, {IList, IPager } from "../../Utils";
import EmployeeServices, { IEmployee } from "../../services/EmployeeService";



export default defineComponent({
    data() {
        return {
            result: MaidTicketServices.getBlankMaidTicketTemplate(),
            pager: Utils.getDefaultPager(),
            resultEmployee: EmployeeServices.getBlankEmployeeTemplate(),
        };
    },

    mounted() {
        console.log(this.getData());
        this.getData().then((data) => (this.result = data));
        this.getEmployees().then((data) => (this.resultEmployee = data));
    },

    methods: {
        getId(): string {
            return this.$route.params.id as string;
        },

        async getData(): Promise<IMaid> {
            return await MaidTicketServices.fetch(this.getId());
        },

        async getEmployees(): Promise<IList<IEmployee>> {
            return await EmployeeServices.getEmployeesByPosition("2")
        },

        async save(): Promise<void> {
            await MaidTicketServices.update(this.getId(), this.result);
            Utils.acceptedAlert();
            this.$router.push({ name: 'maid_ticket' });
        }
    },
});
</script>