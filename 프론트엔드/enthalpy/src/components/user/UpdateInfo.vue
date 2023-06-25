<template>
  <div class="user-info-wrapper">
    <h2 class="info-title">정보 수정</h2>
    <div class="info-grid">
      <div class="info-item">
        <label for="id" class="info-label">아이디</label>
        <input
          type="text"
          id="id"
          v-model="id"
          readonly
          class="info-input"
        /><br />
      </div>
      <div class="info-item">
        <label for="password" class="info-label">비밀번호</label>
        <input
          type="password"
          id="password"
          v-model="password"
          @input="checkPasswordRules"
          class="info-input"
        /><br />
        <span v-if="password.length > 0 && !isPasswordValid"
          >비밀번호는 영문자와 숫자를 모두 사용한 8자 이상 12자 이하의
          조합이어야 합니다.</span
        ><br />
      </div>
      <div class="info-item">
        <label for="name" class="info-label">이름</label>
        <input
          type="text"
          id="name"
          v-model="name"
          class="info-input"
          style="margin-top: 3px"
        /><br />
      </div>
      <div class="info-item">
        <v-row>
          <v-col style="padding-top: 0; padding-bottom: 0">
            <label for="nickname" class="info-label">닉네임</label>
            <button @click="checkNicknameExists" class="check-button">
              중복체크
            </button>
          </v-col>
          <v-col>
            <div class="nickname-input-wrapper">
              <input
                type="text"
                id="nickname"
                v-model="nickname"
                class="info-input"
              />
            </div>
          </v-col>
        </v-row>

        <br />
      </div>
      <div class="info-item">
        <label for="height" class="info-label">키</label>
        <input
          type="number"
          id="height"
          v-model="height"
          @input="calculatebmr"
          class="info-input"
        /><br />
      </div>
      <div class="info-item">
        <label for="weight" class="info-label">몸무게</label>
        <input
          type="number"
          id="weight"
          v-model="weight"
          @input="calculatebmr"
          class="info-input"
        /><br />
      </div>
      <div class="info-item">
        <label for="gender" class="info-label">성별</label>
        <input
          type="text"
          id="gender"
          v-model="gender"
          class="info-input"
        /><br />
      </div>
      <div class="info-item">
        <label for="dob" class="info-label">생년월일</label>
        <input
          type="text"
          id="dob"
          v-model="dateOfBirth"
          readonly
          class="info-input"
        /><br />
      </div>
      <div class="info-item">
        <label for="bmr" class="info-label">기초대사량</label>
        <input type="number" id="bmr" v-model="bmr" class="info-input" /><br />
      </div>
    </div>
    <div class="edit-button-wrapper">
      <button class="edit-button" @click="updateUser">등록</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "UpdateInfo",
  data() {
    return {
      id: "",
      password: "",
      name: "",
      nickname: "",
      height: "",
      weight: "",
      dob: "",
      gender: "f",
      bmr: 0,
      isPasswordValid: true,
      isNicknameChecked: false,
      dateOfBirth: "",
    };
  },
  methods: {
    initializeData() {
      this.id = this.loginUser.id;
      this.password = this.loginUser.password;
      this.name = this.loginUser.name;
      this.nickname = this.loginUser.nickname;
      this.height = this.loginUser.height;
      this.weight = this.loginUser.weight;
      this.dob = this.loginUser.dob;
      this.gender = this.loginUser.gender;
      this.bmr = this.loginUser.bmr;
    },
    updateUser() {
      if (!Number.isInteger(this.bmr)) {
        alert("기초대사량은 정수값을 입력해주세요. ");
        console.log(this.bmr);
        return;
      }

      if (
        this.nickname !== this.loginUser.nickname &&
        !this.isNicknameChecked
      ) {
        alert("닉네임 중복체크를 해주세요. ");
        return;
      }
      if (
        this.id === "" ||
        this.password === "" ||
        this.name === "" ||
        this.nickname === "" ||
        this.height === "" ||
        this.weight === "" ||
        this.dob === "" ||
        this.gender === "" ||
        this.bmr === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }
      let user = {
        id: this.id,
        password: this.password,
        name: this.name,
        nickname: this.nickname,
        height: this.height,
        dob: this.dob,
        weight: this.weight,
        gender: this.gender,
        bmr: this.bmr,
      };
      this.$store.dispatch("updateUser", user);
      // alert("수정되었습니다. ");
    },
    checkPasswordRules() {
      const passwordRegex = /^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,12}$/;
      this.isPasswordValid = passwordRegex.test(this.password);
    },
    checkNicknameExists() {
      if (this.nickname === "") {
        this.$store.commit("CHECK_NICKNAME", false);
        this.isNicknameChecked = false;
        return;
      }
      console.log(this.nickname);
      this.$store.dispatch("checkNickname", this.nickname).then(() => {
        this.isNicknameChecked = true;
        console.log(this.$store.state.nicknameExists);
        const nicknameExists = this.$store.state.nicknameExists;
        if (nicknameExists || this.nickname === this.loginUser.nickname) {
          alert("사용 가능한 닉네임입니다.");
        } else {
          alert("이미 사용 중인 닉네임입니다.");
        }
      });
    },
    calculatebmr() {
      const H = this.height;
      const W = this.weight;
      const currentDate = new Date();
      const selectedDate = new Date(this.dob);
      const timeDiff = Math.abs(currentDate.getTime() - selectedDate.getTime());
      const age = Math.floor(timeDiff / (1000 * 3600 * 24 * 365.25));
      const gender = this.gender;

      if (H !== "" && W !== "" && age !== "") {
        let bmrval;

        if (gender === "f") {
          bmrval = 665.1 + 9.56 * W + 1.85 * H - 4.68 * age;
        } else {
          bmrval = 66.47 + 13.75 * W + 5 * H - 6.76 * age;
        }
        this.bmr = parseInt(bmrval.toFixed(0));
        console.log(Number.isInteger(this.bmr));
      } else {
        this.bmr = 0;
      }
    },
  },
  created() {
    this.initializeData();
    this.dateOfBirth = this.loginUser.dob.split(" ")[0];
  },
  computed: {
    ...mapState(["loginUser"]),
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
  color: #fff;
  background-color: #6a90b7;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.edit-button:hover {
  background-color: #5b7fa3;
}

.check-button {
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  color: #fff;
  background-color: #7b7b7b;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-left: 15px;
  font-size: 0.9rem;
}

.check-button:hover {
  background-color: #6a90b7;
}

.nickname-input-wrapper {
  display: flex;
  align-items: center;
}
</style>
