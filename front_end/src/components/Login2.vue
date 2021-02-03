<template>
  <q-layout>
    <q-page-container>
      <q-page class="flex bg-image flex-center">
        <q-card class="cardsize">
          <q-card-section>
            <q-avatar size="103px" class="absolute-center shadow-10">
              <img src="https://pbs.twimg.com/profile_images/810429579747676161/-RIQXV3z_400x400.jpg">
            </q-avatar>

          </q-card-section>
          <q-card-section>
            <div class="text-center q-pt-lg">
              <div class="col text-h4 ellipsis">
                Log in 테스트 페이지 입니다
              </div>
            </div>
          </q-card-section>
          <q-card-section>
            <q-form
              @submit="login({id, pw})"
              class="q-gutter-md"
            >
              <q-input
                filled
                v-model="id"
                label="id"
                lazy-rules
              />

              <q-input
                type="password"
                filled
                v-model="pw"
                label="Password"
                lazy-rules
              />
  
              <div>
                <!-- <q-btn key="login-button" 
                label="Login" 
                type="button" 
                color="primary"
                @click=""
                /> -->
                <q-btn
                    key="email-button"
                    color="secondary"
                    icon="mail"
                    label="Email"
                />
              </div>
              <q-btn label="등록" type="submit" color="primary"/>
            </q-form>

          </q-card-section>
        </q-card>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
// import axios from "axios"
import { mapState, mapActions } from "vuex"
    export default {
        data() {
            return {
                email:null,
                password:null,
                id: null,
                pw: null
            }
        },
        computed:{
          ...mapState(["isLogin", "isLoginError"])
        },
        methods : {
          ...mapActions(["login"])
          // onSubmit (){
          //   console.log(this.email)
          //   console.log(this.password)
          //   axios
          //     .post("https://reqres.in/api/login", {
          //       "email": this.email,
          //       "password": this.password
          //     })
          //     .then(res => {
          //       console.log(res)
          //     })
          //     .then(err => {
          //       console.log(err)
          //     })
          // }
        }
    }
</script>

<style>

  .bg-image {
   background-image: linear-gradient(135deg, #295F2D 0%, #FFE76C 100%);
   /* background-image: linear-gradient(135deg, #7028e4 0%, #e5b2ca 100%); */
  }
</style>
