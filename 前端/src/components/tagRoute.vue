<template>
    <!-- 底部：页面切换的标签栏 -->
    <div class="foot" route >
      <van-tabbar v-model="active">
        <van-tabbar-item name="home" to="/" icon="home-o">主页</van-tabbar-item>
        <van-tabbar-item name="cart" to="/home/cart" icon="cart-o" >购物车</van-tabbar-item>
        <van-tabbar-item name="my" to="/home/my" icon="manager">我的书城</van-tabbar-item>
      </van-tabbar>
    </div>
</template>

<script>
export default {
  data() {
    return {
      active: 'home'
    }
  },

};
</script>

<style>
.foot .van-tabbar {
  height: 6vh;
}
</style>