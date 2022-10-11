<template>
<div class="login">
  <van-nav-bar
  title="请登录"
  left-text="返回"
  right-text="..."
  left-arrow
/>
<div class="login_main">
    <van-image class="img"
  fit="contain"
  width="100px"
  height="100px"
  src="https://i.loli.net/2021/11/20/27FpbsZYBONSljt.png" 
/>                                                       <!--真好玩，哈哈哈哈-->

<van-form @submit="onSubmit">
  <van-field
    v-model="username"
    name="用户名"
    label="用户名"
    placeholder="用户名"
  />
  <van-field
    v-model="password"
    type="password"
    name="密码"
    label="密码"
    placeholder="密码"
  />
  <div style="margin: 16px;">
    <van-button round block type="info" native-type="submit">提交</van-button>
  </div>
</van-form>
</div>
</div>
</template>

<script>
import {login} from '@/api/login'
export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    onSubmit(values) {
      let _this = this;
      login({
        username:this.username,
        password: this.password
      })
      .then(function(res) {
        // console.log(res.message);
        if(res.code == 100) {
          console.log(res)
          localStorage.setItem("token",res.message);  //存储token
          localStorage.setItem("username",res.data.username);  //存储当前的user
          _this.$router.replace('/');
          location.reload();
        }
        else {
          alert("账号或密码错误！");
        }
      })
      .catch(err=> {
        alert("账号或密码错误！");
      })
    },
  },
}
</script>

<style scoped>
.login_main {
    text-align: center;
    margin: 25vh auto;
}
.login_main .img {
  position: relative;
  margin-bottom: 0;
}
</style>
