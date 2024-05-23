<template>
    <div>
        <h3>
            출고 리스트
        </h3>
    </div>
    <div v-if="loading">데이터를 로딩중 입니다.</div>
    <ListReleaseComItem v-else :datas="datas"/>
    
</template>

<script setup>
import { onMounted, ref } from 'vue';
import ListReleaseComItem from '../components/ListReleaseComItem.vue';
import axios from 'axios';

const datas = ref([]);
const loading = ref(false);


const fetchData = async() => {

    try {
        loading.value = true;
        const response = await axios.post("/api/releaseList")
        datas.value = response.data.data
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