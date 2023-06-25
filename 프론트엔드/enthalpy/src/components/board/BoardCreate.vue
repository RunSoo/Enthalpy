<template>
  <div>
    <v-app>
      <div>
            
            <div >
              <h3 style="text-align: center; margin-top: 40px; font-size: 24px !important; font-family: 'NanumBarunGothic'; font-weight: 600">게시글 작성</h3>
              <v-card-text style="max-width: 1000px; margin-left: auto; margin-right: auto;" class="rounded-shadow">
                <v-form enctype="multipart/form-data">
                  <v-row>
                    <v-col cols="6">
                      <v-combobox
                        v-model="selectedActivity"
                        :items="METs"
                        item-text="activity"
                        item-value="id"
                        label="운동"
                        hide-no-data
                        persistent-hint
                        dense
                        outlined
                        class="input-style combobox-style"
                        @change="handleActivityChange"
                        return-object
                      ></v-combobox>
                    </v-col>
                    <v-col cols="6">
                      <v-text-field
                        v-model="duration"
                        type="number"
                        label="운동 시간"
                        min="0"
                        class="input-style"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-text-field v-model="title" label="제목"></v-text-field>
                  <v-file-input
                    accept="image/*"
                    v-model="file"
                    label="이미지 첨부파일"
                    @change="handleFileChange"
                  ></v-file-input>
                  <v-textarea
                    v-model="contents"
                    label="내용"
                    placeholder="내용을 입력하세요"
                    rows="13"
                  ></v-textarea>
                </v-form>
                <v-card-actions style="display: flex; justify-content: center;">
                <v-btn @click="createBoard" color="primary">작성</v-btn>
                <v-btn to="/boardList" color="primary" text style="border: 1px solid #b3b3b3;">취소</v-btn>
              </v-card-actions>
              </v-card-text>
              
            </div>
              
      </div>
    </v-app>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "BoardCreate",
  components: {},
  data() {
    return {
      file: null,
      activity_id: "",
      duration: "",
      title: "",
      contents: "",
    };
  },
  computed: {
    ...mapState(["METs"]),
  },
  created() {
    this.$store.dispatch("getMETs");
  },
  methods: {
    handleActivityChange(value) {
      this.activity_id = value.id;
    },
    handleFileChange() {
      const fileType = this.file.type;
      if (!fileType.startsWith("image/")) {
        // 이미지 파일이 아닌 경우 처리
        alert("이미지 파일만 선택이 가능합니다");
        this.file = null; // 파일 제거
        return;
      }
    },
    createBoard() {
      if (
        this.activity_id === "" ||
        this.duration === "" ||
        this.title === "" ||
        this.contents === "" ||
        this.duration <= 0
      ) {
        alert("모든 내용을 입력해주세요.", "알림", {
          confirmButtonText: "확인",
        });
        return;
      }
      const formData = new FormData();

      formData.append("activity_id", this.activity_id);
      formData.append("duration", this.duration);
      formData.append("title", this.title);
      formData.append("contents", this.contents);

      if (this.file) {
        formData.append("file", this.file);
      }

      this.$store
        .dispatch("createBoard", formData)
        .then(() => {
          console.log(this.$store.state.boards);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style scoped>
h3 {
  text-align: center;
  margin-bottom: 20px;
}
.board-create-container {
  width: 1200px;
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
.board-card {
  width: 1200px;
}
.rounded-shadow {
  border-radius: 4px; /* 모서리를 살짝 둥글게 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1) !important; /* 바깥 그림자 적용 */
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
