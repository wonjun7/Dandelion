<template>
  <div>
    <form ref="uploadForm" @submit.prevent="submit">
      <q-input type="file" ref="uploadImage" @change="onImgUpload()" class="form-control" requied />
      <q-input type="button" @click="startupload" name="Upload" value="Upload" />

    </form>
  </div>
  
</template>

<script>
import axios from "axios";

export default {
  name: "imgForm",
  data () {
    return {
      formData: [],
    }
  },
  methods : {
    onImgUpload () {
      let file = this.$refs.uploadimge.files[0];
      this.formData = new FormData();
      this.formData.append("file", file)
    },
    startupload() {
      axios({
        url: ' ',
        data: this.formData,
        header: {
          Accept: 'application/json',
          'content-Type' : 'multipart/form-data'
        },
      }). then(res => {
        console.log(JSON.stringify(res.data));
      })

    }
  }

}
</script>

<style>

</style>