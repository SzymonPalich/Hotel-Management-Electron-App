<template>
  <div class="flex flex-row pr-4 rounded-xl">
    <div
      @click="
        this.$parent.getData();
        console.log('test');
      "
      class="flex border-2 border-gray-400 ml-4 rounded-xl"
    >
      <input
        v-model="this.$parent.result.pager.search"
        type="text"
        class="bg-gray-600 px-2 rounded-l-xl outline-none text-lg"
      />
      <div class="flex items-center bg-gray-600 rounded-r-xl">
        <i class="material-icons">search</i>
      </div>
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