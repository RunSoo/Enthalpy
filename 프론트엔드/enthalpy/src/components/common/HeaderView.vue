<template>
  <div>
    <header class="header">
      <div class="flex-item"></div>
      <div class="logo-container">
        <router-link :to="{ name: 'about' }">
          <img
            :src="logoSrcToShow"
            :alt="logoAlt"
            :class="logoClass"
            style="max-width: 600px"
          />
        </router-link>
      </div>
      <div class="login-container">
        <div class="login-functions" v-if="!getUser">
          <router-link class="frame1-text" to="/user/login">로그인</router-link>
          <router-link class="frame1-text1" :to="{ name: 'signin' }"
            >회원가입</router-link
          >
        </div>
        <div class="user-info" v-if="getUser">
          <div class="frame1-text4">{{ user.nickname }}님 안녕하세요.</div>
          <div class="user-actions">
            <a href="#" class="frame1-text2" @click="logout">로그아웃</a
            >&nbsp;&nbsp;
            <router-link class="frame1-text3" :to="{ name: 'userinfo' }"
              >내 정보</router-link
            >
          </div>
        </div>
      </div>
    </header>
  </div>
</template>

<script>
import { mapState } from "vuex";
import logoImage from "../../assets/logo_default.png"; // Import the logo image
import logoHappy from "../../assets/logo_happy.png"; // Import the logo image
import logoSad from "../../assets/logo_sad.png"; // Import the logo image

export default {
  name: "HeaderView",
  data() {
    return {
      logoSrc: logoImage, // Assign the imported logo image as the source
      logoSrcH: logoHappy, // Assign the imported logo image as the source
      logoSrcS: logoSad, // Assign the imported logo image as the source
      logoAlt: "로고", // Set the alt text for the logo
      today: new Date(),
      regDate: "",
      goal: "",
      userData: {
        ...this.loginUser,
      },
    };
  },
  computed: {
    logoClass() {
      const windowWidth = window.innerWidth;
      if (windowWidth < 600) {
        return "logo-small"; // Add a class for small screens
      } else {
        return "logo-large"; // Add a class for large screens
      }
    },
    ...mapState(["calories", "loginUser"]),
    showLogo() {
      const currentDate = this.regDate;
      const goal = this.goal;
      console.log(goal);
      if (this.goal == 0) {
        return 0;
      }

      for (const calory of this.calories) {
        if (calory.regDate === currentDate && calory.calories >= goal) {
          console.log("성고옹");
          return 1; // Show happy logo if the condition is met
        }
        if (calory.regDate === currentDate && calory.calories < goal) {
          return 2; // Show happy logo if the condition is met
        }
      }
      console.log("실패애");
      return 0; // Show default logo if no matching calory is found
    },
    logoSrcToShow() {
      if (this.showLogo === 1) {
        return this.logoSrcH; // Show happy logo
      } else if (this.showLogo === 2) {
        return this.logoSrcS; // Show sad logo
      } else {
        return this.logoSrc; // Show default logo
      }
    },
    getUser() {
      return !!this.loginUser;
    },
    user() {
      return this.userData;
    },
  },
  created() {
    const year = this.today.getFullYear();
    const month = String(this.today.getMonth() + 1);
    const day = String(this.today.getDate()).padStart(2, "0");

    this.regDate = `${year}-${month}-${day}`; // Assign the formatted date to regDate
    const userId = this.$store.state.loginUser.id;
    this.$store.dispatch("getCalories", userId);
    this.goal = this.$store.state.loginUser.goal;
    this.userData = this.loginUser;
  },
  watch: {
    calories: {
      handler() {
        this.updateLogo();
      },
      deep: true,
    },
    goal() {
      this.updateLogo();
    },
    loginUser(newValue) {
      this.userData = newValue;
    },
  },
  methods: {
    updateLogo() {
      console.log("업데이트");
      this.$nextTick(() => {
        // Delayed execution to ensure DOM update
        this.$forceUpdate();
      });
    },
    logout() {
      this.$store.commit("LOGOUT");
    },
  },
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: flex-center;
  background-color: rgba(255, 255, 255, 1);
  padding: 8px 150px;
}

.flex-item {
  flex: 1;
}

.logoClass {
  width: 607px; /* Adjust the width to your reference */
  height: 187px; /* Adjust the height to your reference */
}

.login-container {
  flex: 1;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.login-functions,
.user-info {
  display: inline-flex;
  align-items: center;
  gap: 20px;
}

@media screen and (max-width: 1440px) {
  .header {
    flex-direction: column;
    align-items: center;
  }

  .login-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px; /* Add gap between login and signup */
    margin-top: 10px; /* Add margin top for spacing */
    margin-right: 40%;
  }
}

.user-info {
  flex-direction: column;
}

.frame1-text,
.frame1-text1,
.frame1-text2,
.frame1-text3,
.frame1-text4 {
  color: rgb(0, 0, 0);
  height: auto;
  position: relative;
  font-size: 15px;
  font-style: normal;
  text-align: left;
  font-family: Inter;
  font-weight: 400;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
  margin-left: 8px;
  margin-right: 8px;
}

.frame1-text:hover,
.frame1-text1:hover,
.frame1-text2:hover,
.frame1-text3:hover {
  color: #8a2be2; /* Set the hover color to purple */
}

@font-face {
    font-family: 'SUITE-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2') format('woff2');
    font-weight: 400;
    font-style: normal;
}

*{
  font-family: 'SUITE-Regular';
}
</style>
