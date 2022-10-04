<template>
  <div class="home">

        <router-view></router-view>

        <tag-route></tag-route>
    
  </div>
</template>


<script>
import tagRoute from '../components/tagRoute.vue'
export default {
  components: {tagRoute},
}
</script>

