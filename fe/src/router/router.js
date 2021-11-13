import Vue from "vue";
import Router from "vue-router";
import HomeComp from "../components/Home.vue";
import LoginComp from "../components/Login.vue";
import RegisterComp from "../components/Register.vue";

Vue.use(Router);

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomeComp,
  },
  {
    path: "/login",
    component: LoginComp,
  },
  {
    path: "/signup",
    component: RegisterComp,
  },
];

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});
router.beforeEach((to, from, next) => {
  const publicPages = ["/login", "/signup", "/home", "/"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("user");

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next("/login");
  } else {
    next();
  }
});

export default router;
