<template>
  <div class="password-check-container">
    <div class="password-check-modal">
      <h3 class="password-check-title">비밀번호 확인</h3>
      <input
        type="password"
        v-model="enteredPassword"
        placeholder="비밀번호를 입력해주세요"
        class="password-check-input"
        @keydown.enter="confirmPassword"
      />
      <button @click="confirmPassword" class="password-check-button">확인</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "PasswordCheck",
  data() {
    return {
      enteredPassword: "",
    };
  },
  methods: {
    confirmPassword() {
      const userPassword = this.$store.state.loginUser.password;
      if (this.enteredPassword === userPassword) {
        this.$router.push("/user/updateinfo");
      } else {
        alert("비밀번호가 일치하지 않습니다.");
        this.enteredPassword = "";
      }
    },
  },
};
</script>

<style scoped>
.password-check-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.password-check-modal {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
  max-width: 400px;
  width: 100%;
}

.password-check-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}

.password-check-input {
  margin-bottom: 10px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 100%;
}

.password-check-button {
  background-color: #6a90b7;
  color: white;
  padding: 8px 16px;
  border: none;
  cursor: pointer;
  border-radius: 4px;
  width: 100%;
}
</style>
