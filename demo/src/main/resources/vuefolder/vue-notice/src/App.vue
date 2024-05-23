<template>
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
