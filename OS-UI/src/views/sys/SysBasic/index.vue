<template>
  <div>
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="部门管理" name="first"
        ><system-management></system-management
      ></el-tab-pane>
      <el-tab-pane label="职位管理" name="second"
        ><position-management></position-management
      ></el-tab-pane>
      <el-tab-pane label="职称管理" name="third"
        ><title-management></title-management
      ></el-tab-pane>
      <el-tab-pane label="奖惩规则" name="fourth"><rule></rule></el-tab-pane>
      <el-tab-pane label="权限组" name="five"><promise></promise></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import SystemManagement from '@/components/systemManagement/basicInformation/DepartmentManagement.vue'
import PositionManagement from '@/components/systemManagement/basicInformation/PositionManagement.vue'
import Promise from '@/components/systemManagement/basicInformation/Promise.vue'
import Rule from '@/components/systemManagement/basicInformation/Rule.vue'
import TitleManagement from '@/components/systemManagement/basicInformation/TitleManagement.vue'
export default {
  name: 'SysBasic',
  components: {
    SystemManagement,
    PositionManagement,
    Promise,
    Rule,
    TitleManagement,
  },
  data() {
    return {
      activeName: 'second',
    }
  },
}
</script>
<style>
</style>