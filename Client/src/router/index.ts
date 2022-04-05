import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import LoginView from '../views/LoginView.vue'

import RepairsView from '../views/repairs/RepairsView.vue'
import RepairsFetchView from '../views/repairs/RepairsFetchView.vue'
import RepairsCreateView from '../views/repairs/RepairsCreateView.vue'
import RepairsEditView from '../views/repairs/RepairsEditView.vue'
import RepairsFinalizationView from '../views/repairs/RepairsFinalizationView.vue'

import CleaningView from '../views/cleaning/CleaningView.vue'
import CleaningFinishView from '../views/cleaning/CleaningFinishView.vue'

import ClientsView from '../views/clients/ClientsView.vue'

import RoomsView from '../views/rooms/RoomsView.vue'
import RoomsFetchView from '../views/rooms/RoomsFetchView.vue'
import RoomsCreateView from '../views/rooms/RoomsCreateView.vue'
import RoomsEditView from '../views/rooms/RoomsEditView.vue'

import EmployeeView from '../views/employees/EmployeeView.vue'
import EmployeeCreateView from '../views/employees/EmployeeCreateView.vue'
import EmployeeFetchView from '../views/employees/EmployeeFetchView.vue'
import EmployeeEditView from '../views/employees/EmployeeEditView.vue'

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
  // Moduł napraw
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
  },
  {
    path: '/repairs/:id/edit',
    name: 'repairs-edit',
    component: RepairsEditView
  },
  {
    path: '/repairs/:id/finalization',
    name: 'repairs-finalization',
    component: RepairsFinalizationView
  },
  // Moduł pokoi
  {
    path: '/rooms',
    name: 'rooms',
    component: RoomsView
  },
  {
    path: '/rooms/:id',
    name: 'rooms-fetch',
    component: RoomsFetchView
  },
  {
    path: '/rooms/create',
    name: 'rooms-create',
    component: RoomsCreateView
  },
  {
    path: '/rooms/:id/edit',
    name: 'rooms-edit',
    component: RoomsEditView
  },
  // Moduł klientów
  {
    path: '/clients',
    name: 'clients',
    component: ClientsView
  },
  // Moduł pracownikow
  {
    path: '/employees',
    name: 'employees',
    component: EmployeeView
  },
  {
    path: '/employees/:id/info',
    name: 'employee-info',
    component: EmployeeFetchView
  },
  {
    path: '/employees/create',
    name: 'employees-create',
    component: EmployeeCreateView
  },
  {
    path: '/employees/:id/edit',
    name: 'employees-edit',
    component: EmployeeEditView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
