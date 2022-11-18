<template>
  <div>
    <div>
      <el-input
        placeholder="请输入职称"
        v-model="jobLevel.name"
        clearable
        suffix-icon="el-icon-plus"
        style="width: 300px; margin-right: 10px"
      >
      </el-input>
      <el-select
        v-model="jobLevel.titleLevel"
        placeholder="请选择"
        style="margin-right: 10px"
      >
        <el-option
          v-for="item in titleLevel"
          :key="item"
          :label="item"
          :value="item"
        >
        </el-option>
      </el-select>
      <el-button type="primary" @click="addLevel">添加职位</el-button>
    </div>
    <div>
      <el-table
        :data="levelList"
        border
        height="400"
        @selection-change="handleSelectionChange"
        style="width: 80%; margin-top: 20px"
      >
        <el-table-column type="selection" align="center" width="55">
        </el-table-column>
        <el-table-column
          prop="id"
          align="center"
          label="编号"
          width="55"
        ></el-table-column>

        <el-table-column
          prop="name"
          align="center"
          label="职称名称"
          width="150"
        >
        </el-table-column>
        <el-table-column
          prop="titleLevel"
          label="职称等级"
          width="180"
          align="center"
        ></el-table-column>

        <el-table-column
          prop="createDate"
          align="center"
          label="创建时间"
          width="220"
        >
        </el-table-column>

        <el-table-column label="是否启用" width="100">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.enabled">启用</el-tag>
            <el-tag type="danger" v-else>未启用</el-tag>
          </template>
        </el-table-column>

        <el-table-column fixed="right" min-width="120" label="操作">
          <template slot-scope="scope">
            <el-button
              icon="el-icon-edit"
              size="mini"
              circle
              type="primary"
              @click="edit(scope.row)"
            ></el-button>
            <el-button
              icon="el-icon-delete"
              size="mini"
              circle
              type="danger"
              @click="deleteRow(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-button
      type="danger"
      @click="deleteRows"
      style="margin-top: 10px"
      :disabled="this.multipleSelection.length > 0 ? false : true"
      >批量删除</el-button
    >
    <el-dialog title="编辑职称" :visible.sync="dialogVisible" width="30%">
      <p>
        职称名称：
        <el-input
          placeholder="请输入职称"
          suffix-icon="el-icon-edit"
          size="small"
          style="width: 300px"
          v-model="updateLevel.name"
        >
        </el-input>
      </p>
      <p>
        职称等级：
        <el-select
          v-model="updateLevel.titleLevel"
          placeholder="请选择"
          size="small"
          style="width: 300px"
        >
          <el-option
            v-for="item in titleLevel"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </p>
      <p>
        是否启用：
        <el-switch
          v-model="updateLevel.enabled"
          active-text="启用"
          inactive-text="未启用"
        >
        </el-switch>
      </p>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="doUpDate">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'TitleManagement',
  data() {
    return {
      levelList: [],
      jobLevel: {
        name: '',
        titleLevel: '',
      },
      titleLevel: ['正高级', '副高级', '中级', '初级', ''],
      multipleSelection: [],
      dialogVisible: false,
      updateLevel: {
        id: Number,
        name: '',
        titleLevel: '',
        enabled: true,
      },
    }
  },
  methods: {
    // 获取所有职称
    async getAllLevelList() {
      const result = await this.$API.getAllTitle()
      if (result) {
        this.levelList = result
      }
    },
    // 添加职称
    async addLevel() {
      const result = await this.$API.addLevel(this.jobLevel)
      if (result.code == 200) {
        this.getAllLevelList()
      }
    },
    // 编辑
    edit(row) {
      this.dialogVisible = true
      let { id, name, titleLevel, enabled } = row
      Object.assign(this.updateLevel, { id, name, titleLevel, enabled })
    },
    // 选中
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    // 确定修改
    async doUpDate() {
      const result = await this.$API.upDateLevel(this.updateLevel)
      if (result.code == 200) {
        this.getAllLevelList()
      }
      this.dialogVisible = false
    },
    // 删除一条数据
    async deleteRow(row) {
      console.log(row)
      const result = await this.$API.deleteRowLevel(row.id)
      console.log(result)
      if (result.code == 200) {
        this.getAllLevelList()
      } else {
        this.$message.error(result.message)
      }
    },
    // 批量删除
    async deleteRows() {
      let ids = '?'
      this.multipleSelection.forEach((item) => {
        ids += `ids=${item.id}&`
      })
      const result = await this.$API.deleteRowLevel(ids)
      if (result.code == 200) {
        this.getAllLevelList()
      }
    },
  },
  mounted() {
    this.getAllLevelList()
  },
}
</script>
<style>
</style>