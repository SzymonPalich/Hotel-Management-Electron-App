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
            Realizacja naprawy
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
                {{ this.result.issue }}
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Numer pokoju</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ this.result.room_nr }}
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
                {{ this.result.desc }}
              </dd>
            </div>
            <div
              class="
                bg-white
                px-4
                py-5
                sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6
              "
            >
              <dt class="text-sm font-medium text-gray-500">Uwagi</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <textarea rows="5" maxlength="400"
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
                    focus:border-2
                    focus:border-cyan-400 
                    focus:rounded-xl 
                    resize-none
                  "
                  required
                ></textarea>
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
              <dt class="text-sm font-medium text-gray-500">Cena części</dt>
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
                  type="number"
                  required
                  
                />
              </dd>
            </div>
          </dl>
          <div class="text-center px-4 py-5">
            <button
              class="
                bg-gray-800
                rounded-xl
                px-2
                py-2
                text-white
                border-2 border-black
                hover:
              "
              @click="$router.push({ name: 'repairs' })"
            >
              Finalizuj
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";
import RepairService, { IRepair } from "../../services/RepairService";

let temp_repair: IRepair = {
  id: 1,
  issue: "Telewizor",
  room_nr: 113,
  desc: "Zniszczona matryca"
};

export default class RepairsFinalizationView extends Vue {
  data() {
    return {
      result: temp_repair,
    };
  }
}
</script>