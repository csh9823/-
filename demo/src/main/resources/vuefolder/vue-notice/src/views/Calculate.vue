<template>
    <div>
        <h3>
            정산 페이지
        </h3>
    </div>
    <div v-if="loading">데이터를 로딩 중입니다...</div>
    <CalculateItem v-else :datas="datas"/>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import CalculateItem from '../components/CalculateItem.vue';
import axios from 'axios';

const datas = ref([]);
const loading = ref(false);


const fetchData = async() => {
    try {
        loading.value = true;
        const response = await axios.post('/api/calculate');
        console.log(response);
        datas.value = response.data.data;
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