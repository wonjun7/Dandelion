<template>
  <q-page class="">
    <div class="row">
      <div v-for="member in memberList" :key="member.id" class="col-3 cardArea">
        <div class="myCard">
          <q-card dark bordered class="bg-grey-9 my-card">
            <q-card-section>
              <div class="text-h6">{{ member.name }}</div>
              <div class="text-subtitle2">by {{ member.email }}</div>
            </q-card-section>

            <q-separator dark inset />

            <q-card-section>
              <!-- {{ lorem }} -->
              {{ member.text }}
            </q-card-section>
            <q-btn color="primary" @click="test"> Test</q-btn>
          </q-card>
          <q-btn color="brown-5" icon="exit_to_app" label="로그아웃" class="LogoutButton" @click="logout" />
        </div>
      </div>

      <div v-for="user in userList" :key="user.id" class="col-3 cardArea">
        <div class="myCard">
          <q-card dark bordered class="bg-grey-9 my-card">
            <q-card-section>
              <q-img :src="user.avatar" :ratio="1" />
              <div class="text-h6">id : {{ user.id }}</div>
              <div class="text-subtitle2">{{ user.first_name }}</div>
            </q-card-section>

            <q-separator dark inset />

            <q-card-actions>
              {{ user.email }}
            </q-card-actions>
          </q-card>
        </div>
      </div>
      <!--  -->
    </div>
  </q-page>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      lorem:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
      memberList: [
        {
          id: 1,
          name: "김승환",
          email: "shk2000v",
          text: "하고싶은말! 지금은 새벽2시 58분 내가 머하고있지 허헣"
        },
        {
          id: 2,
          name: "김깜비",
          email: "GGAMBI",
          text:
            "멍ㅁ엄엄엄어멍 멍멍멍멍 멍멍 멍멍멍멍 멍멍멍멍멍멍 멍멍멍멍멍멍 멍멍멍멍멍멍 멍멍 멍멍멍멍 멍멍멍멍멍멍멍멍멍 멍멍멍 멍멍"
        }
      ],
      userList: [], // userList: null,
      // userList: null,
      err: false,
      loading: true
      // info: []
    };
  },
  mounted() {
    this.test();
  },
  methods: {
    test() {
      axios
        .get("api/order-history/sell")
        .then(res => {
          console.log(res);
          this.userList = res.data.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    logout() {
      axios
      .get("api/")
      .then(res => {
        console.log(res);
        localStorage.removeItem('access_token');
        location.reload();
        this.$router("/");
      })
    }
  }
};
</script>

<style lang="scss" scoped>
.cardArea {
  padding: 30px;
  .myCard {
    cursor: pointer;
    transition: all 0.2s ease-in-out;
    &:hover {
      transform: scale(1.03);
    }
  }
}
</style>
