<template>
  <div class="nav-wrapper">
    <nav class="main-nav">
      <router-link :to="{ name: 'about' }" class="nav-link" style="max-width: 40%;">About</router-link>
      <router-link v-if="this.loginUser" :to="{ name: 'myCalendar' }" class="nav-link"
        >My Page</router-link
      >
      <router-link v-if="this.loginUser" :to="{ name: 'boardList' }" class="nav-link"
        >Board</router-link
      >
    </nav>
  </div>
</template>

<script>

export default {
  name: "NavView",
  data() {
    return {
      loginUser: "",
    }
  },
  created() {
    this.loginUser = this.$store.state.loginUser;
  },
};

</script>

<style scoped>
.nav-wrapper {
  height: 63px;
  width: 100%;
  padding-left: 25%;
  padding-right: 25%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  position: relative;
}

.main-nav {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.nav-link {
  background-color: #ffffff;
  height: 63px;
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: Inter;
  font-size: 20px;
  color: #000000;
  border-style: hidden;
  outline: none;
  text-decoration: none;
  position: relative;
  overflow: hidden;
}

.nav-link:before {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  width: 100%;
  height: 2.5px;
  background-color: #000000;
  transition: transform 0.3s ease;
  transform: translateX(-50%) scaleX(0);
  transform-origin: left;
}

.nav-link.router-link-exact-active:before {
  background-color: #000000;
  transform: translateX(-50%) scaleX(1);
}

.nav-link:hover:before {
  background-color: rgb(197, 197, 197);
  transform: translateX(-50%) scaleX(1);
}
</style>
