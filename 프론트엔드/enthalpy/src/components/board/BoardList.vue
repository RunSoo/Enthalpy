<template>
  <div class="container">
    <v-app>
      <v-container class="board-list-container">
        <v-card class="board-list" :elevation="0">
          <v-card-title class="board-list__title">
            <h3>게시글 목록</h3>
            <v-btn style="" outlined>
              <router-link :to="{ name: 'boardCreate' }" style="text-decoration: none; color: black;" 
                >게시글 등록</router-link
              >
            </v-btn>
          </v-card-title>
          <div class="centered-container">
            <v-card-text class="board-list__filter">
            <v-select v-model="key" :items="filterOptions" style="width: 100px !important; margin-top: 2px; padding-top: 20px; padding-right: 7px;"></v-select>
            <div class="input-wrapper">
              <input
                type="text"
                class="view"
                v-model="word"
                placeholder="검색어를 입력하세요"
                @keydown.enter="search"
              />
            </div>
            <!-- <v-select v-model="orderByDir" :items="sortOptions"></v-select> -->
            <v-btn
              class="board-list__search-button"
              @click="search"
              style="color: black !important"
              >검색</v-btn
            >
          </v-card-text>
          </div>
          
          <table class="board-list__table">
            <thead>
              <tr>
                <th @click="orderByActivity" style="width: 15%;">
                  <b v-if="this.orderBy == 'activity' && this.orderByDir == 'ASC'">운동&nbsp;<span style="font-size: 10px;">▲</span></b>
                  <b v-if="this.orderBy == 'activity' && this.orderByDir == 'DESC'">운동&nbsp;<span style="font-size: 10px;">▼</span></b>
                  <span v-if="this.orderBy != 'activity'">운동</span>
                </th>
                <th @click="orderByTitle" style="width: 40%;">
                  <b v-if="this.orderBy == 'title' && this.orderByDir == 'ASC'">제목&nbsp;<span style="font-size: 10px;">▲</span></b>
                  <b v-if="this.orderBy == 'title' && this.orderByDir == 'DESC'">제목&nbsp;<span style="font-size: 10px;">▼</span></b>
                  <span v-if="this.orderBy != 'title'">제목</span>
                </th>
                <th @click="orderByNickName" style="width: 15%;">
                  <b v-if="this.orderBy == 'nickname' && this.orderByDir == 'ASC'">작성자&nbsp;<span style="font-size: 10px;">▲</span></b>
                  <b v-if="this.orderBy == 'nickname' && this.orderByDir == 'DESC'">작성자&nbsp;<span style="font-size: 10px;">▼</span></b>
                  <span v-if="this.orderBy != 'nickname'">작성자</span>
                </th>
                <th @click="orderByRegDate" style="width: 20%;">
                  <b v-if="this.orderBy == 'regDate' && this.orderByDir == 'ASC'">작성일&nbsp;<span style="font-size: 10px;">▲</span></b>
                  <b v-if="this.orderBy == 'regDate' && this.orderByDir == 'DESC'">작성일&nbsp;<span style="font-size: 10px;">▼</span></b>
                  <span v-if="this.orderBy != 'regDate'">작성일</span>
                </th>
                <th @click="orderByViewCnt" style="width: 10%;">
                  <b v-if="this.orderBy == 'viewCnt' && this.orderByDir == 'ASC'">조회수&nbsp;<span style="font-size: 10px;">▲</span></b>
                  <b v-if="this.orderBy == 'viewCnt' && this.orderByDir == 'DESC'">조회수&nbsp;<span style="font-size: 10px;">▼</span></b>
                  <span v-if="this.orderBy != 'viewCnt'">조회수</span>
                </th>
              </tr>  
            </thead>
              <tbody>
                <tr v-for="board in paginatedBoards" :key="board.id" class="hoverable-row">
                <td class="text-center">{{ board.activity }}</td>
                <td>
                  <div class="router-link-wrapper"><router-link
                    :to="{ name: 'boardDetail', params: { id: board.id } }"
                    style="text-decoration: none; color: inherit;"
                    >{{ board.title }}</router-link
                  ></div>
                  
                </td>
                <td class="text-center">{{ board.writer }}</td>
                <td class="text-center">{{ board.regDate }}</td>
                <td class="text-center">{{ board.viewCnt }}</td>
              </tr>
            </tbody>
          </table>
          <div class="text-center">
            <v-pagination
              v-model="page"
              :length="totalPages"
              style="box-shadow: none;"
              class="board-list__pagination"
            ></v-pagination>
          </div>
        </v-card>
      </v-container>
    </v-app>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "BoardList",
  data() {
    return {
      key: "title",
      word: "",
      orderBy: "",
      orderByDir: "ASC",
      page: 1,
      pageSize: 10,
      filterOptions: [
        { text: "제목", value: "title" },
        { text: "운동", value: "activity" },
        { text: "내용", value: "contents" },
      ],
      sortOptions: [
        { text: "오름차순", value: "ASC" },
        { text: "내림차순", value: "DESC" },
      ],
    };
  },
  computed: {
    ...mapState(["boards"]),
    totalPages() {
      return Math.ceil(this.boards.length / this.pageSize);
    },
    paginatedBoards() {
      const startIndex = (this.page - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.boards.slice(startIndex, endIndex);
    },
  },
  created() {
    this.$store.dispatch("getBoards");
  },
  methods: {
    search() {
      const payload = {
        key: this.key,
        word: this.word,
        orderBy: this.key,
        orderByDir: this.orderByDir,
      };
      this.$store.dispatch("getBoards", payload);
    },
    orderByTitle() {
      const payload = {
        key: this.key,
        word: this.word,
        orderBy: "",
        orderByDir: "",
      };
      if (this.orderBy == 'title' && this.orderByDir == 'ASC') {
        this.orderByDir = 'DESC'
        payload.orderBy = this.orderBy;
        payload.orderByDir = this.orderByDir;
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      if (this.orderBy == 'title' && this.orderByDir == 'DESC') {
        this.orderBy = "",
        this.orderByDir = "",
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      this.orderBy = 'title'
      this.orderByDir = 'ASC'
      payload.orderBy = this.orderBy;
      payload.orderByDir = this.orderByDir;
      this.$store.dispatch("getBoards", payload);
    },
    orderByRegDate() {
      const payload = {
        key: this.key,
        word: this.word,
        orderBy: "",
        orderByDir: "",
      };
      if (this.orderBy == 'regDate' && this.orderByDir == 'ASC') {
        this.orderByDir = 'DESC'
        payload.orderBy = this.orderBy;
        payload.orderByDir = this.orderByDir;
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      if (this.orderBy == 'regDate' && this.orderByDir == 'DESC') {
        this.orderBy = "",
        this.orderByDir = "",
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      this.orderBy = 'regDate'
      this.orderByDir = 'ASC'
      payload.orderBy = this.orderBy;
      payload.orderByDir = this.orderByDir;
      this.$store.dispatch("getBoards", payload);
    },
    orderByViewCnt() {
      const payload = {
        key: this.key,
        word: this.word,
        orderBy: "",
        orderByDir: "",
      };
      if (this.orderBy == 'viewCnt' && this.orderByDir == 'ASC') {
        this.orderByDir = 'DESC'
        payload.orderBy = this.orderBy;
        payload.orderByDir = this.orderByDir;
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      if (this.orderBy == 'viewCnt' && this.orderByDir == 'DESC') {
        this.orderBy = "",
        this.orderByDir = "",
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      this.orderBy = 'viewCnt'
      this.orderByDir = 'ASC'
      payload.orderBy = this.orderBy;
      payload.orderByDir = this.orderByDir;
      this.$store.dispatch("getBoards", payload);
    },
    orderByActivity() {
      const payload = {
        key: this.key,
        word: this.word,
        orderBy: "",
        orderByDir: "",
      };
      if (this.orderBy == 'activity' && this.orderByDir == 'ASC') {
        this.orderByDir = 'DESC'
        payload.orderBy = this.orderBy;
        payload.orderByDir = this.orderByDir;
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      if (this.orderBy == 'activity' && this.orderByDir == 'DESC') {
        this.orderBy = "",
        this.orderByDir = "",
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      this.orderBy = 'activity'
      this.orderByDir = 'ASC'
      payload.orderBy = this.orderBy;
      payload.orderByDir = this.orderByDir;
      this.$store.dispatch("getBoards", payload);
    },
    orderByNickName() {
      const payload = {
        key: this.key,
        word: this.word,
        orderBy: "",
        orderByDir: "",
      };
      if (this.orderBy == 'nickname' && this.orderByDir == 'ASC') {
        this.orderByDir = 'DESC'
        payload.orderBy = this.orderBy;
        payload.orderByDir = this.orderByDir;
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      if (this.orderBy == 'nickname' && this.orderByDir == 'DESC') {
        this.orderBy = "",
        this.orderByDir = "",
        this.$store.dispatch("getBoards", payload);
        return;
      } 
      this.orderBy = 'nickname'
      this.orderByDir = 'ASC'
      payload.orderBy = this.orderBy;
      payload.orderByDir = this.orderByDir;
      this.$store.dispatch("getBoards", payload);
    },
  },
};
</script>

<style scoped>
.board-list-container {
  margin-left: auto;
  margin-right: auto;
}

.board-list {
  background-color: white;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.board-list__title {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.board-list__create-button {
  margin-left: 10px;
}

.board-list__filter {
  max-width: 700px;
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.board-list__search-button {
  margin-left: 10px;
}

.board-list__table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.board-list__table th,
.board-list__table td {
  padding: 10px;
  border: none;
  border-bottom: 1px solid #ddd;
}

.board-list__table th {
  border-top: 1px solid #b1b1b1; /* 테두리 가로줄 스타일 변경 */
  border-bottom: 1px solid #b1b1b1; /* 테두리 가로줄 스타일 변경 */
}

.text-center {
  text-align: center;
}

@media (max-width: 600px) {
  .board-list-container {
    margin-left: 0;
    margin-right: 0;
    padding-left: 10px;
    padding-right: 10px;
  }
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.centered-container {
  display: grid;
  place-items: center;
}

tbody tr:hover {
  /* hover 상태에 적용할 스타일 정의 */
  background-color: #f5f5f5;
  color: #000;
}

.hoverable-row:hover .router-link-wrapper a {
  text-decoration: underline !important;
  color: black;
}
.input-wrapper {
  position: relative;
  width: 80%;
}

.input-wrapper input.view {
  border: none;
  border-bottom: 1px solid #bdbdbd;
  outline: none;
  padding: 5px;
  width: 100%;
}

.input-wrapper input.view:focus {
  border-bottom: 1px solid #767676; /* 포커스 상태일 때 밑줄 색상 변경 */
}

</style>
