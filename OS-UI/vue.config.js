let proxyObj = {}

proxyObj['/'] = {
  // websoket
  ws: false,
  target: 'http://localhost:8081/',
  changeOrigin: true,
  // 不重写请求地址
  pathReWrite: {
    '^/': '/'
  }
}


module.exports = {
  // 关闭eslint检查
  lintOnSave: false,
  // 配置代理
  devServer: {
    // host: 'localhost',
    // port: 8080,
    proxy: 'http://localhost:8081/'
  }
}