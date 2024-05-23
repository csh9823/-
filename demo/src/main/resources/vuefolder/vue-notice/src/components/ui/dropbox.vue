<template>
    <div class="container">
        <input type="checkbox" v-model="showDeleted"/>삭제된 코드 보기<br/>
        <select v-model="selectedCategory">
            <option v-for="(option) in filteredOptions" :key="option.code" :value="option.value">{{ option.value }}</option>
        </select>
    </div>
</template>

<script setup>
import axios from 'axios';
import { computed, onMounted, ref, watch } from 'vue';


const a = ref([{ code: 0, value: 'all', situation: 1}]);

    const props = defineProps({
        options: Array,
        modelValue: {
            type: String,
            // default: () => [{ code: 0, value: 'all', situation: 1}]
            
            
        }
    });


    const fetchData = async() => {
        try {
            const response = await axios.post("/api/categoryHistory");
            a.value = [...a.value, ...response.data.data];
        } catch (error) {
            console.error("Error fetching data:", error);
        }
    }

    const showDeleted = ref(false);

    const filteredOptions = computed(() => {
        if (showDeleted.value) {
            return a.value;
        } else {
            return a.value.filter(option => option.situation === 1);
        }
    });

    const emit = defineEmits();

    const selectedCategory = ref(props.modelValue);

    watch(selectedCategory, (value) => {
        emit('update:modelValue', value);
    });

    onMounted(()=> {
        fetchData();
    })

</script>

<style scoped>
    /* 컨테이너 스타일 */
    .container {
        margin-bottom: 10px;
        background-color: #f5f5f5; /* 컨테이너 배경색 */
    padding: 10px; /* 컨테이너 안쪽 여백 */
    }

    /* 체크박스 스타일 */
    .container input[type="checkbox"] {
        margin-right: 5px;
        transform: scale(1.5); /* 체크박스 크기 확대 */
    }

    /* 셀렉트 박스 스타일 */
    .container select {
        padding: 5px;
        border: 1px solid #ccc; /* 셀렉트 박스 테두리 추가 */
        border-radius: 5px; /* 셀렉트 박스 모서리 둥글게 */
    }

    /* 셀렉트 박스 드롭다운 화살표 아이콘 스타일 */
    .container select::after {
        content: '\25BC'; /* 화살표 아이콘 추가 */
        position: absolute;
        top: 50%;
        right: 10px;
        transform: translateY(-50%);
        pointer-events: none; /* 이벤트 차단 */
        color: #666; /* 화살표 아이콘 색상 */
    }
</style>