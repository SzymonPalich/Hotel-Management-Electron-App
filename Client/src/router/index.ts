import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import LoginView from '../views/LoginView.vue'

import RepairsView from '../views/repairs/RepairsView.vue'
import RepairsFetchView from '../views/repairs/RepairsFetchView.vue'
import RepairsCreateView from '../views/repairs/RepairsCreateView.vue'

import CleaningView from '../views/cleaning/CleaningView.vue'
import CleaningFinishView from '../views/cleaning/CleaningFinishView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  // Moduł pokojówki
  {
    path: '/cleaning',
    name: 'cleaning',
    component: CleaningView
  },
  {
    path: '/cleaning/:id/finish',
    name: 'cleaning-finish',
    component: CleaningFinishView
  },
  // Modół napraw
  {
    path: '/repairs',
    name: 'repairs',
    component: RepairsView
  },
  {
    path: '/repairs/:id',
    name: 'repairs-fetch',
    component: RepairsFetchView
  },
  {
    path: '/repairs/create',
    name: 'repairs-create',
    component: RepairsCreateView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
