<template>
    <div>
        <h3>
            구매 완료 리스트
        </h3>
    </div>
        <!-- <button @click="listApi">api 통신</button> -->
        <!-- <ListPurComItem :datas="datas"/> -->
        <div v-if="loading">데이터를 로딩 중입니다...</div>
        <ListPurComItem v-else :datas="datas" />
        

        
</template>

<script setup>
import { onMounted, ref } from 'vue';
import ListPurComItem from '../components/ListPurComItem.vue';

const datas = ref([]);
const loading = ref(false);

const fetchData = async() => {
    try {
        loading.value = true;
        const response = await axios.post('/api/purchaseList');
        console.log(response.data.data);
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