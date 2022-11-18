<template>
  <div style="width: 70%; min-width: 350px">
    <el-input
      prefix-icon="el-icon-search"
      placeholder="输入关键字进行过滤"
      v-model="filterText"
    >
    </el-input>

    <el-tree
      class="filter-tree"
      :data="departments"
      :props="defaultProps"
      default-expand-all
      :filter-node-method="filterNode"
      ref="tree"
      :expand-on-click-node="false"
    >
      <span class="custom-tree-node" slot-scope="{ data }">
        <span>{{ data.name }}</span>
        <span class="tree-button">
          <el-button type="primary" size="mini" @click="appendNode(data)">
            添加部门
          </el-button>
          <el-button
            type="danger"
            size="mini"
            style="margin-left: 3px"
            @click="removeNode(data)"
          >
            删除部门
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog
      class="dialog"
      title="添加部门"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <p>
        <span>上级部门：</span><el-tag>{{ parentName }}</el-tag>
      </p>
      <p>
        <span>部门名称：</span>
        <el-input
          style="width: 60%"
          v-model="baseDep.name"
          placeholder="请输入添加部门名称"
          size="small"
        ></el-input>
      </p>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmAdd">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'DepartmentManagement',
  data() {
    return {
      departments: [],
      filterText: '',
      defaultProps: {
        children: 'children',
        label: 'name',
      },
      dialogVisible: false,
      // 添加部门携带参数
      baseDep: {
        name: '',
        parentId: -1,
      },
      parentName: '',
    }
  },
  methods: {
    async initDepartment() {
      const result = await this.$API.getAllDepartment()
      if (result) {
        this.departments = result
      }
    },
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 添加部门
    appendNode(data) {
      this.dialogVisible = true
      this.parentName = data.name
      this.baseDep.parentId = data.id
      console.log(data)
    },
    async confirmAdd() {
      const result = await this.$API.addDepartment(this.baseDep)
      if (result.code == 200) {
        this.initDepartment()
        this.dialogVisible = false
        this.baseDep.name = ''
      } else {
        this.$message.error('请填写字段')
      }
    },
    // 删除部门
    async removeNode(data) {
      const result = await this.$API.deleteDepartment(data.id)
      if (result.code == 200) {
        this.initDepartment()
      }
    },
  },
  mounted() {
    this.initDepartment()
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    },
  },
}
</script>
<style lang="scss" scoped>
.filter-tree {
  margin-top: 10px;
  padding: 10px;
  border-radius: 5px;
  .custom-tree-node {
    display: flex;
    justify-content: space-between;
    width: 100%;
    .tree-button {
      button {
        padding: 1px;
      }
    }
  }
}
.dialog {
  p {
    span {
      margin-right: 10px;
    }
  }
}
</style>