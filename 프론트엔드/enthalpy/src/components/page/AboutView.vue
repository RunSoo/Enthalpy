<template>
  <div>
    <div class="banner-container">
      <img src="@/assets/그림1.png" alt="배너" class="banner" />
    </div>
    <div class="group2-container">
      <div class="group2-group2">
        <div class="group2-text">
          <span>
            <span> 저희 사이트에 오신 여러분을 환영합니다! </span>
            <br />
            <span>
              저희 사이트는 운동과 체중 조절을 위한 최고의 도구를 제공합니다.
            </span>
            <br />
            <span>
              운동과 체중 조절은 균형 있는 칼로리 소비와 섭취에 달려 있습니다.
              칼로리는 운동과 건강에 대한 중요한 요소 중 하나로 자리
              잡았습니다.</span
            >
            <br />
            <span>
              여러분은 매일 얼마나 많은 칼로리를 소비하고 있는지 궁금하셨을
              것입니다. 칼로리 소비는 운동의 부하를 가늠해보는 훌륭한 방법이기도
              합니다.
            </span>
            <br />
            <span>
              칼로리는 과학적으로 증명된 개념이며, 식품과 운동의 역할을 이해하는
              데 도움이 되는 중요한 개념입니다.
            </span>
          </span>
        </div>
        <div class="group2-image">
          <img
            src="@/assets/calories.jpg"
            alt="group2-a"
            class="group2-ajpg"
            style="width: 150%; height: auto"
          />
        </div>
        <div class="group2-text">
          <span>
            <span>
              그렇다면 칼로리는 무엇일까요? 간단히 말해, 칼로리는 에너지의
              단위입니다. 식품을 소비하면 신체에 에너지가 공급되며, 이를 통해
              신체는 움직이고 기능을 수행합니다. 운동에도 마찬가지로 칼로리가
              사용됩니다. 섭취한 에너지가 사용한 에너지를 초과하면 잉여 에너지가
              쌓입니다. 이것은 전체 계로 보았을 때 흡열 반응으로 볼 수 있습니다.
              흔히 말하는 증량을 하게 되는 현상이 나타납니다. 반대로 사용한
              에너지가 섭취한 에너지보다 많아지면 전체 에너지의 변화량이
              감소하는 방향으로 향하게 됩니다. 이것은 발열 반응으로 볼 수 있죠.
              많은 사람들이 성취하기 원하는 체중 감량은 일종의 발열 반응입니다.
            </span>
            <br />
            <span>
              이러한 원리를 이해하는 것은 건강한 라이프스타일을 살기 위해
              필수적입니다. 체중을 감량하기 위해서는 전체 칼로리의 변화량이
              0보다 작으면, 즉 섭취한 칼로리보다 소비한 칼로리가 더 많으면
              됩니다.
            </span>
            <br />
            <span>
              칼로리 소비를 정확히 계산하려면 어떻게 해야 할까요? 이 때
              MET(Metabolic Equivalent of Task) 방법이 유용합니다. MET는 운동의
              강도와 에너지 소비를 측정하는 지표입니다. 우리 웹사이트에서는
              MET를 활용하여 운동의 칼로리 소비를 계산하고, 여러분이 매일 소모한
              칼로리를 추적할 수 있습니다.
            </span>
          </span>
        </div>
        <div class="group2-image">
          <img
            src="@/assets/exercise.jpg"
            alt="group2-b"
            class="group2-bjpg"
            style="width: 150%; height: auto"
          />
        </div>
        <div class="group2-text">
          <span>
            <span>
              더 나아가, 기초대사량(BMR)이란 무엇일까요? 기초대사량은 정상적인
              상태에서 몸이 유지하기 위해 필요한 최소한의 에너지량을 의미합니다.
              BMR은 나이, 성별, 체중, 신체 활동 수준 등의 요소에 따라 다르며,
              우리 웹사이트에서는 간편한 방법으로 BMR을 계산할 수 있습니다.
            </span>
            <br />
            <span>
              매일 소비한 칼로리를 계산하고, 다른 사용자들과 공유하며 서로에게
              자극을 받을 수 있는 이 웹사이트의 목적과 의의는 무엇일까요? 바로
              건강한 라이프스타일을 지향하는 우리의 가치와 열정입니다. 우리는
              사용자들이 건강을 관리하고 목표를 이루는 데 필요한 도구와 지원을
              제공하기 위해 최선을 다하고 있습니다.
            </span>
            <br />
            <span>
              이제 우리와 함께 건강한 운동과 체중 조절을 위한 여정을
              시작해보세요. 칼로리를 계산하고, 운동을 추적하며, 자신의 목표에
              도달하는 데 도움을 받으세요. 우리 웹사이트에서는 건강과 행복을
              위한 토대를 마련해드릴 것입니다.
            </span>
          </span>
        </div>
      </div>
    </div>
    <div class="group3-text">
      <span> 관련 개념을 더 잘 알고 싶으시다면 참고하세요! </span>
    </div>
    <div class="button-group">
      <button
        class="about-button"
        @mouseenter="toggleButtonColor($event, 'Metabolism')"
      >
        <span class="button-text">MET</span>
      </button>
      <button
        class="about-button"
        @mouseenter="toggleButtonColor($event, '기초대사량')"
      >
        <span class="button-text">기초대사량</span>
      </button>
      <button
        class="about-button"
        @mouseenter="toggleButtonColor($event, '운동')"
      >
        <span class="button-text">운동</span>
      </button>
      <button
        class="about-button"
        @mouseenter="toggleButtonColor($event, '칼로리')"
      >
        <span class="button-text">칼로리</span>
      </button>
      <button
        class="about-button"
        @mouseenter="toggleButtonColor($event, 'enthalpy')"
      >
        <span class="button-text">엔탈피</span>
      </button>
    </div>
    <div class="youtube-container">
      <v-row justify="center">
        <v-col
          cols="12"
          sm="6"
          md="6"
          lg="4"
          v-for="video in videos"
          :key="video.id.videoId"
        >
          <v-card class="video-card" @click="openVideo(video.id.videoId)">
            <v-img
              :src="video.snippet.thumbnails.medium.url"
              class="video-thumbnail"
            ></v-img>
            <v-card-title class="video-title">{{
              video.snippet.title
            }}</v-card-title>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
