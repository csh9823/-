<template>
    <div>
        <div class="filter-container">
            <div class="allShow">
                <button @click="allShow">전체보기</button>
            </div>
            <SearchOption @search="handleSearch"/>
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
                    
                    <!-- toggleKey == 0 -->
                    <th v-if="toggleKey === 0" rowspan="2">제품명</th>
                    <th v-if="toggleKey === 0" rowspan="2">출고 부서</th>
                    <th v-if="toggleKey === 0" rowspan="2">거래처</th>
                    
                    <!-- toggleKey == 1 -->
                    <th v-if="toggleKey === 1" rowspan="2">거래처</th>
                    <th v-if="toggleKey === 1" rowspan="2">제품명</th>
                    <th v-if="toggleKey === 1" rowspan="2">출고 부서</th>
                    
                    <!-- toggleKey == 2 -->
                    <th v-if="toggleKey === 2" rowspan="2">출고 부서</th>
                    <th v-if="toggleKey === 2" rowspan="2">제품명</th>
                    <th v-if="toggleKey === 2" rowspan="2">거래처</th>

                    <th colspan="2">재고</th>
                    <th colspan="3">출고</th>
                    <th rowspan="2">수익</th>
                </tr>
                <tr>
                    <th>입고 가격</th>
                    <th>입고 수량</th>
                    <th>출고 가격</th>
                    <th>출고 일자</th>
                    <th>출고 수량</th>

                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in calculateList" :key="index">

                    <!-- toggleKey == 0 -->
                    <td v-if="toggleKey === 0">{{ item.product_name }}</td>
                    <td v-if="toggleKey === 0">{{ item.department_name}}</td>
                    <td v-if="toggleKey === 0">{{ item.account_name}}</td>

                    <!-- toggleKey == 1 -->
                    <td v-if="toggleKey === 1">{{ item.account_name}}</td>
                    <td v-if="toggleKey === 1">{{ item.product_name }}</td>
                    <td v-if="toggleKey === 1">{{ item.department_name}}</td>

                    <!-- toggleKey == 2 -->
                    <td v-if="toggleKey === 2">{{ item.department_name}}</td>
                    <td v-if="toggleKey === 2">{{ item.product_name }}</td>
                    <td v-if="toggleKey === 2">{{ item.account_name}}</td>




                    <td>{{ item.store_price }}</td>
                    <td>{{ item.store_box }}</td>
                    <td>{{ item.release_price }}</td>
                    <td>{{ formatDate(item.release_date) }}</td>
                    <td>{{ item.release_box }}</td>
                    <td>{{ (item.release_price - item.store_price) * item.release_box }}</td>
                </tr>
                <tr>
                    <td colspan="8">총 수익</td>
                    <td>{{ totalProfit }}</td>
                </tr>


            </tbody>
        </table>
    </div>

</template>

<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import SearchOption from './ui/SearchOption.vue';
import './css/List.css';



    const props = defineProps({
        datas: {
            type: Array,
            required: true
        },
    })

    const calculate = [
        ...props.datas
    ]


    const calculateList = ref(calculate);


    const totalProfit = computed(() => {
        if (calculateList.value && Array.isArray(calculateList.value)) {
            return calculateList.value.reduce((sum, item) => {
                return sum + (item.release_price - item.store_price) * (item.release_box);
            }, 0);
        }
        return 0;
    });

    let toggleKey = 0;


    // 검색
    const searchResult = ref(null);

    const handleSearch = (payload) => {
        const keyword = payload.keyword.trim().toLowerCase();
        
        if (keyword === '') {
            // 검색어가 빈 문자열인 경우, 전체 데이터를 다시 표시
            calculateList.value = calculate;
            toggleKey = 0;
            return;
        }
        console.log(keyword)
        let newList = calculate;    

        if (keyword !== '') {
            if (payload.option === 'product_name') {
                newList = newList.filter(item => item.product_name.toLowerCase().includes(keyword));
                toggleKey = 0;
            } else if (payload.option === 'account_value') {
                newList = newList.filter(item => item.account_value.toLowerCase().includes(keyword));
                toggleKey = 1;
            } else if (payload.option === 'department_value') {
                newList = newList.filter(item => item.department_value.toLowerCase().includes(keyword));
                toggleKey = 2;
            }
        } else {
            console.log('hi')
        }
        calculateList.value = newList;
    };

    

    

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}.${month}.${day}`;
    };

    const startDate = ref('');
    const endDate = ref('');
    
    // 날짜
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

    // 날짜, 카테고리별 조회
    function filteredList() {
        let newList = calculate;

        toggleKey = 0;

    
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
        calculateList.value = newList;
    }


    // 오름차순 내림차순
    let sortOrder = ref('asc');
    

    const toggleSortOrder = () => {
        sortOrder.value = sortOrder.value === 'asc' ? 'desc' : 'asc';
        if (sortOrder.value === 'asc') {
            calculateList.value.sort((a, b) => new Date(a.release_date) - new Date(b.release_date));
        } else {
            calculateList.value.sort((a, b) => new Date(b.release_date) - new Date(a.release_date));
        }
    };


    // 전체보기
    const allShow = () => {
        toggleKey = 0;
        calculateList.value = calculate;
    }

    onMounted(() => {
        filteredList();
    })

    watch([ startDate, endDate], () => {
        filteredList();
    });



</script>

<style scoped>

</style>