import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../views/LoginView.vue";
import DashboardView from "../views/DashboardView.vue";

const routes = [
  {
    path: "/",
    component: LoginView
  },
  {
    path: "/dashboard/:id",
    component: DashboardView
  },
  {
    path: "/transfer/:userId",
    component: () => import("../views/TransferView.vue")
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;