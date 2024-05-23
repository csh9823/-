<template>
    <div>
        <div class="filter-container">

            <div class="dropbox">
                <Dropbox v-model="selectedCategory" />
                
            </div>
            <div class="search-container">
                <select v-model="selectedSearchOption">
                    <option v-for="(option,index) in searchOptions" :key="index" :value="option.value">{{ option.label }}</option>
                </select>
                <input type="text" v-model="searchKeyword" placeholder="검색">
                <button @click="search">검색</button>
            </div>
            <div class="date-container">
                <input type="date" v-model="startDate" placeholder="시작 날짜">
                ~ 
                <input type="date" v-model="endDate" placeholder="종료 날짜" :max="maxEndDate">
            </div>
        </div>
        <div class="sortBtn">
        <!-- <button @click="toggleSortOrder">
            {{ sortOrder === 'asc' ? '날짜 내림차순 정렬' : '날짜 오름차순 정렬' }}
        </button> -->
    </div>
    <table>
        <thead>
            <tr>
                <th>제품명</th>
                <th>출고부서</th>
                <th>단위</th>
                <!-- <th>박스 수</th> -->
                <!-- <th>현재 수량</th> -->
                <th>출고 박스</th>
                <th>입고가</th>
                <th>출고가</th>
                <!-- <th>입고일</th> -->
                <th>출고일</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item, index) in releaseList" :key="index">
                
                <td>{{ item.product_name }}</td>
                <td>{{ item.department_value }}</td>
                <td>{{ item.boxname }} / {{ item.boxcount }}</td>
                <!-- <td>{{ item.store_quantity }}</td> -->
                <!-- <td>{{ item.boxcount * item.store_quantity }}</td> -->
                <!-- 현재 수량 관련 상의해야함 -->
                <td>{{ item.release_quantity }}</td>
                <td>{{ item.store_price }}</td>
                <td>{{ item.release_price }}</td>
                <!-- <td>{{ formatDate(item.store_date) }}</td> -->
                <td>{{ formatDate(item.release_date) }}</td>
            </tr>
        </tbody>
        </table>
    </div>
</template>

<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import './css/List.css'
import Dropbox from './ui/dropbox.vue';


const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString();
};

const props = defineProps({
    datas: {
        type: Array
        
    }
})

const release = [
    ...props.datas
]

const releaseList = ref(release);


const searchOptions = computed(() => [
        { label: '제품명', value: 'product_name'},
        { label: '부서명', value: 'department_value'}
])

    const selectedCategory = ref('all');
    const searchKeyword = ref('');
    const startDate = ref('');
    const endDate = ref('');

    const selectedSearchOption = ref('product_name');

// 오늘의 날짜를 구하기 위한 함수
function getTodayDate() {
        const today = new Date();
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = String(today.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    // 7일 전의 날짜를 구하기 위한 함수
    function getStartDate() {
        const today = new Date();
        today.setDate(today.getDate() - 7);
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = String(today.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    // 기본값 설정
    startDate.value = getStartDate();
    endDate.value = getTodayDate();

    const maxEndDate = getTodayDate();



    // 날짜, 카테고리별 조회
    function filteredList() {
        let newList = release;

        if (selectedCategory.value !== 'all') {
            console.log(selectedCategory.value)
            newList = newList.filter(item => item.category_value === selectedCategory.value);
            console.log(newList)
        }
        if (startDate.value.trim() !== '' && endDate.value.trim() !== '') {
            const start = new Date(startDate.value);
            const end = new Date(endDate.value);
            newList = newList.filter(item => {
                const itemDate = new Date(item.release_date);
                return itemDate >= start && itemDate <= end;
            });
        }
        releaseList.value = newList;
    }


    // 제품, 거래처 조건검색
    function search() {
        console.log(selectedSearchOption.value);
        let newList = release;
        if (searchKeyword.value.trim() !== '') {
            const keyword = searchKeyword.value.trim().toLowerCase();
            if (selectedSearchOption.value == 'product_name') {
                newList = newList.filter(item => item.product_name.toLowerCase().includes(keyword));
            } else {
                newList = newList.filter(item => item.department_value.toLowerCase().includes(keyword));
            }
        }
        releaseList.value = newList;
    }

    watch([selectedCategory, startDate, endDate], () => {
        filteredList();
    });

    onMounted(() => {
        filteredList();
    })

</script>

<style scoped>

</style>