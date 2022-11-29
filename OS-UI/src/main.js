import Vue from 'vue'
import App from './App.vue'
import router from '@/router'
import store from '@/store'
import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
// 字体图标
import 'font-awesome/css/font-awesome.css'

Vue.use(ElementUi, { size: "small" })

// 统一管理api接口
import * as api from '@/api'
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  beforeCreate() {
    Vue.prototype.$API = api
  },
  router,
  store
}).$mount('#app')
