<template>


   <div class="sidebar">
     <div class="logo_content">
       <div class="logo">
           <i class='bx bx-hotel' id='name_logo'></i>
         <div class="logo_name">SpurVaGo</div>
       </div>
       <!-- <i class='bx bx-menu' id="btn"></i> -->
     </div>
     <ul class="nav_list">
       <!-- <li>
          <i class='bx bx-search'></i>
          <input type="text" placeholder="Wyszukaj...">
          <span class="tooltip">Wyszukaj</span>
       </li> -->
       <li>
        <a href="#">
         <i class='bx bx-grid-alt'></i>
         <span class="links_name">Strona Główna</span>
        </a>
      </li>
       <li>
         <a href="#">
          <i class='bx bx-collection'></i>
          <span class="links_name">Magazyn</span>
         </a>
       </li>
       <li>
        <a href="#">
         <i class='bx bx-money'></i>
         <span class="links_name">Przychody</span>
        </a>
      </li>
      <li>
        <a href="#">
         <i class='bx bx-home'></i>
         <span class="links_name">Pokoje</span>
        </a>
      </li>
      <li>
        <a href="#">
         <i class='bx bx-wrench'></i>
         <span class="links_name">Naprawy</span>
        </a>
      </li>
      <li>
        <a href="#">
         <i class='bx bx-brush-alt'></i>
         <span class="links_name">Pokojówka</span>
        </a>
      </li>
      <li>
        <a href="#">
         <i class='bx bx-file'></i>
         <span class="links_name">Raporty</span>
        </a>
      </li>
     </ul>
     <div class="content">
       <div class="user">
         <div class="user_details">
            <img src="https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/05876b8a-135a-42d6-9412-bdd6f8fad6dd/d6ehtjk-dd05b8a9-7287-4795-8abe-5673bea6945b.png/v1/fill/w_1024,h_750,strp/spurdo_sparde_vector_by_kevinino_d6ehtjk-fullview.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NzUwIiwicGF0aCI6IlwvZlwvMDU4NzZiOGEtMTM1YS00MmQ2LTk0MTItYmRkNmY4ZmFkNmRkXC9kNmVodGprLWRkMDViOGE5LTcyODctNDc5NS04YWJlLTU2NzNiZWE2OTQ1Yi5wbmciLCJ3aWR0aCI6Ijw9MTAyNCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.idrmJAHLT4aD2lWE6T-JIP4Frs2-ZnecB1UAPGnuvw8"
                            alt="profileImg">
           <div class="name_job">
             <div class="name">Jan Spurdziński</div>
             <div class="job">Menadżer</div>
           </div>
         </div>
         <i class='bx bx-log-out' id="log_out"></i>
       </div>
     </div>
   </div>
   <div class="home_content">
     <div class="text">Dashboard</div>
   </div>

</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent ({
    name: 'SideBar',
    methods: {}
});

// let btn = document.querySelector("#btn");

// let sidebar = document.querySelector(".sidebar");
// if(sidebar!=null)
// sidebar.classList.toggle("active");
// let searchBtn = document.querySelector(".bx-search");



</script>

