<template>
  <div class="container" id="cont">
    <div class="sidebar">
      <div class="logo_content">
        <div class="logo">
          <img class="material-icons ml-3" src="../../public/css/fonts/icons8-occupied-bed-25.png" style="margin-bottom:0.3rem"/>
          <div class="logo_name">SpurVaGo</div>
        </div>
      </div>
      <ul class="nav_list">
        <li v-if="this.isActive('clients') && (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST')" class="router-link-active">
          <router-link to="/clients">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-user-male-25.png"/>
            <span class="links_name ml-3">Klienci</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST'">
          <router-link to="/clients">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-user-male-25.png"/>
            <span class="links_name ml-3">Klienci</span>
          </router-link>
        </li>
        <li v-if="this.isActive('employees') && (this.result.role=='ROLE_MANAGER')" class="router-link-active">
          <router-link to="/employees">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-contact-25.png"/>
            <span class="links_name ml-3 ml-3">Pracownicy</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER'">
          <router-link to="/employees">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-contact-25.png"/>
            <span class="links_name ml-3">Pracownicy</span>
          </router-link>
        </li>
        <li v-if="this.isActive('rooms') && (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST' || this.result.role=='ROLE_TECHNICIAN')" class="router-link-active">
          <router-link to="/rooms">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-hotel-room-key-25.png" style='-webkit-filter: invert(1);filter: invert(1);'/>
            <span class="links_name ml-3">Pokoje</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST' || this.result.role=='ROLE_TECHNICIAN'">
          <router-link to="/rooms">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-hotel-room-key-25.png" style='-webkit-filter: invert(1);filter: invert(1);'/>
            <span class="links_name ml-3">Pokoje</span>
          </router-link>
        </li>
        <li v-if="this.isActive('room_types') && (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST' || this.result.role=='ROLE_TECHNICIAN')" class="router-link-active">
          <router-link to="/room_types">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-home-25.png"/>
            <span class="links_name ml-3">Typy pokoi</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_RECEPTIONIST' || this.result.role=='ROLE_TECHNICIAN'">
          <router-link to="/room_types">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-home-25.png"/>
            <span class="links_name ml-3">Typy pokoi</span>
          </router-link>
        </li>
        <li v-if="this.isActive('maid_ticket')&& (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_MAID'|| this.result.role=='ROLE_RECEPTIONIST')" class="router-link-active">
          <router-link to="/maid_ticket">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-broom-25.png"/>
            <span class="links_name ml-3">Pokojówka</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_MAID'|| this.result.role=='ROLE_RECEPTIONIST'">
          <router-link to="/maid_ticket">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-broom-25.png"/>
            <span class="links_name ml-3">Pokojówka</span>
          </router-link>
        </li>
        <li v-if="this.isActive('repairs') && (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_TECHNICIAN'|| this.result.role=='ROLE_RECEPTIONIST')" class="router-link-active">
          <router-link to="/repairs">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-wrench-25.png"/>
            <span class="links_name ml-3">Naprawy</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_TECHNICIAN'|| this.result.role=='ROLE_RECEPTIONIST'">
          <router-link to="/repairs">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-wrench-25.png"/>
            <span class="links_name ml-3">Naprawy</span>
          </router-link>
        </li>
        <li v-if="this.isActive('product') && (this.result.role=='ROLE_MANAGER' || this.result.role=='ROLE_MAID')" class="router-link-active">
          <router-link to ="/product">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-stack-25.png"/>
            <span class="links_name ml-3">Magazyn</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER'|| this.result.role=='ROLE_MAID'">
          <router-link to="/product">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-stack-25.png"/>
            <span class="links_name ml-3">Magazyn</span>
          </router-link>
        </li>
        <li v-if="this.isActive('accommodation') && (this.result.role=='ROLE_MANAGER'|| this.result.role=='ROLE_RECEPTIONIST')" class="router-link-active">
          <router-link to ="/accommodation">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-occupied-bed-25.png"/>
            <span class="links_name ml-3">Rezerwacje</span>
          </router-link>
        </li>
        <li v-else-if="this.result.role=='ROLE_MANAGER'|| this.result.role=='ROLE_RECEPTIONIST'">
          <router-link to="/accommodation">
            <img class="material-icons ml-3" src="../../public/css/fonts/icons8-occupied-bed-25.png"/>
            <span class="links_name ml-3">Rezerwacje</span>
          </router-link>
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
      this.$router.push('login');
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