<template>
  <div>
    <el-container>
      <el-header class="header">
        <div class="logo">
          <img src="@/assets/logo.png" alt="" />
          <span>办公系统</span>
        </div>
        <div class="userInfo">
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              <p>{{ userInfo.name }}</p>
              <img :src="userInfo.userFace" alt="" />
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="myCenter">个人中心</el-dropdown-item>
              <el-dropdown-item command="setting">设置</el-dropdown-item>
              <el-dropdown-item command="loginOut">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside width="auto">
          <Aside />
        </el-aside>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }"
              >首页</el-breadcrumb-item
            >
            <el-breadcrumb-item
              v-for="(item, index) in this.$router.currentRoute.matched"
              :key="index"
              >{{ item.name }}</el-breadcrumb-item
            >
          </el-breadcrumb>
          <router-view class="content"></router-view>
          <div
            v-show="this.$router.currentRoute.path == '/home'"
            class="welcome5U"
          >
            欢迎使用5U系统
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from '@/components/Aside.vue'
export default {
  components: { Aside },
  name: 'Home',
  data() {
    return {
      userInfo: {},
    }
  },
  methods: {
    async getUserInfo() {
      const result = await this.$API.getUserInfo()
      if (result) {
        this.userInfo = result
      }
    },
    handleCommand(command) {
      if (command == 'loginOut') {
        this.$confirm('此操作将退出登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true,
        })
          .then(async () => {
            const result = await this.$API.loginOut()
            if (result.code == 200) {
              window.sessionStorage.removeItem('tokenStr')
              // 清空菜单
              this.$store.state.path.routerList = []
              this.$router.replace('/')
            }
          })
          .catch(() => {
            this.$message({
              type: 'info',
              message: '已取消登录',
            })
          })
      }
    },
  },
  mounted() {
    this.getUserInfo()
  },
}
</script>
<style lang="scss" scoped>
.el-header {
  display: flex;
  justify-content: space-between;
  background-color: rgb(255, 255, 255);
  height: 8vh !important;
  .logo {
    display: flex;
    align-items: center;
    img {
      width: 50px;
      height: 34px;
      margin-right: 5px;
    }
    span {
      font-size: 30px;
      font-family: '华文琥珀';
    }
  }
  .userInfo {
    height: 60px;

    .el-dropdown-link {
      display: flex;
      justify-content: space-between;
      align-items: center;
      img {
        width: 70px;
        height: 60px;
      }
      p {
        margin-right: 10px;
      }
    }
  }
}
.el-main {
  .welcome5U {
    font-size: 72px;
    font-family: '华文琥珀';
    color: rgb(25, 107, 238);
    text-align: center;
    margin-top: 20%;
  }
  .content {
    margin-top: 10px;
  }
}
</style>