<style>
 /* Google Font CDN Link */
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins" , sans-serif;
}
:root{
    --black-color:#11101d;
    --white-color:#fff;
    --sky-color:#E4E9F7;
    --light-black:#1d1b31;
}
body{
  position: relative;
  min-height: 100vh;
  width: 100%;
  overflow: hidden;
}
::selection{
  color:var(--white-color);
  background: var(--black-color);
} 
.sidebar{
  position:fixed;
  top:0;
  left:0;
  height:100%;
  width:78px;
  background:var(--black-color);
  padding:6px 14px;
  z-index:99;
  transition:all 0.5s ease;
}
.sidebar{
  width:240px;
}
.sidebar .logo_content .logo{
  color:var(--white-color);
  display: flex;
  height:50px;
  width:100%;
  align-items: center;
  opacity: 0;
  pointer-events: none;
  transition:all 0.5s ease;
}
.sidebar .logo_content .logo{
  opacity:1;
  pointer-events: none;
}
.logo_content .logo .logo_name{
  font-size: 20px;
  font-weight: 400;
}
.sidebar #btn{
  position:absolute;
  color:var(--white-color);
  top:6px;
  left:50%;
  font-size: 22px;
  height:50px;
  width:50px;
  text-align: center;
  line-height: 50px;
  transform: translateX(-50%);
  cursor: pointer;
}
.sidebar #btn{
  left:90%;
}
.sidebar ul{
  margin-top:20px;
}
.sidebar ul li{
  position:relative;
  height:50px;
  width:100%;
  margin:5px 0;
  list-style: none;
  line-height: 50px;
}
.sidebar ul li .tooltip{
  position:absolute;
  left:125px;
  top:0;
  transform:translate(-50%, -50%);
  border-radius: 6px;
  height:35px;
  width:120px;
  background: var(--white-color);
  line-height: 35px;
  text-align: center;
  box-shadow: 0 5px 10px rgba(0,0,0,0.2);
  transition:0s;
  opacity:0;
  pointer-events: none;
  display:block;
}
.sidebar ul li .tooltip::before{
  content:'';
  width:0;
  height:0;
  border-top:10px solid transparent;
  border-bottom:10px solid transparent;
  border-right:10px solid var(--white-color);
  position:absolute;
  left:-8px;
  top:7px;
}
.sidebar ul li .tooltip{
  display:none;
}
.sidebar ul li:hover .tooltip{
  transition:all 0.5s ease;
  opacity:1;
  top:50%;
}
.sidebar ul li input{
  position:absolute;
  height:100%;
  width:100%;
  left:0;
  top:0;
  border-radius: 12px;
  outline:none;
  border:none;
  background: #1d1b31;
  padding-left:50px;
  font-size: 18px;
  color:var(--white-color);
}
.sidebar ul li .bx-search{
  position:relative;
  z-index: 99;
  color:var(--white-color);
  font-size: 22px;
  transition:all 0.5s ease;
}
.sidebar ul li .bx-search:hover{
  background:var(--white-color);
  color:#1d1b31;
}
.sidebar ul li a{
  color:var(--white-color);
  display:flex;
  align-items:center;
  text-decoration: none;
  border-radius: 12px;
  white-space: nowrap;
  transition:all 0.4s ease;
}

.sidebar ul li a:hover{
  color:var(--black-color);
  background:var(--white-color);
}
.sidebar ul li i{
  font-size: 18px;
  font-weight: 400;
  height:50px;
  min-width: 50px;
  border-radius: 12px;
  line-height: 50px;
  text-align: center;
}
.sidebar .links_name{
  font-size: 15px;
  font-weight: 400;
  opacity:0;
  pointer-events: none;
  transition:all 0.3s ease;
}
.sidebar .links_name{
  transition:0s;
  opacity:1;
  pointer-events: auto;
}
.sidebar .content{
  position:absolute;
  color:var(--white-color);
  bottom:0;
  left:0;
  width:100%;
}
.sidebar .content .user{
  position:relative;
  padding:10px 6px;
  height:60px;
  background: none;
  transition:all 0.4s ease;
}
.sidebar .content .user{
  background:var(--light-black);
}
.content .user .user_details{
  display:flex;
  align-items:center;
  opacity:0;
  pointer-events: none;
  white-space: nowrap;
  transition:all 0.4s ease;
}
.sidebar .user .user_details{
  opacity:1;
  pointer-events: auto;
}
.content .user .user_details img{
  height:45px;
  width:45px;
  object-fit: cover;
  border-radius: 12px;
}
.name_job{
  margin-left:10px;
}
 .user_details .name{
  font-size: 15px;
  font-weight: 400;
}
 .user_details .job{
  font-size: 12px; 
}
 #log_out{
  position:absolute;
  bottom:5px;
  left:50%;
  transform:translateX(-50%);
  min-width: 50px;
  line-height: 50px;
  font-size: 20px;
  border-radius: 12px;
  text-align: center;
  transition:all 0.4s ease;
  transform: rotate(180deg);
}
.sidebar .user #log_out{
  left:80%;
  background:none;
}
.home_content{
  position:absolute;
  height:100%;
  width:calc(100% - 78px);
  left:78px;
  background:var(--sky-color);
  padding:20px;
  box-shadow: 0 5px 10px rgba(0,0,0,0.2);
  transition:all 0.5s ease;
}
.sidebar ~ .home_content{
  z-index:100;
  width:calc(100% - 240px);
  left:240px;
}
.home_content .text{
  font-size: 25px;
  font-weight: 600;
  color:var(--light-black);
  margin-bottom:15px;
}
.home_content p{
  margin-bottom:15px;
}
#name_logo{
font-size: 30px;
   color: #fff;
   height: 50px;
   min-width: 47px;
   text-align: center;
   line-height: 50px;
}
      
</style>