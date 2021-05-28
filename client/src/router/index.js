import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import Order from "@/views/Order";
import OrderDetail from "@/views/OrderDetail";
import Sale from "@/views/Sale";
import SaleDetail from "@/views/SaleDetail";
import InventoryReceive from "@/views/InventoryReceive";
import InventoryDelivery from "@/views/InventoryDelivery";
import InventoryReceiveDetail from "@/views/InventoryReceiveDetail";
import InventoryDeliveryDetail from "@/views/InventoryDeliveryDetail";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/order',
    name: 'Order',
    component: Order
  },
  {
    path: '/orderDetail/:id',
    name: 'OrderDetail',
    component: OrderDetail
  },
  {
    path: '/sale',
    name: 'Sale',
    component: Sale
  },
  {
    path: '/sale/:id',
    name: 'SaleDetail',
    component: SaleDetail
  },
  {
    path: '/inventoryReceive',
    name: 'InventoryReceive',
    component: InventoryReceive
  },
  {
    path: '/inventoryReceiveDetail/:id',
    name: 'InventoryReceiveDetail',
    component: InventoryReceiveDetail
  },
  {
    path: '/inventoryDelivery',
    name: 'InventoryDelivery',
    component: InventoryDelivery
  },
  {
    path: '/inventoryDeliveryDetail/:id',
    name: 'InventoryDeliveryDetail',
    component: InventoryDeliveryDetail
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
