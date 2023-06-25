<template>
  <div class="container">
    
    <br>
    
    <div class="calendar-header-container">
      <div class="calendar-buttons">
        <v-btn class="arrow-btn no-hover-style" @click="changeMonth(-1)" style="background-color: transparent !important; elevation: 0 !important;" elevation="0" dark="true">
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
        <h2 class="calendar-header">&nbsp;&nbsp;{{ calendarHeader }}&nbsp;&nbsp;</h2>
        <v-btn class="arrow-btn no-hover-style" @click="changeMonth(1)" style="background-color: transparent !important; elevation: 0 !important;" elevation="0" dark="true">
          <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
      </div>
    </div>

    <div class="calendar-container">
      <table class="calendar-table">
        <thead>
          <tr>
            <th v-for="index in week" :key="index" class="day-header">
              {{ index }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(index, i) in days" :key="i">
            <td
              v-for="childIndex in index"
              :key="childIndex"
              :class="{
                active:
                  childIndex === curDay.getDate() &&
                  curMonth == curDay.getMonth() &&
                  curYear == curDay.getFullYear(),
              }"
            >
              <div class="date-container">
                <span class="date">{{ childIndex }}</span>
              </div>
              <br />
              <span
                v-if="hasCalories(childIndex)"
                :style="getCaloriesStyle()"
                class="calories-text"
              >
                {{ matchingCalorie.calories }}
              </span>
              <span
                v-if="
                  childIndex === curDay.getDate() &&
                  curMonth == curDay.getMonth() &&
                  curYear == curDay.getFullYear()
                "
                class="pencil-icon-container"
              >
                <!-- <br /> -->
                <router-link :to="{ name: 'boardCreate' }">
                  <v-icon class="pencil-icon">mdi-pencil</v-icon>
                </router-link>
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div>
      <v-row>
        <v-col>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <span v-if="this.haveGoal()" style="font-size: 15px; color: gray;">
            <b style="color: green; font-size: 20px !important;">•</b> 목표량 달성
            <b style="color: red; font-size: 20px !important;">&nbsp;&nbsp;&nbsp;&nbsp;• </b>목표 달성 실패
          </span>
        </v-col>
        <v-col>
          <div class="input-container">
            <label for="activityGoal" class="mr-2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;운동 목표를 입력해주세요:</label>
            <div class="input-group">
              <input
                v-model="goal"
                id="activityGoal"
                type="number"
                placeholder="운동 목표"
                @keydown.enter="modifyGoal"
                style="width: 150px;"
                class="no-spin-button no-outline"
              />
              <div class="button-container">
                <v-btn @click="modifyGoal">수정</v-btn>
                <v-btn @click="deleteGoal" v-if="myGoal !== 0 && myGoal !== null">삭제</v-btn>
              </div>
            </div>
          </div>
        </v-col>
      </v-row>
    </div>
      

    <!-- <div class="calendar-row">
      <div class="input-container">
        <v-row class="align-center" justify="end">
          <div class="flex-hi" style="text-align: left;">표 설명이 들어가는 영역</div>
          <div class="flex-item">
            <v-col cols="auto">
            <label for="activityGoal" class="mr-2">운동 목표를 입력해주세요:</label>
            </v-col>
            <v-col>
              <input
                v-model="goal"
                id="activityGoal"
                type="number"
                placeholder="운동 목표"
                @keydown.enter="modifyGoal"
                style="width: 150px;"
                class="no-spin-button no-outline"
              />
            </v-col>
          <div class="button-container">
          <v-btn @click="modifyGoal">수정</v-btn>
          <v-btn @click="deleteGoal" v-if="myGoal !== 0 && myGoal !== null">삭제</v-btn>
          </div>
          
      </div>
        </v-row>
      </div>
      
    </div> -->


    <div class="board-list-container">
      <table class="board-list__table">
        <thead>
          <tr style="height: 35px;">
            <th style="width: 15%;">운동</th>
            <th style="width: 40%; text-align: center;">제목</th>
            <th style="width: 15%;">작성자</th>
            <th style="width: 20%;">작성일</th>
            <th style="width: 10%;">조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="board in filteredBoards()" :key="board.id">
            <td class="text-center" style="width: 15%">{{ board.activity }}</td>
            <td style="width: 40%; text-align: left">
              <router-link
                :to="{ name: 'boardDetail', params: { id: board.id } }"
                >{{ board.title }}</router-link
              >
            </td>
            <td class="text-center" style="width: 15%">{{ board.writer }}</td>
            <td class="text-center" style="width: 20%">{{ board.regDate }}</td>
            <td class="text-center" style="width: 10%">{{ board.viewCnt }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "MyCalendar",
  data() {
    return {
      today: new Date(),
      week: ["일", "월", "화", "수", "목", "금", "토"],
      calendarHeader: "",
      days: [],
      regDate: "",
      regMonth: "",
      curYear: "",
      curMonth: "",
      curDay: new Date(),
      matchingCalorie: {},
      goal: 0,
      myGoal: 0,
      myId: "",
    };
  },
  watch: {
    goal(newValue) {
      if (newValue !== null && newValue !== "") {
        this.goal = Number(newValue.replace(/^0+/, ""));
      }
    },
  },
  computed: {
    ...mapState(["calories", "myboards"]),
  },
  mounted() {
    this.calendarImplementation();
  },
  created() {
    const userId = this.$store.state.loginUser.id;
    this.myId = userId;
    this.$store.dispatch("getCalories", userId);
    const payload = {
      key: userId,
      orderBy: 'regDate',
      orderByDir: 'ASC',
    };
    this.$store.dispatch("getBoardsbyUser", payload);
    this.goal = this.$store.state.loginUser.goal;
    this.myGoal = this.goal;
  },
  methods: {
    haveGoal() {
      return this.myGoal !== 0;
    },
    getCaloriesStyle() {
      if (this.haveGoal()) {
        const goal = parseInt(this.myGoal);
        const calories = parseInt(this.matchingCalorie.calories);
        if (calories > goal) {
          return { color: "green" };
        } else if (calories < goal) {
          return { color: "red" };
        }
      }
      return { color: "black" };
    },
    calendarImplementation() {
      this.days = [];
      const year = this.today.getFullYear();
      const month = this.today.getMonth();
      const startDayOfTheMonth = new Date(year, month, 1).getDay();
      const endDayOfTheMonth = new Date(year, month + 1, 0).getDate();
      const basicDays = Array.from(
        { length: endDayOfTheMonth },
        (v, i) => i + 1
      );
      const emptyDays = Array(startDayOfTheMonth).fill(null);
      const combinedDays = [...emptyDays, ...basicDays];
      for (let i = 0; i < endDayOfTheMonth + startDayOfTheMonth; i += 7) {
        this.days.push(combinedDays.slice(i, i + 7));
      }
      this.calendarHeader = `${year}. ${month + 1}`;
      this.regDate = `${year}-${month + 1}-`;

      if ((month + 1).toString().length === 1) {
        this.regMonth = `${year}-0${month + 1}-`;
      } else {
        this.regMonth = `${year}-${month + 1}-`;
      }

      this.curYear = `${year}`;
      this.curMonth = `${month}`;

      this.addLastWeekEmptyDays();
    },
    addLastWeekEmptyDays() {
      const daysLastIndex = this.days.length - 1;
      if (this.days[daysLastIndex].length !== 7) {
        this.days[daysLastIndex] = this.days[daysLastIndex].concat(
          Array(7 - this.days[daysLastIndex].length).fill(null)
        );
      }
    },
    changeMonth(val) {
      this.today.setMonth(this.today.getMonth() + val, 1);
      this.calendarImplementation();
    },
    hasCalories(date) {
      const matchingCalorie = this.calories.find(
        (cal) => cal.regDate === this.regDate + date
      );
      this.matchingCalorie = matchingCalorie || {};
      return matchingCalorie !== undefined;
    },
    filteredBoards() {
      if (!Array.isArray(this.myboards)) {
        return []; // Return an empty array if `this.boards` is not an array
      }
      return this.myboards.filter((board) =>
        board.regDate.includes(this.regMonth)
      );
    },
    modifyGoal() {
      const user = {
        id: this.myId,
        goal: this.goal,
      };
      if (this.goal <= 0) {
        alert("목표값을 0보다 큰 값으로 설정해 주세요");
        return;
      }
      this.$store
        .dispatch("updateGoal", user)
        .then(() => {
          this.myGoal = this.goal;
        })
        .catch((error) => {
          console.log(error);
        });

      alert ('목표가 수정되었습니다.');
      location.reload();
    },
    deleteGoal() {
      const user = {
        id: this.myId,
        goal: 0,
      };
      this.goal = 0;
      this.$store
        .dispatch("updateGoal", user)
        .then(() => {
          this.myGoal = this.goal;
        })
        .catch((error) => {
          console.log(error);
        });
      alert ('목표가 삭제되었습니다.');
      location.reload();
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.calendar-row {
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.input-container {
  display: flex;
  align-items: center;
  gap: 10px;
}

.input-group {
  display: flex;
  align-items: center;
}


.input-container input {
  width: 100%;
  padding: 10px;
  border: 1px solid #e3e7eb;
  border-radius: 4px;
}

.button-container {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-left: 10px;
}

.calendar-header {
  margin: 0;
  font-size: 36px;
  font-weight: 300;
  text-align: center;
  color: #4f5a66;
}

.calendar-header-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.calendar-buttons {
  display: flex;
  align-items: center;
  margin-left: 20px;
}

.calendar-container {
  text-align: center;
  margin-bottom: 20px;
  padding: 0 20px;
}
.calendar-table th.day-header {
  border: 1px solid #e3e7eb; /* 여기가 월화수목금토일 칸에 테두리 부분 */
}

.calendar-table {
  width: 100%;
  table-layout: fixed;
}

.calendar-table th {
  font-weight: 500;
  padding: 15px;
  text-align: center;
}

.calendar-table td {
  padding: 0;
  text-align: center;
  position: relative;
  border: 1px solid #e3e7eb;
  height: 100px;
}

.date-container {
  position: absolute;
  top: 13px;
  left: 50%;
  transform: translateX(-50%);
}

.calendar-table td.active {
  background-color: #f0f3f5;
}

.calories-text {
  font-weight: 500;
}

.arrow-btn {
  height: 30px;
  width: 30px;
  min-width: 30px;
  padding: 0;
  margin: 0;
  text-align: center;
  background: transparent;
  border: none;
  cursor: pointer;
  font-size: 16px;
  color: #4f5a66;
  outline: none;
}

.pencil-icon-container {
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}

.pencil-icon {
  font-size: 16px;
  margin-right: 5px;
}

.board-list-container {
  margin-top: 20px;
  padding: 0 20px;
}

.board-list__table {
  width: 100%;
  table-layout: fixed;
  border-collapse: collapse;
}

.board-list__table th {
  font-weight: 500;
  text-align: center;
  border-bottom: 1px solid #cbcbcb;
}

.board-list__table td {
  padding: 10px;
  text-align: center;
  border-bottom: 1px solid #e3e7eb;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.board-list__table th:first-child,
.board-list__table td:first-child {
  border-left: none;
}

.board-list__table th:not(:first-child),
.board-list__table td:not(:first-child) {
  border-left: 1px solid #e3e7eb;
  border-right: 1px solid #e3e7eb;
}

.board-list__table a {
  text-decoration: none;
  color: #4f5a66;
}

.input-container {
  margin: 0 10px;
}

.input-container input {
  width: 100%;
  padding: 10px;
  border: 1px solid #e3e7eb;
  border-radius: 4px;
}

@media (max-width: 768px) {
  .calendar-row {
    flex-direction: column;
    align-items: center;
  }

  .input-container {
    width: 100%;
    max-width: 200px;
    margin: 10px 0;
  }
}

/* Hide vertical borders in board list table */
.board-list__table th:not(:first-child),
.board-list__table td:not(:first-child) {
  border-left: none;
  border-right: none;
}

.no-spin-button {
  -moz-appearance: textfield; /* Firefox에서 스핀 버튼 숨김 */
  -webkit-appearance: none; /* Chrome, Safari, Opera에서 스핀 버튼 숨김 */
  appearance: none; /* 일반적인 스핀 버튼 숨김 */
}

.no-spin-button::-webkit-inner-spin-button,
.no-spin-button::-webkit-outer-spin-button {
  -webkit-appearance: none; /* Chrome, Safari에서 스핀 버튼 숨김 */
  appearance: none; /* 일반적인 스핀 버튼 숨김 */
  margin: 0; /* 스핀 버튼 마진 제거 */
}

.no-outline {
  outline: none; /* 클릭 시 테두리 제거 */
}

.no-hover-style:hover {
  background-color: transparent !important;
}

.flex-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 15px;
}
.flex-hi {
  display: flex;
  align-items: start;
  justify-content: space-between;
  gap: 15px;
}

.table-description {
  text-align: left;
}



</style>
