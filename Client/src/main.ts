import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vSelect from 'vue-select'
import 'material-design-icons/iconfont/material-icons.css'

const app = createApp(App)

app.component('v-select', vSelect)
app.use(router)

app.mount('#app')