<template>
    <div>
        <button @click="addRow">추가</button>
    </div>
    <div>
        <table>
            <tr>
                <th>출고 부서</th>
                <th>제품명</th>
                <th>단위수</th>
                <th>박스수</th>
                <th>현재 수량</th>
                <th>입고가</th>
                <th>입고일자</th>
                <th>출고 박스</th>
                <th>출고가</th>
            </tr>
            <tr v-for="(row, index) in rows" :key="index">
                <td>
                    <select v-model="row.selectedDept">
                        <option value="">부서를 선택하세요</option>
                        <!-- deptList 배열을 반복하여 각 항목을 option으로 생성 -->
                        <option v-for="dept in deptList" :key="dept.department_code" :value="dept.department_code">{{ dept.department_name }}</option>
                    </select>
                </td>
                <td>
                    <select v-model="row.selectedProduct">
                        <option value="">품목 선택</option>
                        <!-- releasePage 배열을 반복하여 각 항목을 option으로 생성 -->
                        <option v-for="store in releasePage" :key="store.store_no" :value="store">{{ store.product_name }}</option>
                    </select>
                </td>
                <td>
                    <!-- releasePage의 선택된 항목의 box_count 값을 가져온다 -->
                    <!-- <select v-model="selectEach">
                        <option value="선택">선택</option>
                        <option v-for="box in listEach" :key="box">{{ box }}</option>
                    </select> -->
                    <!-- {{ selectedProduct.value.box_name }} -->
                    {{ row.selectedProduct?.box_name || '선택된 상품 없음' }} / 
                    {{ row.selectedProduct?.box_count || 'xxx' }}
                </td>
                <td>{{ row.selectedProduct?.store_box || '0' }}</td>
                <td>{{ row.selectedProduct?.store_quantity || '0' }}</td>
                <td>{{ row.selectedProduct?.store_price || '0' }}</td>
                <td>{{ row.selectedProduct? formatDate(row.selectedProduct.store_date) : '0' }}</td>
                <td>
                    <input type="number" v-model.number="row.release_box" @input="validateRelease_box(index)"/>
                </td>
                <td>
                    <input type="number" v-model.number="row.release_price"/>
                </td>
                <td class="deleteRow_Btn" style="border: none;">
                    <button @click="removeRow(index)">취소</button>
                </td>
            </tr>
        </table>
        <input type="button" @click="releaseSubmit" value="출고"/>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, watch } from 'vue';
import './css/List.css';

const props = defineProps({
    datas: {
        type: Object,
    },
})

console.log(props.datas);


// 부서정보 상위컴포넌트로부터 복제
const deptList = ref(props.datas.dept);

const productList = ref(props.datas.product);

// 입고 테이블 정보 복제
const releasePage = ref(props.datas.releasePage);

console.log(releasePage);

console.log(productList);

// 출고할 아이템 선택, 부서선택, 




console.log(deptList);

console.log(props.datas.dept)



// 복제된 정보 변경 및 추가할 시 다음 객체 사용
const dept = [
    ...deptList.value
]

console.log(dept)


// 선택된 부서를 저장할 변수
const selectedDept = ref('');

// 선택된 부서가 변경될 때 실행될 함수
const handleDeptChange = () => {
  console.log('선택된 부서:', selectedDept.value);
};

// 선택된 부서 변경 감지
watch(selectedDept, handleDeptChange);

// 선택된 상품을 저장할 변수
const selectedProduct = ref(null);

// 선택된 상품이 변경될 때 실행될 함수
const handleProductChange = (index) => {
    console.log(selectedProduct.value.account_code)
    const selectedProduct = rows.value[index].selectedProduct;

};



// 선택된 상품 변경 감지
watch(selectedProduct, handleProductChange);




// 날짜 형식 변경    
const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}.${month}.${day}`;
};

// 출고 수량 제한(재고 초과 x)
const release_box = ref(0);

const validateRelease_box = (index) => {
    if (rows.value[index].selectedProduct) {
        const maxQuantity = rows.value[index].selectedProduct.store_box;
        if (rows.value[index].release_box > maxQuantity) {
            rows.value[index].release_box = maxQuantity;
        }
    }
};


// 상태 변수
const rows = ref([]);

// 새로운 행을 추가하는 함수
const addRow = () => {
    rows.value.push({
        selectedDept: '',
        selectedProduct: null,
        totalbox: 0,
        quantity: 0,
        listEach: new Set(),
        selectEach: '선택',
        store_date: null
    });
};

// 행을 삭제하는 함수
const removeRow = (index) => {
    rows.value.splice(index, 1);
};


const releaseSubmit = () => {

    // 각 행에서 필요한 데이터가 모두 채워져 있는지 확인
    const allDataFilled = rows.value.every(row => {
        return row.selectedDept && row.selectedProduct && row.release_box && row.release_price;
    });

    console.log(allDataFilled);

    if (allDataFilled) {
    
        
        // 제출 데이터 정의
        const submittedData = rows.value.map(row => ({
            department_code: row.selectedDept,
            product_code: row.selectedProduct ? row.selectedProduct.product_code : '0',
            category_code: row.selectedProduct ? row.selectedProduct.category_code : '0',
            box_code: row.selectedProduct ? row.selectedProduct.box_code : '0',
            store_no: row.selectedProduct ? row.selectedProduct.store_no : '0',
            store_quantity: row.selectedProduct ? row.selectedProduct.store_quantity : '0',
            store_price: row.selectedProduct ? row.selectedProduct.store_price : '0',
            store_date: row.selectedProduct ? row.selectedProduct.store_date : '0',
            release_quantity: row.selectedProduct ? row.release_box * row.selectedProduct.box_count : '',
            release_box: row.release_box,
            release_price: row.release_price,
        }));
        
        console.log('출고 데이터:', submittedData);
        // 서버에 제출하거나 다른 로직을 수행

        let res = false;

        const fetchData = async() => {

            try {
                const response = await axios.post('/api/releaseSave', submittedData)
                console.log(response.data.res);
                if (response.data.res == 'fail') {
                    alert("실패")
                } else {
                    alert("성공")
                    rows.value = [];
                }
            } catch (error) {
                console.log(error);
            }

        }

        fetchData();

    } else {
        alert("모든 데이터를 입력해 주세요")
    }
        
};

    
</script>

<style scoped>

</style>