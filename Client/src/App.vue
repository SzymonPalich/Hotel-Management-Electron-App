<template>
  <nav v-if="!checkIfLogin()">
    <router-link to="/login">LogOut</router-link> |
    <router-link to="/home">About</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/repairs">Naprawy</router-link>
  </nav>
  <router-view/>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";

export default class App extends Vue { 
  checkIfLogin(){
    return this.$router.currentRoute.value.name === "login"
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: white;
}

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
