<template>
  <div class="q-pa-md" >
    <q-form @submit="onSubmit" class="q-gutter-md">
            <q-card class="my-card">
        <q-card-section>
      <h2 class="title">상품 등록</h2>
      <q-separator/>
      <!-- 사진업로드 -->

      <!-- 이름 작성 -->
      <div class="text-h4 q-my-md">상품명</div>

      <q-input
        name="name"
        v-model="name"
        color="primary"
        label="이름"
        filled
      />
      <!-- <q-input
      v-model="text"
      label="위로 할까 아래로 할까 고민이네 뭐하지"
      :dense="dense
      "/> -->
      <div class="text-h4 q-my-md">가격</div>
      <q-input
        v-model.number="price"
        type='number'
        label="가격"
        filled
      />

      <div class="text-h4 q-my-md">상품설명</div>
      <q-input
        v-model="textarea"
        style="max-width: 600px"
        filled
        type="textarea"
        label="상품설명"
      />
      <div class="text-h4 q-my-md">이미지 업로드</div>
      <!-- <div class="q-gutter-md row items-start">
        <q-input
          @input="val => { file = val[0] }"
          filled
          type="file"
          hint="클릭하여 이미지를 올리세요"
        />
      </div> -->

      <q-uploader
        color="teal"
        field-name="file"
        ref="uploadImage"
        multiple
        style="max-width: 300px"
      />



      <!--      <input type="file" ref="uploadImage" @change="onImageUpload()" class="form-control" required>-->
      <!--  -->
      <div class="q-mt-md">
        <q-separator />
        <q-btn label="등록" type="submit" color="primary" class="full-width" size="xl"/>
      </div>
        </q-card-section>
      </q-card>
    </q-form>

    <!-- <q-card v-if="submitResult.length > 0" flat bordered class="q-mt-md bg-grey-2">
      <q-card-section>Submitted form contains the following formData (key = value):</q-card-section>
      <q-separator />
      <q-card-section class="row q-gutter-sm items-center">
        <div
          v-for="(item, index) in submitResult"
          :key="index"
          class="q-px-sm q-py-xs bg-grey-8 text-white rounded-borders text-center text-no-wrap"
        >{{ item.name }} = {{ item.value }}
        </div>
      </q-card-section>
    </q-card> -->

  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      name: '',
      submitResult: [],
      text: '',
      textarea: '',
      ph: '',
      price:'',
      formData: null,

      dense : false
    }
  },
  methods: {
    onSubmit() {
      const formData = {
        title: this.name,
        price: this.price,
        contents: this.textarea
      }

      let file = this.$refs.uploadImage.files;
      this.formData = new FormData();
      file.forEach((file) => this.formData.append("files", file));

      axios
        .post('api/addBoard/', formData)
        .then(res => {
          console.log(res.data)
          axios
            .post('api/fileupload/', this.formData)
            .then(res => {
              console.log(res);
              alert("등록이 완료되었습니다");
              this.$router.push('/main');
            })
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
  padding: 15px 30%;
  max-width: 50%;
  height: 100vhs;
  display: flex;

  .q-input{
    max-width: 500px;
  }
}
.title{
  text-align: center;
}
</style>
