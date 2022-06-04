<template>
  <div class="max-w-xs grid min-w-full min-h-screen place-items-center">
    <form class="bg-neutral-50 shadow-md rounded px-8 pt-6 pb-8 mb-4 w-1/3">
      <div class="mb-4">
        <label class="block text-gray-700 text-base font-bold mb-2" for="email">
          Adres email
        </label>
        <input
          class="
            shadow
            appearance-none
            border
            rounded
            w-full
            py-2
            px-3
            text-gray-700
            leading-tight
            focus:outline-none focus:shadow-outline
          "
          id="email"
          type="text"
          placeholder="p.plizga@spurva.go"
        />
      </div>
      <div class="mb-6">
        <label
          class="block text-gray-700 text-base font-bold mb-2"
          for="password"
        >
          Hasło
        </label>
        <input
          class="
            shadow
            appearance-none
            border border-red-500
            rounded
            w-full
            py-2
            px-3
            text-gray-700
            mb-3
            leading-tight
            focus:outline-none focus:shadow-outline
          "
          id="password"
          type="password"
          placeholder="********"
        />
        <p class="text-red-500 text-xs italic">Błędne hasło</p>
      </div>
      <div class="grid place-items-center md:divide-y-8">
        <button
          @click="logIn()"
          class="
            bg-blue-500
            hover:bg-blue-700
            text-white
            font-bold
            py-2
            px-4
            rounded
            focus:outline-none focus:shadow-outline
          "
          type="button"
        >
          Nie loguj się
        </button>
        <hr />
      </div>
      <div class="w-full border-t border-gray-300 mt-2">
        <p class="text-center text-gray-500 text-xs pt-3">
          &copy;2022 Spur Va Go? Wszelkie prawa zastrzeżone.
        </p>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";

export default class LoginView extends Vue {
  private logIn(): void {
    this.$router.push({ path: "clients" });
  }
}
</script>