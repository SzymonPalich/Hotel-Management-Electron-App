<template>
  <div class="flex flex-row pr-4 rounded-xl">
      <div class="flex border-2 border-gray-400 mr-4 rounded-xl">
        <input type="text" class="bg-gray-600 px-2 rounded-l-xl outline-none text-lg">
        <div class="flex items-center bg-gray-600 rounded-r-xl">
          <i class="material-icons">search</i>
        </div>
      </div>
    <div>
      <select class="bg-gray-600 border-2 border-gray-400 px-2 py-0_1 rounded-xl outline-none text-lg">
        <option>Gotowy</option>
        <option>Rezerwacja</option>
        <option>Sprzątanie</option>
        <option>Usterka</option>
        <option>Zablokowany</option>
      </select>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "SearchBarComponent",
  methods: {},
});
</script>