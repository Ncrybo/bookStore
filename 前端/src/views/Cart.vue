<template>
  <div class="cart">
    <!-- 顶栏 -->
    <div class="nav">
      <van-sticky>
        <van-nav-bar title="购物车" left-text="返回" left-arrow>
          <template #right>
            <span>...</span>
          </template>
        </van-nav-bar>
      </van-sticky>
    </div>
    <div class="catrt-content"></div>

    <!--复选框之商品卡片-->
    <div class="fuxuan">
      <van-checkbox-group
        v-model="results"
        ref="checkboxGroup"
        @change="(names) => setTotalPrice()"
      >
        <!--如果复选框选中值发生变化，则总价发生变化-->
        <van-checkbox
          v-for="(item, index) in books"
          :key="index"
          label-disabled
          :name="index"
        >
          <van-card
            :price="item.price.toFixed(2)"
            :desc="item.author"
            :title="item.name"
            :thumb="'/' + item.img"
            @click-thumb="todetail(item)"
          >
            <template #footer>
              <van-stepper
                v-model="value[index]"
                theme="round"
                button-size="26"
                disable-input
                @plus="addAmount(item.id), toast()"
                @minus="deAmount(item.id), toast()"
              />
            </template>
          </van-card>
          <div class="cai">
            <van-icon size="large" name="delete-o" @click="delbook(item.id)" />
          </div>
        </van-checkbox>
      </van-checkbox-group>
    </div>

    <!-- 提交订单 -->
    <div class="tijiao">
      <van-submit-bar :price="totalprice * 100" button-text="提交订单">
        <van-checkbox v-model="checked" @change="checkAll">全选</van-checkbox>
      </van-submit-bar>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      results: [],
      checked: false,
      books: [],
      totalprice: 0,
      value: [],
    };
  },
  methods: {
    refresh() {
      let _this = this;
      this.$ajax.post("/searchCart").then((res) => {
        this.books = res.data;
      });
    },
    checkAll() {
      if (this.checked) this.$refs.checkboxGroup.toggleAll(true);
      else this.$refs.checkboxGroup.toggleAll(false);
    },
    todetail(id) {
      this.$router.push({ name: "About", params: { page: id } });
    },
    toast() {
      this.$toast.loading({
        message: "加载中...",
        forbidClick: true,
        loadingType: "spinner",
        duration: 500,
      });
    },
    deAmount(id) {
      this.$ajax.post("/deAmount/" + id).then((res) => {
        console.log(res.message);
        this.refresh();
      });
    },
    addAmount(id) {
      this.$ajax.post("/addAmount/" + id).then((res) => {
        console.log(res.message);
        this.refresh();
      });
    },
    delbook(id) {
      this.$ajax.post("/delGoods/" + id).then((res) => {
        console.log(res.message);
        this.refresh();
      });
    },
    setTotalPrice() {
      this.totalprice = 0;
      for (var i = 0; i < this.results.length; i++) {
        this.totalprice += this.books[this.results[i]].price;
      }
    },
  },
  watch: {
    books: function () {
      this.setTotalPrice();
    },
  },

  mounted() {
    let _this = this;
    this.$ajax.post("/searchCart").then((res) => {
      _this.books = res.data;
      for (var i = 0; i < res.data.length; i++) {
        _this.value[i] = res.data[i].amount;
      }
    });
  },
};
</script>

<style scoped>
.cart .van-card {
  width: 88vw;
}
.tijiao .van-submit-bar {
  margin-bottom: 6vh;
}
.fuxuan {
  margin-bottom: 6vh;
}
.fuxuan .van-checkbox-group {
  margin-left: 5px;
}
.fuxuan .van-checkbox {
  position: relative;
  background-color: #fafafa;
  padding-left: 6px;
  margin-bottom: 10px;
}
.cai {
  position: absolute;
  top: 8px;
  right: 6px;
  padding-left: 6px;
}
.van-icon {
  padding-left: 4px;
}
</style>