<template>
    <div class="h1div"><strong>단위 코드 등록</strong></div>
    <div class="insertform">

        <div class="name">
            <span>코드</span> 
        </div>

        <div class="name">
            <span>이름</span> 
        </div>

        <div class="name">
            <span>개수</span> 
        </div>
    </div>
      <form @submit.prevent="submitForm">
          <div class="insertform2">
              <div class="name">
                  <input type="text" class="nameinput" v-model="boxcodes" name="">
              </div>

              <div class="name">
                  <input type="text" class="nameinput" v-model="boxnames" name="">
              </div>

              <div class="name">
                  <input type="number" class="nameinput" v-model="boxcounts" name="">
              </div>
          </div>
          <div class="insertform2">
              <div class="insertbtn">
                  <button type="submit" class="btnsubmit">등록</button>
              </div>
          </div>
      </form>

      <form @submit.prevent="submitForm2">      
        <div class="namelist">
            <div  class="listh1"><span><strong>단위코드 리스트</strong></span></div>

            <div class="listdate">
                <div>코드</div>
                <div>이름</div>
                <div>개수</div>
                <div>삭제하기</div>
                <div>수정하기</div>
            </div>

            <div class="listdate" v-for="(boxList, index) in boxList" :key="index">
                <div v-if="!edit[index]">{{boxList.BOXCODE}}</div>
                <input v-else type="text" v-model="boxList.BOXCODE" class="reinput">
                <div v-if="!edit[index]">{{boxList.BOXNAME}}</div>
                <input v-else type="text" v-model="boxList.BOXNAME" class="reinput">
                <div v-if="!edit[index]">{{boxList.BOXCOUNT}}</div>
                <input v-else type="text" v-model="boxList.BOXCOUNT" class="reinput">
                <div v-if="!edit[index]" ><input type="checkbox" :value="boxList.BOXCODE" v-model="deletet[index]"></div>
                <div v-else></div>
                <div><input type="checkbox" :value="boxList.BOXCODE" v-model="edit[index]"></div>
            </div>

        </div>

        <div class="insertform2">

            <div class="insertbtn2">
                <button type="submit" class="btnsubmit">처리</button>
            </div>       
        </div>
    </form>
</template>

<script setup>
import { ref,onMounted } from 'vue';
import axios from 'axios';
import '../../css/box.css'

// 변수 선언
const boxList = ref([]);
const boxcodes = ref([]);
const boxnames = ref([]);
const boxcounts = ref([]);

const deletet = ref([]);
const edit = ref([]);

const toggleEdit = (index) => {
  if (edit.value[index]) {
    boxList.value[index].isEditing = true;
  } else {
    boxList.value[index].isEditing = false;
  }
};

// 박스 수정
const submitForm2 = async () => {
    try {
    const checkedIndexes = [];
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
        BOXCODE: boxList.value[index].BOXCODE,
        BOXNAME: boxList.value[index].BOXNAME,
        BOXCOUNT: boxList.value[index].BOXCOUNT,
        REBOXCODE: boxList.value[index].REBOXCODE,
        deletet: deletet.value[index] ? boxList.value[index].BOXCODE : null, // deletet 배열이 true일 경우 해당 인덱스의 VALUE 사용
        edit: edit.value[index] ? boxList.value[index].BOXCODE : null // edit 배열이 true일 경우 해당 인덱스의 VALUE 사용
    }));

    const response = await axios.post('/api/boxedit', dataToSend);
    deletet.value = []; // edit 배열을 빈 배열로 초기화합니다.
    edit.value = []; // edit 배열을 빈 배열로 초기화합니다.
    await box();
  } catch (error) {
    console.error(error);
  }
};

//박스 등록
const submitForm = async () => {
  try {
    const response = await axios.post('/api/boxadd', null ,{
      params: {
        boxcode: boxcodes.value,
        boxname: boxnames.value,
        boxcount: boxcounts.value
      }
    });
    // 요청이 성공하면 입력값을 비웁니다.
    boxcodes.value = '';
    boxnames.value = '';
    boxcounts.value = '';
    await box();
  } catch (error) {
    console.error(error);
  }
};

// 리스트 가져오기
const box = async () => {
  try {
    const response = await axios.get('/api/boxList');
    boxList.value = response.data; // 응답 데이터를 categories에 저장
  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};
// 컴포넌트 마운트 시 데이터 fetch 실행
onMounted(box);



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
    .insertbtn2{
        margin-top: 10px;
        margin-left: 350px;
        display: flex;
        align-items: center; /* 수직 정렬을 위해 사용 */
        height: 30px;
        text-align: center;
    }
    .reinput{
        width: 100px;
    }
</style>
