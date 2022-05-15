<template>
  <div class="relative w-full flex flex-col h-screen overflow-hidden">
    <div class="rounded-xl mx-80 mt-6">
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
            Pokój: {{ id }}
          </h1>
        </div>
        <div class="bg-white h-full rounded-b-xl text-black">
          <dl>
            <div
              class="
                bg-gray-50
                px-2
                py-5
                sm:grid sm:grid-cols-3 sm:gap-5 sm:px-6
              "
            >
              <dt class="text-lg font-medium text-gray-500 text-right">Rum</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    bg-gray-200
                    appearance-none
                    border-2 border-gray-200
                    rounded
                    w-full
                    py-2
                    px-4
                    text-gray-700
                    leading-tight
                    focus:outline-none focus:bg-white focus:border-purple-500
                  "
                  id="rum"
                  type="number"
                  min="0"
                  max="10"
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
              <dt class="text-lg font-medium text-gray-500 text-right">
                Whisky
              </dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    bg-gray-200
                    appearance-none
                    border-2 border-gray-200
                    rounded
                    w-full
                    py-2
                    px-4
                    text-gray-700
                    leading-tight
                    focus:outline-none focus:bg-white focus:border-purple-500
                  "
                  id="whisky"
                  type="number"
                  min="0"
                  max="10"
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
              <dt class="text-lg font-medium text-gray-500 text-right">
                Wódka
              </dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    bg-gray-200
                    appearance-none
                    border-2 border-gray-200
                    rounded
                    w-full
                    py-2
                    px-4
                    text-gray-700
                    leading-tight
                    focus:outline-none focus:bg-white focus:border-purple-500
                  "
                  id="wodka"
                  type="number"
                  min="0"
                  max="10"
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
              <dt class="text-lg font-medium text-gray-500 text-right">
                Tequila
              </dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    bg-gray-200
                    appearance-none
                    border-2 border-gray-200
                    rounded
                    w-full
                    py-2
                    px-4
                    text-gray-700
                    leading-tight
                    focus:outline-none focus:bg-white focus:border-purple-500
                  "
                  id="tequila"
                  type="number"
                  min="0"
                  max="10"
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
              <dt class="text-lg font-medium text-gray-500 text-right">Sake</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    bg-gray-200
                    appearance-none
                    border-2 border-gray-200
                    rounded
                    w-full
                    py-2
                    px-4
                    text-gray-700
                    leading-tight
                    focus:outline-none focus:bg-white focus:border-purple-500
                  "
                  id="sake"
                  type="number"
                  min="0"
                  max="10"
                />
              </dd>
            </div>
          </dl>
          <div class="text-center text-white rounded-xl pb-4">
            <button
              @click="swap()"
              class="border-2 px-2 py-2 rounded-xl bg-red-800 mr-3"
            >
              Anuluj
            </button>
            <button
              @click="swap()"
              class="border-2 px-2 py-2 rounded-xl bg-purple-800"
            >
              Akceptuj
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";
import { IRoom } from "../../services/CleaningService";

export default class CleaningFinishView extends Vue {
  data() {
    return {
      id: this.$route.params.id,
      room: this.$route.params.room
    };
  }
  swap() {
    this.$router.push({ name: "cleaning" });
  }
}

</script>