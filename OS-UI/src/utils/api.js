import axios from 'axios'
import router from '@/router'
import { Message } from 'element-ui'


const requests = axios.create({
  // 配置对象
  //基础路径发送请求时，路径当中会出现api
  //代表请求超时的时间为5秒
  timeout: 5000,
});

// 请求拦截器
requests.interceptors.request.use(config => {
  // 如果存在Token，请求携带这个Token
  const tokenStr = window.sessionStorage.getItem('tokenStr')
  if (tokenStr) {
    config.headers['Authorization'] = tokenStr;
  }
  return config
}, error => {
  console.log(error);
})

//响应拦截器
requests.interceptors.response.use(config => {
  // 业务逻辑判断
  if (config.status && config.status.code == 200) {
    if (config.data.code == 500 || config.data.code == 401 || config.data.code == 403) {
      Message.error({
        message: config.data.message
      })
      return;
    }
  }
  if (config.data.code == 200) {
    Message.success({
      message: config.data.message
    })
  }
  //  else {
  //   Message.error({
  //     message: config.data.message
  //   })
  // }
  return config.data
},
  error => {
    // do something with request error
    if (error.response.code == 504 || error.response.code == 404) {
      Message.error({
        message: '服务器出错了！！！'
      })
    } else if (error.response.code == 403) {
      Message.error({
        message: '权限不足，请联系管理员'
      })
    } else if (error.response.code == 401) {
      Message.error({
        message: '请登录！'
      })
      router.replace('/')
    } else {
      if (error.response.data.message) {
        Message.error({
          message: error.response.data.message
        })
      } else {
        Message.error({
          message: '未知错误！'
        })
      }
    }
    return error;
  });


export default requests