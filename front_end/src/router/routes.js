import store from "../store"

const rejectAuthUser = (to, from , next) => {
  if(store.state.isLogin === true){
    // 이미 로그인된 유저니까 막아야 한다.
    alert("이미 로그인 되었다")
    next("/") // 홈으로 리다이렉션 시킨다
  } else{
    next()
  }
}
const onlyAuthUser = (to, from , next) => {
  if(store.state.isLogin === false){
    // 아직 로그인안된 유저니까 막아야 한다.
    alert("로그인이 필요한 기능입니다")
    next("/") // 홈으로 리다이렉션 시킨다
  } else{
    next()
  }
}


const routes = [
  {
    path: '/',
    component: () => import('pages/Login.vue'),
    children: [
    //   { path: '', name: 'PageIndex', component: () => import('pages/Index.vue') },
    //   { path: 'detail/:itemId',name:'Detail', component: () => import('pages/Detail.vue') },
    //   { path: 'buy/:itemId', component: () => import('pages/Buy.vue') },
    //   { path: '/test', component: () => import('pages/Test.vue')},
    //   { path: '/stuff', name: 'Stuff', component: () => import('pages/Stuff_upload.vue')},
    //   { path: '/profile',name:'UserProfile', component: () => import('pages/Profile.vue')},
    //   { path: '/main2', component: () => import('pages/Main2.vue')},
    //   { path: '/myPage', name:'myPage', component: () => import('pages/MyPage.vue')}
    //   // { path: '/new_main',component: () => import('pages/asdfasdf.vue')}
    ]
  },
  {
    path: '/main',
    component: () => import('layouts/Layout.vue'),
    children: [
      { path: '', name: 'PageIndex', component: () => import('pages/Index.vue') },
      { path: '/main/detail/:itemId',name:'Detail', component: () => import('pages/Detail.vue') },
      { path: '/main/bird_detail/:itemId',name:'Brid_Detail', component: () => import('pages/Bird_Detail.vue') },
      { path: '/buy/:itemId', component: () => import('pages/Buy.vue') },
      { path: '/test', component: () => import('pages/Test.vue')},
      { path: '/stuff', name: 'Stuff', component: () => import('pages/Stuff_upload.vue')},
      { path: '/profile',name:'UserProfile', component: () => import('pages/Profile.vue')},
      { path: '/main2', component: () => import('pages/Main2.vue')},
      { path: '/myPage', name:'myPage', component: () => import('pages/MyPage.vue')},

      { path: '/bird',name:'bird', component: () => import('pages/Bird_menu.vue')},
      { path: '/developer',name:'developer', component: () => import('pages/Developer.vue')},
      { path: '/mydeal',name:'mydeal', component: () => import('pages/MyDeal.vue')},
      { path: '/selldeal',name:'selldeal', component: () => import('pages/DealPage/SellDeal.vue')},
      { path: '/buydeal',name:'buydeal', component: () => import('pages/DealPage/BuyDeal.vue')},
      { path: '/modifyPassword', name:'modifyPassword', component: () => import('pages/modifyPassword.vue')},
      { path: '/selldetail/:itemId', name:'SellDetail', component: () => import('pages/SellDetail.vue')}
      // { path: '/new_main',component: () => import('pages/asdfasdf.vue')}
    ]

  },
  {
    path: '/login2',
    component: () => import('components/Login2.vue')
  },
  {
    path: '/register',
    component: () => import('pages/Register.vue')
  },
  {
    path: '/mainhome',
    component: () => import('pages/Mainhome.vue')
  },

  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
