import axios from "axios"
import router from "../router/index.js";

let service = axios.create({});   

//请求拦截器
service.interceptors.request.use(    
    config=>{
        config.headers['token'] = window.localStorage.getItem('token');
        return config;
    },
    err=>{
        Dialog({message: '请求超时'})
        return Promise.reject(err)
    }
)
//响应拦截器
service.interceptors.response.use(       
    response=>{
        if(response.data.code == 100){
            //console.log("响应正常");
        }
        else{
            console.log(response.data.message);
        }
        return response.data;
    },
    err =>{
        if(err.response) {
            console.log(err);
            router.replace('/login')
            }
        }
    
)


export default service

