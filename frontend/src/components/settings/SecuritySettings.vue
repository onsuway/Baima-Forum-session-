<template>
    <div>
        <div>
            <h1>
                <el-icon>
                    <Message/>
                </el-icon>
                邮箱设置
            </h1>
            <el-form
                    :rules="rules"
                    ref="formEmail"
                    label-position="top"
                    label-width="100px"
                    :model="securityForm"
                    style="max-width: 460px"
            >
                <el-form-item prop="email" label="邮箱地址">
                    <el-input v-model="securityForm.email"/>
                </el-form-item>
                <el-button type="success" :icon="Select" @click="saveEmail()">重置邮箱地址</el-button>
            </el-form>
        </div>
        <el-divider/>
        <div>
            <h1>
                <el-icon>
                    <Lock/>
                </el-icon>
                密码设置
            </h1>
            <el-form
                    :rules="rules"
                    ref="formPassword"
                    label-position="top"
                    label-width="100px"
                    :model="securityForm"
                    style="max-width: 460px"
            >
                <el-form-item prop="password_old" label="原密码">
                    <el-input type="password" show-password v-model="securityForm.password_old"/>
                </el-form-item>

                <el-form-item prop="password_new" label="新密码">
                    <el-input type="password" show-password v-model="securityForm.password_new"/>
                </el-form-item>

                <el-form-item prop="password_new_repeat" label="重复新密码">
                    <el-input type="password" show-password v-model="securityForm.password_new_repeat"/>
                </el-form-item>
            </el-form>
            <el-button type="success" :icon="Select" @click="changePassword()">重置密码</el-button>
        </div>

    </div>

</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {Lock, Message, Select} from "@element-plus/icons-vue";
import {get, logout, me, post} from "@/net";
import {ElMessage} from "element-plus";

const formEmail = ref()
const formPassword = ref()

const securityForm = reactive({
    email: null,
    password_old: '',
    password_new: '',
    password_new_repeat: '',
})

//验证密码合法性
const validatePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入密码'))
    } else if (/\s+/.test(value)) {
        callback(new Error('密码不能包含空格'))
    } else {
        callback()
    }
}
//验证两次输入密码必须一致
const validatePasswordRepeat = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== securityForm.password_new) {
        callback(new Error('两次输入的密码不一致'))
    } else {
        callback()
    }
}

const rules = {
    email: [
        {required: true, message: '请输入邮箱地址', trigger: 'blur'},
        {type: 'email', message: '请输入合法的邮箱地址', trigger: ['blur', 'change']}
    ],
    password_old: [
        {validator: validatePassword, trigger: ['blur', 'change']},
        {min: 6, max: 16, message: '密码的长度在6-16个字符', trigger: ['blur', 'change']},
    ],
    password_new: [
        {validator: validatePassword, trigger: ['blur', 'change']},
        {min: 6, max: 16, message: '密码的长度必须在6-16个字符', trigger: ['blur', 'change']},
    ],
    password_new_repeat: [
        {validator: validatePasswordRepeat, trigger: ['blur', 'change']},
    ],
}

const saveEmail = () => {
    formEmail.value.validate(isValid => {
        if (isValid) {
            post('/api/user/save-email', {
                email: securityForm.email
            }, () => {
                me()
                ElMessage.success('重置邮箱地址成功！')
            })
        } else {
            ElMessage.warning('请正确填写邮箱地址')
        }
    })
}

const changePassword = () => {
    formPassword.value.validate(isValid => {
        if (isValid){
            post('/api/user/save-password', {
                old: securityForm.password_old,
                new: securityForm.password_new
            }, () => {
                ElMessage.success('密码修改成功，请重新登录')
                logout()
            })
        }else {
            ElMessage.warning('密码校验失败，请正确填写新旧密码')
        }
    })


}

onMounted(() => {
    if (securityForm.email == null) {
        get('/api/user/email', message => {
            securityForm.email = message
        })
    }
})
</script>

<style scoped>

</style>
