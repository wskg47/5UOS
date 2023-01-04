import axios from "axios";
// import { escape } from "core-js/fn/regexp";

const downloadService = axios.create({
  responseType: "arraybuffer"
})

downloadService.interceptors.request.use(config => {
  // 如果存在Token，请求携带这个Token
  const tokenStr = window.sessionStorage.getItem('tokenStr')
  if (tokenStr) {
    config.headers['Authorization'] = tokenStr;
  }
  return config
}, error => {
  console.log(error);
})

downloadService.interceptors.response.use(config => {
  let headers = config.headers;
  let reg = RegExp(/application\/json/);

  if (headers['content-type'].match(reg)) {
    config.data = unitToString(config.data)
  } else {
    let fileDownload = require('js-file-download')
    let fileName = headers['content-disposition'].split(';')[1].split('filename=')[1]
    let contentType = headers['content-type']
    fileName = decodeURIComponent(fileName)
    fileDownload(config.data, fileName, contentType)
  }

}, error => {
  console.log(error);
})


function unitToString(unitArray) {
  let encodedString = String.fromCharCode.apply(null, new Uint8Array(unitArray))
  let decodedString = decodeURIComponent(escape(encodedString))
  return JSON.parse(decodedString)
}

export default downloadService