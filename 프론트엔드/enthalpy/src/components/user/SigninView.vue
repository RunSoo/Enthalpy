<template>
  <div class="container">
    <v-card-title class="headline">회원가입</v-card-title>

          <v-row no-gutters>
            <v-col cols="9">
              <v-text-field
                ref="idField"
                label="아이디"
                v-model="id"
                @input="checkIdRules"
                outlined
              ></v-text-field>
            </v-col>
            <v-col cols="3">
              <v-btn @click="checkIdExists" block color="primary" height="63%"
                >중복체크</v-btn
              >
            </v-col>
          </v-row>
          <v-alert v-if="id.length > 0 && !isIdValid" type="error">
            아이디는 영문자나 숫자로 이루어진 8자 이상 12자 이하의 조합이어야
            합니다.
          </v-alert>

          <v-text-field
            ref="pwField"
            label="비밀번호"
            type="password"
            v-model="password"
            @input="checkPasswordRules"
            outlined
          ></v-text-field>
          <v-alert v-if="password.length > 0 && !isPasswordValid" type="error">
            비밀번호는 영문자와 숫자를 모두 사용한 8자 이상 12자 이하의
            조합이어야 합니다.
          </v-alert>

          <v-row no-gutters>
            <v-text-field ref="nameField" label="이름" v-model="name" outlined></v-text-field>
          </v-row>

          <v-row no-gutters>
            <v-col cols="9">
              <v-text-field
                ref="nickField"
                label="닉네임"
                v-model="nickname"
                outlined
              ></v-text-field>
            </v-col>
            <v-col cols="3">
              <v-btn
                @click="checkNicknameExists"
                block
                color="primary"
                height="63%"
                >중복체크</v-btn
              >
            </v-col>
          </v-row>

          <v-text-field
            ref="hField"
            label="키"
            type="number"
            v-model="height"
            @input="calculatebmr"
            outlined
          ></v-text-field>

          <v-text-field
            ref="wField"
            label="몸무게"
            type="number"
            v-model="weight"
            @input="calculatebmr"
            outlined
          ></v-text-field>

          <v-radio-group ref="gField" v-model="gender" row label="성별" @click="calculatebmr">
            <v-radio label="여성" value="f" @click="calculatebmr"></v-radio>
            <v-radio label="남성" value="m" @click="calculatebmr"></v-radio>
          </v-radio-group>

          <v-row no-gutters>
            <v-col cols="4">
              <v-select
                ref="dField"
                v-model="dobYear"
                :items="years"
                label="Year"
                @change="updateDob"
                outlined
              ></v-select>
            </v-col>
            <v-col cols="4">
              <v-select
                v-model="dobMonth"
                :items="months"
                label="Month"
                @change="updateDob"
                outlined
              ></v-select>
            </v-col>
            <v-col cols="4">
              <v-select
                v-model="dobDay"
                :items="days"
                label="Day"
                @change="updateDob"
                outlined
              ></v-select>
            </v-col>
          </v-row>

          <v-text-field
            label="기초대사량"
            type="number"
            v-model.number="bmr"
            outlined
          ></v-text-field>

    <v-row no-gutters class="justify-center">
      <v-col cols="6" sm="6" class="text-center">
        <v-btn @click="regist" color="success" block>등록하기</v-btn>
      </v-col>
      <v-col cols="6" sm="6" class="text-center">
        <router-link to="/">
          <v-btn color="error" block>취소</v-btn>
        </router-link>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "SigninView",
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
      isIdValid: true,
      isPasswordValid: true,
      isIdChecked: false,
      isNicknameChecked: false,
      dobYear: "",
      dobMonth: "",
      dobDay: "",
      years: Array.from(
        { length: 121 },
        (_, i) => new Date().getFullYear() - i
      ),
      months: Array.from({ length: 12 }, (_, i) => i + 1),
      days: Array.from({ length: 31 }, (_, i) => i + 1),
    };
  },
  computed: {},
  methods: {
    updateDob() {
      if (this.dobYear && this.dobMonth && this.dobDay) {
        this.dob = `${this.dobYear}-${this.pad(this.dobMonth)}-${this.pad(
          this.dobDay
        )}`;
        this.calculatebmr();
      }
    },

    pad(value) {
      return String(value).padStart(2, "0");
    },
    regist() {
      if (!Number.isInteger(this.bmr)) {
        alert("기초대사량은 정수값을 입력해주세요. ");
        console.log(this.bmr);
        return;
      }

      
      // if (
      //   this.id === "" ||
      //   this.password === "" ||
      //   this.name === "" ||
      //   this.nickname === "" ||
      //   this.height === "" ||
      //   this.weight === "" ||
      //   this.dob === "" ||
      //   this.age === "" ||
      //   this.gender === "" ||
      //   this.bmr === 0
      // ) {
      //   alert("모든 내용을 입력해주세요");
      //   return;
      // }
      if (this.id === "") {
        alert("아이디는 필수 입력 항목입니다.");
        this.$refs.idField.focus();
        return;
      }
      if (this.password === "") {
        alert("비밀번호는 필수 입력 항목입니다.");
        this.$refs.pwField.focus();
        return;
      }
      if (this.name === "") {
        alert("이름은 필수 입력 항목입니다.");
        this.$refs.nameField.focus();
        return;
      }
      if (this.nickname === "") {
        alert("닉네임은 필수 입력 항목입니다.");
        this.$refs.nickField.focus();
        return;
      }
      if (this.height === "") {
        alert("키는 필수 입력 항목입니다.");
        this.$refs.hField.focus();
        return;
      }
      if (this.weight === "") {
        alert("몸무게는 필수 입력 항목입니다.");
        this.$refs.wField.focus();
        return;
      }
      if (this.dob === "") {
        alert("생년월일은 필수 입력 항목입니다.");
        this.$refs.dField.focus();
        return;
      }
      if (this.gender === "") {
        alert("성별은 필수 입력 항목입니다.");
        this.$refs.gField.focus();
        return;
      }

      if (!this.isIdValid) {
        alert('아이디 규칙을 확인해주세요.')
        return;
      }
      if (!this.isPasswordValid) {
        alert('비밀번호 규칙을 확인해주세요.')
        return;
      }
      if (!this.isIdChecked && !this.isNicknameChecked) {
        alert("아이디와 닉네임 중복체크를 해주세요. ");
        return;
      } else if (!this.isIdChecked) {
        alert("아이디 중복체크를 해주세요. ");
        return;
      } else if (!this.isNicknameChecked) {
        alert("닉네임 중복체크를 해주세요. ");
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
      this.$store.dispatch("createUser", user);
    },
    checkIdExists() {
      if (this.id === "") {
        this.$store.commit("CHECK_ID", false);
        this.isIdChecked = false;
        return;
      }
      console.log(this.id);
      this.$store.dispatch("checkId", this.id).then(() => {
        this.isIdChecked = true;
        console.log(this.$store.state.idExists);
        const idExists = this.$store.state.idExists;
        if (idExists) {
          alert("사용 가능한 아이디입니다.");
        } else {
          alert("이미 사용 중인 아이디입니다.");
        }
      });
    },

    checkNicknameExists() {
      this.$store.state.nicknameExists = false;
      if (this.nickname === "") {
        this.$store.commit("CHECK_NICKNAME", false);
        this.isNicknameChecked = false;
        return;
      }
      console.log(this.nickname);
      this.$store.state.nicknameExists=false;
      this.$store.dispatch("checkNickname", this.nickname).then(() => {
        this.isNicknameChecked = true;
        console.log(this.$store.state.nicknameExists);
        const nicknameExists = this.$store.state.nicknameExists;
        if (nicknameExists) {
          alert("사용 가능한 닉네임입니다.");
        } else {
          alert("이미 사용 중인 닉네임입니다.");
        }
      });
    },

    checkIdRules() {
      // const idRegex = /^[a-zA-Z0-9]{8,12}$/;
      const idRegex = /^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,12}$/;
      this.isIdValid = idRegex.test(this.id);
    },
    checkPasswordRules() {
      const passwordRegex = /^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,12}$/;
      this.isPasswordValid = passwordRegex.test(this.password);
    },
    calculatebmr() {
      const H = this.height;
      const W = this.weight;
      const currentDate = new Date();
      const selectedDate = new Date(this.dob);
      const timeDiff = Math.abs(currentDate.getTime() - selectedDate.getTime());
      const age = Math.floor(timeDiff / (1000 * 3600 * 24 * 365.25));
      const gender = this.gender;

      if (H !== "" && W !== "" && age !== "" && this.dob !== "") {
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
};
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
  margin-top: 30px;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}
</style>
