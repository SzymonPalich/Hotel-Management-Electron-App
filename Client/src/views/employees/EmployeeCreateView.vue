<template>
  <div class="relative w-full flex flex-col h-screen overflow-hidden">
    <div class="rounded-xl mx-24 mt-auto mb-auto">
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
            Dodaj pracownika
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
              <dt class="text-sm font-medium text-gray-500">Imię</dt>
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
                    focus:border-2
                    focus:border-cyan-400 
                    focus:rounded-xl
                  "

                  type="text"
                  required
                  v-model = this.result.firstName
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Nazwisko</dt>
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
                    focus:border-2
                    focus:border-cyan-400 
                    focus:rounded-xl
                  "
                  type="text"
                  required
                  v-model = this.result.lastName
                />
              </dd>
            </div>
            <div
              class="bg-gray-50 px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">E-mail</dt>
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
                    focus:border-2
                    focus:border-cyan-400 
                    focus:rounded-xl
                  "
                  type="email"
                  required
                  v-model = this.result.email
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Numer telefonu</dt>
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
                    focus:border-2
                    focus:border-cyan-400 
                    focus:rounded-xl
                  "
                  type="text"
                  required
                  v-model = this.result.phoneNumber
                />
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
              <dt class="text-sm font-medium text-gray-500">Stanowisko</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <select class="w-full border-2 border-gray-400 px-2 py-0_1 rounded-xl outline-none" @change="selectPosition($event.target.value)">
                  <option value="1">Kierownik</option>
                  <option value="2">Pokojówka</option>
                  <option value="3">Technik</option>
                  <option value="4">Recepcjonista</option>
                </select>
              </dd>
            </div>
            <div
              class="bg-white px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">PESEL</dt>
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
                    focus:border-2
                    focus:border-cyan-400 
                    focus:rounded-xl
                  "
                  type="text"
                  required
                  v-model = this.result.pesel
                />
              </dd>
            </div>
            <div
              class="bg-gray-50 px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Data zatrudnienia</dt>
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
                    focus:border-2
                    focus:border-cyan-400 
                    focus:rounded-xl
                  "
                  type="date"
                  required
                  v-model = this.result.employmentDate
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Pensja</dt>
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
                    focus:border-2
                    focus:border-cyan-400 
                    focus:rounded-xl
                  "
                  type="number"
                  min="0"
                  maxlength="6"
                  required
                  v-model = this.result.salary
                />
              </dd>
            </div>
          </dl>
          <div class="bg-gray-50 text-center px-4 py-3 rounded-b-xl">
            <button
              class="
                bg-gray-800
                rounded-xl
                px-6
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
import EmployeeServices, { IEmployee } from "../../services/EmployeeService";
import { defineComponent } from "vue";
import Utils, {IList, IPager } from "../../Utils";



export default defineComponent({
    data() {
        return {
            result: EmployeeServices.getBlankEmployeeTemplate(),
        };
    },

    mounted() {
      this.result.position = "1";
    },

    methods: {
        async add() : Promise<void> {
          console.log(this.result);
          await EmployeeServices.create(this.result);
          this.$router.push({ name: 'employees' }); 
        },

        selectPosition: function(value: string) {
          this.result.position = value;
        },
    },
});
</script>