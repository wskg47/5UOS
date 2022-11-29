<template>
  <div>
    <el-table
      :data="employeeData"
      border
      ref="multipleTable"
      @selection-change="handleSelectionChange"
      style="width: 100%"
      height="450"
      size="small"
    >
      <el-table-column fixed type="selection"></el-table-column>
      <el-table-column fixed prop="name" label="姓名" width="80">
      </el-table-column>
      <el-table-column prop="workID" label="工号" width="120">
      </el-table-column>
      <el-table-column prop="gender" label="性别" width="55"> </el-table-column>
      <el-table-column prop="birthday" label="出生日期" width="120">
      </el-table-column>
      <el-table-column prop="idCard" label="身份证号码" width="200">
      </el-table-column>
      <el-table-column prop="wedlock" label="婚姻状况" width="80">
      </el-table-column>
      <el-table-column prop="nation.name" label="民族" width="55">
      </el-table-column>
      <el-table-column prop="nativePlace" label="籍贯" width="70">
      </el-table-column>
      <el-table-column prop="politicsStatus.name" label="政治面貌" width="120">
      </el-table-column>
      <el-table-column prop="email" label="电子邮件" width="200">
      </el-table-column>
      <el-table-column prop="phone" label="电话号码" width="120">
      </el-table-column>
      <el-table-column prop="address" label="联系地址" width="300">
      </el-table-column>
      <el-table-column prop="department.name" label="所属部门" width="120">
      </el-table-column>
      <el-table-column prop="joblevel.name" label="职位" width="120">
      </el-table-column>
      <el-table-column prop="engageForm" label="聘用形式" width="120">
      </el-table-column>
      <el-table-column prop="tiptopDegree" label="最高学历" width="120">
      </el-table-column>
      <el-table-column prop="school" label="毕业院校" width="160">
      </el-table-column>
      <el-table-column prop="specialty" label="所学专业" width="160">
      </el-table-column>
      <el-table-column prop="workState" label="在职状态" width="80">
      </el-table-column>
      <el-table-column prop="beginDate" label="入职日期" width="120">
      </el-table-column>
      <el-table-column prop="conversionTime" label="转正日期" width="120">
      </el-table-column>
      <el-table-column prop="beginContract" label="合同起始日期" width="120">
      </el-table-column>
      <el-table-column prop="endContract" label="合同截止日期" width="120">
      </el-table-column>
      <el-table-column prop="contractTerm" label="合同期限" width="120">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            @click="editEmployeeData(scope.row)"
            type="text"
            size="small"
            icon="el-icon-edit"
            >编辑</el-button
          >
          <el-button type="text" size="small" icon="el-icon-view"
            >查看</el-button
          >

          <el-popconfirm
            title="这是一段内容确定删除吗？"
            @confirm="deleteEmployeeInfo(scope.row)"
          >
            <el-button
              type="text"
              style="color: red"
              size="small"
              icon="el-icon-delete"
              slot="reference"
              >删除</el-button
            >
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页器 -->
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="100"
      layout=" prev, pager, next, jumper,sizes,->,total"
      :total="total"
      style="text-align: center; margin-top: 20px"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'EmployeeData',
  props: {
    keyWord: {
      type: String,
    },
    visible: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      employeeData: [],
      currentPage: 1,
      size: 10,
      total: 0,
    }
  },
  methods: {
    // 初始化员工信息
    async initEmployee() {
      let { currentPage, size } = this
      const result = await this.$API.getAllEmployee(
        currentPage,
        size,
        this.$props.keyWord
      )
      if (result) {
        this.employeeData = result.data
        this.total = result.total
      }
    },
    editEmployeeData(row) {
      this.$emit('openDialog', true, row)
    },
    // 展示多少条信息回调
    handleSizeChange(val) {
      this.size = val
      // console.log(this.size)
      this.initEmployee()
    },
    // 页码改变回调
    handleCurrentChange(val) {
      this.currentPage = val
      this.initEmployee()
    },
    // 选择框回到
    handleSelectionChange() {
      console.log(11)
    },
    // 删除员工信息
    async deleteEmployeeInfo(row) {
      const { total, size } = this
      let pageTotal = Math.ceil((total - 1) / size)
      this.currentPage =
        this.currentPage > pageTotal ? pageTotal : this.currentPage
      this.currentPage = this.currentPage < 1 ? 1 : this.currentPage
      console.log(pageTotal + '共多少页')
      const result = await this.$API.deleteEmployee(row.id)
      if (result.code == 200) {
        this.initEmployee()
      }
      console.log(result)
    },
  },
  mounted() {
    this.initEmployee()
  },
  watch: {
    keyWord(val) {
      if (val == '') {
        this.initEmployee()
      }
    },
  },
}
</script>
<style>
</style>