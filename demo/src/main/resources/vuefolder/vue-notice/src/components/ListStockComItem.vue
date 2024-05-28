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
                    <th>구분</th>
                    <th>제품명</th>
                    <th>단위</th>
                    <th>박스 개수</th>
                    <th>수량(예시데이터라 조금 다를 수 있음)</th>
                    <th>입고 단가</th>
                    <th>구매 회사</th>
                    <th>입고일</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in filteredItemList" :key="index" >
                    <td>{{ item.category_name }}</td>
                    <td>{{ item.product_name }}</td>
                    <td>{{ item.box_name }}<br/>{{ item.box_count }} EA</td>
                    <td>{{ item.store_box }}</td>
                    <td>{{ item.store_quantity }}</td>
                    <td>{{ item.store_price }}</td>
                    <td>{{ item.account_name }}</td>
                    <td>{{ formatDate(item.store_date) }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import Dropbox from './ui/dropbox.vue';

    const props = defineProps({
        datas: {
            type: Array,
            required: true
        },
    })

    const storeList = ref([...props.datas]);

        
    // const selectedCategory = ref(''); // 기본값은 전체보기
    const selectedCategory = ref('0');
    const searchKeyword = ref('');
    const startDate = ref('');
    const endDate = ref('');

    const selectedSearchOption = ref('product_code');

    const searchOptions = computed(() => [
        { label: '제품명', value: 'product_code'},
        { label: '거래처', value: 'account_code'}
    ])

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


    const filteredItemList = ref([...storeList.value]);


    // 날짜, 카테고리별 조회
    function filteredList() {
        let newList = [...storeList.value];

        if (selectedCategory.value !== '0') {
            console.log(selectedCategory.value)
            newList = newList.filter(item => item.category_code === selectedCategory.value.toString());
        }
        if (startDate.value.trim() !== '' && endDate.value.trim() !== '') {
            const start = new Date(startDate.value);
            start.setHours(0, 0, 0, 0); // 시작일의 시간을 00:00:00으로 설정

            const end = new Date(endDate.value);
            end.setHours(23, 59, 59, 999); // 하루의 끝으로 설정

            newList = newList.filter(item => {
                const itemDate = new Date(item.store_date);
                return itemDate >= start && itemDate <= end;
            });
        }

        // store_situation filtering
        // newList = newList.filter(item => item.store_situation === '1');


        filteredItemList.value = newList;
    }


    // 제품, 거래처 조건검색
    function search() {
        console.log(selectedSearchOption.value);
        let newList = [...storeList.value];
        if (searchKeyword.value.trim() !== '') {
            const keyword = searchKeyword.value.trim().toLowerCase();
            if (selectedSearchOption.value == 'product_code') {
                newList = newList.filter(item => item.product_name.toLowerCase().includes(keyword));
            } else {
                newList = newList.filter(item => item.account_name.toLowerCase().includes(keyword));
            } 
        }
        filteredItemList.value = newList;
    }

    watch([selectedCategory, startDate, endDate], () => {
        filteredList();
    });

    onMounted(() => {
        filteredList();
    });



    // 날짜 형식 변경    
    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}.${month}.${day}`;
    };

    // 오름차순 내림차순
    let sortOrder = ref('asc');
    

    const toggleSortOrder = () => {
        sortOrder.value = sortOrder.value === 'asc' ? 'desc' : 'asc';
        if (sortOrder.value === 'asc') {
            filteredItemList.value.sort((a, b) => new Date(a.store_date) - new Date(b.store_date));
        } else {
            filteredItemList.value.sort((a, b) => new Date(b.store_date) - new Date(a.store_date));
        }
    };


</script>

<style scoped>

.filter-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        margin-bottom: 20px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #f9f9f9;

    }

    .dropbox {
        flex: 1 1 20%;
        margin-right: 10px;
    }

    .search-container {
        display: flex;
        align-items: center;
        flex: 1 1 40%;
        margin-right: 10px;
    }

    .search-container select {
        margin-right: 10px;
        padding: 5px;
    }

    .search-container input[type="text"] {
        flex: 1;
        padding: 5px;
        margin-right: 10px;
    }
    
    .search-container button {
        padding: 5px 10px;
        background-color: #007bff;
        color: white;
        cursor: pointer;
    }

    .search-container button:hover {
        background-color: #0056b3;
    }
    
    .date-container {
        
        display: flex;
        align-items: center;
        flex: 1 1 30%;
        justify-content: space-between;
    }
    
    .date-container input[type="date"] {
        margin-right: 10px;
        padding: 5px;
    }
    
    /* 정렬 버튼 스타일 */
    .date-container .sortBtn {
        margin-left: auto;
    }
    
    .date-container .sortBtn button {
        padding: 5px 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }
    
    .date-container .sortBtn button:hover {
        background-color: #0056b3;
    }


    /* 테이블 */
    table {
        width: 1000px;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    /* 표 헤더 스타일 */
    th {
        background-color: #f2f2f2;
        padding: 8px;
        text-align: left;
        border: 1px solid #ddd;
    }

    /* 표 데이터(셀) 스타일 */
    td {
        padding: 8px;
        border: 1px solid #ddd;
    }

    /* 짝수 행 배경색 */
    tbody tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    /* 마우스를 갖다 대었을 때 표시되는 효과 */
    tbody tr:hover {
        background-color: #ddd;
    }


    
</style>