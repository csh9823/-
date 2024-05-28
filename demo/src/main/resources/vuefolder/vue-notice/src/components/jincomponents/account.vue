<template>
    <div class="h1div"><strong>거래처 등록</strong></div>
    <form @submit.prevent="submitForm">
    <div class="namelist">
        <div class="listdate">
            <div>거래처코드</div>
            <div>주소</div>
            <div>이름</div>
            <div>연락처</div>
            <div>팩스번호</div>
        </div>
        <div class="listdate">
            <div><input type="text" class="nameinput" v-model="accode" name=""></div>
            <div><input type="text" class="nameinput" v-model="acaddress" name=""></div>
            <div><input type="text" class="nameinput" v-model="achp" name=""></div>
            <div><input type="text" class="nameinput" v-model="acname" name=""></div>
            <div><input type="text" class="nameinput" v-model="acfax" name=""></div>
        </div>
        <button type="submit" class="btnsubmit">등록</button>
    </div>
  </form>
    <form @submit.prevent="submitForm2">
    <div class="namelist">
        <div  class="listh1"><span><strong>거래처 리스트</strong></span></div>

        <div class="listdate">
            <div>거래처코드</div>
            <div>주소</div>
            <div>연락처</div>
            <div>이름</div>
            <div>팩스번호</div>
            <div>삭제</div>
            <div>수정</div>
        </div>

        <div class="listdate" v-for="(accountList, index) in accountList" :key="index">
            <div>{{accountList.CODE }}</div>
            <div v-if="!edit[index]">{{ accountList.ADDRESS }}</div>
            <div v-else><input type="text" v-model="accountList.ADDRESS" class="reinput"></div>
            <div v-if="!edit[index]">{{ accountList.HP }}</div>
            <div v-else><input type="text" v-model="accountList.HP" class="reinput"></div>
            <div v-if="!edit[index]">{{accountList.VALUE }}</div>
            <div v-else><input type="text" v-model="accountList.VALUE" class="reinput"></div>
            <div v-if="!edit[index]">{{accountList.FAX }}</div>
            <div v-else><input type="text" v-model="accountList.FAX" class="reinput"></div>
            <div v-if="!edit[index]" ><input type="checkbox" :value="accountList.VALUE" v-model="deletet[index]"></div>
            <div v-else></div>
            <div><input type="checkbox" :value="accountList.VALUE" v-model="edit[index]"></div>
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
const accode = ref('');
const  acaddress  = ref('');
const  achp  = ref('');
const  acname  = ref('');
const  acfax  = ref('');
const accountList = ref([]);
const deletet = ref([]);
const edit = ref([]);

const submitForm = async () => {
  try {

    const dataToSend = {
        accode: accode.value,
        acaddress: acaddress.value,
        achp: achp.value,
        acname: acname.value,
        acfax: acfax.value
    };

    const response = await axios.post('/api/accountadd', dataToSend);
    // 요청이 성공하면 입력값을 비웁니다.
    accode.value = '';
    acaddress.value = '';
    achp.value = '';
    acname.value = '';
    acfax.value = '';
    await accList();
  } catch (error) {
    console.error(error);
  }
};

// 리스트 가져오기
const accList = async () => {
  try {
    const response = await axios.get('/api/accountList');
    accountList.value = response.data; // 응답 데이터를 categories에 저장
  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};
// 컴포넌트 마운트 시 데이터 fetch 실행
onMounted(accList);

// 수정 체크박스가 선택되었을 때 해당 카테고리의 값을 인풋 폼으로 변경
const toggleEdit = (index) => {
  if (edit.value[index]) {
    accountList.value[index].isEditing = true;
  } else {
    accountList.value[index].isEditing = false;
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
      CODE: accountList.value[index].CODE,
      VALUE: accountList.value[index].VALUE,
      ADDRESS: accountList.value[index].ADDRESS,
      FAX: accountList.value[index].FAX,
      HP: accountList.value[index].HP,      
      deletet: deletet.value[index] ? accountList.value[index].VALUE : null, // deletet 배열이 true일 경우 해당 인덱스의 VALUE 사용
      edit: edit.value[index] ? accountList.value[index].VALUE : null // edit 배열이 true일 경우 해당 인덱스의 VALUE 사용
    }));

    const response = await axios.post('/api/accountedit', dataToSend);
    deletet.value = []; // edit 배열을 빈 배열로 초기화합니다.
    edit.value = []; // edit 배열을 빈 배열로 초기화합니다.
    await accList();
  } catch (error) {
    console.error(error);
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
.nameinput {
  width: 90%;
}
.insertbtn{
margin-left: 10px;
display: flex;
align-items: center; /* 수직 정렬을 위해 사용 */
height: 30px;
text-align: center;
}
.reinput{
        width: 90%;
    }
.namelist{
margin: 0 auto; /* 가운데 정렬을 위해 margin 속성 사용 */
justify-content: center;
width: 800px;
text-align: center;
margin-top: 20px;
}
.listdate{
width: 800px;
display: flex;
justify-content: center;
}
.listdate div{
  width: 300px;
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