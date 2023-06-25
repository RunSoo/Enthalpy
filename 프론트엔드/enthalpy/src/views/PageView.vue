<template>
  <div>
    <nav-view></nav-view>
    <router-view></router-view>
  </div>
</template>

<script>
import NavView from "../components/common/NavView.vue";
export default {
  name: "PageView",
  components: {
    NavView,
  },
};
</script>

<style></style>
