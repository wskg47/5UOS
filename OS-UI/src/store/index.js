import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

import home from './home'
import path from './path'
import department from './department'

export default new Vuex.Store({
  modules: {
    home,
    path,
    department
  }
})