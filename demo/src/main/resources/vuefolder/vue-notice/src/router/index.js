import { createRouter, createWebHistory } from "vue-router"
import HomeView from "../views/HomeView.vue"
import LoginView from "../views/LoginView.vue"
import JoinView from "../views/JoinView.vue"
import ListPurchaseCompleted from "../views/ListPurchaseCompleted.vue"
import ListStockCom from "../views/ListStockCom.vue"
import Division from "../components/jincomponents/division.vue"
import Department from "../components/jincomponents/department.vue"
import Account from "../components/jincomponents/account.vue"
import Box from "../components/jincomponents/box.vue"
import Productmaster from "../components/jincomponents/Productmaster.vue"
import Purchase from "../components/jincomponents/purchase.vue"
import Store from "../components/jincomponents/store.vue"

const routes = [
    { path: '/login', component: LoginView},
    { path: '/join', component: JoinView},
    { path: '/listPurchaseCompleted', component: ListPurchaseCompleted },
    { path: '/listStockCom', component: ListStockCom },
    { path: '/division', component: Division},
    { path: '/department', component: Department},
    { path: '/account', component: Account},
    { path: '/box', component: Box},
    { path: '/Productmaster', component: Productmaster},
    { path: '/Purchase', component: Purchase},
    { path: '/Store', component: Store}
]


const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;