<script lang="ts">
import { Vue } from "vue-class-component";

export default class MinibarView extends Vue {
  data() {
    return {
      nr: this.$route.params.nr,
    };
  }
  swap() {
    this.$router.push({ name: "cleaning" });
  }
}
</script>
<template>
  <form class="w-full max-w-sm">
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
          Podgląd
        </h1>
      </div>
      <div class="md:flex md:items-center mb-4">
        <div class="md:w-1/3">
          <label
            class="
              block
              text-gray-500
              font-bold
              md:text-right
              mb-1
              md:mb-0
              pr-4
            "
            for="rum"
          >
            Rum
          </label>
        </div>
        <div class="md:w-2/3">
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
        </div>
      </div>
      <div class="md:flex md:items-center mb-6">
        <div class="md:w-1/3">
          <label
            class="
              block
              text-gray-500
              font-bold
              md:text-right
              mb-1
              md:mb-0
              pr-4
            "
            for="whisky"
          >
            Whisky
          </label>
        </div>
        <div class="md:w-2/3">
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
        </div>
      </div>
      <div class="md:flex md:items-center mb-6">
        <div class="md:w-1/3">
          <label
            class="
              block
              text-gray-500
              font-bold
              md:text-right
              mb-1
              md:mb-0
              pr-4
            "
            for="wodka"
          >
            Wódka
          </label>
        </div>
        <div class="md:w-2/3">
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
        </div>
      </div>
      <div class="md:flex md:items-center mb-6">
        <div class="md:w-1/3">
          <label
            class="
              block
              text-gray-500
              font-bold
              md:text-right
              mb-1
              md:mb-0
              pr-4
            "
            for="tequila"
          >
            Tequila
          </label>
        </div>
        <div class="md:w-2/3">
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
        </div>
      </div>
      <div class="md:flex md:items-center mb-6">
        <div class="md:w-1/3">
          <label
            class="
              block
              text-gray-500
              font-bold
              md:text-right
              mb-1
              md:mb-0
              pr-4
            "
            for="sake"
          >
            Sake
          </label>
        </div>
        <div class="md:w-2/3">
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
        </div>
      </div>
      <div class="md:flex md:items-center">
        <div class="md:w-1/3"></div>
        <div class="md:w-3/3 mx-6">
          <button
            @click="swap()"
            class="
              shadow
              bg-purple-500
              hover:bg-purple-400
              focus:shadow-outline focus:outline-none
              text-white
              font-bold
              py-2
              px-4
              rounded
            "
            type="button"
          >
            Anuluj
          </button>
        </div>
        <div class="md:w-3/3">
          <button
            @click="swap()"
            class="
              shadow
              bg-purple-500
              hover:bg-purple-400
              focus:shadow-outline focus:outline-none
              text-white
              font-bold
              py-2
              px-4
              rounded
            "
            type="button"
          >
            Akceptuj
          </button>
        </div>
      </div>
    </div>
  </form>
</template>

<style scoped>
template {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>