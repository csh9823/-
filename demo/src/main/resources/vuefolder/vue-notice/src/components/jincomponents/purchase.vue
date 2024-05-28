<template>
    <div class="h1div"><strong>구매</strong></div>
    단위선택
    <select v-model="selectedBox">
              <option v-for="(boxItem, boxIndex) in boxList" :key="boxIndex" :value="boxItem.BOXCODE">{{ boxItem.BOXCOUNT }}</option>
    </select>
    <div class="namelist">
      <div class="listdate">
        <div>구분</div>
        <div>제품 명</div>
        <div>적정 수량</div>
        <div>현재 수량</div>
        <div>주문박스</div>
        <div>주문수량</div>
        <div>입고가</div>
      </div>
      <form @submit.prevent="submitForm">
        <div class="listdate" v-for="(purchList, index) in purchList" :key="index">
          <div>{{ purchList.CATANAME }}</div>
          <div>{{ purchList.NAME }}</div>
          <div>{{ purchList.PQTI }}</div>
          <div>{{ purchList.IQTI }}</div>
          <div><input type="number" class="incount" v-model="purchList.inputCount" @input="updateTotalCount(purchList, index)"></div>
          <div class="thiscount">{{ purchList.thisCount }}</div>    
          <div><input type="number" v-model="purchList.price"></div>
        </div>
        <div class="insertform2">
          <div class="insertbtn2">
            <button type="submit" class="btnsubmit">구매</button>
          </div>
        </div>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const purchList = ref([]);
  const price = ref([]);
  const boxList = ref([]);
  const selectedBox = ref('');
  
  // 구매하기
  const submitForm = async () => {
    try {

     const plist = purchList.value.map(purchItem => ({
            CODE:purchItem.CODE,
            BOXCODE: selectedBox.value,
            pcatacode: purchItem.PCATACODE,
            paccode: purchItem.PACCODE,
            inputCount: purchItem.inputCount,
            price: purchItem.price,
            thisCount: purchItem.thisCount
    }));          
      const response = await axios.post('/api/jinPurchaseadd',plist );
      console.log('응답완료', response.data);
      await purchaseList();
    } catch (error) {
      console.log('에러 발생:', error);
    }
  };
  
  // 리스트 가져오기
  const purchaseList = async () => {
    try {
      const response = await axios.get('/api/jinPurchaseList');
      purchList.value = response.data; // 응답 데이터를 categories에 저장
    } catch (error) {
      console.error('Error fetching categories:', error);
    }
  };
  
  // 박스 리스트
  const box = async () => {
    try {
      const response = await axios.get('/api/boxList');
      boxList.value = response.data; // 응답 데이터를 boxList에 저장
  
      // 데이터 로드 후 기본값 설정
      if (boxList.value.length > 0) {
        selectedBox.value = boxList.value[0].BOXCODE
      }
    } catch (error) {
      console.error('Error fetching categories:', error);
    }
  };
  
  onMounted(() => {
    box();
  });
  
  // 컴포넌트 마운트 시 데이터 fetch 실행
  onMounted(purchaseList);
  
// 업데이트된 수량 계산
const updateTotalCount = (purchItem, index) => {
  const selectedBoxItem = boxList.value.find(item => item.BOXCODE === selectedBox.value);
  if (selectedBoxItem) {
    purchItem.thisCount = purchItem.inputCount * selectedBoxItem.BOXCOUNT;
  } else {
    purchItem.thisCount = 0;
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
.selectbox{
    width: 90%;
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
</style>