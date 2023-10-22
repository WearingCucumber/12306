import { createRouter, createWebHistory } from 'vue-router'
import store from "@/store";
import {notification} from "ant-design-vue";


const routes = [

  {
    path:'/login',
    name: 'login',
    component: ()=>import('../views/LoginView.vue')
  },
  {
    path:'/',
    name: 'main',
    component: ()=>import('../views/MainView.vue'),
    meta:{
      loginRequire:true
    },
    children:[{
      path: 'welcome',
      component:()=> import('../views/main/welcomeView.vue')
    },
      {
        path: "passenger",
        component:()=>import("../views/main/passengerView.vue")

      }]
  },
  {
    path: "",
    redirect:'/welcome'
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
router.beforeEach((to,from,next)=>{
  //要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item){
    console.log(item,"是否需要登陆校验：",item.meta.loginRequire || false);
    return item.meta.loginRequire
  })){
    const  member = store.state.member;
    console.log("页面登录校验开始");
    if (!member.token){
      notification.error({ description: '登录状态异常,请重新登录' });
      next('/login')
    }else {
      next();
    }
  }else {
    next();
  }
})

export default router
