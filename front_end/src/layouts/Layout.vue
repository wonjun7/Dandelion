<template>
  <q-layout view="hHh lpR fFf">

    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <!-- <q-btn dense flat round icon="menu" @click="left = !left" /> -->
        <span>
        <q-toolbar-title class="q-ma-sm">
          <q-avatar>
            <img :src="require('../assets/Dandelion2.png')"  >
          </q-avatar>
          DandeilOn
        </q-toolbar-title>
        </span>
      </q-toolbar>
    </q-header>

    <q-drawer show-if-above v-model="left" side="left" bordered>
      <!-- drawer content -->
        <q-scroll-area style="height: calc(100% - 150px); margin-top: 150px; border-right: 1px solid #ddd" >
          <q-list padding>
            <!-- <q-item clickable v-ripple router :to="{name: 'main'}"> -->
            <q-item clickable v-ripple :to="{ name: 'PageIndex' }">
              <q-item-section avatar>
                <q-icon name="local_florist" />
              </q-item-section>

              <q-item-section>
                메인페이지
              </q-item-section>
            </q-item>
<!-- active 클릭 되있는 상태를 표시하려면 -->
            <q-item clickable v-ripple :to="{ name: 'myPage'}">
              <q-item-section avatar>
                <q-icon
                name="emoji_nature"
                 />
              </q-item-section>

              <q-item-section>
                마이 페이지
              </q-item-section>
            </q-item>

            <q-item clickable v-ripple :to="{name: 'Stuff'}">
              <q-item-section avatar>
                <q-icon name="eco" />
              </q-item-section>

              <q-item-section>
                상품 등록
              </q-item-section>
            </q-item>

            <q-item clickable v-ripple :to="{ name: 'bird' }">
              <q-item-section avatar>
                <q-icon name="flutter_dash" />
              </q-item-section>
              <q-item-section>
                버드페이지
              </q-item-section>
            </q-item>

            <q-item class="float-" clickable v-ripple :to="{ name: 'developer' }">
              <q-item-section avatar>
                <q-icon name="groups" />
              </q-item-section>
              <q-item-section>
                제작진
              </q-item-section>
            </q-item>

          </q-list>
        </q-scroll-area>


      <!-- header-img section -->
        <q-img class="absolute-top" src="https://cdn.quasar.dev/img/material.png" style="height: 150px">
          <div class="absolute-bottom bg-transparent">
            <q-avatar size="56px" class="q-mb-sm">
              <!-- <img src="https://cdn.quasar.dev/img/boy-avatar.png"> -->
              <img src="https://item.kakaocdn.net/do/a56e6ddd117688df80be93b1154a9856f43ad912ad8dd55b04db6a64cddaf76d">
            </q-avatar>
            <div class="text-weight-bold">Razvan Stoenescu</div>
            <div>@rstoenescu</div>
            <q-btn color="brown-5" icon="monetization_on" label="충전하기" class="pointCharge q-pr-md" @click="prompt = true" />
            <q-btn color="brown-5" icon="exit_to_app" label="로그아웃" class="LogoutButton" @click="logout" />
          </div>
        </q-img>

<!--      다이얼로그-->
      <q-dialog v-model="prompt" persistent>
        <q-card style="min-width: 350px">
          <q-card-section>
            <div class="text-h6">충전하기</div>
          </q-card-section>

          <q-card-section class="q-pt-none">
            <q-input dense v-model="point.result" autofocus @keyup.enter="prompt = false" />
          </q-card-section>

          <q-card-actions align="right" class="text-primary">
            <q-btn flat label="취소하기" v-close-popup />
            <q-btn flat label="충전하기" @click="pointCharge" v-close-popup  />
          </q-card-actions>
        </q-card>
      </q-dialog>

      <div class="absolute-bottom">
          <q-avatar class="" size="250px" square>
            <img :src="require('../assets/Dandelion2.png')">
          </q-avatar>
          <div class="flex flex-center text-h3">
            DandeliOn
          </div>


        </div>

    </q-drawer>

    <q-page-container>
      <!-- route 되는 페이지를 로드시킨다 -->
      <transition
      :name="transitionName">
      <router-view />
      </transition>
    </q-page-container>
  </q-layout>



</template>

<script>
import axios from "axios";
export default {
  data () {
    return {
      left: false,
      transitionName: 'slide-left',
      prompt: false,
      point: {
        result: null
      }
    }
  },
  watch:{
    $route (to, from){
      this.transitionName = to.name = 'PageIndex' ? 'slide-right' : 'slide-left'
      // console.log(to.name)
    }
  },
  methods: {
    pointCharge () {
      axios
        .post('api/pointCharge', this.point)
        .then(res => {
          alert("충전이 완료되었습니다");
          this.$router.push('/main')
        })
        .catch(err => {
          console.log(err);
          console.log(this.point);
          alert("숫자를 입력하세요")
        })
    },

    logout () {
      localStorage.removeItem('access_token');
      this.$router.push('/')
      location.reload();
    }
  }
}
</script>

<style lang="scss" scoped>
.slide-left-enter-active,
.slide-left-leave-active,
.slide-right-enter-active,
.slide-right-leave-active{
  transition-duration: 0.5s;
  transition-property: height, opacity, transform;
  /* transition-timing-function: cubic-bezier(0.55,0,0.1,1); */
  overflow: hidden;
}

.slide-left-enter,
.slide-right-leave-active{
  opacity:0;
  transform: translate(100%,0);
}

.slide-left-leave-active,
.slide-right-enter{
  opacity: 0;
  transform: translate(-100%,0);
}
.LogoutButton {
  float: right;
}

</style>
