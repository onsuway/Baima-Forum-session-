import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useUserStore} from "@/stores";

const defaultError = () => ElMessage.error('发生了一个错误，请联系管理员')
const defaultFailure = (message) => ElMessage.warning(message)
const userStore = useUserStore()

function post(url, data, success, type = 'x-www-form-urlencoded', failure = defaultFailure, error = defaultError) {
    axios.post(url, data, {
        headers: {
            'Content-Type': 'application/' + type
        },
        withCredentials: true
    }).then(({data}) => {
        if (data.status === 401) {
            localStorage.removeItem('user')
            userStore.auth.user = undefined
            router.push('/')
        } else if (data.success) {
            success(data.message, data.status)
        } else {
            failure(data.message, data.status)
        }
    }).catch(error)
}

function get(url, success, failure = defaultFailure, error = defaultError) {
    axios.get(url, {
        withCredentials: true
    }).then(({data}) => {
        if (data.status === 401) {
            localStorage.removeItem('user')
            userStore.auth.user = undefined
            router.push('/')
        } else if (data.success) {
            success(data.message, data.status)
        } else {
            failure(data.message, data.status)
        }
    }).catch(error)
}

//退出登录
const logout = () => {
    get('/api/auth/logout', (message) => {
        ElMessage.success(message)
        userStore.auth.user = null
        localStorage.removeItem('user')
        router.push('/')
    })
}

//刷新个人信息
const me = (index = false) => {
    get('/api/user/me', (message) => {
        userStore.auth.user = message
        localStorage.setItem('user', JSON.stringify(message))
        if (index) {
            router.push('/index')
        }
    },() => {
        userStore.auth.user = null
    })
}

export {post, get, logout, me}
