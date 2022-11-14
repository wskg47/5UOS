import store from '@/store'
import { initMenu } from '@/utils/initMenu'
import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes =
  [
    {
      path: '/',
      name: '登录',
      component: () => import("@/views/login"),
      hidden: true
    }
  ]

let router = new VueRouter({
  routes,
  mode: 'history'
})

router.beforeEach((to, from, next) => {
  if (window.sessionStorage.getItem('tokenStr')) {
    initMenu(router, store)
    next()
  } else {
    if (to.path == '/') {
      next()
    } else {
      next('/?redirect=' + to.path)
    }
  }

})

export default router