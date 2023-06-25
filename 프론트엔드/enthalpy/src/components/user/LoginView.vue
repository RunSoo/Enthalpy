<template>
  <v-card class="container" outlined>
    <h2 class="login-title">로그인</h2>
    <v-text-field
      ref="idInput"
      label="아이디"
      v-model="id"
      outlined
      class="input-field"
      @keydown.enter="focusPassword"
    ></v-text-field>
    <v-text-field
      ref="passwordInput"
      label="비밀번호"
      v-model="password"
      type="password"
      outlined
      class="input-field"
      @keydown.enter="login"
    ></v-text-field>
    <v-btn color="primary" class="mt-3 login-button" block @click="login">로그인</v-btn>
    <router-link to="/user/signin">
      <v-btn text class="mt-1 signup-button" block>회원가입</v-btn>
    </router-link>
  </v-card>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      id: "",
      password: "",
    };
  },
  methods: {
    login() {
      let user = {
        id: this.id,
        password: this.password,
      };
      console.log(user);
      this.$store.dispatch("setLoginUser", user);
    },
    focusPassword() {
      this.$refs.passwordInput.focus();
    },
  },
  mounted() {
    this.$refs.idInput.focus();
  },
};
</script>


<style>
.container {
  max-width: 500px;
  margin: 0 auto;
  padding: 50px;
}

.login-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center;
}

.input-field {
  width: 100%;
  margin-bottom: 20px;
}

.login-button {
  margin-top: 20px;
}

.signup-button {
  margin-top: 10px;
}

</style>
