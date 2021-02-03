import Vue from 'vue'
import Vuex from 'vuex'
// import example from './module-example'
import axios from "axios"
import router from "../router"

Vue.use(Vuex)

/*
 * If not building with SSR mode, you can
 * directly export the Store instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Store instance.
 */


/*

*/
axios.interceptors.request.use(
  (config) => {
    let token = localStorage.getItem('access_token')

    if (token) {
      config.headers['authorization'] = token
      console.log(config.headers['authorization'])
    }

    return config
  },
  (error) => {

    console.log(config.headers)
    return Promise.reject(error)
  }
)


export default function (/* { ssrContext } */) {
  const Store = new Vuex.Store({
    state: {
      userInfo:null,

      //로그인을 한지 안한지를 판단
      isLogin: false,
      isLoginError: false,
    },
    //mutations는 status값을 변화시키는 로직
    mutations: {
      // 로그인이 성공했을 때,
      loginSuccess(state, payload){
        state.isLogin = true;
        state.isloginError = false;
        state.userInfo = payload;
      },
      // 로그인이 실패했을 떄,
      loginError(state){
        state.isLogin = false;
        state.isLoginError = true;
      },
      logout(state){
        state.isLogin = false;
        state.isLoginError = false;
        state.userInfo = null;
      }
    },
    // actions는 비즈니스 로직
    actions: {
      // 로그인 시도
      login({ dispatch },loginObj){
        // 로그인 -> 토큰 반환
        axios
          // .post('https://reqres.in/api/login',loginObj) // 두번째 인자는 파라메터(body)를 넣어줄수있다
          .post('api/login',loginObj) // 두번째 인자는 파라메터(body)를 넣어줄수있다
          .then( res => {
            console.log(res);
            // 성공 시 token: 블라블라(실제로는 user_id 값을 받아옴)
            // 토큰을 헤어데 포함시켜서 유저정보를 요청
            let token = res.headers['authorization'];
            // 토큰을 로컬스토리지에 저장
            localStorage.setItem("access_token", token);// 속성 , value
            dispatch("getMemberInfo");
            this.$router.push('/main');

        })
        .catch(err => {
          alert("이메일과 비밀번호를 확인하세요");
          console.log(err);
          console.log(loginObj);
        })

      },

      logout({ commit }){
        commit("logout")
        router.push({name: "home"})
      },

      getMemberInfo({ commit }){
        // 로컬 스토리지에 저장되어 있는 토큰을 불러온다
        let token = localStorage.getItem("access_token")
        let config = { // 컨피그에서 헤더값을 설정해줄수 있다
          headers: {
            "access-token" : token
          }
        }
            // 반환된 토큰을 가지고 -> 맴버의 정보를 반환
            // 새로 고침 을 했을때 -> 토큰만 가지고 맴버정보 요청
            axios
              .get('https://reqres.in/api/users/2', config)
                .then(response => { // 백과 연동시 파라메타 수정
                  console.log(response)
                  let userInfo = {
                    id: response.data.data.id,
                    pw: response.data.data.pw,
                  }
                  commit("loginSuccess", userInfo)
                })
                .catch(error => {
                  alert("이메일과 비밀번호를 확인하세요")
                  console.log(error)
                })
          }
        },
    modules: {
      // example
    },

    // enable strict mode (adds overhead!)
    // for dev mode only
    strict: process.env.DEBUGGING
  })

  return Store
}
