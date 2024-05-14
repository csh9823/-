<template>
    <div>
        <h3>구매 완료 리스트</h3>
        <button @click="listApi">api 통신</button>
        <ListPurComItem />
    </div>
</template>

<script setup>
import ListPurComItem from '../components/ListPurComItem.vue';
import axios from 'axios';

const listApi = () => {

    const fetchData = async() => {
        try {
            const response = await axios.post('/api/purchaseList', 'hi');
            console.log(response);
            
        } catch (error) {
            console.log(error)
        }
    }

    fetchData();
}



</script>

<style lang="scss" scoped>

</style>