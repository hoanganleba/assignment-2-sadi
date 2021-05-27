import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import Order from "@/views/Order";
import OrderDetail from "@/views/OrderDetail";
import Sale from "@/views/Sale";
import SaleDetail from "@/views/SaleDetail";
import InventoryReceive from "@/components/InventoryReceive";
import InventoryDelivery from "@/components/InventoryDelivery";
import InventoryReceiveDetail from "@/components/InventoryReceiveDetail";
import InventoryDeliveryDetail from "@/components/InventoryDeliveryDetail";

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
    path: '/inventoryReceive/:id',
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
