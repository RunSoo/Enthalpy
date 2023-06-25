<template>
  <div class="footercontainer-footercontainer">
    <div class="footercontainer-information">
      <span class="footercontainer-frame22">
        <span class="footercontainer-text"><span>Developers</span></span>
      </span>
      <span class="footercontainer-text02">
        <span>
          <span>서울 6반 Enthalpy team</span>
          <br />
          <span>장명주 | 권현수</span>
        </span>
      </span>
    </div>
    <div class="footercontainer-ssafy">
      <div class="footercontainer-image">
        <img
          src="@/assets/ssafy.png"
          alt="ssafy"
          class="footercontainer-image3"
        />
      </div>
      <span class="footercontainer-text07">
        <span>
          <span>
            싸피 1학기
            <span v-html="raw87x8"></span>
          </span>
          <br />
          <span>최종 관통프로젝트</span>
        </span>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "FooterView",
  props: {},
  data() {
    return {
      raw87x8: " ",
    };
  },
};
</script>

<style scoped>
.footercontainer-footercontainer {
  gap: 10px;
  width: 100%;
  height: 150px;
  display: flex;
  padding: 10px 20px 0 470px;
  margin-top: 50px;
  overflow: hidden;
  align-items: flex-start;
  flex-shrink: 0;
  background-color: rgba(222, 222, 222, 1);
}
.footercontainer-information {
  gap: 11px;
  height: 140px;
  display: flex;
  padding: 14px 0 24px 0;
  overflow: hidden;
  align-items: center;
  flex-shrink: 0;
  flex-direction: column;
  justify-content: center;
  background-color: rgba(222, 222, 222, 1);
}
.footercontainer-frame22 {
  gap: 10px;
  margin-right: 580px;
  align-items: flex-start;
}
.footercontainer-text {
  color: rgba(0, 0, 0, 0.699);
  height: auto;
  font-size: 20px;
  font-style: Bold;
  text-align: left;
  font-weight: 700;
  line-height: 30px;
  font-stretch: normal;
}
.footercontainer-text02 {
  color: rgba(0, 0, 0, 0.699);
  width: 699px;
  height: auto;
  font-size: 14px;
  font-style: Regular;
  text-align: left;
  font-weight: 400;
  line-height: 30px;
  font-stretch: normal;
  text-decoration: none;
}
.footercontainer-ssafy {
  display: flex;
  padding: 0 0 0 119px;
  overflow: hidden;
  align-items: flex-start;
  flex-direction: column;
}
.footercontainer-image {
  gap: 10px;
  display: flex;
  padding: 10px;
  margin-left: 30px;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}
.footercontainer-image3 {
  width: 100px;
  height: auto;
}
.footercontainer-text07 {
  color: rgba(0, 0, 0, 0.6991);
  width: 188px;
  height: auto;
  font-size: 14px;
  font-style: Regular;
  text-align: center;
  font-weight: 400;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
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
