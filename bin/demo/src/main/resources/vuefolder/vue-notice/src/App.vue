<template>
  <div>
    <h1>메인 페이지 입니다.</h1>
  </div>
  <router-link to="/">홈</router-link> 
  <br/>
  <router-link to="/join">회원가입</router-link>
  <br/>
  <router-link to="/login">로그인</router-link>
  <br/>
  <router-link to="/listPurchaseCompleted">구매완료 리스트</router-link>
  <br/>
  <router-link to="/listStockCom">입고완료 리스트</router-link>
  <router-view></router-view>
</template>

<script setup>
  

</script>

<style lang="scss" scoped>

</style>