import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import router from "@/router";

Vue.use(Vuex);

const REST_API = `http://localhost:9999`;

export default new Vuex.Store({
  state: {
    users: [],
    user: {},
    boards: [],
    myboards: [],
    board: {},
    comments: [],
    METs: [],
    loginUser: JSON.parse(sessionStorage.getItem("loginUser")),
    idExists: false,
    nicknameExists: false,
    calories: [],
  },
  getters: {
    idExists: (state) => state.idExists,
  },
  mutations: {
    UPDATE_GOAL(state, goal) {
      state.loginUser.goal = goal;
      console.log(state.loginUser);
      sessionStorage.removeItem("loginUser");
      sessionStorage.setItem("loginUser", JSON.stringify(state.loginUser));
    },
    UPDATE_LIKE(state, board) {
      state.board = board;
    },
    UPDATE_BOARD(state, board) {
      state.board = board;
    },
    UPDATE_COMMENT(state, updatedComment) {
      const index = state.comments.findIndex(
        (comment) => comment.id === updatedComment.id
      );
      if (index !== -1) {
        Vue.set(state.comments, index, updatedComment);
      }
    },
    DELETE_COMMENT(state, commentId) {
      state.comments = state.comments.filter(
        (comment) => comment.id !== commentId
      );
    },
    GET_BOARD(state, board) {
      state.board = board;
    },
    GET_CALORIES(state, payload) {
      state.calories = payload;
    },
    GET_BOARDS(state, payload) {
      state.boards = payload;
    },
    GET_MYBOARDS(state, payload) {
      state.myboards = payload;
    },
    GET_METS(state, payload) {
      state.METs = payload;
    },
    CREATE_BOARD(state, payload) {
      if (state.boards.length === 0) {
        state.boards = [payload];
      } else {
        state.boards.push(payload);
      }
    },
    LOGOUT(state) {
      state.loginUser = null;
      sessionStorage.removeItem("loginUser");
      window.location.reload();
    },
    SET_LOGIN_USER(state, user) {
      state.loginUser = user;
      sessionStorage.setItem("loginUser", JSON.stringify(user));
      console.log(state.loginUser);
    },
    SET_USERS(state, users) {
      state.users = users;
      console.log(state.users);
    },
    CREATE_USER(state, payload) {
      if (state.users.length === 0) {
        state.users = [payload];
      } else {
        state.users.push(payload);
      }
    },
    CHECK_ID(state, exists) {
      state.idExists = exists;
    },
    CHECK_NICKNAME(state, exists) {
      console.log(exists);
      state.nicknameExists = exists;
    },
    UPDATE_USER(state, user) {
      const index = state.users.findIndex((u) => u.id === user.id);
      if (index !== -1) {
        state.users[index] = user;
      }
      if (state.loginUser && state.loginUser.id === user.id) {
        state.loginUser = user;
        sessionStorage.setItem("loginUser", JSON.stringify(user));
      }
    },
    ADD_COMMENT(state, comment) {
      state.comments = [...state.comments, comment];
    },
    GET_COMMENTS(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    deleteBoard({ commit }, id) {
      const API_URL = `${REST_API}/board/list/${id}`;
      console.log(API_URL);
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          console.log("성공");
          commit;
          router.push({ name: "boardList" });
          location.reload();
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
    },
    likeUpdate({ commit }, like) {
      const API_URL = `${REST_API}/board/like`;
      axios({
        url: API_URL,
        method: "POST",
        params: like,
      })
        .then((res) => {
          console.log("성공");
          commit("UPDATE_LIKE", res.data);
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
    },
    updateBoard({ commit }, formData) {
      const API_URL = `${REST_API}/board/list`;
      axios({
        url: API_URL,
        method: "PUT",
        data: formData,
        headers: { "Content-Type": "multipart/form-data" },
      })
        .then(() => {
          commit("UPDATE_BOARD", formData);
          const id = formData.get("id");
          router.push({ name: "boardDetail", params: { id: id } });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateGoal({ commit }, user) {
      const API_URL = `${REST_API}/user/goal`;
      axios({
        url: API_URL,
        method: "PUT",
        data: user,
      })
        .then(() => {
          commit("UPDATE_GOAL", user.goal);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteGoal({ commit }, id) {
      const API_URL = `${REST_API}/user/goal/del`;
      console.log(id);
      axios({
        url: API_URL,
        method: "PUT",
        params: {
          id: id,
        },
      })
        .then(() => {
          commit("UPDATE_GOAL", 0);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    modifyBoard({ commit }, formData) {
      const API_URL = `${REST_API}/board/list`;
      axios({
        url: API_URL,
        method: "PUT",
        data: formData,
      })
        .then(() => {
          commit("UPDATE_BOARD", formData);
          const id = formData.get("id");
          router.push({ name: "boardDetail", params: { id: id } });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getBoard({ commit }, payload) {
      console.log(payload);
      const API_URL = `${REST_API}/board/list/${payload.id}`;
      console.log(API_URL);
      const userId =  payload.userId;
      let params = {
        userId: userId,
      }
      axios({
        url: API_URL,
        method: "GET",
        params
        })
        .then((res) => {
          commit("GET_BOARD", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getComments({ commit }, payload) {
      const API_URL = `${REST_API}/comment/${payload.boardId}`;
      return axios
        .get(API_URL)
        .then((res) => {
          const comments = res.data;
          commit("GET_COMMENTS", comments);
          return comments;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    getCalories({ commit }, userId) {
      let params = null;
      if (userId)
        params = {
          userId: userId,
        };

      const API_URL = `${REST_API}/dailyCal/`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
        .then((res) => {
          console.log(res.data);
          const calories = Array.isArray(res.data) ? res.data : [];
          commit("GET_CALORIES", calories);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    getBoards({ commit }, payload) {
      let params = null;
      if (payload) params = payload;

      const API_URL = `${REST_API}/board/list`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
        .then((res) => {
          commit("GET_BOARDS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getBoardsbyUser({ commit }, payload) {
      let params = payload;

      const API_URL = `${REST_API}/board/list/user`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
        .then((res) => {
          commit("GET_MYBOARDS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getMETs({ commit }, payload) {
      let params = null;
      if (payload) params = payload;

      const API_URL = `${REST_API}/board/MET`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
        .then((res) => {
          commit("GET_METS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    createBoard({ commit, state }, formData) {
      const API_URL = `${REST_API}/board/list`;
      formData.append("userId", state.loginUser.id);
      console.log(formData);
      axios({
        url: API_URL,
        method: "POST",
        data: formData,
        headers: { "Content-Type": "multipart/form-data" },
      })
        .then((response) => {
          const board = response.data;
          console.log(board);
          commit("CREATE_BOARD", board);
          router.push({ name: "boardDetail", params: { id: board.id } });
          location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setLoginUser({ commit, state }, user) {
      console.log(state.users);
      let matched = false;
      for (let i = 0; i < state.users.length; i++) {
        if (
          user.id === state.users[i].id &&
          user.password === state.users[i].password
        ) {
          state.user = state.users[i];
          matched = true;
          console.log("matched~");
          break;
        }
      }
      if (matched) {
        commit("SET_LOGIN_USER", state.user);
        alert("로그인 성공");
        router.push("/");
      } else {
        alert("아이디나 비밀번호가 일치하지 않습니다. ");
      }
    },

    getUsers({ commit }) {
      const API_URL = `${REST_API}/user/list`;
      return axios
        .get(API_URL)
        .then((res) => {
          commit("SET_USERS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    createUser({ commit }, user) {
      console.log(user);
      const API_URL = `${REST_API}/user/signup`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then(() => {
          commit("CREATE_USER", user);
          console.log(user);
          alert("등록되었습니다. ");
          router.push("/user/login");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    checkId({ commit }, id) {
      const API_URL = `${REST_API}/user/idCheck`;
      return axios
        .get(API_URL, {
          params: {
            id: id,
          },
        })
        .then((response) => {
          console.log(response.data);
          const exists = response.data;
          commit("CHECK_ID", exists);
          return exists;
        })
        .catch((error) => {
          console.log(error);
          return false;
        });
    },
    checkNickname({ commit }, nickname) {
      const API_URL = `${REST_API}/user/nickCheck`;
      return axios
        .get(API_URL, {
          params: {
            nickname: nickname,
          },
        })
        .then((response) => {
          console.log(response.data);
          const exists = response.data;
          commit("CHECK_NICKNAME", exists);
          return exists;
        })
        .catch((error) => {
          console.log(error);
          return false;
        });
    },
    updateUser({ commit }, user) {
      const API_URL = `${REST_API}/user/info`;
      console.log(user);
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then(() => {
          commit("UPDATE_USER", user);
          console.log(user);
          alert("수정되었습니다. ");
          router.push("/user/userinfo");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateComment({ commit }, comment) {
      const API_URL = `${REST_API}/comment/`;
      console.log(comment);
      return axios({
        url: API_URL,
        method: "PUT",
        params: comment,
      })
        .then(() => {
          commit("UPDATE_COMMENT", comment);
          return comment;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteComment({ commit }, commentId) {
      const API_URL = `${REST_API}/comment/${commentId}`;
      return axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          commit("DELETE_COMMENT", commentId);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addComment({ commit }, comment) {
      const API_URL = `${REST_API}/comment/`;
      console.log(comment);
      axios({
        url: API_URL,
        method: "POST",
        params: comment,
      })
        .then((response) => {
          const addedComment = response.data;
          console.log(addedComment);
          commit("ADD_COMMENT", addedComment);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  modules: {},
});
