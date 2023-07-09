<script setup>
import {useUserStore} from "@/stores";

const userStore = useUserStore()
const user = localStorage.getItem('user')

if (user != null) {
    userStore.auth.user = JSON.parse(user)
}

</script>

<template>
    <router-view/>
</template>

<style scoped>

</style>
