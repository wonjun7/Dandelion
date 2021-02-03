
<template>
  <div class="no-margin no-padding">
    <q-carousel
     
      width= "100%"
      animated
      v-model="slide"
      navigation
      infinite
      swipreable
      :autoplay="autoplay"
      
      transition-prev="slide-right"
      transition-next="slide-left"
      @mouseenter="autoplay = false"
      @mouseleave="autoplay = true"
    >
      <q-carousel-slide :name="1" img-src="https://cdn.quasar.dev/img/mountains.jpg" />
      <q-carousel-slide :name="2" img-src="https://cdn.quasar.dev/img/parallax1.jpg" />
      <q-carousel-slide :name="3" img-src="https://cdn.quasar.dev/img/parallax2.jpg" />
      <q-carousel-slide :name="4" img-src="https://cdn.quasar.dev/img/quasar.jpg" />
    </q-carousel>

    <div class="q-pt-lg">
      <div class="col text-h4 ellipsis q-pb-lg text-center ">
        단델리온은 어떤 서비스인가요?
      </div>
      <div class="col text-h6 q-px-xl q-mb-xl text-center ">
        단델리온은 비대면으로 중고거래를 도와주는 사이트입니다. <br>
        또한, '버드'를 통하여 거래한 중고물품을 집앞까지 배송해드립니다!<br>
        모든 회원분들은 '버드'를 신청 할 수 있고<br>
        어쩌고저쩌고 블라블라울라숑<br>
        * 시간 총량제 주 20시간 이내
      </div>
<!--  -->
      <div class="main-top-color">
        <div class="main-top-content">

        <div class="row">
          
            <div class="col-4 flex flex-center text-center">
              <div class="text-h4">안전한 거래 플랫폼 <br> 단델리온</div> 
              <q-btn color="primary" glossy label="시작하기" size="xl" to="/login"  />
            </div>
          <!--  -->
          <div class="col">
            <q-img 
            class="mainimg"
            src="https://d1unjqcospf8gs.cloudfront.net/assets/home/main/3x/image-top-4eb6b8642f61c5c012136597a25a7b72c705d6c6479a7270f3fb23726fddf585.png" 
            fixed
            />
          </div>
        </div>

        </div>
      </div>
<!--  -->
      <div class="row justify-center">
        <div class="col-md-8 offset-md q-pt-xl">
          <div class="row">
            <!-- 3 pick content -->
            <div class="col col-md q-my-md q-mx-md ">
              Ratio: 1
              <q-img
                src="https://placeimg.com/500/300/nature"
                :ratio="1"
              />
              <div class="text-h5 q-px-xl text-center ">
                자유롭게 정하는 스케줄
              </div>

              <div class="text-h6 q-px-xl text-center ">
                내 스케줄에맞춰서 날짜와<br>
                시간을 자유롭게 선택할수있어요<br>
              </div>
              <div class="1-pa-xl">
                <hr>
              </div>
            </div>
            <!--  -->
            <div class="col col-md q-my-md q-mx-md">
              Ratio: 1
              <q-img
                src="https://placeimg.com/500/300/nature"
                :ratio="1"
              />
              <div class="text-h5 q-px-xl text-center ">
                자유롭게 정하는 스케줄
              </div>

              <div class="text-h6 q-px-xl text-center ">
                내 스케줄에맞춰서 날짜와<br>
                시간을 자유롭게 선택할수있어요<br>
              </div>
              <div class="1-pa-xl">
                <hr>
              </div>
            </div>
            <!--  -->
            <div class="col col-md q-my-md q-mx-md ">
              Ratio: 1
              <q-img
                src="https://placeimg.com/500/300/nature"
                :ratio="1"
              />
              <div class="text-h5 q-px-xl text-center ">
                자유롭게 정하는 스케줄
              </div>

              <div class="text-h6 q-px-xl text-center ">
                내 스케줄에맞춰서 날짜와<br>
                시간을 자유롭게 선택할수있어요<br>
              </div>
              <div class="1-pa-xl">
                <hr>
              </div>
            </div>
            <!-- 3 pick content -->
          </div>

            <div class="asdqwe q-pt-sm q-pb-xl">
              - 시간당 평균 수입은 2020년 4월 21일~ 2020년 5월 20일 기준으로 산정된 금액 입니다.<br>
              - 배달수단, 지역, 거리에 따라 배달 금액이 다르며, 해당 금액은 사전 공지 후 변경 될 수 있습니다.
            </div>

            <div class="text-h4 text-center q-mb-xl">
              '버드'분들은 오토바이 외에도 <br>
              다양한 이동 수단으로 배송 할 수 있어요

            </div>

        </div>
      </div>

    </div>
  </div>


</template>

<script>
export default {
  data () {
    return {
      slide: 1,
      autoplay: true
    }
  }
}
</script>

<style lang="scss">

.asdqwe{
  margin-bottom: 50px;
  margin-top: 0px;
}
.main-top-color{
  background-color: bisque;
  height: 750px;
}
.main-main-top-content{
  padding: 10px 10px;
}
.mainimg{
  display: inline-block;
}
</style>