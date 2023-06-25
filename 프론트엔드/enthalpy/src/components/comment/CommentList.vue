<template>
  <div class="card-container" style="background-color: white;">
    <v-card flat class="mx-auto comment-card" style="background-color: white;">
      <v-card-title flat class="mx-auto comment-card mt-2" style="margin-left: 5px; color: dimgray; background-color: white;">&nbsp;&nbsp;댓글&nbsp;<span v-if="boardComments.length > 0" :style="countStyle">{{ count }}개</span></v-card-title>
      <v-card-text flat class="mx-auto comment-card no-padding" style="padding-bottom: 0; margin-bottom: 0;">
        <div
          v-for="comment in paginatedComments"
          :key="comment.id"
          :class="['comment-container', {'comment-bg-gray': comment.userId === userId}]"
        >
          <v-divider class="mb-0" style="padding-bottom: 0; margin-bottom: 0;"></v-divider> <!-- Moved the divider here -->
          <div class="hehe">
            <v-card-title flat class="mx-auto comment-card pt-0 pb-0" style="font-size: 14px; padding-left: 22px;">
              &nbsp;{{ comment.writer }}
              <v-chip
                  v-if="boardUserId == comment.userId"
                  small
                  color="#ffebeb"
                  class="ml-2"
                  style="font-size: 10px;"
                  >작성자</v-chip
                >
              <v-card-subtitle class="caption">
                {{ comment.regDate }}
                <v-chip
                  v-if="comment.modifiedDate"
                  small
                  color="grey lighten-2"
                  class="ml-2"
                  style="font-size: 10px;"
                  >수정됨: {{ comment.modifiedDate }}</v-chip
                >
                <v-btn
                  v-if="comment.userId === userId"
                  small
                  elevation="0"
                  @click="modifyComment(comment)"
                  >수정</v-btn
                ><span v-if="comment.userId === userId">|</span>
                <v-btn
                  v-if="comment.userId === userId"
                  small
                  elevation="0"
                  @click="deleteComment(comment.id)"
                  >삭제</v-btn
                >
              </v-card-subtitle>
            </v-card-title>
            <v-card-text flat class="mx-auto comment-card pt-0" style="padding-left: 22px;">&nbsp;{{ comment.contents }}</v-card-text>
          </div>
          <v-row
            v-if="comment.id === editingCommentId"
            no-gutters
            align="center"
            class="pa-2 comment-edit-box"
            dense
          >
            <v-col cols="9">
              <v-textarea
                v-model="editingCommentContent"
                auto-grow
                rows="1"
                flat
                solo
              ></v-textarea>
            </v-col>
            <v-col cols="3" class="text-right">
              <v-btn
                small
                outlined
                color="primary"
                @click="saveEditedComment(comment)"
                >저장</v-btn
              >
              <v-btn small outlined color="error" @click="cancelEdit"
                >취소</v-btn
              >
            </v-col>
          </v-row>
        </div>
      </v-card-text>
    </v-card>
    <div class="text-center pagination-wrapper">
          <v-pagination
            v-if="this.count != 0"
            v-model="page"
            :length="totalPages"
            class="custom-pagination"
            style="box-shadow: none;"
          ></v-pagination>
        </div>
    <v-card flat class="mx-auto mt-2 comment-card" style="background-color: white;">
      <v-card-text style="padding-top: 0;">
        <v-card flat class="mx-auto comment-card rounded-box">
          <v-form @submit.prevent="submitComment">
            <v-row no-gutters>
              <v-col cols="10">
                <v-textarea
                  v-model="commentContent"
                  placeholder="댓글을 남겨보세요."
                  auto-grow
                  rows="1"
                  flat
                  solo
                  height="100px"
                  style="width: 2000%"
                ></v-textarea>
              </v-col>
              <v-col cols="1.5" class="text-right align-end">
                <v-btn v-if="this.commentContent.length == 0" type="submit" outlined color="grey lighten-2" style="margin-right: 10%; margin-top: 40%;">등록</v-btn>
                <v-btn v-if="this.commentContent.length != 0" type="submit" outlined color="#6e6e6e" style="margin-right: 10%; margin-top: 40%;">등록</v-btn>
              </v-col>
            </v-row>
          </v-form>
        </v-card>
      </v-card-text>
    </v-card>

    
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "CommentList",
  props:['boardUserId'],
  // props: {
  //   boardId: {
  //     type: Number,
  //     required: true,
  //   },
  // },
  data() {
    return {
      boardId: "",
      editingCommentId: null,
      editingCommentContent: "",
      userId: this.$store.state.loginUser.id,
      commentContent: "",
      regDate: "",
      boardComments: {
        ...this.comments,
      },
      count: 0,
      page: 1,
      pageSize: 5,
    };
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const boardId = pathName[pathName.length - 1];
    console.log("boardId", boardId);
    this.boardId = boardId;
    this.$store.dispatch("getComments", { boardId: boardId }).then(() => {
      this.page = this.totalPages; // Set the page to the last page
    });
    this.count = this.comments.length;
  },
  computed: {
    ...mapState(["comments"]),
    totalPages() {
      return Math.ceil(this.comments.length / this.pageSize);
    },
    paginatedComments() {
      const startIndex = (this.page - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.comments.slice(startIndex, endIndex);
    },
  },
  watch: {
    comments: {
      immediate: true,
      handler(newValue) {
        this.boardComments = newValue;
        this.count = this.comments.length;
      },
    },
  },
  methods: {
    modifyComment(comment) {
      this.editingCommentId = comment.id;
      this.editingCommentContent = comment.contents;
    },
    saveEditedComment(comment) {
      const currentDate = new Date();
      const year = currentDate.getFullYear();
      const month = String(currentDate.getMonth() + 1).padStart(2, "0");
      const day = String(currentDate.getDate()).padStart(2, "0");
      const hours = String(currentDate.getHours()).padStart(2, "0");
      const minutes = String(currentDate.getMinutes()).padStart(2, "0");
      const seconds = String(currentDate.getSeconds()).padStart(2, "0");
      const formattedDate = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      const updatedComment = {
        id: comment.id,
        boardId: comment.boardId,
        userId: comment.userId,
        writer: comment.writer,
        contents: this.editingCommentContent,
        regDate: comment.regDate,
        modifiedDate: formattedDate,
      };
      console.log("여기서", updatedComment);
      this.$store
        .dispatch("updateComment", updatedComment)
        .then((updatedCommentFromAPI) => {
          const index = this.comments.findIndex(
            (c) => c.id === updatedComment.id
          );
          console.log(index);
          if (index !== -1) {
            this.boardComments.splice(index, 1, updatedCommentFromAPI);
          }
          this.editingCommentId = null;
          this.editingCommentContent = "";
        });
    },
    cancelEdit() {
      this.editingCommentId = null;
      this.editingCommentContent = "";
    },
    deleteComment(commentId) {
      this.$store.dispatch("deleteComment", commentId).then(() => {
        this.$store.dispatch("getComments", { boardId: this.boardId });
      });
    },
    submitComment() {
      if (this.commentContent.length == 0) {
        alert ('댓글을 입력해주세요.');
        return;
      }
      const currentDate = new Date();
      const year = currentDate.getFullYear();
      const month = String(currentDate.getMonth() + 1).padStart(2, "0");
      const day = String(currentDate.getDate()).padStart(2, "0");
      const hours = String(currentDate.getHours()).padStart(2, "0");
      const minutes = String(currentDate.getMinutes()).padStart(2, "0");
      const seconds = String(currentDate.getSeconds()).padStart(2, "0");
      const formattedDate = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      const comment = {
        boardId: this.$store.state.board.id,
        userId: this.$store.state.loginUser.id,
        writer: this.$store.state.loginUser.nickname,
        contents: this.commentContent,
        regDate: formattedDate,
      };

      this.$store
        .dispatch("addComment", comment)
        .then(() => {
          this.commentContent = "";
          this.$store.dispatch("getComments", { boardId: this.boardId });
          this.page = this.totalPages; // Move to the last page after adding a comment
        });
    },
    onPageChange(page) {
      this.page = page;
    },
  },
};
</script>

<style scoped>
.comment-card {
  max-width: 1200px;
}

.comment-container {
  background-color: white;
  height: auto;
}

.comment-bg-gray {
  background-color: #f5f5f5;
}

.comment-edit-box {
  background-color: #e0e0e0; /* Adjust the background color here */
}

.card-container {
  margin-top: -8px;
  margin-bottom: -8px;
  background-color: grey lighten-2;
}

.card-container > .comment-card {
  margin-top: 8px;
  margin-bottom: 8px;
}
.no-padding {
  padding-left: 0;
  padding-right: 0;
  margin-bottom: 0 !important;
}
.rounded-box {
  border: 1px solid #ccc;
  border-radius: 8px;
}
.mb-0 {
  margin-bottom: 0;
}

.board-list__pagination .v-pagination__item {
  box-shadow: none !important;
}

.board-list__pagination .v-pagination__item--active {
  background-color: gray;
  color: white;
}
.v-pagination__navigation {
  box-shadow: none;
}
.v-application .primary {
  background-color: aqua !important;
}

.pagination-wrapper .custom-pagination {
  box-shadow: none;
}

.custom-pagination .v-pagination__item--active {
  background-color: gray;
  color: white;
}

*{
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
