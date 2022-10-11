<template>
  <div class="homeCon">
    <!-- 顶部：搜索框和轮番图 -->
    <div class="tou"> 
      <van-sticky>
      <van-search class="search" v-model="value" placeholder="商品搜索" />
      </van-sticky>
      <van-swipe :autoplay="3000">
      <van-swipe-item v-for="(image, index) in images" :key="index">
        <img :src="'/slider/'+image+'.jpg'" />
      </van-swipe-item>
    </van-swipe>
    </div>

    <!-- 宫格，书籍种类的选择 -->
    <div class="select">
      <van-grid :column-num="3" :gutter="10">
        <van-grid-item icon="orders-o" clickable="true" 
        v-for="(item,index) in category" :key="index" :text="item.name" 
         @click="trans(item.id)"
         />
      </van-grid>
    </div>

    <!-- 商品卡片 -->
    <div class="cards">
      <van-card v-for="(item,index) in category_item" :key="index"
        :price="item.price.toFixed(2)"
        :desc="item.author"
        :title="item.name"
        :thumb="'/'+item.img"
        @click-thumb="todetail(item)"
      >
        <template #tags>   <!-- 商品的种类标签 -->
          <van-tag type="danger">{{category[item.category].name}}</van-tag>
        </template>

        <template #footer>
          <van-button size="small" icon="cart"  @click="addAmount(item.id),toast()" color="red"></van-button>
        </template>
      </van-card>
    </div>

    <van-popup
      v-model="show"  :overlay-style="{marginTop: '6vh'}" round
      position="top" :style="{ height: '30%', marginTop: '6vh'}"
    >          <!--------------------------------------搜索------------------------------------------------------->

      <van-cell-group v-for="(item,index) in searchBooks" :key="index">
        <van-cell :value="item.name" @click="todetail(item)" :icon="'/'+item.img"></van-cell>
      </van-cell-group>
    </van-popup>
  </div>
</template>


<script>
import { mapState,mapGetters,mapMutations } from "vuex";
import {get_books_by_category, searchBook, getCategory, getAllBooks} from '@/api/book'
export default {
  data() {
    return {
      value:'',
      images:[1,2,3,4,5],
      category:[],
      category_item:[],
      ac : 'blue',
      show: false,
      searchBooks:[],
    }
  },
  methods : {
    trans(index) {           //选择不同的种类书籍
      let slef = this;
      get_books_by_category(index-1)  //这样就直接得到对应书籍的下标，就不用遍历数组了
    .then(function(response){
      slef.category_item = response;
    })
    },
    todetail(book) {
      this.$router.push({name:'About',params:{page:book}})
    },
    toast() {
      this.$toast.loading({
        message: '加载中...',
        forbidClick: true,
        loadingType: 'spinner',
        duration:500,
      })
    },
    showPop() {
      this.show = true;
    },

    getContainer() {
      return document.querySelector('.search');
    },
    search() {
      searchBook(this.value)
      .then((res) => {
        this.searchBooks = res.data;
      })
      .catch((err) => {
        console.log("出现了错误:"+err)
      })
    },
    addAmount(id) {
            this.$ajax.post("/addAmount/"+id)
            .then((res)=> {
                console.log(res.message);
                if(res.code == 303)
                  this.$router.push({name:'toLogin'})
            })
        },

  },
  watch: {
    value() {
      let _this = this;
      searchBook(this.value)
      .then((res) => {
        _this.searchBooks = res.data;
        if(_this.value.length == 0)
          _this.show = false;
        else
          _this.show = true;
      })
      .catch((err) => {
        console.log("出现了错误:"+err)
      })
    }
  },
  mounted() {
    let self = this;
    getCategory()   //加载书籍种类
    .then(function(response){
      self.category= response;
    })

    getAllBooks()    //加载所有书籍
    .then(function(response){
      self.category_item = response.data;
    })
  }
}
</script>

<style>
  .my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
  }
  .van-swipe-item {
    display: flex;
    justify-content: center;
  }
  img {
    max-width: 100%; height: auto; width: auto;  
  }
  .foot {          /* 底部的标签栏不再遮住其他的内容 */
    max-height: 8vh;
    min-height: 8vh;
  }
</style>
