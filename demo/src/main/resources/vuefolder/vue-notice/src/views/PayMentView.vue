<template>
    <div>
        <h3>
            정산 페이지
        </h3>
    </div>
    <div v-if="loading">데이터를 로딩 중입니다...</div>
    <PayMent v-else :datas="datas"/>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import PayMent from '../components/PayMent.vue';

const datas = ref(null);
const loading = ref(true);


const fetchData = async() => {
    try {
        const response = await axios.post('/api/payMent');
        console.log(response);
        datas.value = response.data;
        console.log(datas)
        loading.value = false;
    } catch (error) {
        loading.value = false;
        console.log(error)
    }
}

onMounted(() => {
    fetchData();
})


</script>

<style lang="scss" scoped>


</style>