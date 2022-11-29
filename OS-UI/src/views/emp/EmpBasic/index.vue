<template>
  <div>
    <div class="header-button">
      <div>
        <el-input
          v-model="searchEmployee"
          placeholder="请输入员工姓名进行搜索"
          style="width: 50%; margin-right: 10px"
          prefix-icon="el-icon-search"
          size="small"
          clearable
          @clear="searchKeyword"
          @keyup.enter.native="searchKeyword"
        ></el-input>
        <el-button
          type="primary"
          size="small"
          icon="el-icon-search"
          @click="searchKeyword"
          >搜索</el-button
        >
        <el-button type="primary" size="small" icon="el-icon-place"
          >高级搜索</el-button
        >
      </div>
      <div>
        <el-button type="success" size="small" icon="el-icon-upload2"
          >导入数据</el-button
        >
        <el-button type="success" size="small" icon="el-icon-download"
          >导入数据</el-button
        >
        <el-button
          type="primary"
          size="small"
          icon="el-icon-plus"
          @click="addEmployee"
          >添加员工</el-button
        >
      </div>
    </div>
    <div style="margin-top: 20px">
      <employee-data
        :keyWord="searchEmployee"
        :visible="dialogVisible"
        @openDialog="openDialog"
        ref="operationEmployeeData"
      ></employee-data>
    </div>
    <el-dialog
      v-if="dialogVisible"
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      width="70%"
    >
      <add-employee-data
        ref="employeeInfo"
        :editData="editData"
        :workId="workId"
      ></add-employee-data>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="getAddEmployeeInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import EmployeeData from '@/views/emp/EmpBasic/EmployeeData'
import AddEmployeeData from '@/views/emp/EmpBasic/AddEmployeeData'
export default {
  name: 'EmpBasic',
  components: {
    EmployeeData,
    AddEmployeeData,
  },
  data() {
    return {
      searchEmployee: '',
      dialogVisible: false,
      dialogTitle: '',
      editData: {},
      workId: '',
    }
  },
  methods: {
    searchKeyword() {
      this.$refs.operationEmployeeData.initEmployee()
    },
    // 添加员工
    addEmployee() {
      this.dialogTitle = '添加员工'
      this.dialogVisible = true
      this.editData = {}
      this.getWorkId()
    },
    // 子组件传值打开dialog对话框
    openDialog(status, data) {
      this.dialogVisible = status
      this.editData = data
      this.dialogTitle = '编辑员工'
    },
    // 点击确定添加员工
    async getAddEmployeeInfo() {
      console.log(this.$refs.employeeInfo.employeeData)
      if (this.$refs.employeeInfo.employeeData.id) {
        const result = await this.$API.updateEmployeeInfo(
          this.$refs.employeeInfo.employeeData
        )
        if (result.code == 200) {
          this.dialogVisible = false
          this.$refs.operationEmployeeData.initEmployee()
        }
      } else {
        this.$refs.employeeInfo.$refs.employeeForm.validate(async (valid) => {
          if (valid) {
            const result = await this.$API.addEmployee(
              this.$refs.employeeInfo.employeeData
            )
            if (result.code == 200) {
              this.dialogVisible = false
              this.$refs.operationEmployeeData.initEmployee()
            }
          } else {
            console.log('error submit!!')
            return false
          }
        })
      }
    },
    // 关闭对话框
    closeDialog() {
      this.dialogVisible = false
    },
    // 获得工号
    async getWorkId() {
      const result = await this.$API.getWorkId()
      if (result.code == 200) {
        this.workId = result.obj
      }
    },
  },
  mounted() {
    this.getWorkId()
    this.$store.dispatch('getPoliticsStatus')
    this.$store.dispatch('getNations')
    this.$store.dispatch('getPositions')
    this.$store.dispatch('getJobLevel')
    this.$store.dispatch('getDepartment')
  },
}
</script>
<style lang="scss" scope>
.header-button {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}
</style>