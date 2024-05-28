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
                <div class="sortBtn">
                    <button @click="toggleSortOrder">
                        {{ sortOrder === 'asc' ? '날짜 내림차순 정렬' : '날짜 오름차순 정렬' }}
                    </button>
                </div>
            </div>
        </div>
    <table>
        <thead>
            <tr>
                <th>제품명</th>
                <th>출고부서</th>
                <th>단위</th>
                <th>출고 박스</th>
                <th>입고가</th>
                <th>출고가</th>
                <th>출고일</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item, index) in releaseList" :key="index">
                
                <td>{{ item.product_name }}</td>
                <td>{{ item.department_name }}</td>
                <td>{{ item.box_name }} / {{ item.box_count }}</td>
                <td>{{ item.release_quantity }}</td>
                <td>{{ item.store_price }}</td>
                <td>{{ item.release_price }}</td>
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
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}.${month}.${day}`;
};

const props = defineProps({
    datas: {
        type: Array
        
    }
})

const release = ref([...props.datas])


const searchOptions = computed(() => [
        { label: '제품명', value: 'product_code'},
        { label: '부서명', value: 'department_code'}
])

    const selectedCategory = ref('0');
    const searchKeyword = ref('');
    const startDate = ref('');
    const endDate = ref('');

    const selectedSearchOption = ref('product_code');

// 오늘의 날짜를 구하기 위한 함수
function getTodayDate() {
        const today = new Date();
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = String(today.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    // 해당 월의 1일을 구하기 위한 함수
    function getStartDate() {
        const today = new Date();
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = '01';
        return `${year}-${month}-${day}`;
    }

    // 기본값 설정
    startDate.value = getStartDate();
    endDate.value = getTodayDate();

    const maxEndDate = getTodayDate();

    const releaseList = ref([...release.value]);

    // 날짜, 카테고리별 조회
    function filteredList() {
        let newList = [...release.value];

        if (selectedCategory.value !== '0') {
            console.log(selectedCategory.value)
            newList = newList.filter(item => item.category_code === selectedCategory.value.toString());
            console.log(newList)
        }
        if (startDate.value.trim() !== '' && endDate.value.trim() !== '') {
            const start = new Date(startDate.value);
            start.setHours(0, 0, 0, 0); // 시작일의 시간을 00:00:00으로 설정

            const end = new Date(endDate.value);
            end.setHours(23, 59, 59, 999); // 하루의 끝으로 설정

            newList = newList.filter(item => {
                const itemDate = new Date(item.release_date);
                return itemDate >= start && itemDate <= end;
            });
        }
        console.log(newList);

        releaseList.value = newList;
    }


    // 제품, 거래처 조건검색
    function search() {
        console.log(selectedSearchOption.value);
        let newList = [...release.value];
        if (searchKeyword.value.trim() !== '') {
            const keyword = searchKeyword.value.trim().toLowerCase();
            if (selectedSearchOption.value == 'product_code') {
                newList = newList.filter(item => item.product_name.toLowerCase().includes(keyword));
            } else {
                newList = newList.filter(item => item.department_name.toLowerCase().includes(keyword));
            }
        }
        releaseList.value = newList;
    }


    // 오름차순 내림차순
    let sortOrder = ref('asc');
    

    const toggleSortOrder = () => {
        sortOrder.value = sortOrder.value === 'asc' ? 'desc' : 'asc';
        if (sortOrder.value === 'asc') {
            releaseList.value.sort((a, b) => new Date(a.release_date) - new Date(b.release_date));
        } else {
            releaseList.value.sort((a, b) => new Date(b.release_date) - new Date(a.release_date));
        }
    };

    watch([selectedCategory, startDate, endDate], () => {
        filteredList();
    });

    onMounted(() => {
        filteredList();
    })

</script>

<style scoped>

</style>