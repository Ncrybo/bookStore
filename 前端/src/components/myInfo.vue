<template>
    <div class="myInfo">
        <van-image class="img"
        fit="contain"
        width="100px"
        height="100px"
        src="https://i.loli.net/2021/11/20/27FpbsZYBONSljt.png" 
        />
         <p>欢迎您，{{username}}</p>
          <van-button round type="primary" @click="logout">退出登陆</van-button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            username: localStorage.getItem("username"),
        }
    },
    methods: {
      logout() {
        localStorage.removeItem("username");
        localStorage.removeItem("token");
        location.reload();
      }
  }   
}
</script>



<style scoped>
.myInfo {
    text-align: center;
    margin: 35vh auto;
}
.myInfo .van-button {
    width: 80%;
}
</style>