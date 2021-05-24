import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Order from "@/views/Order";
import OrderDetail from "@/views/OrderDetail";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/orders',
    name: 'Order',
    component: Order
  },
  {
    path: '/orderDetail/:id',
    name: 'OrderDetail',
    component: OrderDetail
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
