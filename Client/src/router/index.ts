import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import LoginView from '../views/LoginView.vue'

import RepairsView from '../views/repairs/RepairsView.vue'
import RepairsFetchView from '../views/repairs/RepairsFetchView.vue'
import RepairsCreateView from '../views/repairs/RepairsCreateView.vue'
import RepairsEditView from '../views/repairs/RepairsEditView.vue'
import RepairsFinalizationView from '../views/repairs/RepairsFinalizationView.vue'

import MaidTicketView from '../views/maid_ticket/MaidTicketView.vue'
import MaidTicketFetchView from '../views/maid_ticket/MaidTicketFetchView.vue'
import MaidTicketEditView from '../views/maid_ticket/MaidTicketEditView.vue'
import MaidTicketCreateView from '../views/maid_ticket/MaidTicketCreateView.vue'

import ClientsView from '../views/clients/ClientsView.vue'
import ClientsFetchView from '../views/clients/ClientsFetchView.vue'
import ClientsCreateView from '../views/clients/ClientsCreateView.vue'
import ClientsEditView from '../views/clients/ClientsEditView.vue'

import RoomsView from '../views/rooms/RoomsView.vue'
import RoomsFetchView from '../views/rooms/RoomsFetchView.vue'
import RoomsCreateView from '../views/rooms/RoomsCreateView.vue'
import RoomsEditView from '../views/rooms/RoomsEditView.vue'

import EmployeeView from '../views/employees/EmployeeView.vue'
import EmployeeCreateView from '../views/employees/EmployeeCreateView.vue'
import EmployeeFetchView from '../views/employees/EmployeeFetchView.vue'
import EmployeeEditView from '../views/employees/EmployeeEditView.vue'

import ProductView from '../views/product/ProductView.vue'
import ProductFetchView from '../views/product/ProductFetchView.vue'
import ProductEditView from '../views/product/ProductEditView.vue'
import ProductCreateView from '../views/product/ProductCreateView.vue'

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
    path: '/maid_ticket',
    name: 'maid_ticket',
    component: MaidTicketView
  },
  {
    path: '/maid_ticket/:id',
    name: 'maid_ticket-fetch',
    component: MaidTicketFetchView
  },
  {
    path: '/maid_ticket/:id/edit',
    name: 'maid_ticket-edit',
    component: MaidTicketEditView
  },
  {
    path:'/maid_ticket/create',
    name: 'maid_ticket-create',
    component: MaidTicketCreateView
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
  {
    path: '/clients/:id',
    name: 'clients-fetch',
    component: ClientsFetchView
  },
  {
    path: '/clients',
    name: 'clients-create',
    component: ClientsCreateView
  },
  {
    path: '/clients/:id/edit',
    name: 'clients-edit',
    component: ClientsEditView
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
  },
  // Moduł magazynu
  {
    path: '/product',
    name: 'product',
    component: ProductView
  },
  {
    path: '/product/:id',
    name: 'product-fetch',
    component: ProductFetchView
  },
  {
    path: '/product/:id/edit',
    name: 'product-edit',
    component: ProductEditView
  },
  {
    path: '/product/create',
    name: 'product-create',
    component: ProductCreateView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router