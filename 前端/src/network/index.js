import axios from "axios"
import router from "../router/index.js";

let instance = axios.create({});   
instance.interceptors.request.use(    //请求拦截器
    config=>{
        config.headers['token'] = window.localStorage.getItem('token');
        return config;
    },
    err=>{
        Dialog({message: '请求超时'})
        return Promise.reject(err)
    }
)
let _this = this;
instance.interceptors.response.use(       //响应拦截器
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

export default{

    post(url,data) {
        return instance.post(url,data)
    },
    get_category() {
        return axios.post('/category')  //获取书籍的种类
    },
    getAllBooks() {
        return instance.post('/getAllBooks')   //获取所有书籍
    },
    get_books_by_category(cid) {
        return axios.post('/getCategoryBooks/'+cid)  //获取不同种类的书籍，参数：种类的ID
    },
    get_book(book_id){
        return axios.get("/json/details/"+book_id+".json");
    },
    login(data) {          //封装登录axios
        return instance.post('/login',data)
    }

    
}
