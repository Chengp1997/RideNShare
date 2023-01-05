import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";
import ExploreView from "../views/ExploreView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import UserDetailView from "../views/UserDetailView.vue";
import PostsView from "../views/PostsView.vue";
import PostDetailView from "../views/PostDetailView.vue";
import OrderView from "../views/OrderView.vue";
import OrderDetailView from "../views/OrderDetailView.vue";
import AboutView from "../views/AboutView.vue";
import ChatView from "../views/ChatView.vue";
import { ElMessage } from "element-plus";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home-view",
    component: HomeView,
  },
  {
    path: "/explore",
    name: "explore-view",
    component: ExploreView,
  },
  {
    path: "/posts",
    name: "posts-view",
    component: PostsView,
  },
  {
    path: "/post/:id",
    name: "post-view",
    component: PostDetailView,
  },
  {
    path: "/login",
    name: "login-view",
    component: LoginView,
  },
  {
    path: "/signup",
    name: "register-view",
    component: RegisterView,
  },
  {
    path: "/user",
    name: "user-view",
    component: UserDetailView,
  },
  {
    path: "/order",
    name: "order-view",
    component: OrderView,
  },
  {
    path: "/orderDetail/:id",
    name: "orderDetail-view",
    component: OrderDetailView,
  },
  {
    path: "/about",
    name: "about-view",
    component: AboutView,
  },
  {
    path: "/chat",
    name: "chat-view",
    component: ChatView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const isAuthenticated = !!sessionStorage.getItem("userInfo");
  if (
    to.path !== "/" &&
    to.path !== "/login" &&
    to.path !== "/signup" &&
    to.path !== "/about" &&
    !isAuthenticated
  ) {
    next({ path: "/login" });
    alert("You need to log in first.");
  } else if (to.path == "/" && isAuthenticated) {
    next({ path: "explore" });
  } else next();
});

export default router;
