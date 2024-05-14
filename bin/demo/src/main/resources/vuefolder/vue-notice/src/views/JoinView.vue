<template>
    <div>
        <h2>회원 가입</h2>
        <form @submit.prevent="joinForm">
            <div class="form-group">
                <label for="id">사용자 이름:</label>
                <input type="text" id="id" v-model="id" required>
            </div>
            <div class="form-group">
                <label for="email">이메일:</label>
                <input type="email" id="email" v-model="email" required>
            </div>
            <div class="form-group">
                <label for="pwd">비밀번호:</label>
                <input type="pwd" id="pwd" v-model="pwd" required>
            </div>
            <button type="submit">가입하기</button>
        </form>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';


const id = ref('');
const pwd = ref('');
const email = ref('');

const joinForm = () => {
    console.log("id : ", id.value)
    console.log("pwd : ", pwd.value)
    console.log("email : ", email.value)

    // 컴포넌트화가 가능한지 확인하기
    const fetchData = async() => {
        try {
            const response = await axios.post('/api/join', 'hi');
            console.log(response);
            
        } catch (error) {
            console.log(error)
        }
    }

    fetchData();


}


</script>

<style scoped>
.signup-form {
  max-width: 400px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="text"],
input[type="email"],
input[type="password"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>