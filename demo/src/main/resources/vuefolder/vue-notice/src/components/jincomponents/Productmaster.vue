<template>
<form @submit.prevent="submitForm">
    <div class="h1div"><strong>제품 마스터</strong></div>
    <div class="insertselectbox">
        구분: 
      <select class="divisionbox" v-model="selectedCategory">
        <option v-for="(category, index) in categories" :key="index" :value="category.VALUE">{{ category.VALUE }}</option>
      </select>

        거래처: <select v-model="selectedaccount">
                <option v-for="(accountList, index) in accountList" :key="index">{{ accountList.VALUE }}</option>
              </select>
    </div>
    
    <div class="insertform">

        <div class="name">
            <span>제품코드</span> 
        </div>

        <div class="name">
            <span>적정수량</span> 
        </div>

        <div class="name">
            <span>제품명</span> 
        </div>
    </div>

    <div class="insertform2">
        <div class="name">
            <input type="text" class="nameinput" v-model="codemaster">
        </div>

        <div class="name">
            <input type="number" class="nameinput" v-model="countmaster">
        </div>

        <div class="name">
            <input type="text" class="nameinput" v-model="namemaster">
        </div>
    </div>
    <div class="insertform2">
        <div class="insertbtn">
            <button type="submit" class="btnsubmit">등록</button>
        </div>   
    </div>
</form>


    <div class="namelists">
        <form @submit.prevent="submitForm2" class="subfrom2">
            <div  class="listh1"><span><strong>등록 제품 리스트</strong></span></div>

            <div class="listselectbox">
                <div>
                    구분 <select class="divisionbox" v-model="selectedCategory">
                        <option v-for="(category, index) in categories" :key="index" :value="category.VALUE">{{ category.VALUE }}</option>
                    </select>
                </div>

                <div>
                    <select v-model="search">
                        <option value="1" selected>제품명</option>
                        <option value="2">거래처</option>
                    </select>
                    <input type="text" class="submitinput" v-model="searchinto"> <button type="submit">검색</button>
                </div>

            </div>
        </form>
        
        <div class="listdate">
            <div>제품코드</div>
            <div>적정수량</div>
            <div>제품명</div>
            <div>거래처</div>
            <div>삭제하기</div>
            <div>수정하기</div>
        </div>  
    <form @submit.prevent="submitForm3" class="subfrom2">
        <div class="listdate" v-for="(searchlist, index) in searchlist" :key="index">
            <div>{{ searchlist.CODE }}</div>
            <div v-if="!edit[index]">{{searchlist.QUANTITY}}</div>
            <div v-else><input type="number" v-model="searchlist.QUANTITY" class="reinput"></div>
            <div>{{ searchlist.NAME }}</div>
            <div>{{ searchlist.ACVALUE }}</div>
            <div v-if="!edit[index]" ><input type="checkbox" :value="searchlist.CODE" v-model="deletet[index]"></div>
            <div v-else></div>
            <div><input type="checkbox" :value="searchlist.CODE" v-model="edit[index]"></div>
        </div>
        <div class="insertform2">
            <div class="insertbtn2">
                <button type="submit" class="btnsubmit">처리</button>
            </div>       
        </div>
    </form>        
</div>

</template>

<script setup>
import { ref,onMounted } from 'vue';
import axios from 'axios';

const categories = ref([]);
const accountList = ref([]);
const selectedCategory = ref('');
const selectedaccount = ref('');
const codemaster = ref('');
const countmaster = ref('');
const namemaster = ref('');
const search = ref('1');
const searchinto = ref('');
const searchlist = ref([]);
const deletet = ref([]);
const edit = ref([]);

// 체크박스 이벤트
// 수정 체크박스가 선택되었을 때 해당 카테고리의 값을 인풋 폼으로 변경
const toggleEdit = (index) => {
  if (edit.value[index]) {
    searchlist.value[index].isEditing = true;
  } else {
    searchlist.value[index].isEditing = false;
  }
};

// 수정 삭제하기
const submitForm3 = async () =>{
    try {
    const checkedIndexes = [];
    // deletet 배열의 상태를 감지하여 체크된 인덱스를 추적합니다.
    deletet.value.forEach((value, index) => {
      if (value) {
        checkedIndexes.push(index);
      }
    });

    // edit 배열의 상태를 감지하여 체크된 인덱스를 추적합니다.
    edit.value.forEach((value, index) => {
        if (value) {
            checkedIndexes.push(index);
        }
    });
    const dataToSend = checkedIndexes.map(index => ({
        CODE: searchlist.value[index].CODE,
        QUANTITY: searchlist.value[index].QUANTITY,
        NAME: searchlist.value[index].NAME,
        ACVALUE: searchlist.value[index].ACVALUE,
        deletet: deletet.value[index] ? searchlist.value[index].CODE : null, // deletet 배열이 true일 경우 해당 인덱스의 VALUE 사용
        edit: edit.value[index] ? searchlist.value[index].CODE : null // edit 배열이 true일 경우 해당 인덱스의 VALUE 사용
    }));

    const response = await axios.post('/api/productedit', dataToSend);
    deletet.value = []; // edit 배열을 빈 배열로 초기화합니다.
    edit.value = []; // edit 배열을 빈 배열로 초기화합니다.
    await submitForm2();
    }catch(error){
        console.log(error);
    }
}

