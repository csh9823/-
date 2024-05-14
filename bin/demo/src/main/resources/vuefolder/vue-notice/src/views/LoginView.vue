<template>
    <div>
        <div class="login-form">
            <h2>로그인</h2>
            <form @submit.prevent="login">
            <div class="form-group">
                <label for="id">id :</label>
                <input type="text" id="id" v-model="id" required>
            </div>
            <div class="form-group">
                <label for="pwd">비밀번호 :</label>
                <input type="pwd" id="pwd" v-model="pwd" required>
            </div>
            <button type="submit">로그인</button>
            </form>
        </div>
        
    </div>
</template>

<script setup>
import { ref } from 'vue';


const id = ref('');
const pwd = ref('');

// 로그인 로직
const login = () => {
    console.log("id : ", id.value);
    console.log("pwd : ", pwd.value);
}

</script>

<style scoped>
.login-form {
  max-width: 300px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  width: 100%;
  padding: 10px;
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