<template>
   <div class="h1div"><strong>대분류 등록</strong></div>
    <div class="insertform">

        <div class="name">
            <span>부서 명</span> 
        </div>

        <div class="nameinsert">
            <input type="text" class="nameinput" name="">
        </div>

        <div class="insertbtn">
            <button type="submit" class="btnsubmit">등록</button>
        </div>

    </div>

    <div class="namelist">
        <div  class="listh1"><span><strong>대분류 리스트</strong></span></div>

        <div class="listdate">
            <div>구분</div>
            <div>삭제하기</div>
            <div>수정하기</div>
        </div>

        <div class="listdate">
            <div>노트북</div>
            <div><input type="checkbox" value="1"></div>
            <div><input type="checkbox" value="2"></div>
        </div>
    </div>
    <div class="insertform2">
        <div class="insertbtn2">
            <button type="submit" class="btnsubmit">삭제</button>
        </div>
    </div>
</template>

<script setup>
import { ref,onMounted } from 'vue';
import axios from 'axios';
const data = ref('');
const fetchData = async () => {
  try {
    const response = await axios.get('/api/testvue');
    data.value = response.data;
  } catch (error) {
    console.error('Error fetching data:', error);
  }
};
onMounted(() => {
  fetchData();
});
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
    input{
        width: 100%;
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
        width: 100px;
        border: solid 1px black;
    }

    .listh1{
        font-size: 30px;
        margin-bottom: 10px ;
    }

    .btnsubmit{
        width: 50px;
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