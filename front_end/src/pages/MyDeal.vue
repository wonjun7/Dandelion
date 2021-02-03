<template>
  <div class="q-pa-md q-mt-xl">

    <div class="row">

      <div class="col flex flex-center">
        <div class="text-purple q-gutter-md" style="font-size: 2em">
          <q-icon name="receipt" size=300px />              
        </div>
        <!--  -->
          <q-separator vertical spaced="xl" />
        <!--  -->
          <div>
            <q-icon name="warning" size=300px />
          </div>
      </div>
    </div>

    <div class="row">
      <div class="col flex flex-center">
          <div>
            <q-btn
              color="primary"
              size="70px"
              label="판매내역"
              to="/selldeal"
            />
          </div>
          <q-separator vertical spaced="xl" />
          <div>

            <q-btn
              color="secondary"
              size="70px"
              label="구매내역"
              to="buydeal"
            />
          </div>
      </div>

    </div>

  </div>
</template>

<script>
export default {
    data() {
        return{
            notuserd: false,
            sizes : 50,
        }

    }

}
</script>

<style lang="scss" scoped>

</style>