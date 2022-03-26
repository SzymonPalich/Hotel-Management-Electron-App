<template>
  <div class="cleaning">
    <body class="font-family-karla flex">
      <div class="relative w-full flex flex-col h-screen overflow-y-hidden">
        <div class="w-full h-screen overflow-x-hidden flex flex-col">
          <div class="w-full flex-grow p-6">
            <h1 class="text-4xl text-white pb-6 text-center">Sprzątanie</h1>

            <div class="w-full mt-6">
              <div class="overflow-auto rounded-xl">
                <table id="cleaning_table" class="min-w-full">
                  <thead class="bg-gray-800 text-white">
                    <tr>
                      <th
                        class="
                          w-1/3
                          text-left
                          py-3
                          px-4
                          uppercase
                          font-semibold
                          text-sm
                        "
                      >
                        Numer Pokoju
                      </th>
                      <th
                        class="
                          w-1/3
                          text-center
                          py-3
                          px-4
                          uppercase
                          font-semibold
                          text-sm
                        "
                      >
                        Uzupełnienie minibaru
                      </th>
                    </tr>
                  </thead>
                  <tbody class="text-gray-700">
                    <tr v-for="item in results" :key="item.nr" class="bg-white">
                      <td class="w-1/3 text-left py-3 px-4">{{ item.nr }}</td>
                      <td class="w-1/3 text-center py-3 px-4">
                        {{ item.minibar }}
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <p class="pt-3 text-gray-600"></p>
            </div>
          </div>
        </div>
      </div>
    </body>
  </div>
</template>

<script>
import { Vue } from "vue-class-component";

export default class CleaningView extends Vue {
  data() {
    return {
      results: [
        {
          nr: 1231,
          minibar: "Tak",
        },
        {
          nr: 531,
          minibar: "Tak",
        },
        {
          nr: 21,
          minibar: "Nie",
        },
        {
          nr: 37,
          minibar: "Tak",
        },
        {
          nr: 67,
          minibar: "Nie",
        },
        {
          nr: 701,
          minibar: "Tak",
        },
      ],
    };
  }
}
</script>
