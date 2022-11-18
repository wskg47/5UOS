<template>
  <div>
    <!-- 添加职位 -->
    <div>
      <el-input
        placeholder="请输入内容"
        v-model="addDepartment"
        clearable
        suffix-icon="el-icon-plus"
        style="width: 300px; margin-right: 20px"
        @keyup.enter.native="addPosition"
      >
      </el-input>
      <el-button type="primary" @click="addPosition">添加职位</el-button>
    </div>
    <el-table
      :data="tableData"
      border
      height="400"
      ref="multipleTable"
      tooltip-effect="dark"
      @selection-change="handleSelectionChange"
      style="width: 70%; margin-top: 20px"
    >
      <el-table-column type="selection" align="center" width="55">
      </el-table-column>
      <el-table-column prop="id" align="center" label="编号" width="60">
      </el-table-column>
      <el-table-column prop="name" align="center" label="职位" width="180">
      </el-table-column>
      <el-table-column
        prop="createDate"
        align="center"
        label="创建时间"
        width="240"
      >
      </el-table-column>
      <el-table-column label="是否启用" align="center" width="120">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.enabled"
            active-color="#13ce66"
            inactive-color="#ff4949"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button
            @click="editPosition(scope.row)"
            type="primary"
            icon="el-icon-edit"
            size="mini"
            circle
            style="margin-right: 10px"
          ></el-button>
          <el-popconfirm
            :title="`确定删除${scope.row.name}吗？`"
            @confirm="deleteInformation(scope.row)"
          >
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              slot="reference"
              circle
            ></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-button
      type="danger"
      style="margin-top: 10px"
      :disabled="isDisabled"
      @click="deleteInformations"
      >批量删除</el-button
    >
    <!-- 对话框 -->
    <el-dialog title="修改职位" :visible.sync="dialogVisible" width="30%">
      职位:
      <el-input
        placeholder="点击输入职位"
        v-model="editPositionValue.name"
        clearable
        style="width: 200px"
        size="small"
      >
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updatePosition">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'PositionManagement',
  data() {
    return {
      addDepartment: '',
      tableData: [],
      dialogVisible: false,
      editPositionValue: {
        id: Number,
        name: '',
      },
      multipleSelection: [],
      isDisabled: true,
    }
  },
  methods: {
    // 添加职位
    async addPosition() {
      const result = await this.$API.addPosition({ name: this.addDepartment })
      if (result.code == 200) {
        this.getPosition()
      }
      this.addDepartment = ''
    },
    // 获取所有职位
    async getPosition() {
      const result = await this.$API.getAllPositionData()
      this.tableData = result
    },
    // 编辑职位信息
    editPosition(row) {
      this.dialogVisible = true
      this.editPositionValue.name = row.name
      this.editPositionValue.id = row.id
    },
    // 确定编辑职位
    async updatePosition() {
      const result = await this.$API.putAllPositionData(this.editPositionValue)
      if (result.code == 200) {
        this.dialogVisible = false
        this.getPosition()
      }
    },
    // 删除职位信息
    async deleteInformation(row) {
      const result = await this.$API.deletePosition(row.id)
      if (result == 200) {
        this.getPosition()
      }
    },
    // 单选框选中状态
    handleSelectionChange(val) {
      this.multipleSelection = val
      this.multipleSelection.length > 0
        ? (this.isDisabled = false)
        : (this.isDisabled = true)
    },
    // 批量删除数据
    async deleteInformations() {
      let ids = '?'
      this.multipleSelection.forEach((item) => {
        ids += `ids=${item.id}&`
      })
      const result = await this.$API.deletePosition(ids)
      if (result.code == 200) {
        this.getPosition()
      }
    },
  },
  mounted() {
    this.getPosition()
  },
}
</script>
<style>
</style>