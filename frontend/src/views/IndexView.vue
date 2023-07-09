<template>
    <div style="height: 100vh;">
        <el-container style="height: 100%;">
            <el-aside class="content-aside" :style="{width: isCollapse? '60px' : '220px'}">
                <div style="text-align: center; padding: 15px 0; margin-left: 13px">
                    <el-image src="https://element-plus.org/images/element-plus-logo.svg"
                              style="width: 180px"
                    />
                </div>
                <el-menu
                        style="border: none"
                        router
                        :collapse="isCollapse"
                        :default-active="router.currentRoute.value.path"
                >
                    <el-menu-item index="/index">
                        <el-icon>
                            <Menu/>
                        </el-icon>
                        <span>帖子列表</span>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <el-icon>
                            <Menu/>
                        </el-icon>
                        <span>校园表白墙</span>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <el-icon>
                            <Menu/>
                        </el-icon>
                        <span>帖子管理</span>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <el-icon>
                            <Menu/>
                        </el-icon>
                        <span>我的收藏</span>
                    </el-menu-item>
                    <el-menu-item index="/index/settings">
                        <el-icon>
                            <Tools/>
                        </el-icon>
                        <span>个人设置</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-container>
                <el-header class="content-header">
                    <div>
                        <el-button style="margin-top: 8px;font-size: 24px;width: 20px"
                                   :icon="isCollapse ? Expand : Fold" text @click="isCollapse = !isCollapse"/>
                    </div>
                    <div style="flex:1; text-align: center">
                        <el-input
                                placeholder="搜索论坛内容..."
                                style="width: 400px; margin-top: 8px"
                        >
                            <template #prefix>
                                <el-icon>
                                    <Search/>
                                </el-icon>
                            </template>
                        </el-input>
                    </div>
                    <div style="text-align: right;margin: 13px 15px 0 0; line-height: 14px">
                        <div style="font-weight: bold;font-size: 18px">{{ userStore.auth.user?.username }}</div>
                        <div style="color: grey;font-size: 14px;">{{ userStore.auth.user?.email }}</div>
                    </div>
                    <div style="margin-top: 3px">
                        <el-dropdown>
                            <el-avatar class="user-avatar"
                                       :size="45"
                                       src="https://image.itbaima.net/images/90/image-2023042612172367.jpeg"/>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item :icon="User">个人信息</el-dropdown-item>
                                    <el-dropdown-item :icon="SwitchButton" divided @click="logout"
                                                      style="color: #c43a3a">
                                        退出登录
                                    </el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>

                    </div>
                </el-header>
                <el-main style="padding: 0">
                    <el-scrollbar height="100%">
                        <router-view/>
                    </el-scrollbar>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import {logout} from "@/net";
import router from "@/router";
import {useUserStore} from "@/stores";
import {
    Expand, Fold, Menu,
    Search, SwitchButton, Tools,
    User
} from "@element-plus/icons-vue";
import {ref} from "vue";

const isCollapse = ref(false)

const userStore = useUserStore();

</script>

<style scoped>
.user-avatar:hover {
    cursor: pointer;
}

.content-header {
    padding: 0 10px;
    display: flex;
    height: 50px;
    border-bottom: solid 1px #e0e0e0;
}

.content-aside {
    width: 220px;
    border-right: solid 1px #e0e0e0;
    transition: 0.5s;
}

</style>
