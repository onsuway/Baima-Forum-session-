<template>
    <div style="display: flex; padding: 20px 30px">
        <div style="flex: 1">
            <el-tabs v-model="activeName" class="demo-tabs">
                <el-tab-pane label="个人信息" name="info">
                   <info-setting/>
                </el-tab-pane>
                <el-tab-pane label="账号安全" name="security">
                    <security-settings/>
                </el-tab-pane>
                <el-tab-pane label="隐私设置" name="privacy">
                    <privacy-settings/>
                </el-tab-pane>
            </el-tabs>
        </div>
        <div style="width: 300px;">
            <div class="user-card" style="margin: 15px 0 0 30px">
                <div style="text-align: right; padding: 5px 15px 0 15px">
                    <div style="width: 70px;display: inline-block">
                        <el-avatar :size="70"
                                   src="https://image.itbaima.net/images/90/image-2023042612172367.jpeg"/>
                        <div style="text-align: center;font-weight: bold;"> {{ userStore.auth.user.username }}</div>
                    </div>
                </div>
                <div style="padding: 10px;font-size: 13px">
                    当今社会，人类群星闪耀时，我们身边不乏那些激励人心的名言名句。
                    其中一位伟大思想家曾经说过：“生活的意义不在于活得长久，而在于以一种有意义的方式活着。”
                    这句话提醒着我们，人生的价值不应只看时间的长短，而是取决于我们所做的有意义的事情。
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import {ref} from "vue";
import {useUserStore} from "@/stores";
import InfoSetting from "@/components/settings/InfoSettings.vue";
import SecuritySettings from "@/components/settings/SecuritySettings.vue";
import PrivacySettings from "@/components/settings/privacySettings.vue";

const userStore = useUserStore()
const activeName = ref('info')


</script>

<style scoped>
.user-card {
    height: 300px;
    width: 260px;
    border-radius: 5px;
    border: solid 1px lightgrey;
}

.user-card > div {
    border-bottom: solid 1px lightgrey;
}
</style>
