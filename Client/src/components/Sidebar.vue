<template>
<div class="container" id="cont">
  <div class="sidebar">
    <div class="logo_content">
      <div class="logo">
        <i class="bx bx-hotel" id="name_logo"></i>
        <div class="logo_name">SpurVaGo</div>
      </div>
    </div>
    <ul class="nav_list">
      
      <li>
        <a href="#">
          <i class="bx bx-user-circle"></i>
          <span class="links_name">Klienci</span>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="bx bx-hard-hat"></i>
          <span class="links_name">Pracownicy</span>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="bx bx-home"></i>
          <span class="links_name">Pokoje</span>
        </a>
      </li>
      <li>
        <router-link to="cleaning">
            <i class="bx bx-brush-alt"></i>
            <span class="links_name">Pokojówka</span>
        </router-link>
      </li>
      <li>
        <router-link to="home">
          <i class="bx bx-wrench"></i>
          <span class="links_name">Naprawy</span>
        </router-link>
      </li>
      <li>
        <a href="#">
          <i class="bx bx-collection"></i>
          <span class="links_name">Magazyn</span>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="bx bx-money"></i>
          <span class="links_name">Przychody</span>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="bx bx-file"></i>
          <span class="links_name">Raporty</span>
        </a>
      </li>
    </ul>
    <div class="content">
      <div class="user">
        <div class="user_details">
          <div class="name_job">
            <div class="name">Jan Spurdziński</div>
            <div class="job">Menadżer</div>
          </div>
        </div>
        <router-link to="/login">
            <i class="bx bx-log-out" id="log_out"></i>
        </router-link>
      </div>
    </div>
  </div>
</div>


  
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "SideBar",
  methods: {},
});
</script>