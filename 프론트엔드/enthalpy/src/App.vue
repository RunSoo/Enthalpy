<template>
  <v-app>
    <header-view />
    <v-main>
      <router-view />
    </v-main>
    <footer-view />
  </v-app>
</template>

<script>
import HeaderView from "@/components/common/HeaderView.vue";
import FooterView from "./components/common/FooterView.vue";
export default {
  components: { HeaderView, FooterView },
  name: "App",

  data: () => ({
    //
  }),
  created() {
    this.$store.dispatch("getUsers");
  },
};
</script>
