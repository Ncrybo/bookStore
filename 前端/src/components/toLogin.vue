<template>
    <div class="toLogin">
        <van-image class="img"
        fit="contain"
        width="100px"
        height="100px"
        src="https://i.loli.net/2021/11/20/27FpbsZYBONSljt.png" 
        />  
        <h4>您还没有登录，请登录！</h4>
        <van-button round type="primary" @click="topage">去登录</van-button>
    </div>
</template>



<script>
export default {
    methods: {
        topage() {
            this.$router.push('/login')
        }
    }
}
</script>



<style scoped>
.toLogin {
    text-align: center;
    margin: 35vh auto;
}
.toLogin .van-button {
    width: 80%;
}
</style>