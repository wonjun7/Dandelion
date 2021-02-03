<template>
  <q-layout>
    <q-page-container>
      <q-page class="flex bg-image flex-center">
        <q-card class="cardsize">
          <q-card-section>
          </q-card-section>
          <q-card-section>
            <div class="text-center q-pt-lg">
              <div class="col text-h4 ellipsis">
                회원가입
              </div>
            </div>
          </q-card-section>
          <q-card-section>
            <q-form
              class="q-gutter-md"
              @submit="onRegister"
            >
              <q-input
                filled
                v-model="id"

                hint="이메일(Id)를 입력해주세요"
                lazy-rules
              />

              <!-- <q-input
                type="password"
                filled
                v-model="pw"
                label="Password"
                lazy-rules

              /> -->
              <q-input v-model="pw" filled type="password" hint="비밀번호를 입력해주세요" />

              <q-input v-model="pw" filled :type="isPwd ? 'password' : 'text'" hint="비밀번호 확인">
                <template v-slot:append>
                  <q-icon
                    :name="isPwd ? 'visibility_off' : 'visibility'"
                    class="cursor-pointer"
                    @click="isPwd = !isPwd"
                  />
                </template>
              </q-input>
              <q-input v-model="name" filled type="name" hint="이름" />
              <q-input v-model="phone" filled type="phone" hint="전화번호" />
              <q-input v-model="address" filled type="address" hint="주소" />
              <!-- <q-input v-model="date" filled type="date" hint="Native date" /> -->

              <div>
                <!-- <q-btn label="Login" to="/" type="button" color="primary"/> -->
                <q-btn label="등록" type="submit" color="primary" />
              </div>

            </q-form>
          </q-card-section>
        </q-card>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import axios from "axios"
    export default {
        data() {
            return {
                id: null,
                pw: null,
                birthday: null,
                name: null,
                address: null,
                role: null, // role: 0 == 일반회원 /// role:1 == 버드 회원
                phone: null,
                point: 0,
                tel: null,
                date: null,
                isPwd: true
            }
        },
        methods :{
          onRegister(){
            axios
              .post("api/signUp",{
                "id": this.id,
                "pw": this.pw,
                "name": this.name,
                "address": this.address,
                "isBird": 0,
                "phone": this.phone,
                "point": 0
              })
              .then(res => {
                console.log(res);
                alert("회원가입이 완료되었습니다");
                this.$router.push("/");
              })
              .catch(err => {
                console.log(err);
              })


          }
        }
    }
</script>

<style>

  .bg-image {
   background-image: linear-gradient(135deg, #295F2D 0%, #FFE76C 100%);
   /* background-image: linear-gradient(135deg, #7028e4 0%, #e5b2ca 100%); */
  }
</style>
