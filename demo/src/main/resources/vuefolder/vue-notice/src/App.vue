<template>
<<<<<<< HEAD
  <!-- <router-link to="/">홈</router-link> 
  <br/>
  <br/>
  <router-link to="/listPurchaseCompleted">구매완료 리스트</router-link>
  <br/>
  <router-link to="/listStockCom">입고완료 리스트</router-link>
  <br/>
  <router-link to="/calculate">정산</router-link>
  <br/>
  <router-link to="/listRelease">출고 리스트</router-link>
  <br/> -->
  <div id="apps">

    <Sidebar/>
    <div class="view_container">
      <router-view></router-view>
    </div>
  </div>
=======
    <router-link to="/division">분류등록</router-link> 
    <router-link to="/department">부서등록</router-link> 
    <router-link to="/account">거래처 등록</router-link>
    <router-link to="/box">단위코드 등록</router-link>
    <router-link to="/Productmaster">제품마스터 등록</router-link>
    <router-link to="/Purchase">구매</router-link>
    <router-link to="/Store">입고 대기</router-link>
  <router-view></router-view>
>>>>>>> branch 'master' of https://github.com/csh9823/demo.git
</template>

<script setup>
import Sidebar from './components/menu/Sidebar.vue';
// import './style.css'
  


</script>
<style>

#apps {
    display: flex;
    max-width: 1280px;
    margin: 0 auto;
    padding: 2rem;
    text-align: center;
}

/* view_container 스타일 */
.view_container {
    margin-left: 200px; /* 사이드바 너비만큼 마진을 줍니다 */
    padding: 20px;
    width: calc(100% - 200px); /* 전체 너비에서 사이드바 너비를 뺀 너비를 계산합니다 */
    min-height: 100vh;
    background-color: #fff;
    box-sizing: border-box;
    overflow-y: auto;
}

</style>
