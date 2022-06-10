import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'material-design-icons/iconfont/material-icons.css'
import axios from "axios";
const app = createApp(App)
const token = localStorage.getItem('token');
axios.defaults.headers.common['Authorization'] = `${token}`
app.use(router)

app.mount('#app')

