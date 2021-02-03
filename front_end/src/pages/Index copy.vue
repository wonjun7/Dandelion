<template>
  <q-page class="">
    <div class="row">
      <div v-for="item in itemList" :key="item.id" class="col-4 cardArea">
        <q-card class="myCard" @click="onDetail(item.id)">
          <q-img
            :src="item.itemImg"
            :ratio="1"
          />

          <q-card-section>
            <div class="text-h6">{{item.title}}</div>
            <div class="text-subtitle2">by {{item.seller.name}}</div>
            <div class="text-subtitle2">by {{item.date}}</div>
            <div class="text-h6 text-right">{{numberWithCommas(item.price)}}원</div>
          </q-card-section>

          <q-card-section class="q-pt-none">
            {{ item.contents }}
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>
import axios from "axios";

export default {

  name: 'PageIndex', 
  data() {
    return {
      itemList: [],
    }
  },
  mounted (){
    axios
      .post("http://localhost:9091/mung/fileinsert")
      .then(res => {
        this.itemList = res
        console.log(res)
      })
  },
  methods : {
    onDetail (itemId) {
      this.$router.push(`/detail/${itemId}`)
    },
    numberWithCommas(num) {
      return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  }
}
</script>
<style lang="scss" scoped>
  .cardArea {
    padding: 30px;
    .myCard {
      cursor: pointer;
      transition: all .2s ease-in-out;
      &:hover {
        transform: scale(1.03);
      }
    }
  }
</style>