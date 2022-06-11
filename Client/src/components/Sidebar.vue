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
        <li v-if="this.isActive('clients') && (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST')" class="router-link-active">
          <router-link to="/clients">
            <i class="material-icons">people</i>
            <span class="links_name">Klienci</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST'">
          <router-link to="/clients">
            <i class="material-icons">people</i>
            <span class="links_name">Klienci</span>
          </router-link>
        </li>
        <li v-if="this.isActive('employees') && (this.result.role=='ROLE_MANAGER')" class="router-link-active">
          <router-link to="/employees">
            <i class="material-icons">contact_mail</i>
            <span class="links_name">Pracownicy</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER'">
          <router-link to="/employees">
            <i class="material-icons">contact_mail</i>
            <span class="links_name">Pracownicy</span>
          </router-link>
        </li>
        <li v-if="this.isActive('rooms') && (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST' || this.result.role=='ROLE_TECHNICIAN')" class="router-link-active">
          <router-link to="/rooms">
            <i class="material-icons">home</i>
            <span class="links_name">Pokoje</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST' || this.result.role=='ROLE_TECHNICIAN'">
          <router-link to="/rooms">
            <i class="material-icons">home</i>
            <span class="links_name">Pokoje</span>
          </router-link>
        </li>
        <li v-if="this.isActive('maid_ticket')&& (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_MAID'|| this.result.role=='ROLE_RECEPTIONIST')" class="router-link-active">
          <router-link to="/maid_ticket">
            <i class="material-icons">rowing</i>
            <span class="links_name">Pokojówka</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_MAID'|| this.result.role=='ROLE_RECEPTIONIST'">
          <router-link to="/maid_ticket">
            <i class="material-icons">rowing</i>
            <span class="links_name">Pokojówka</span>
          </router-link>
        </li>
        <li v-if="this.isActive('repairs') && (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_TECHNICIAN'|| this.result.role=='ROLE_RECEPTIONIST')" class="router-link-active">
          <router-link to="/repairs">
            <i class="material-icons">build</i>
            <span class="links_name">Naprawy</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_TECHNICIAN'|| this.result.role=='ROLE_RECEPTIONIST'">
          <router-link to="/repairs">
            <i class="material-icons">build</i>
            <span class="links_name">Naprawy</span>
          </router-link>
        </li>
        <li v-if="this.isActive('product') && (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_MAID')" class="router-link-active">
          <router-link to ="/product">
            <i class="material-icons">storage</i>
            <span class="links_name">Magazyn</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER'|| this.result.role=='ROLE_MAID'">
          <router-link to="/product">
            <i class="material-icons">storage</i>
            <span class="links_name">Magazyn</span>
          </router-link>
        </li>
        <li v-if="this.isActive('accommodation')" class="router-link-active">
          <router-link to ="/accommodation">
            <i class="material-icons">hotel</i>
            <span class="links_name">Rezerwacje</span>
          </router-link>
        </li>
        <li v-else>
          <router-link to="/accommodation">
            <i class="material-icons">hotel</i>
            <span class="links_name">Rezerwacje</span>
          </router-link>
        </li>
        <li v-if="this.isActive('incomes')" class="router-link-active">
          <a href="#">
            <i class="material-icons">attach_money</i>
            <span class="links_name">Przychody</span>
          </a>
        </li>
        <li v-else>
          <a href="#">
            <i class="material-icons">attach_money</i>
            <span class="links_name">Przychody</span>
          </a>
        </li>
        <li v-if="this.isActive('reports')" class="router-link-active">
          <a href="#">
            <i class="material-icons">receipt</i>
            <span class="links_name">Raporty</span>
          </a>
        </li>
        <li v-else>
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
              <div class="name"> {{ this.result.firstName }} {{ this.result.lastName }}</div>
              <div class="job"> {{ setPosition(this.result.role) }}</div>
            </div>
            <div class="logout_icon" @click="logout()">
              <img src="../../public/css/fonts/outline_logout_white_18dp.png" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import LoginServices, { ILogin } from "../services/LoginService";
import EmployeeServices from "../services/EmployeeService";
export default defineComponent({
  data() {
    return {
      result: LoginServices.getBlankLoginTemplate(),
    };
  },
  mounted() {
    this.getData().then((data) => (this.result = data));
  }, 
  name: "SidebarComponent",
  methods: {
    isActive(path: string): boolean {
      if (this.$route.name?.toString().includes(path)) return true;
      else return false;
    },
    logout(){
      localStorage.removeItem('token');
      window.location.replace("login");
    },

   async getData(): Promise<ILogin> {
      return await LoginServices.fetch();
    },

    setPosition(role: string){
      return EmployeeServices.setPosition(role);
    },
  },
});
</script>