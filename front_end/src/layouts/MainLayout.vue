<template>
  <q-layout view="lHh Lpr lFf">
    <q-drawer
      show-if-above
      bordered
      content-class="bg-grey-1"
    >
      <div class="column full-height">
        <div class="col text-center">
          <h2 class="q-ma-none">네비</h2>
        </div>
        <div class="col-10">
          <div class="column justify-center full-height">

            <div class="col-2 relative-position">
              <!-- <q-icon name="home" class="text-purple absolute-center" size="xl" /> -->
              <q-btn round color="primary" 
              class="absolute-center" 
              size="xl" 
              icon="home"
              to = "/" />
            </div>
            <!--  -->
            <div class="col-2 relative-position">
               <q-btn align="between" 
               class="btn-fixed-width absolute-center" 
               color="accent" 
               label="TEST PAGE" 
               icon="flight_takeoff" 
               to="/test"/>
            </div>

            <div class="col-2 relative-position">
               <q-btn align="between" 
               class="btn-fixed-width absolute-center" 
               color="accent" 
               label="물건등록페이지" 
               icon="flight_takeoff" 
               to="/stuff"/>
            </div>

            <div class="col-2 relative-position">
               <q-btn :loading="loading1"
               class = "absolute-center" 
               color="secondary" 
               @click="simulateProgress(1)" 
               label="upload page button" 
               to="/upload"/>
            </div>

            <div class="col-2 relative-position">
              <q-icon name="account_circle" 
              class="text-purple absolute-center" 
              size="xl" />
            </div>

          </div>
        </div>
        <div class="col text-center">
          <h2 class="q-ma-none">단델리온</h2>
        </div>
      </div>
      
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>


export default {
  name: 'MainLayout',
  components: {  },
  data () {
    return {
      loading1: false,
    }
  },
  methods: {
    simulateProgress(number){
      this[`loading${number}`] = true
      setTimeout(() =>{
        thos[`loading${number}`] = false
      }, 3000)
    }
  }
}
</script>
