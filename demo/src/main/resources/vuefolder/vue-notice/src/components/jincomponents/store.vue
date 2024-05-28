<template>
<form @submit.prevent="submitForm">
<div class="h1div"><strong>입고 처리</strong></div>
<div class="namelist">
    <div class="indate"><input type="date" class="dateinput"  v-model="datees"></div>
    <div class="listdate">
        <div>구분</div>
        <div>제품 명</div>
        <div>단위 수</div>
        <div>주문박스</div>
        <div>입고수량</div>
        <div>입고가</div>
        <div>구매회사</div>
        <div>미 입고 박스</div>
        <div>체크</div>
    </div>

    <div class="listdate"  v-for="(item, index) in storelist" :key="index">
        <div>{{item.CATENAME}}</div>
        <div>{{item.PRNAME}}</div>
        <div>{{item.BOXCC}}</div>
        <div>{{item.BOXCOUNT}}</div>
        <div>{{item.QUANTITY}}</div>
        <div>{{item.PRICE}}</div>
        <div>{{item.ACNAME}}</div>
        <div><input type="text" v-model="storelist[index].noadd"></div>
        <div><input type="checkbox" :value="storelist.no" v-model="insert[index]"></div>
    </div>
</div>
<div class="insertform2">
    <div class="insertbtn2">
        <button type="submit" class="btnsubmit">구매</button>
    </div>
</div>
</form>
</template>

<script setup>
import { ref,onMounted } from 'vue';
import axios from 'axios';
const storelist = ref([]);
const insert = ref([]);
const datees = ref('2024-01-01');
// 리스트 가져오기
const storelists = async () => {
  try {
    const response = await axios.post('/api/stolist');
    storelist.value = response.data; // 응답 데이터를 categories에 저장
  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};
// 컴포넌트 마운트 시 데이터 fetch 실행
onMounted(storelists);

//입고 승인 
const submitForm = async () => {
    try {
    const checkedIndexes = [];
    // deletet 배열의 상태를 감지하여 체크된 인덱스를 추적합니다.
    insert.value.forEach((value, index) => {
      if (value) {
        checkedIndexes.push(index);
      }
    });


    const dataToSend = checkedIndexes.map(index => ({
      NO: storelist.value[index].NO,
      datees: datees.value,
      noadd:storelist.value[index].noadd,
      CATECODE: storelist.value[index].CATECODE,
      PROCODE: storelist.value[index].PROCODE,
      ACCCODE: storelist.value[index].ACCCODE,
      BOXCODE: storelist.value[index].BOXCODE,
      BOXCOUNT: storelist.value[index].BOXCOUNT,
      QUANTITY: storelist.value[index].QUANTITY,
      PRICE: storelist.value[index].PRICE,
      BOXCC:  storelist.value[index].BOXCC,
    }));
    const response = await axios.post('/api/stoupdate', dataToSend); 
    await storelists();

    insert.value=[];

    }catch(error){
        console.log(error);
    }
};
</script>

<style scoped>
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
    input {
        width: 90%;
    }
    .insertbtn{
        margin-left: 10px;
        display: flex;
        align-items: center; /* 수직 정렬을 위해 사용 */
        height: 30px;
        text-align: center;
    }
    .namelist{
        margin: 0 auto; /* 가운데 정렬을 위해 margin 속성 사용 */
        justify-content: center;
        width: 1200px;
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
    .insertform2{
            display: flex;
            justify-content: center;
            text-align: center;            
    }
    .insertbtn2{
        margin-top: 10px;
        margin-left: 750px;
        display: flex;
        align-items: center; /* 수직 정렬을 위해 사용 */
        height: 30px;
        text-align: center;
    }
    .dateinput{
        width: 100px;
    }
    .indate{
        text-align: left;
        margin-left: 194px;
    }
</style>
