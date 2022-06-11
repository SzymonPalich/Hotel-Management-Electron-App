<template>
  <div class="max-w-xs grid min-w-full min-h-screen place-items-center">
    <div class="bg-neutral-50 shadow-md rounded px-8 pt-6 pb-8 mb-4 w-1/3">
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
          v-model= this.result.email
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
        @keyup.enter="login()"
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
          id="password"
          type="password"
          placeholder="********"
          v-model= this.result.password
        />
        <p v-if="badCredentials" class="wrongCredentials text-red-500 text-s italic">Błędne dane logowania.</p>
      </div>
      <div class="grid place-items-center md:divide-y-8">
        <button
        @click= "login()"
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
        >
          Zaloguj
        </button>
        <hr />
      </div>
      <div class="w-full border-t border-gray-300 mt-2">
        <p class="text-center text-gray-500 text-xs pt-3">
          &copy;2022 Spur Va Go? Wszelkie prawa zastrzeżone.
        </p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import LoginServices, { ILogin } from "../services/LoginService";
import Utils from "../Utils";
import { defineComponent } from "vue";
import { Extension } from "electron";

export default defineComponent({
  data() {
    return {
      result: LoginServices.getBlankLoginTemplate(),
      badCredentials: false
    };
  },
  async created(){
    if(localStorage.getItem('token')!=undefined){
      await this.getData().then((data) => (this.result = data));
        this.redirect(this.result.role);
      }
  },
  methods: {
    async login(): Promise<void> {
      try{
      await LoginServices.login(this.result);
      }
      catch(e){
        this.badCredentials = true;
      }
        await this.getData().then((data) => (this.result = data));
         this.redirect(this.result.role);

    },
    async getData(): Promise<ILogin> {
      return await LoginServices.fetch();
      
    },

    alertDisplay(): void {
      Utils.alertDisplay();
    },

    redirect(role: string): void{
      if(role=="ROLE_MANAGER"){
         this.$router.push("clients");
        }
      else if (role=="ROLE_MAID"){
         this.$router.push("maid_ticket");
        }
      else if (role=="ROLE_TECHNICIAN"){
         this.$router.push("rooms");
        }
      else if (role=="ROLE_RECEPTIONIST"){
          this.$router.push("clients");
        }
      else{
          console.log("Rola nie ma przypisanego widoku!")
        }
    }
  }
});
</script>