<template>
  <div class="about">
    <!--顶部-->
    <div class="nav">
      <van-nav-bar
        :title="book.name"
        left-text="返回"
        right-text="..."
        left-arrow
        @click-left="back"
      />
    </div>

    <!--图片-->
    <div class="bookimg">
      <img :src="'/'+book.img">   <!--考察src属性-->
    </div>

    <!--书籍信息---tag-->
    <div class="tag">
      <van-card :title="book.name"
        :desc="book.author"
        :price="book.price">
        <template #tags>
          <p><van-tag plain type="primary">{{book.publisher}}</van-tag></p>
          <p><van-tag type="danger">{{book.category}}</van-tag></p>
        </template>
      </van-card>
    </div>

    <!--书籍的内容介绍-->
    <div class="conduction">
      <p v-html="book.description.replace(/\\n/gm,'<br>')"></p>
    </div>
    
    <!--底部 购买功能-->
    <div class="buy">
      <van-goods-action route>
        <van-goods-action-icon icon="chat-o" text="客服"  />
        <van-goods-action-icon icon="cart-o" to="/home/cart" text="购物车"  />
        <van-goods-action-icon icon="shop-o" text="店铺" />
        <van-goods-action-button
          type="danger"
          text="立即购买" @click="()=>{show = true}"
        />
      </van-goods-action>
    </div>

    <van-sku
      v-model="show"
      :sku="sku"
      :goods="goods"
      :goods-id="goodsId"
      :hide-stock="sku.hide_stock"
      :message-config="messageConfig"
      :initial-sku="initialSku"
      buy-text="买买买"
      add-cart-text="改天再买"
    />
  </div>
</template>


<script>
export default {
  data() {
    return {
      book:this.$route.params.page,
      catName:'',
      show:false,
      goodsId: 2259,
      sku: {
        tree: [
          {
            k: '规格', // skuKeyName：规格类目名称
            k_s: 's1', // skuKeyStr：sku 组合列表（下方 list）中当前类目对应的 key 值，value 值会是从属于当前类目的一个规格值 id
            v: [
              {
                id: '1', // skuValueId：规格值 id
                name: '官方推荐', // skuValueName：规格值名称
                imgUrl: '/'+this.$route.params.page.img, // 规格类目图片，只有第一个规格类目可以定义图片
                previewImgUrl: '/'+this.$route.params.page.img, // 用于预览显示的规格类目图片
              },
              {
                id: '2', // skuValueId：规格值 id
                name: '豪华版', // skuValueName：规格值名称
                imgUrl: '/'+this.$route.params.page.img, // 规格类目图片，只有第一个规格类目可以定义图片
                previewImgUrl: '/'+this.$route.params.page.img, // 用于预览显示的规格类目图片
              },

            ],
            largeImageMode: false, //  是否展示大图模式
          },

        ],
        // 所有 sku 的组合列表，比如红色、M 码为一个 sku 组合，红色、S 码为另一个组合
        list: [
          {
            id: 2259, // skuId
            s1: '1', // 规格类目 k_s 为 s1 的对应规格值 id
            price: this.$route.params.page.price * 100, // 价格（单位分）
            stock_num: 110 // 当前 sku 组合对应的库存
          },
          {
            id: 2258,
            s1: '2', 
            price: (this.$route.params.page.price +20)* 100, 
            stock_num: 40 
          },
        ],
        
        price: this.$route.params.page.price, // 默认价格（单位元）
        stock_num: 227, // 商品总库存
        collection_id: 2259, // 无规格商品 skuId 取 collection_id，否则取所选 sku 组合对应的 id
        none_sku: false, // 是否无规格商品
        messages: [
          {
            // 商品留言
            datetime: '1', // 留言类型为 time 时，是否含日期。'1' 表示包含
            name: '预定日期', // 留言名称
            type: 'date', // 留言类型，可选: id_no（身份证）, text, tel, date, time, email
            required: '0', // 是否必填 '1' 表示必填
             extraDesc: '默认为当天发货'  // 可选值，附加描述文案
          },
          {
            // 商品留言
            datetime: '1', // 留言类型为 time 时，是否含日期。'1' 表示包含
            multiple: '1', // 留言类型为 text 时，是否多行文本。'1' 表示多行
            name: '备注', // 留言名称
            type: 'text', // 留言类型，可选: id_no（身份证）, text, tel, date, time, email
            required: '0', // 是否必填 '1' 表示必填
            extraDesc: '唉要大声说出来！'  // 可选值，附加描述文案
          }
        ],
        hide_stock: false // 是否隐藏剩余库存
      },
      goods: {
        picture: '/'+this.$route.params.page.img
      },
      
      messageConfig: {
        // 数据结构见下方文档
      },
      initialSku: {
        s1: '1',
        // 初始选中数量
        selectedNum: 1,
      },
    }
    
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    getCategory() {
      this.$ajax.post('/getCategory/'+this.$route.params.page)
      .then((res)=> {
        this.catName = res.data;
        return res.data;
      })
    }
  },

  mounted() {
    let _this = this;
    this.$ajax.post('/getCNById/'+(this.$route.params.page.category+1))
    .then((res)=> {
      _this.book.category = res;
    });

  }
}
</script>

<style>
.bookimg {
  display: flex;
  justify-content: center;
}

.tag p{
  margin: 3px;
}
.van-tag {
  padding: 2px;
}

.conduction p {
  margin-left: 12px;
  margin-right: 12px;
}
.conduction {
  white-space:pre-wrap;  /*保留文本中的空白，并且能识别换行符，进行正常的换行*/
}
.conduction {
  letter-spacing:2.5px;
  
}
.buy {
  max-height: 8vh;
  min-height: 8vh;
}
.conduction{
  white-space: pre-line
} 
</style>
