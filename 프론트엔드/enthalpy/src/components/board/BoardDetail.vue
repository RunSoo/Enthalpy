<template>
  <div>
    <div class="board-detail">
      <v-card class="board-card" >
        <v-card-title class="board-title" style="vertical-align: bottom !important;">
          <v-row>
            <v-col class="title-col">{{ board.title }}</v-col>
          </v-row>
          
          </v-card-title>
        <v-card-text>
          <div class="board-info">
            <div class="board-writer">작성자: {{ board.writer }} &nbsp;&nbsp;|&nbsp;&nbsp; {{ board.regDate }}</div>
          </div>
          <div class="board-image-container">
            <template v-if="board.img">
            <img
              :src="getImageUrl(board.img)"
              alt="image"
              class="board-image"
            />
          </template>
          </div>
          <pre class="board-contents" style="margin-top: 30px;">{{ board.contents }}</pre>
        </v-card-text>
        <v-card-actions class="board-footer">
          <div class="board-footer-info" style="margin-left: 10px;">
            <div class="board-view-count">조회수: {{ board.viewCnt }}&nbsp;&nbsp;&nbsp;&nbsp;</div>
            <div class="board-like">
              <v-icon class="like-icon" @click="likeUpdate">mdi-thumb-up</v-icon>
              <span class="like-count">{{ board.likeCnt }}</span>
            </div>
          </div>
          <div>
            <v-btn v-if="isCurrentUser" color="transparent" @click="moveUpdate">수정</v-btn>
            <v-btn v-if="isCurrentUser" color="transparent" @click="deleteBoard" style="margin-left: 10px;">삭제</v-btn>
          </div>
        </v-card-actions>
        <br>
        <comment-list v-if="board.id" :boardId="board.id" :boardUserId="board.userId"></comment-list>
      </v-card>
    </div>
    <br>
  </div>
</template>

<script>
import { mapState } from "vuex";
import CommentList from "@/components/comment/CommentList.vue";
export default {
  name: "BoardDetail",
  computed: {
    ...mapState(["board"]),
    isCurrentUser() {
      return this.board.userId === this.$store.state.loginUser.id;
    },
  },
  components: {
    CommentList,
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    const userId = this.$store.state.loginUser.id;
    const payload = {
      id: id,
      userId: userId,
    }
    this.$store.dispatch("getBoard", payload);
  },
  methods: {
    moveUpdate() {
      this.$router.push({ name: "boardUpdate" });
    },
    getImageUrl(fileName) {
      console.log(`@/assets/uploads/${fileName}`);
      return require(`@/assets/uploads/${fileName}`);
    },
    likeUpdate() {
      const pathName = new URL(document.location).pathname.split("/");
      const id = pathName[pathName.length - 1];

      const userId = this.$store.state.loginUser.id;
      let like = {
        boardId: id,
        userId: userId,
      };
      console.log(like);
      this.$store.dispatch("likeUpdate", like);
    },
    deleteBoard() {
      const pathName = new URL(document.location).pathname.split("/");
      const id = pathName[pathName.length - 1];
      this.$store.dispatch("deleteBoard", id);
    },
  },
};
</script>

<style scoped>
.board-detail {
  display: flex;
  justify-content: center;
  align-items: center;
}

.board-card {
  width: 1200px;
  margin-top: 35px;
}

.board-title {
  font-size: 30px;
  font-weight: bold;
  padding-top: 30px;
  padding-left: 30px;
}

.board-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  padding: 0 15px;
}

.board-footer-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.board-writer {
  font-size: 14px;
}

.board-reg-date {
  font-size: 14px;
}

.board-contents {
  font-size: 17px;
  margin-bottom: 10px;
  padding: 0 24px;
}

.board-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
  padding: 0 24px;
  margin-bottom: 5px;
}

.board-view-count {
  font-size: 14px;
}

.board-like {
  display: flex;
  align-items: center;
}

.like-icon {
  font-size: 16px;
  margin-right: 5px;
}

.like-count {
  font-size: 14px;
}
.board-image-container {
  display: flex;
  justify-content: left;
}
.board-image {
  max-width: 100%;
  height: auto;
  margin-bottom: 10px;
  padding: 0 24px;
}
.title-col {
  align-self: flex-end;
}

* {
  font-family: 'NanumBarunGothic';
}

@font-face {
 font-family: 'NanumBarunGothic';
 font-style: normal;
 font-weight: 400;
 src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.eot');
 src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.eot?#iefix') format('embedded-opentype'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.woff') format('woff'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.ttf') format('truetype');
}

@font-face {
 font-family: 'NanumBarunGothic';
 font-style: normal;
 font-weight: 700;
 src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.eot');
 src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.eot?#iefix') format('embedded-opentype'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.woff') format('woff'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.ttf') format('truetype')
}

@font-face {
 font-family: 'NanumBarunGothic';
 font-style: normal;
 font-weight: 300;
 src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot');
 src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot?#iefix') format('embedded-opentype'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.woff') format('woff'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.ttf') format('truetype');
}

.nanumbarungothic * {
 font-family: 'NanumBarunGothic', sans-serif;
}




</style>
