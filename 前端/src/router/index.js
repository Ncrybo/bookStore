import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Cart from '../views/Cart.vue'
import List from '../views/homeContent.vue'
import Login from '../views/login.vue'
import My from '../views/my.vue'
import toLogin from '../components/toLogin.vue'
import myinfo from '../components/myInfo.vue'
import ajax from '../network/index.js'

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    redirect:'/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    redirect:'/home/list',
    children: [
      {
        path: 'list',
        name: 'list',
        component: List
      },
      { 
        path: 'cart',
        name: 'cart',
        component: Cart,
        meta: {
          requiresAuth: true
        }
      },

      {
        path:'my',
        name: 'my',
        component: My,
        redirect: '/home/my/info',
        children: [
          {
            path:'toLogin',
            name: 'toLogin',
            component: toLogin
          },
          {
            path: 'info',
            name: 'myinfo',
            component: myinfo,
            meta: {
              requiresAuth: true
            }
          }
        ],
      }
    ]
  },
  {
    path: '/about/:page',
    name: 'About',
    component: () => import( '../views/About.vue')
  },

  {
    path: '/login',
    name: 'login',
    component: Login,
  },
  
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next) => {
  let _this = this;
  if(to.meta.requiresAuth) {
    ajax.post("/test")
  .then((res)=> {
    if(res.code == 100)
    {
      next();
    }
    else {
      console.log("无token");
      next({
        name: 'toLogin',
       // query: { redirect: to.fullPath }
      })
    }
  })
    
  }
  else {
    next()
  }
})

export default router
