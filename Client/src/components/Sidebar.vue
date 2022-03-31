<template>
  <div class="container" id="cont">
    <div class="sidebar">
      <div class="logo_content">
        <div class="logo">
          <i class="material-icons">hotel</i>
          <div class="logo_name">SpurVaGo</div>
        </div>
      </div>
      <ul class="nav_list">
        <li>
          <a href="#">
            <i class="material-icons">people</i>
            <span class="links_name">Klienci</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class="material-icons">contact_mail</i>
            <span class="links_name">Pracownicy</span>
          </a>
        </li>
        <li>
          <router-link to="/rooms">
            <i class="material-icons">home</i>
            <span class="links_name">Pokoje</span>
          </router-link>
        </li>
        <li>
          <router-link to="/cleaning">
            <i class="material-icons">rowing</i>
            <span class="links_name">Pokojówka</span>
          </router-link>
        </li>
        <li>
          <router-link to="/repairs">
            <i class="material-icons">build</i>
            <span class="links_name">Naprawy</span>
          </router-link>
        </li>
        <li>
          <a href="#">
            <i class="material-icons">storage</i>
            <span class="links_name">Magazyn</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class="material-icons">attach_money</i>
            <span class="links_name">Przychody</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class="material-icons">receipt</i>
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
          <router-link to="/">
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
  name: "SidebarComponent",
  methods: {},
});
</script>