<template>
  <q-page class="q-px-md">
    <h3 class="q-mb-sm">주문/결제</h3>
    <section>
      <div class="row">
        <div class="col-6">
          <h4 class="q-mb-sm">상품 정보</h4>

            <div class="q-pa-md">
              <q-carousel
                swipeable
                animated
                v-model="slide"
                thumbnails
                infinite
              >
                <q-carousel-slide v-for="itemImage in curItem.itemImages" :key="itemImage.number" :name="itemImage.orderNumber" :img-src="require('../assets/images/' + itemImage.fileName +itemImage.format)">
                </q-carousel-slide>
              </q-carousel>
            </div>


          <div class="col-6 q-px-md">
            <div class="text-h5 q-mb-none q-mt-md">{{curItem.title}}</div>
            <div class="text-subtitle1 q-mt-sm">{{curItem.centents}}</div>

          </div>
        </div>
        <div class="col-6">
          <section>
            <h4 class="q-mb-sm">구매자 정보</h4>
            <q-list bordered class="rounded-borders">
              <q-item>
                <q-item-section class="col-2 gt-sm" >이름</q-item-section>
                <q-item-section><q-input v-model="buyer.name" readonly/></q-item-section>
              </q-item>
              <q-item>
                <q-item-section class="col-2 gt-sm">아이디</q-item-section>
                <q-item-section><q-input v-model="buyer.id" readonly/></q-item-section>
              </q-item>
              <q-item>
                <q-item-section class="col-2 gt-sm">휴대전화</q-item-section>
                <q-item-section><q-input v-model="buyer.phone" readonly/></q-item-section>
              </q-item>
            </q-list>
          </section>
<!--          <section>-->
<!--            <h4 class="q-mb-sm">구매 방법</h4>-->
<!--            <q-tabs-->
<!--              v-model="payMethod"-->
<!--              inline-label-->
<!--              class="bg-purple text-white shadow-2"-->
<!--            >-->
<!--              <q-tab name="pickup" icon="archive" label="픽업" />-->
<!--              <q-tab name="connector" icon="pedal_bike" label="커넥터" />-->
<!--            </q-tabs>-->
<!--            <div v-show="payMethod == 'pickup'" class="q-px-sm">-->
<!--              <h5 class="q-my-sm">보관소 위치</h5>-->
<!--              <q-select v-model="pickupSpace" :options="pickupOptions" label="보관소 위치를 선택하세요." />-->
<!--              <div v-if="pickupSpace" class="text-subtitle1 q-mt-sm">{{pickupSpace.desc}}</div>-->
<!--            </div>-->
<!--            <div v-show="payMethod == 'connector'" class="q-px-sm">-->
<!--              <h5 class="q-my-sm">배송 정보</h5>-->
<!--              <q-list bordered class="rounded-borders">-->
<!--                <q-item>-->
<!--                  <q-item-section class="col-2 gt-sm">상품 가격</q-item-section>-->
<!--                  <q-item-section><q-input v-model="addr" label="주소를 입력하세요" /></q-item-section>-->
<!--                </q-item>-->
<!--                <q-item>-->
<!--                  <q-item-section class="col-2 gt-sm">연락처</q-item-section>-->
<!--                  <q-item-section><q-input v-model="phone" /></q-item-section>-->
<!--                </q-item>-->
<!--                <q-item>-->
<!--                  <q-item-section class="col-2 gt-sm">요청사항</q-item-section>-->
<!--                  <q-item-section><q-input v-model="request" label="예) 문 앞에 놓아주세요" /></q-item-section>-->
<!--                </q-item>-->
<!--              </q-list>-->
<!--            </div>-->
<!--          </section>-->
        </div>
      </div>
    </section>
    <section>
      <h4 class="q-mb-sm">상품 정보</h4>

      <q-list bordered class="rounded-borders">
        <q-item>
          <q-item-section class="col-2 gt-sm">상품 가격</q-item-section>
          <q-item-section>{{numberWithCommas(curItem.price)}}원</q-item-section>
        </q-item>
        <q-item>
          <q-item-section class="col-2 gt-sm">{{payMethod == 'pickup' ? '보관소 이용료': '커넥터 수수료'}}</q-item-section>
          <q-item-section>{{numberWithCommas(2000)}}원</q-item-section>
        </q-item>
        <q-item>
          <q-item-section class="col-2 gt-sm">보유 포인트</q-item-section>
          <q-item-section>{{numberWithCommas(buyer.point)}}원</q-item-section>
        </q-item>
        <q-item>
          <q-item-section class="col-2 gt-sm">결제 후 포인트</q-item-section>
          <q-item-section>{{numberWithCommas(buyer.point - curItem.price - 2000)}}원</q-item-section>
        </q-item>
      </q-list>
    </section>
    <section class="text-center">
      <q-btn size="xl" color="primary" class="q-px-xl q-my-md" @click="buy">결제하기</q-btn>
    </section>
  </q-page>
</template>

<script>
import axios from "axios";
export default {
  name: 'Buy',
  data () {
    return {
      curItem: null,
      buyer: null,
      slide: 0
    }
  },
  mounted() {
    this.curItem_find()
    this.buyerInfo()
  },
  computed: {
    itemId () {
      return this.$route.params.itemId
    },
    servicePrice () {
      return this.payMethod == 'pickup' ? 3000: 2000
    }
  },
  methods : {
    onDetail (itemId) {
      this.$router.push(`/detail/${itemId}`)
    },
    numberWithCommas(num) {
      return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    curItem_find() {
      axios
        .get('api/findBoardId/' + this.itemId)
        .then(res => {
          this.curItem = res.data;
        })
        .catch(err => {
          console.log(err);
        })
    },
    buy () {
      axios
        .post('api/buy/' + this.itemId)
        .then(res => {
          if (res.data === true) {
            console.log(res.data)
            alert("결제가 완료되었습니다")
            this.$router.push('/main')
          } else {
            alert("잔액이 부족합니다")
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    //구매자 정보 가져오기
    buyerInfo () {
      axios
        .get('api/member/view')
        .then(res => {
          this.buyer = res.data;
          console.log(this.buyer);
        })
        .catch(err => {
          console.log(err);
        })
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
