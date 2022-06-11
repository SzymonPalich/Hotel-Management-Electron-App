import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'material-design-icons/iconfont/material-icons.css'
import vSelect from 'vue-select'


const app = createApp(App)
app.component('v-select', vSelect)
app.use(router)
app.mount('#app')

