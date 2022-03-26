import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import AboutView from '../views/AboutView.vue'
import CleaningView from '../views/CleaningView.vue'
import TestView from '../views/TestView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: AboutView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/cleaning',
    name: 'cleaning',
    component: CleaningView
  },
  {
    path: '/testview',
    name: 'testview',
    component: TestView
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
