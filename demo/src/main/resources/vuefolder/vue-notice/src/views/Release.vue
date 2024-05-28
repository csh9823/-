<template>
    <div>
        출고
        <div v-if="loading">데이터를 로딩 중입니다...</div>
        <ReleaseItem v-else :datas="datas"/>
    </div>
</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import ReleaseItem from '../components/ReleaseItem.vue';

const datas = ref(null);
const loading = ref(true);


const fetchData = async() => {

    try {
        const response = await axios.post('/api/releasePage');
        console.log(response.data);
        datas.value = response.data;



        loading.value = false;
    } catch (error) {
        loading.value = false;
        console.log(error)
    } finally {
        loading.value = false;
    }

}

onMounted(() => {
    fetchData();
})

</script>

<style scoped>

</style>