// import YoutubeSearchResult from "../youtube/YoutubeSearchResult.vue";
import axios from "axios";

export default {
  name: "AboutView",
  components: {
    // YoutubeSearchResult,
  },
  data() {
    return {
      element: null,
      videos: [],
      isElementVisible: false,
    };
  },
  methods: {
    toggleButtonColor(event, value) {
      const buttons = document.querySelectorAll(".about-button");
      buttons.forEach((button) => {
        button.classList.remove("selected");
      });
      event.target.classList.add("selected");
      this.search(value);
    },
    handleScroll() {
      if (this.element) {
        const rect = this.element.getBoundingClientRect();
        const isVisible = rect.top <= window.innerHeight && rect.bottom >= 0;
        this.isElementVisible = isVisible;
      }
    },

    search(value) {
      const URL = "https://www.googleapis.com/youtube/v3/search";
      const API_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
      axios({
        url: URL,
        method: "GET",
        params: {
          key: API_KEY,
          part: "snippet",
          q: value,
          type: "video",
          maxResults: 10,
        },
      })
        .then((res) => res.data.items)
        .then((res) => {
          this.videos = res;
        })
        .catch((err) => console.log(err));
    },
    // 변경된 부분: 영상을 클릭하면 새로운 페이지에서 해당 영상을 시청할 수 있도록 합니다.
    openVideo(videoId) {
      const playerUrl = `https://www.youtube.com/embed/${videoId}`;
      const windowFeatures = "width=800,height=600";
      window.open(playerUrl, "popupWindow", windowFeatures);
    },
  },
  mounted() {
    this.search("엔탈피");
    this.element = document.querySelector(".group2-group2");
    window.addEventListener("scroll", this.handleScroll);
    this.handleScroll();
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.handleScroll);
  },
};
</script>

<style scoped>
* {
  font-family: "SUITE-Regular";
}
.banner {
  padding-left: 10%;
  padding-right: 20%;
  width: 100%;
  min-width: 1200px;
  height: auto;
  background-color: rgba(255, 243, 140, 1);
}

