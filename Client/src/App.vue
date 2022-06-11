<template>
  <div class="flex flex-row">
    <sidebar v-if="!this.checkIfLoginPage()" />
    <div class="w-screen">
      <router-view />
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import Sidebar from "./components/Sidebar.vue";

@Options({
  components: {
    Sidebar,
  },
})
export default class App extends Vue {
  private checkIfLoginPage(): boolean {
        if(localStorage.getItem('token')==undefined){
      this.$router.push("login");
    }
    return this.$router.currentRoute.value.name === "login";
  }


}


</script>

<style>
nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: white;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
