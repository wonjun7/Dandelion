<template>
  <div class="q-pa-md">
    <q-table
      :grid="$q.screen.xs"
      title="판매내역"
      :data="data"
      :columns="columns"
      row-key="name"
      :filter="filter"
    >
      <!--ㄴ 정렬헤더 숨기고 싶으면 사용 hide-header -->
      <template v-slot:top-right>
        <q-input borderless dense debounce="300" v-model="filter" placeholder="Search">
          <template v-slot:append>
            <q-icon name="search" />
          </template>
        </q-input>
      </template>
    </q-table>
  </div>
</template>

<script>
import axios from "axios";
import jwt_decode from "jwt-decode";

let token = localStorage.getItem("access_token");
let decoded = jwt_decode(token);
console.log(decoded.role);

export default {
    computed:{
        numberWithCommas(price) {
            return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
    },
  data () {
    return {
      filter: '',
      columns: [
        {
          name: 'desc',
          required: true,
          label: '게시글 제목',
          align: 'left',
          field: row => row.title,
          format: val => `${val}`,
          sortable: true,
        },
        { name: 'contents', align: 'center', label: '내용', field: 'contents', sortable: true },
        { name: 'price', align: 'center', label: '가격', field: row => row.price, sortable: true },
        { name: 'status', label: '거래상태', field: 'status', sortable: true }
        // { name: 'buyer', label: '구매자', field: 'buyerNumber'  }
      ],
      data: [
        // {
        //   name: '강화유리 필름 아이폰6,7,8플러스',
        //   price: 1000,
        //   dealmethod: '버드',
        //   location: '낙성대역'
        // },

      ]
    }
    },
  mounted() {
    this.orderListBuy()
  },
  methods: {
    orderListBuy () {
      axios
        .get('api/board/findMyBoard')
        .then(res => {
          this.data = res.data.filter(data =>  data.status < 5 || data.status === 7);
          console.log(this.data);
        })
        .catch(err => {
          console.log(err);
        })
    }
  }
}
</script>
