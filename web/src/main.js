import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd, {notification} from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import * as Icons from '@ant-design/icons-vue';
import axios from "axios";




const app = createApp(App);
app.use(store).use(router).use(Antd).mount('#app')
const icons = Icons;
for (const i in icons){
    app.component(i,icons[i])
}
/**
 * axios 拦截器
 */
axios.interceptors.request.use(function (config){
    console.log('请求参数: ',config);
    const token = store.state.member.token;
    if (token){
        config.headers.token = token;
        console.log("请求headers增加token：",token)
    }
    return config;
},error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response){
    console.log('返回结果： ', response);
    return response;
},error=>{
    console.log('返回错误： ',error);
    const response = error.response;
    const status = response.status;
    if (status === 401){
        notification.error({ description: '登录状态异常,请重新登录' });
        store.commit("setMember",{});
        router.push("/login")
    }
    return Promise.reject(error);
});
axios.defaults.baseURL=process.env.VUE_APP_SERVER
console.log("环境",process.env.NODE_ENV)
console.log("服务端",process.env.VUE_APP_SERVER)


