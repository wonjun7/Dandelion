<template>
  <q-layout>
    <q-page-container>
      <q-page class="flex bg-image flex-center">
        <q-card class="cardsize">
          <q-card-section>



          </q-card-section>
          <q-card-section>
            <div class="text-center q-pt-lg q-px-xl">
              <div class="col text-center text-h3 ellipsis">
                Login
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
                label="pw"
                lazy-rules
              />

              <div>
                <!-- <q-btn key="login-button"
                label="Login"
                type="button"
                color="primary"
                @click=""
                /> -->
                <!-- <q-btn
                    key="email-button"
                    color="secondary"
                    icon="mail"
                    label="Email"
                /> -->
              </div>
              <div>
                <q-btn class="full-width flex-center"  label="로그인" type="submit" color="primary" size="xl" />
                <hr class="q-my-md">
                <q-btn class="full-width flex-center"  label="회원가입" type="submit" color="white" text-color="primary" size="xl" to="/register" />
              </div>
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
  .cardsize{
    width: 30em;
  }
</style>
