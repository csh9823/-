<template>
    <div>
        <select v-model="selectedCategory">
            <!-- <option value="all">전체보기</option>
            <option value="식품">식품</option>
            <option value="생활용품">생활용품</option> -->
            <option v-for="(option, index) in options" :key="index" :value="option.value">{{ option.label }}</option>
        </select>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';

    const props = defineProps({
        options: Array,
        value: {
            type: String,
            default: 'all'
        }
    });

    const emit = defineEmits();

    const selectedCategory = ref(props.value);

    watch(selectedCategory, (value) => {
        emit('update:modelValue', value);
    });
</script>

<style lang="scss" scoped>

</style>