.frame12 {
  background-color: rgba(255, 243, 140, 0.6899999976158142);
  height: 130px;
  width: 100%;
  padding-left: 1%;
  padding-right: 1%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 70px;
  position: relative;
}

.frame13 {
  height: 100%;
  width: 586px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 0;
}

.intro {
  color: #000000;
  text-align: left;
  vertical-align: auto;
  font-size: 40px;
  font-family: Itim;
  width: 107px;
  height: 25px;
  position: relative;
  line-height: auto;
  border-style: hidden;
  outline: none;
}

.title {
  color: #000000;
  text-align: left;
  vertical-align: auto;
  font-size: 10px;
  font-family: Inter;
  width: 600px;
  height: 10px;
  padding-top: 3%;
  padding-bottom: 5%;
  position: relative;
  line-height: auto;
  border-style: hidden;
  outline: none;
}

.image {
  height: 128px;
  width: 260px;
}

.group2-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.group2-text-container {
  max-width: 900px;
  width: 100%;
}

.group2-text {
  margin-bottom: 2rem;
  padding: 3rem;
  margin-left: 24%;
  margin-right: 24%;
  min-width: 700px;
  line-height: 3;
  font-size: 18px;
}

.group2-text p {
  margin: 0;
  font-size: 1rem;
  line-height: 1.5;
}

.group3-text {
  padding: 2rem;
  margin-left: 22%;
  margin-right: 22%;
  font-size: 20px;
  color: purple;
}

.group2-image {
  max-width: 30%;
  height: auto;
  margin-bottom: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.group2-group2 {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 80vh;
}

@media screen and (min-width: 768px) {
  .group2-text-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    column-gap: 2rem;
  }

  .group2-text:nth-child(even) {
    order: -1;
  }
}

.about-container {
  max-width: 960px;
  margin: auto;
  padding: 2rem 1rem;
  text-align: center;
  display: flex; /* 컨테이너를 플렉스 박스로 설정 */
  flex-direction: column; /* 아이템들을 세로로 나열하도록 설정 */
  align-items: center; /* 아이템들을 가운데 정렬 */
}

.group2-group2 {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.group2-image {
  width: 50%;
  height: auto;
  margin-bottom: 2rem;
  margin-right: 10%;
  margin-left: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.about-title {
  font-size: 2.5rem;
  margin-bottom: 1.5rem;
  color: #333;
  text-transform: uppercase;
}

.about-text {
  margin-bottom: 1.5rem;
  font-size: 1.1rem;
  text-align: center; /* 텍스트를 가운데 정렬로 변경 */
}

.about-image {
  display: block;
  margin-bottom: 1.5rem;
}

.button-group {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin-bottom: 2rem;
  align-items: center;
}

.button-wrapper {
  margin: 0.5rem;
  display: flex;
}

.about-button {
  padding: 0.75rem 0.75rem;
  font-size: 1.1rem;
  border-radius: 0.5rem;
  margin-left: 1%;
  margin-right: 1%;
  margin-bottom: 1%;
  background-color: rgba(255, 243, 140, 1);
  border: none;
  color: rgba(0, 0, 0, 1);
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease,
    background-color 0.3s ease;
  width: 9%;
  min-width: 7rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* 그림자 효과 추가 */
}

.about-button.selected {
  background-color: rgb(255, 144, 144);
}

.about-button:hover {
  background-color: rgb(255, 194, 147);
}

.about-button::before {
  content: "";
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  height: 4px;
  background-color: rgba(0, 0, 0, 0.1);
  opacity: 0;
  transition: opacity 0.3s ease;
}

.about-button:hover::before,
.about-button:active::before,
.about-button:focus::before {
  opacity: 1;
}

.button-text {
  position: relative;
  z-index: 1;
}

v-row {
  display: flex;
  margin: 0 50%;
  max-width: 1200px !important;
}

.youtube-container {
  margin: 0 23%;
}

@font-face {
  font-family: "SUITE-Regular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2")
    format("woff2");
  font-weight: 400;
  font-style: normal;
}
</style>