// 검색하기
const submitForm2 = async () => {
    console.log(selectedCategory.value);
    console.log(search.value)
    console.log(searchinto.value)
    const dataToSend = {
        catavalue: selectedCategory.value,
        search: search.value,
        searchinto: searchinto.value
    };
    
    try{
        const response = await axios.post('/api/searchlist',dataToSend);
        searchlist.value = response.data;
    }catch(error){
        console.error('Error fetching categories:', error);
    }
};

// 제품 등록
const submitForm = async () => {
    console.log(selectedCategory.value);
    console.log(selectedaccount.value);
    console.log(codemaster.value);
    console.log(countmaster.value);
    console.log(namemaster.value);

    let dataToSend = {
        code: codemaster.value,
        name: namemaster.value,
        quantity: countmaster.value,
        catavalue: selectedCategory.value,
        accountvalue: selectedaccount.value
    }; 
    try{
        const response = await axios.post('/api/masteradd',dataToSend);
        codemaster.value = '';  // .value를 사용하여 값 변경
        countmaster.value = '';  // .value를 사용하여 값 변경
        namemaster.value = '';  // .value를 사용하여 값 변경
    }catch(error){
        console.error('Error fetching categories:', error);
    }
};

// 분류 리스트 가져오기
const cataList = async () => {
  try {
    const response = await axios.get('/api/catagoryList');
    categories.value = response.data; // 응답 데이터를 categories에 저장
    // 데이터 로드 후 기본값 설정
    if (categories.value.length > 0) {
      selectedCategory.value = categories.value[0].VALUE; // 첫 번째 항목으로 설정 (원하는 항목으로 변경 가능)
    }
  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};

onMounted(() => {
  cataList();
  accList();
  submitForm2();
});

// 거래처 리스트 가져오기
const accList = async () => {
  try {
    const response = await axios.get('/api/accountList');
    accountList.value = response.data; // 응답 데이터를 accountList에 저장
    // 데이터 로드 후 기본값 설정
    if (accountList.value.length > 0) {
      selectedaccount.value = accountList.value[0].VALUE; // 첫 번째 항목으로 설정 (원하는 항목으로 변경 가능)
    }
  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};
</script>

<style scoped>
        .h1div{
            text-align: center;
            font-size: 50px;
        }

        .insertselectbox{
            text-align: center;
        }
        
        .listselectbox{
            display: flex;
            justify-content: center;
            text-align: center;
            margin-bottom: 10px;
        }
        .insertform {
            display: flex;
            justify-content: center;
            margin-top: 10px;
            text-align: center;
        }
        .insertform2{
            display: flex;
            justify-content: center;
            text-align: center;            
        }
        .name{
            display: flex;
            align-items: center; /* 수직 정렬을 위해 사용 */
            width: 100px;
            height: 30px;
            border: solid 1px black;
        }
        .name span {
            width: 100%; /* 부모 요소에 대해 100% 너비를 가짐 */
            text-align: center; /* 텍스트 가로 중앙 정렬 */
        }
        .nameinsert{
            display: flex;
            align-items: center; /* 수직 정렬을 위해 사용 */
            height: 30px;
            border: solid 1px black;
            text-align: center;
        }
        .nameinput {
        border: none;
        outline: none;
        /* 기타 원하는 스타일 설정 */
        width: 100%;
    }
    .insertbtn{
        margin-top: 10px;
        margin-left: 245px;
        display: flex;
        align-items: center; /* 수직 정렬을 위해 사용 */
        height: 30px;
        text-align: center;
    }
    .namelists{
        margin: 0 auto; /* 가운데 정렬을 위해 margin 속성 사용 */
        justify-content: center;
        width: 500px;
        text-align: center;
        margin-top: 20px;
    }
    .listdate{
        display: flex;
        justify-content: center;
    }
    .listdate div{
        width: 100px;
        border: solid 1px black;
    }
    .listh1{
        font-size: 30px;
        margin-bottom: 10px ;
    }
    .btnsubmit{
        width: 60px;
        height: 30px;
    }
    .insertbtn2{
        margin-top: 10px;
        margin-left: 440px;
        display: flex;
        align-items: center; /* 수직 정렬을 위해 사용 */
        height: 30px;
        text-align: center;
    }
    .divisionbox{
        margin-right: 90px;
    }
    .listdepartbox{
        margin-right: 100px;
    }
    .subfrom2{
        width: 500px;
    }
    .listselectbox > div{
        display: flex;
        width: 100%;
        height: 20px;
    }
    .submitinput{
        width: 130px;
    }
    .reinput{
        width: 80%;
        height: 80%;
    }
    </style>