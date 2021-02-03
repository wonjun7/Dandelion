<template>

  <div class="q-pa-md" >
    <q-form class="col-12 text-center self-center" @submit="onSubmit">
      <div class="col-12 text-center self-center">
        <h2 class="title"> 내 정보 수정 </h2>
      </div>
      <!-- 사진업로드 -->

      <!-- 이름 작성 -->
      <div class="col-12 text-center self-center">
        <q-avatar class="self-center" size="200px">
          <img src="https://mblogthumb-phinf.pstatic.net/MjAxOTEwMjlfNDUg/MDAxNTcyMzI5NDcwNjIz.aW2F-SaHTjtOHNUlRixK7I_scEWzQDe7k-JHLkxj9_wg.fKoqWcVf8Y-vVCKGpIqUCy--2rC8Na4pHoGawaOwmVcg.PNG.moonkuki/SE-c0ad31f7-b153-4905-9f10-9d81b853e1e6.png?type=w800">
        </q-avatar>
      </div>

      <div class="col-12 text-center self-center">
        <q-item-section>
          <q-btn class="col-12 text-center self-center" label="Add Photo"  rounded color="info" style="max-width: 120px"></q-btn>
        </q-item-section>
      </div>

      <q-item class="col-lg-12 col-md-6 col-sm-12 col-xs-12">
        <q-item-section>
          <q-input v-model="user_details.id" label="id" stack-label :dense="dense" readonly />
        </q-item-section>
      </q-item>

      <q-item class="col-lg-12 col-md-8 col-sm-12 col-xs-12">
        <q-item-section>
          <q-input v-model="user_details.name" label="name" stack-label :dense="dense" />
        </q-item-section>
      </q-item>

      <q-item class="col-lg-12 col-md-9 col-sm-12 col-xs-12">
        <q-item-section>
          <q-input v-model="user_details.phone" label="phone" stack-label :dense="dense" />
        </q-item-section>
      </q-item>

      <q-item class="col-lg-12 col-md-10 col-sm-12 col-xs-12">
        <q-item-section>
          <q-input v-model="user_details.address" label="address" stack-label :dense="dense" />
        </q-item-section>
      </q-item>



      <!--      <input type="file" ref="uploadImage" @change="onImageUpload()" class="form-control" required>-->
      <!--  -->
      <div class="">
        <q-btn label="수정하기" type="submit" color="primary" />
      </div>
      <div class="">
        <q-btn label="비밀번호 변경" type="submit" color="primary" :to="'/modifyPassword'"/>
      </div>
    </q-form>



  </div>
</template>
<script>
import axios from 'axios'
export default {
  data () {
    return {
      dense : false,

      user_details: null
        // number: "nadaYoung",
        // password: "nada",
        // name: "나다용",
        // phone: "010-1234-1234",
        // address: "관악구"
      ,
      password_dict: {
        current_password: null,
        new_password: null,
        confirm_new_password: null
      }
    }
  },
  mounted() {
    this.myProfile();
  },

  methods: {
    myProfile () {
      axios
        .get('api/member/view')
        .then(res => {
          this.user_details = res.data;
          console.log(this.user_details);
        })
        .catch(err => {
          console.log(err);
        })
    },
    onSubmit () {
      axios
        .post('api/member/modify/myProfile', this.user_details)
        .then(res => {
          alert("수정되었습니다");
          this.$router.push('/myPage');
        })
        .catch(err => {
          console.log(err);
        })

    }

  }
}
</script>

<style lang="scss" scoped>
.q-pa-md{
  padding: 5% 30%;
  max-width: 100%;
  height: 100vhs;
  display: flex;
  position: absolute;


  .q-input{
    max-width: 600px;
  }
}
.title{
  text-align: center;
}
.q-gutter-md{

}


.card-bg {
  background-color: #162b4d;
}

</style>
