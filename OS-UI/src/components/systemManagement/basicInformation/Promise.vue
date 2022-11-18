<template>
  <div>
    <div>
      <el-input
        placeholder="请输入角色英文名"
        clearable
        v-model="role.name"
        style="width: 300px; margin-right: 10px"
      >
        <template slot="prepend">ROLE_</template>
      </el-input>
      <el-input
        placeholder="请输入角色中文名"
        v-model="role.nameZh"
        clearable
        style="width: 300px; margin-right: 10px"
      >
      </el-input>
      <el-button type="primary" icon="el-icon-plus" @click="addRole"
        >添加角色</el-button
      >
    </div>
    <div>
      <el-collapse
        style="margin-top: 10px; width: 800px; background-color: white"
        @change="handlerChange"
        accordion
        v-model="activeName"
      >
        <el-collapse-item
          :title="role.nameZh"
          :name="role.id"
          v-for="(role, index) in allRoles"
          :key="role.id"
          style="padding: 0 20px; user-select: none"
        >
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>可访问资源</span>
              <el-button
                style="float: right"
                type="danger"
                size="mini"
                circle
                icon="el-icon-delete"
                @click="deleteRole(role.id)"
              ></el-button>
            </div>
            <el-tree
              :data="treeData"
              node-key="id"
              :default-checked-keys="defaultSelectKey"
              show-checkbox
              :props="defaultProps"
              auto-expand-parent
              ref="tree"
            >
            </el-tree>
            <div style="display: flex; justify-content: flex-end">
              <el-button type="info" size="mini" @click="cancelEdit"
                >取消修改</el-button
              >
              <el-button
                type="primary"
                size="mini"
                @click="submitEdit(role.id, index)"
                >确定修改</el-button
              >
            </div>
          </el-card>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Promise',
  data() {
    return {
      role: {
        name: '',
        nameZh: '',
      },
      activeName: -1,
      allRoles: [],
      defaultProps: {
        children: 'children',
        label: 'name',
      },
      treeData: [],
      defaultSelectKey: [],
    }
  },
  methods: {
    handlerChange(id) {
      if (id) {
        this.getAllRolesMenus()
        this.getMyIdById(id)
      }
    },
    // 添加权限
    async addRole() {
      if (this.role.name != '' && this.role.nameZh != '') {
        const result = await this.$API.addRole(this.role)
        if (result.code == 200) {
          this.getAllRoles()
        }
      } else {
        this.$message.error('请填写所有字段！')
      }
    },
    // 获取所有权限
    async getAllRoles() {
      const result = await this.$API.getAllRoles()
      if (result) {
        this.allRoles = result
      }
    },
    // 获取所有权限菜单
    async getAllRolesMenus() {
      const result = await this.$API.getAllRolesMenus()
      if (result) {
        this.treeData = result
      }
    },
    // 根据角色Id获取默认展开节点
    async getMyIdById(rid) {
      const result = await this.$API.getMyIdByRid(rid)
      this.defaultSelectKey = result
    },
    cancelEdit() {
      this.activeName = -1
    },
    async submitEdit(id, index) {
      let selectKeysArr = this.$refs.tree[index].getCheckedKeys(true)
      let url = `/system/basic/permiss?rid=${id}`
      selectKeysArr.forEach((key) => {
        url += `&mids=${key}`
      })
      const result = await this.$API.updateRoleMenu(url)
      if (result.code == 200) {
        this.getAllRoles()
        this.activeName = -1
      }
    },
    async deleteRole(id) {
      console.log(id)
      const result = await this.$API.deleteRole(id)
      if (result.code == 200) {
        this.getAllRoles()
      } else {
        this.$message.error('数据有关联，不能删除')
      }
    },
  },
  mounted() {
    this.getAllRoles()
  },
}
</script>
<style>
</style>