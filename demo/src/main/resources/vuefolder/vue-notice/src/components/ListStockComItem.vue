<template>
    <div>
        <h4>입고완료 표</h4>
        <div class="filter-container">

            <div class="dropbox">
                <Dropbox :options="categoryOptions" v-model="selectedCategory" />
            </div>
            <div class="search-container">
                <input type="text" v-model="searchKeyword" placeholder="제품명으로 검색">
                <button @click="search">검색</button>
            </div>
            <div class="date-container">
                <input type="date" v-model="startDate" placeholder="시작 날짜">
                 ~ 
                <input type="date" v-model="endDate" placeholder="종료 날짜" :max="maxEndDate">
            </div>
        </div>
            <table>
            <thead>
                <tr>
                    <th>구분</th>
                    <th>제품 명</th>
                    <th>단위</th>
                    <th>박스 개수</th>
                    <th>수량</th>
                    <th>입고가</th>
                    <th>구매 회사</th>
                    <th>입고날</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in filteredItemList" :key="index">
                    <td>{{ item.category }}</td>
                    <td>{{ item.productName }}</td>
                    <td>{{ item.unit }}</td>
                    <td>{{ item.boxCount }}</td>
                    <td>{{ item.quantity }}</td>
                    <td>{{ item.unitPrice }}</td>
                    <td>{{ item.company }}</td>
                    <td>{{ item.date }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { computed, ref, watch } from 'vue';
import Dropbox from './ui/dropbox.vue';

    const purchaseList = [
        { category: '식품', productName: '사과', unit: '개', boxCount: 10, quantity: 100, unitPrice: 1000, company: '과일농장', date: '2024-05-13' },
        { category: '식품', productName: '바나나', unit: '송이', boxCount: 5, quantity: 50, unitPrice: 1500, company: '과일농장', date: '2024-05-13' },
        { category: '생활용품', productName: '치약', unit: '개', boxCount: 20, quantity: 200, unitPrice: 500, company: '세이프마트', date: '2024-05-12' },
        { category: '생활용품', productName: '샴푸', unit: '병', boxCount: 10, quantity: 100, unitPrice: 2000, company: '세이프마트', date: '2024-05-12' }
    ]

        
    const selectedCategory = ref('all'); // 기본값은 전체보기
    const searchKeyword = ref('');
    const startDate = ref('');
    const endDate = ref('');

    const categoryOptions = computed(() => [
        { label: '전체보기', value: 'all' },
        { label: '식품', value: '식품' },
        { label: '생활용품', value: '생활용품' },
        { label: '가나다라', value: '가나다라' }
    ]);

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



    // 자동검색
    // const filteredItemList = computed(() => {
    //     let filteredList = purchaseList;
    //     if (selectedCategory.value !== 'all') {
    //         filteredList = filteredList.filter(item => item.category === selectedCategory.value);
    //     }
    //     if (searchKeyword.value.trim() !== '') {
    //         const keyword = searchKeyword.value.trim().toLowerCase();
    //         filteredList = filteredList.filter(item => item.productName.toLowerCase().includes(keyword));
    //     }
    //     if (startDate.value.trim() !== '' && endDate.value.trim() !== '') {
    //         const start = new Date(startDate.value);
    //         const end = new Date(endDate.value);
    //         filteredList = filteredList.filter(item => {
    //             const itemDate = new Date(item.date);
    //             return itemDate >= start && itemDate <= end;
    //         });
    //     }
    //     return filteredList;
    // });
    

    const filteredItemList = ref(purchaseList);

    function updateFilteredList() {
        let newList = purchaseList;
        if (selectedCategory.value !== 'all') {
            newList = newList.filter(item => item.category === selectedCategory.value);
        }
        if (startDate.value.trim() !== '' && endDate.value.trim() !== '') {
            const start = new Date(startDate.value);
            const end = new Date(endDate.value);
            newList = newList.filter(item => {
                const itemDate = new Date(item.date);
                return itemDate >= start && itemDate <= end;
            });
        }
        filteredItemList.value = newList;
    }

    function search() {
        let newList = purchaseList;
        if (searchKeyword.value.trim() !== '') {
            const keyword = searchKeyword.value.trim().toLowerCase();
            newList = newList.filter(item => item.productName.toLowerCase().includes(keyword));
        }
        filteredItemList.value = newList;
    }

    watch([selectedCategory, startDate, endDate], () => {
        updateFilteredList();
    });

    
    

</script>

<style scoped>

    .filter-container {
        display: flex;
        justify-content: space-between;
        margin-bottom: 15px;
    }

    .dropbox {
        text-align: left;
        
    }

    .search-container {
        text-align: center;
        
    }

    .date-container {
        
        text-align: right;
    }

    table {
        width: 100%;
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