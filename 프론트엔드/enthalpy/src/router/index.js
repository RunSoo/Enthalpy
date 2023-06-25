import Vue from "vue";
import VueRouter from "vue-router";

import AboutView from "../components/page/AboutView";
import MyPageView from "../components/page/MyPageView";
import MyCalendar from "../components/my/MyCalendar.vue";
import BoardView from "../components/page/BoardView.vue";
import BoardList from "../components/board/BoardList.vue";
import BoardDetail from "../components/board/BoardDetail.vue";
import BoardCreate from "../components/board/BoardCreate.vue";
import BoardUpdate from "../components/board/BoardUpdate.vue";

import LoginView from "../components/user/LoginView.vue";
import SigninView from "../components/user/SigninView.vue";
import UpdateInfo from "../components/user/UpdateInfo.vue";
import UserInfo from "../components/user/UserInfo.vue";
import PasswordCheck from "../components/user/PasswordCheck.vue";

import PageView from "../views/PageView";
import UserView from "../views/UserView";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "page",
    component: PageView,
    children: [
      {
        path: "",
        name: "about",
        component: AboutView,
      },
      {
        path: "mypage",
        name: "myPage",
        component: MyPageView,
        children: [
          {
            path: "",
            name: "myCalendar",
            component: MyCalendar,
          },
        ],
      },
      {
        path: "board",
        name: "board",
        component: BoardView,
        children: [
          {
            path: "",
            name: "boardList",
            component: BoardList,
          },
          {
            path: "create",
            name: "boardCreate",
            component: BoardCreate,
          },
          {
            path: ":id",
            name: "boardDetail",
            component: BoardDetail,
          },
          {
            path: "update",
            name: "boardUpdate",
            component: BoardUpdate,
          },
        ],
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: UserView,
    children: [
      {
        path: "login",
        name: "login",
        component: LoginView,
      },
      {
        path: "signin",
        name: "signin",
        component: SigninView,
      },
      {
        path: "updateinfo",
        name: "updateinfo",
        component: UpdateInfo,
      },
      {
        path: "userinfo",
        name: "userinfo",
        component: UserInfo,
      },
      {
        path: "passwordCheck",
        name: "passwordCheck",
        component: PasswordCheck,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
