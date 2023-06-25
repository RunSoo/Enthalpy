<template>
  <div class="user-info-wrapper">
    <h2 class="info-title">내 정보</h2>
    <div class="info-grid">
      <div class="info-item">
        <label for="id" class="info-label">아이디</label>
        <input
          type="text"
          id="id"
          v-model="user.id"
          readonly
          class="info-input"
        />
      </div>
      <div class="info-item">
        <label for="password" class="info-label">비밀번호</label>
        <input
          type="password"
          id="password"
          v-model="user.password"
          readonly
          class="info-input"
        />
      </div>
      <div class="info-item">
        <label for="name" class="info-label">이름</label>
        <input
          type="text"
          id="name"
          v-model="user.name"
          readonly
          class="info-input"
        />
      </div>
      <div class="info-item">
        <label for="nickname" class="info-label">닉네임</label>
        <input
          type="text"
          id="nickname"
          v-model="user.nickname"
          readonly
          class="info-input"
        />
      </div>
      <div class="info-item">
        <label for="height" class="info-label">키</label>
        <input
          type="number"
          id="height"
          v-model="user.height"
          readonly
          class="info-input"
        />
      </div>
      <div class="info-item">
        <label for="weight" class="info-label">몸무게</label>
        <input
          type="number"
          id="weight"
          v-model="user.weight"
          readonly
          class="info-input"
        />
      </div>
      <div class="info-item">
        <label for="gender" class="info-label">성별</label>
        <input
          type="text"
          id="gender"
          v-model="user.gender"
          readonly
          class="info-input"
        />
      </div>
      <div class="info-item">
        <label for="dob" class="info-label">생년월일</label>
        <input
          type="text"
          id="dob"
          v-model="dateOfBirth"
          readonly
          class="info-input"
        />
      </div>
      <div class="info-item">
        <label for="bmr" class="info-label">기초대사량</label>
        <input
          type="number"
          id="bmr"
          v-model="user.bmr"
          readonly
          class="info-input"
        />
      </div>
    </div>
    <div class="edit-button-wrapper">
      <button class="edit-button">
        <router-link :to="{ name: 'passwordCheck' }" class="edit-link"
          >수정</router-link
        >
      </button>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "UserInfo",
  data() {
    return {
      showModal: false,
      user: {
        ...this.loginUser,
      },
      dateOfBirth: "",
    };
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    console.log(this.loginUser);
    this.user = this.$store.state.loginUser;
    console.log("user", this.user);
    this.dateOfBirth = this.user.dob.split(' ')[0];
    console.log(this.dateOfBirth);
  },
  watch: {
    loginUser(newValue) {
      this.user = newValue;
    },
  },
};
</script>

<style scoped>
.user-info-wrapper {
  max-width: 600px;
  margin: 0 auto;
  margin-top: 30px;
  padding: 1rem;
  font-family: "Roboto", sans-serif;
  color: #191919;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.info-title {
  text-align: center;
  margin-bottom: 1.5rem;
  font-size: 2rem;
  color: #333;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 1rem;
}

.info-item {
  display: flex;
  flex-direction: column;
  padding: 0.5rem;
}

.info-label {
  font-size: 1.1rem;
  color: #666;
  margin-bottom: 0.5rem;
}

.info-input {
  font-size: 1.2rem;
  padding: 0.5rem;
  border: none;
  border-radius: 5px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.edit-button-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 1.5rem;
}

.edit-button {
  padding: 0.5rem 1rem;
  font-size: 1.2rem;
  color: #373737;
  background-color: #7b7b7b;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.edit-button:hover {
  background-color: #6a90b7;
}

.edit-link {
  color: #fff;
  text-decoration: none;
}
</style>
