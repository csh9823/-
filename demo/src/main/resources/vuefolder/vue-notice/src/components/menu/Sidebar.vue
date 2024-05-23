<template>
    <div class="sidebar">
        <router-link to="/" exact-active-class="active-link">홈</router-link>
        <router-link to="/Productmaster">제품마스터 등록</router-link>
        <router-link to="/division">분류등록</router-link> 
        <router-link to="/department">부서등록</router-link> 
        <router-link to="/account">거래처 등록</router-link>
        <router-link to="/box">단위코드 등록</router-link>
        <router-link to="/Purchase">구매</router-link>
        <router-link to="/listPurchaseCompleted" exact-active-class="active-link">구매완료 리스트</router-link>
        <router-link to="/store" exact-active-class="active-link">입고</router-link>
        <router-link to="/listStockCom" exact-active-class="active-link">입고완료 리스트</router-link>
        <router-link to="/listRelease" exact-active-class="active-link">출고 리스트</router-link>
        <router-link to="/calculate" exact-active-class="active-link">정산</router-link>
    </div>
</template>

<script setup>

</script>

<style>
    /* 사이드바 스타일 */
    .sidebar {
        width: 200px;
        height: 100vh;
        background-color: #f8f9fa;
        padding: 20px;
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        position: fixed;
        top: 0;
        left: 0;
        display: flex;
        flex-direction: column;
    }

    /* 링크 스타일 */
    .sidebar a {
        color: #333;
        text-decoration: none;
        padding: 10px 15px;
        margin-bottom: 10px;
        border-radius: 4px;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    /* 링크 호버 스타일 */
    .sidebar a:hover {
        background-color: #007bff;
        color: #fff;
    }

    /* 활성화된 링크 스타일 */
    .sidebar .active-link {
        background-color: #007bff;
        color: #fff;
    }

</style>