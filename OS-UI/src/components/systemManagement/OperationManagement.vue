<template>
  <div>
    <div class="search">
      <el-input
        v-model="keywords"
        placeholder="请输入关键字"
        prefix-icon="el-icon-search"
        style="width: 30%; margin-right: 10px"
        size="small"
      ></el-input>
      <el-button
        type="primary"
        size="small"
        icon="el-icon-search"
        @click="searchKeywords"
        >搜索</el-button
      >
    </div>
    <div class="box-container">
      <el-card class="box-card" v-for="user in allOperations" :key="user.id">
        <div slot="header" class="clearfix">
          <span>{{ user.name }}</span>
          <el-button
            style="float: right; padding: 3px 0; color: red"
            icon="el-icon-delete"
            type="text"
          ></el-button>
        </div>
        <div class="content">
          <div class="picture">
            <img :src="user.userFace" alt="" srcset="" />
          </div>
          <div class="information">
            <P><span>用户名：</span>{{ user.name }}</P>
            <P><span>手机号码：</span>{{ user.phone }}</P>
            <P><span>电话号码：</span>{{ user.telephone }}</P>
            <P><span>地址：</span>{{ user.address }}</P>
            <P>
              <span>用户状态：</span>
              <el-switch
                v-model="user.enabled"
                active-color="#13ce66"
                inactive-color="#ff4949"
                active-text="启用"
                inactive-text="禁用"
                @change="updateEnabled(user)"
              >
              </el-switch>
            </P>
            <P>
              <span>用户角色：</span>
              <el-tag
                style="margin-right: 5px"
                type="success"
                v-for="role in user.roles"
                :key="role.id"
                size="mini"
                >{{ role.nameZh }}</el-tag
              >
              <el-popover
                placement="right-start"
                title="角色列表"
                width="200"
                trigger="click"
                @show="getAllRoles(user)"
                @hide="hidePopover(user)"
              >
                <el-select v-model="selectValue" multiple placeholder="请选择">
                  <el-option
                    v-for="item in roles"
                    :key="item.id"
                    :label="item.nameZh"
                    :value="item.id"
                  >
                  </el-option>
                </el-select>
                <i slot="reference">...</i>
              </el-popover>
            </P>
            <P><span>备注：</span></P>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'OperationManagement',
  data() {
    return {
      keywords: '',
      allOperations: [],
      roles: [],
      selectValue: [],
      copySelectValue: [],
    }
  },
  methods: {
    // 初始化操作员
    async initOperation() {
      const result = await this.$API.getAllOperation()
      console.log(result)
      if (result) {
        this.allOperations = result
      }
    },
    async getAllRoles(user) {
      this.selectValue = []
      user.roles.forEach((role) => {
        this.selectValue.push(role.id)
      })
      Object.assign(this.copySelectValue, this.selectValue)
      const result = await this.$API.getAdminRoles()
      if (result) {
        this.roles = result
      }
    },
    async hidePopover(user) {
      let flag = false
      if (this.selectValue.length == this.copySelectValue.length) {
        for (let i = 0; i < this.selectValue.length; i++) {
          for (let j = 0; j < this.copySelectValue.length; j++) {
            if (this.selectValue[i] != this.copySelectValue[j]) {
              flag = true
            } else {
              flag = false
            }
          }
        }
      } else {
        flag = true
      }

      if (flag) {
        let url = `/system/admin/role?adminId=${user.id}`
        this.selectValue.forEach((rid) => {
          url += `&rids=${rid}`
        })
        console.log(url)
        const result = await this.$API.updateRoles(url)
        if (result.code == 200) {
          this.initOperation()
        }
      }
    },
    async searchKeywords() {
      const result = await this.$API.getKeywordsSearch(this.keywords)
      if (result) {
        this.allOperations = result
      }
    },
    // 更新用户状态
    async updateEnabled(user) {
      const result = await this.$API.updateOperation(user)
      if (!result.code == 200) {
        this.$message.error('更新错误！')
      }
    },
  },
  mounted() {
    this.initOperation()
  },
  watch: {
    keywords(val) {
      if (val == '') {
        this.initOperation()
      }
    },
  },
}
</script>
<style lang="scss" scope>
.search {
  display: flex;
  justify-content: center;
}
.box-container {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  margin-top: 20px;
  .box-card {
    width: 31%;
    min-width: 300px;
    margin-bottom: 20px;
    .content {
      .picture {
        display: flex;
        justify-content: center;

        img {
          width: 60px;
          height: 60px;
          background-color: aquamarine;
          border-radius: 50%;
          overflow: hidden;
        }
      }
      .information {
        p {
          -webkit-margin-before: 0;
          -webkit-margin-after: 0;
          padding: 2px 0;
        }
        i {
          display: inline-block;
          color: cornflowerblue;
          cursor: pointer;
          user-select: none;
          font-size: 24px;
        }
      }
    }
  }
}
</style>