<template>
  <q-page class="">
    <div class="row">
      <div class="col-9 q-pa-md">
        <h3>상품 상세 페이지</h3>
        <div class="row">
          <div class="col-6">
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
          </div>
          <div class="col-6 q-px-md">
            <div class="text-h4 q-mb-none q-mt-md">{{curItem.title}}</div>
            <div class="text-subtitle1 q-mt-sm">{{curItem.contents}}</div>
            <div class="text-h4 q-mt-xl text-right">{{ numberWithCommas(curItem.price) }}원</div>
            <div class="text-right" v-if="curItem.status === 0">
              <q-btn color="primary q-mt-xl " :to="`/buy/${curItem.number}`">구매하기</q-btn>
            </div>
            <div class="text-right" v-else-if="curItem.status === 1">
              <q-btn color="primary q-mt-xl " @click="in_stock" >물건 등록 완료</q-btn>
              <q-btn color="primary q-mt-xl " @click="canceled">취소하기</q-btn>
            </div>
            <div class="text-right" v-else-if="curItem.status === 3">
              <q-btn color="primary q-mt-xl " @click="pickUpComplete">구매자 픽업 완료</q-btn>
              <q-btn color="primary q-mt-xl " @click="birdMatching" >버드 신청</q-btn>
            </div>
            <div class="text-right" v-else-if="curItem.status === 5">
              <q-btn color="primary q-mt-xl " @click="bridMatched" >버드 매칭 </q-btn>
            </div>
            <div class="text-right" v-else-if="curItem.status === 6">
              <q-btn color="primary q-mt-xl " @click="birdComplete" >배달 완료</q-btn>
            </div>
            <div class="text-right" v-else-if="curItem.status === 7 || curItem.status === 4">
              <q-btn color="primary q-mt-xl " >거래 완료</q-btn>
            </div>
          </div>
        </div>


      </div>
      <div class="col-3">
        <q-list bordered separator class="cardArea ">
          <q-item clickable v-ripple v-for="item in itemList" :key="item.number" @click="onDetail(item.number)">
            <q-item-section avatar>
              <q-avatar rounded>
                <q-img
                  :src="require('../assets/images/' + item.itemImages[0].fileName + item.itemImages[0].format)"
                  :ratio="1"
                />
              </q-avatar>
            </q-item-section>
            <q-item-section>
              {{item.title}}
              <q-item-label caption>Caption</q-item-label>
            </q-item-section>

            <q-item-section side>{{ numberWithCommas(item.price) }}원</q-item-section>
          </q-item>
        </q-list>
      </div>
    </div>
  </q-page>
</template>

<script>
import axios from "axios";

export default {
  name: 'Detail',
  data () {
    return {
      itemList: [],
      slide: 0
    }
  },
  mounted() {
    this.items()
  },
  computed: {
    itemId () {
      return this.$route.params.itemId
    },
    curItem () {
      return this.itemList.filter(i => i.number == this.itemId)[0]
    }
  },
  methods : {
    onDetail (itemId) {
      this.$router.push(`/main/bird_detail/${itemId}`)
    },
    numberWithCommas(num) {
      return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    // item 값 다 가져오기
    items () {
      axios
        .get('/api/board/findMyBoard')
        .then(res => {
          this.itemList = res.data
          console.log(this.itemList)
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 물건 등록 완료
    in_stock () {
      axios
        .get('api/inStock/' + this.curItem.number)
        .then(res => {
          alert(res.data);
          this.$router.push('/mydeal');
        })
        .catch(err => {
          console.log(err);
        })
    },
    // 취소하기
    canceled () {
      axios
        .get('api/canceled/' + this.curItem.number)
        .then(res => {
          alert("판매가 취소되었습니다");
          this.$router.push('/mydeal');
        })
        .catch(err => {
          console.log(err);
        })
    },
    //버드 신청
    birdMatching () {
      axios
        .get('api/birdMatching/' + this.curItem.number)
        .then(res => {
          alert("버드 신청이 완료되었습니다");
          this.$router.push('/main');
        })
        .catch(err => {
          console.log(err);
        })
    },
    // 픽업 거래 완료
    pickUpComplete () {
      axios
        .get('api/pickUpComplete/' + this.curItem.number)
        .then(res => {
          alert("거래가 완료되었습니다");
          this.$router.push('/main');
        })
        .catch(err => {
          console.log(err);
        })
    },
    // 버드 매칭
    bridMatched() {
      axios
        .get('api/birdMatched/' + this.curItem.number)
        .then(res => {
          alert("매칭되었습니다");
          this.$router.push('/bird');
        })
        .catch(err => {
          console.log(err);
        })
    },
    // 버드 배달 완료  //deal 에 bird 넘버가 안들어가서 오류가 남
    birdComplete () {
      axios
        .get('api/birdComplete/' + this.curItem.number)
        .then(res => {
          alert("배달 완료");
          this.$router.push('/main');
        })
        .catch(err => {
          console.log(err);
        })
    },
  }

}
</script>
<style lang="scss" scoped>
</style>
