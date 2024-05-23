<template>
    <div class="search-container">
        <select v-model="selectedSearchOption">
            <option v-for="(option,index) in searchOptions" :key="index" :value="option.value">{{ option.label }}</option>
        </select>
        <input type="text" v-model="searchKeyword" placeholder="검색">
        <button @click="emitSearch">검색</button>
    </div>
</template>

<script setup>
import { computed, ref } from 'vue';

const searchOptions = computed(() => [
    { label: '제품명', value: 'product_name'},
    { label: '거래처', value: 'account_value'},
    { label: '부서명', value: 'department_value'}
])


const selectedSearchOption = ref(searchOptions.value[0].value);
const searchKeyword = ref('');

const emit = defineEmits(['search']);

const emitSearch = () => {
    emit('search', {
        option: selectedSearchOption.value,
        keyword: searchKeyword.value
    });
};

</script>

<style scoped>

</style>