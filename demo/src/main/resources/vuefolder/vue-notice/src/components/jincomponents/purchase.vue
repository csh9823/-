<template>
    <div class="h1div"><strong>구매</strong></div>
    <div class="namelist">
        <div class="listdate">
            <div>구분</div>
            <div>제품 명</div>
            <div>박스 당 개수</div>
            <div>적정 수량</div>
            <div>현재 수량</div>
            <div>주문박스</div>
            <div>주문수량</div>
            <div>입고가</div>
        </div>
    <form @submit.prevent="submitForm">
        <div class="listdate" v-for="(purchList, index) in purchList" :key="index">
            <div>{{purchList.CATEVAL}}</div>
            <div>{{purchList.NAME}}</div>
            <div>{{purchList.BOXCOUNT}}</div>
            <div>{{purchList.APPCOUNT}}</div>
            <div>{{purchList.TOTALCOUNT}}</div>
            <div hidden>{{ purchList.BOXCODE }}</div>
            <div hidden>{{ purchList.ACVALUE }}</div>
            <div><input type="number" class="incount" v-model="purchList.inputCount" @input="updateTotalCount(purchList)"></div>
            <div class="thiscount">{{ purchList.thisCount }}</div>    
            <div> <input type="number" v-model="purchList.price"></div>
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
import { ref,onMounted } from 'vue';
import axios from 'axios';
const purchList = ref([]);
const price = ref([]);
// 구매하기
const submitForm = async () => {
    try {
        const dataToSend = purchList.value.map(purchItem => ({
            CATEVAL: purchItem.CATEVAL,
            NAME: purchItem.NAME,
            BOXCODE: purchItem.BOXCODE,
            APPCOUNT: purchItem.APPCOUNT,
            thisCount: purchItem.thisCount,
            TOTALCOUNT: purchItem.TOTALCOUNT,
            price: purchItem.price,
            ACVALUE:purchItem.ACVALUE
        }));

        const response = await axios.post('/api/jinPurchaseadd', dataToSend);
        console.log('응답완료', response.data);
        await purchaseList();
    } catch (error) {
        console.log('에러 발생:', error);
    }
}
// 리스트 가져오기
const purchaseList = async () => {
  try {
    const response = await axios.get('/api/jinPurchaseList');
    purchList.value = response.data; // 응답 데이터를 categories에 저장
  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};
// 컴포넌트 마운트 시 데이터 fetch 실행
onMounted(purchaseList);
const updateTotalCount = (item) => {
  item.thisCount = item.inputCount * item.BOXCOUNT + (item.anotherValue || 0)
}
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
</style>