<template>
  <div class="row flex flex-center">
    <div class="col-lg-8 col-md-8 col-xs-12 col-sm-12 flex flex-center">
    <q-card class="card-bg text-white">
      <q-card-section class="text-h6 q-pa-sm">
        <div class="text-h6">비밀번호 수정</div>
      </q-card-section>
      <q-card-section class="q-pa-sm row">
        <q-item class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
          <q-item-section>
            현재 비밀번호
          </q-item-section>
        </q-item>
        <q-item class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
          <q-item-section>
            <q-input type="password" dark dense outlined color="white" round v-model="password_dict.current_password"
                     label="현재 비밀번호"/>
          </q-item-section>
        </q-item>
        <q-item class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
          <q-item-section>
            새 비밀번호
          </q-item-section>
        </q-item>
        <q-item class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
          <q-item-section>
            <q-input type="password" dark dense outlined color="white" round v-model="password_dict.new_password"
                     label="새 비밀번호"/>
          </q-item-section>
        </q-item>
        <q-item class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
          <q-item-section>
            비밀번호 확인
          </q-item-section>
        </q-item>
        <q-item class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
          <q-item-section>
            <q-input type="password" dark dense outlined round color="white" v-model="password_dict.confirm_new_password"
                     label="비밀번호 확인"/>
          </q-item-section>
        </q-item>
      </q-card-section>
      <q-card-actions align="right">
        <q-btn class="text-capitalize bg-info text-white" @click="modifyPassword">변경하기</q-btn>
      </q-card-actions>

    </q-card>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data () {
    return {
      password_dict: {
        current_password: null,
        new_password: null,
        confirm_new_password: null
      }
    }
  },
  methods: {
    modifyPassword () {
      axios
        .post('api/member/modify/password', this.password_dict)
        .then(res => {
          alert("비밀번호가 변경되었습니다");
          this.$router.push('/');
        })
        .catch(err => {
          console.log(err);
        })
    }
  }
}
</script>
<style scoped>

.card-bg {
  background-color: #162b4d;
}
</style>
