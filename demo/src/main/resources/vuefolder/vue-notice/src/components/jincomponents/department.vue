<template>
    <div class="h1div"><strong>부서 등록</strong></div>
    <div class="insertform">

        <div class="name">
            <span>부서 명</span> 
        </div>
<form @submit.prevent="submitForm">
        <div class="nameinsert">
            <input type="text" class="nameinput" v-model="departname" name="">
        </div>

        <div class="insertbtn">
            <button type="submit" class="btnsubmit">등록</button>
        </div>
</form>
    </div>
    <form @submit.prevent="submitForm2">
    <div class="namelist">
        <div  class="listh1"><span><strong>부서 리스트</strong></span></div>

        <div class="listdate">
            <div>부서</div>
            <div>삭제하기</div>
            <div>수정하기</div>
        </div>

        <div class="listdate" v-for="(departmentList, index) in departmentList" :key="index">
            <div v-if="!edit[index]">{{ departmentList.VALUE }}</div>
            <input v-else type="text" v-model="departmentList.VALUE" class="reinput">
            <div v-if="!edit[index]" ><input type="checkbox" :value="departmentList.VALUE" v-model="deletet[index]"></div>
            <div v-else></div>
            <div><input type="checkbox" :value="departmentList.VALUE" v-model="edit[index]"></div>
            <div hidden>{{ departmentList.VALUE }}</div>
            <div hidden>{{ departmentList.CODE }}</div>
        </div>

    </div>
    <div class="insertform2">
        <div class="insertbtn2">
            <button type="submit" class="btnsubmit">삭제</button>
        </div>
    </div>
</form>
</template>

<script setup>
import { ref,onMounted } from 'vue';
import axios from 'axios';

// 변수 선언
const departname = ref([]);
const departmentList = ref([]);
const deletet = ref([]);
const edit = ref([]);

const submitForm = async () => {
  try {
    const response = await axios.post('/api/department', null ,{
      params: {
        departname: departname.value
      }
    });
    // 요청이 성공하면 입력값을 비웁니다.
    departname.value = '';
    await departList();
  } catch (error) {
    console.error(error);
  }
};

// 리스트 가져오기
const departList = async () => {
  try {
    const response = await axios.get('/api/departmentList');
    departmentList.value = response.data; // 응답 데이터를 categories에 저장
  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};
// 컴포넌트 마운트 시 데이터 fetch 실행
onMounted(departList);

// 수정 체크박스가 선택되었을 때 해당 카테고리의 값을 인풋 폼으로 변경
const toggleEdit = (index) => {
  if (edit.value[index]) {
    departmentList.value[index].isEditing = true;
  } else {
    departmentList.value[index].isEditing = false;
  }
};


const submitForm2 = async () => {
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
      CODE: departmentList.value[index].CODE,
      VALUE: departmentList.value[index].VALUE,
      revalue: departmentList.value[index].REVALUE,
      deletet: deletet.value[index] ? departmentList.value[index].VALUE : null, // deletet 배열이 true일 경우 해당 인덱스의 VALUE 사용
      edit: edit.value[index] ? departmentList.value[index].VALUE : null // edit 배열이 true일 경우 해당 인덱스의 VALUE 사용
    }));

    const response = await axios.post('/api/departmentedit', dataToSend);
    deletet.value = []; // edit 배열을 빈 배열로 초기화합니다.
    edit.value = []; // edit 배열을 빈 배열로 초기화합니다.
    await departList();
  } catch (error) {
    console.error(error);
  }
};

</script>

<style>
.h1div{
    text-align: center;
    font-size: 50px;
}
.insertform {
display: flex;
justify-content: center;
margin-top: 30px;
}
.name{
    display: flex;
    align-items: center; /* 수직 정렬을 위해 사용 */
    height: 30px;
    border: solid 1px black;
    text-align: center;
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
}
.insertbtn{
margin-left: 10px;
display: flex;
align-items: center; /* 수직 정렬을 위해 사용 */
height: 30px;
text-align: center;
}
.reinput{
        width: 200px;
    }
.namelist{
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
width: 200px;
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
.insertform2{
    display: flex;
    justify-content: center;
    text-align: center;            
}
.insertbtn2{
margin-top: 10px;
margin-left: 143px;
display: flex;
align-items: center; /* 수직 정렬을 위해 사용 */
height: 30px;
text-align: center;
}
</style>