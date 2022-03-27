<template>
  <div class="relative w-full flex flex-col h-full overflow-hidden">
    <div class="rounded-xl mx-48 mt-20">
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
            Dodaj naprawę
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
                    border-2 border-gray-800
                    w-full
                    h-full
                    rounded-sm
                    text-md
                    py-1
                    outline-none
                  "
                  type="text"
                />
              </dd>
            </div>
            <div
              class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"
            >
              <dt class="text-sm font-medium text-gray-500">Numer pokoju</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <input
                  class="
                    border-2 border-gray-800
                    w-full
                    h-full
                    rounded-sm
                    text-md
                    py-1
                    outline-none
                  "
                  type="text"
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
              <dt class="text-sm font-medium text-gray-500">Opis</dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                <textarea
                  class="
                    border-2 border-gray-800
                    w-full
                    h-full
                    rounded-sm
                    text-md
                    py-1
                    outline-none
                  "
                ></textarea>
              </dd>
            </div>
          </dl>
          <div class="text-center px-4 py-5">
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
              @click="$router.push({ name: 'repairs' })"
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
import { Vue } from "vue-class-component";

export default class RepairsCreateView extends Vue {}
</script>