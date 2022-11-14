<template>
  <div class="loginContainer">
    <el-form
      v-loading="loading"
      element-loading-text="正在登录"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      :model="loginForm"
      :rules="rules"
      ref="loginForm"
      label-width="70px"
      class="loginForm"
    >
      <h3 class="title">系统管理</h3>
      <el-form-item label="用户名" prop="username">
        <el-input
          type="text"
          v-model="loginForm.username"
          placeholder="请输入账号"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          v-model="loginForm.password"
          placeholder="请输入密码"
        ></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="code" class="code">
        <el-input
          type="text"
          v-model="loginForm.code"
          placeholder="点击图片更换验证码"
          style="width: 180px; margin-right: 10px"
        ></el-input>
        <img :src="captchaUrl" class="loginRemember" @click="updateCode" />
      </el-form-item>
      <el-button
        size="small"
        @click="submitLogin"
        style="width: 100%"
        type="primary"
        >登录</el-button
      >
    </el-form>
  </div>
</template>

<script>
export default {
  name: '',
  data() {
    return {
      captchaUrl: '/captcha?time=' + new Date(),
      loginForm: {
        username: 'admin',
        password: '123',
        code: '',
      },
      rules: {
        userName: [
          { required: true, message: '请输入用户名！', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码！', trigger: 'blur' },
        ],
        code: [{ required: true, message: '验证码', trigger: 'blur' }],
      },
      loading: false,
    }
  },
  methods: {
    submitLogin() {
      this.$refs.loginForm.validate(async (valid) => {
        if (valid) {
          this.loading = true
          const result = await this.$API.submitLogin(this.loginForm)
          if (result.code == 200) {
            this.loading = false
            const tokenStr = result.obj.tokenHead + result.obj.token
            window.sessionStorage.setItem('tokenStr', tokenStr)
            const path = this.$route.query.redirect
            this.$router.replace(
              path == '/' || path == undefined ? '/home' : path
            )
          }
          this.loading = false
        } else {
          this.$message.error('请填写所有字段')
          return false
        }
      })
    },
    updateCode() {
      this.captchaUrl = '/captcha?time=' + new Date()
    },
  },
}
</script>
<style lang="scss" scoped>
.loginContainer {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: rgb(55, 55, 55);

  .loginForm {
    width: 360px;
    height: 300px;
    padding: 20px;
    background-color: aliceblue;
    box-shadow: 0 0 25px #cac6c6;
    border-radius: 5%;
    .title {
      text-align: center;
    }
    .code {
      .loginRemember {
        position: absolute;
      }
    }
  }
}
</style>