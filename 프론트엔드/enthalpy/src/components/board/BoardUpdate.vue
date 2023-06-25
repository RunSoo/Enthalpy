<template>
  <div>
    <v-app>
      <div>
            <h3 style="text-align: center; margin-top: 40px; font-size: 24px !important; font-family: 'NanumBarunGothic'; font-weight: 600">게시글 수정</h3>
              <v-card-text style="max-width: 1000px; margin-left: auto; margin-right: auto;" class="rounded-shadow">
                <v-form enctype="multipart/form-data">
                  <v-row>
                    <v-col cols="6">
                      <v-combobox
                        :value="getActivity(board.activity_id)"
                        :items="METs"
                        label="운동"
                        readonly
                        @click="forbiddenActivityModify"
                      ></v-combobox>
                    </v-col>
                    <v-col cols="6">
                      <v-text-field
                        v-model="board.duration"
                        type="number"
                        label="운동 시간"
                        min="0"
                        class="input-style"
                        readonly
                        @click="forbiddenDurationModify"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-text-field v-model="board.title" label="제목"></v-text-field>
                  <v-file-input
                    v-model="file"
                    label="파일"
                    @change="handleFileChange"
                  ></v-file-input>
                  <v-textarea
                    v-model="board.contents"
                    label="내용"
                    placeholder="내용을 입력하세요"
                    rows="13"
                  ></v-textarea>
                </v-form>
              </v-card-text>
              <v-card-actions style="display: flex; justify-content: center;">
                <v-btn @click="updateBoard" color="primary">수정</v-btn>
                <v-btn to="/board/${board.id}" color="primary" text>취소</v-btn>
              </v-card-actions>
          </div>
    </v-app>
  </div>
</template>

<script>
import {mapState} from 'vuex';

export default {
  name: "BoardUpdate",
  data() {
    return {
      file: '',
      activity_id: "",
      duration: "",
      title: "",
      contents: "",
    };
  },
  computed: {
    ...mapState(['board', 'METs']),
  },
  created() {
    this.$store.dispatch("getMETs");
  },
  mounted() {
    // 기존 파일 선택 초기화
    this.file = this.board.img ? new File([], this.board.img) : null;
  },
  methods: {
    getActivity(activity_id) {
      const matchedActivity = this.METs.find((met) => met.id === activity_id);
      return matchedActivity ? matchedActivity.activity : '';
    },
    handleActivityChange(value) {
      this.activity_id = value.id;
    },
    handleFileChange(event) {
      this.file = event.target.files[0] || ''; // 파일이 선택되지 않았을 때 빈 문자열로 설정
    },

    forbiddenActivityModify() {
      alert("운동은 수정할 수 없습니다.", "알림", {
        confirmButtonText: "확인",
      });
    },
    forbiddenDurationModify() {
      alert("운동 시간은 수정할 수 없습니다.", "알림", {
        confirmButtonText: "확인",
      });
    },
    updateBoard() {
      if (
        this.board.title == "" || this.board.title === null
      ) {
          console.log(this.title);
          alert("제목은 필수 입력사항입니다.", "알림", {
            confirmButtonText: "확인",
          });
          return;
        }
      if (
        this.board.contents == "" || this.board.contents === null
      ) {
          alert("내용은 필수 입력사항입니다.", "알림", {
            confirmButtonText: "확인",
          });
          return;
        }
      const formData = new FormData();
      formData.append("title", this.board.title);
      formData.append("contents", this.board.contents);
      formData.append("id", this.board.id);

      if (!this.file || (this.file && this.file.name != this.board.img)) {
        console.log(this.file);
        console.log('파일 이따ㅓ');
        formData.append("key", 0);
        formData.append("file", this.file);
        
        this.$store.dispatch('updateBoard', formData)
        .then(() => {
          console.log(this.$store.state.boards);
        })
        .catch((error) => {
          console.log(error);
        })
      } else {
        console.log('파일: ' + this.file);
        formData.append("key", 1);
        this.$store.dispatch('modifyBoard', formData)
        .then(() => {
          console.log(this.$store.state.boards);
        })
        .catch((error) => {
          console.log(error);
        })
      }
    },
  }
};
</script>

<style scoped>
h3 {
  text-align: center;
  margin-bottom: 20px;
}
.board-create-container {
  margin-left: auto;
  margin-right: auto;
}

.input-style {
  width: 100%;
  margin-bottom: 16px;
}

.combobox-style {
  margin-top: 11px;
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

.rounded-shadow {
  border-radius: 4px; /* 모서리를 살짝 둥글게 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1) !important; /* 바깥 그림자 적용 */
}


</style>