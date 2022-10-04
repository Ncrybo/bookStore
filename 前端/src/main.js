import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import network from "./network"
import Vant from 'vant';
import 'vant/lib/index.css';

Vue.use(Vant);
Vue.config.productionTip = false
Vue.prototype.$ajax = network;

axios.defaults.baseURL = 'http://localhost:18083'
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
