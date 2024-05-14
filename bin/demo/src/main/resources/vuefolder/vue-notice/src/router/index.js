import { createRouter, createWebHistory } from "vue-router"
import HomeView from "../views/HomeView.vue"
import LoginView from "../views/LoginView.vue"
import JoinView from "../views/JoinView.vue"
import ListPurchaseCompleted from "../views/ListPurchaseCompleted.vue"
import ListStockCom from "../views/ListStockCom.vue"


const routes = [
    { path: '/', component: HomeView},
    { path: '/login', component: LoginView},
    { path: '/join', component: JoinView},
    { path: '/listPurchaseCompleted', component: ListPurchaseCompleted },
    { path: '/listStockCom', component: ListStockCom },
    
]


const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;