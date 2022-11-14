import requests from '@/utils/api'


// 登录
export const submitLogin = data => requests({ url: '/login', method: 'post', data })

// 侧边栏菜单
export const getMenu = () => requests({ url: '/system/config/menu', method: 'get' })

// 获取用户信息
export const getUserInfo = () => requests({ url: '/admin/info', method: 'get' })

// 退出登录
export const loginOut = () => requests({ url: '/logout', method: 'post' })

// 获取所有职位信息
export const getAllPositionData = () => requests({ url: '/system/basic/pos/', method: 'get' })

// 修改职位信息
export const putAllPositionData = (data) => requests({ url: '/system/basic/pos/', method: 'put', data })
// 添加职位
export const addPosition = (data) => requests({ url: '/system/basic/pos/', method: 'post', data })

// 删除一条职位信息
export const deletePosition = (id) => requests({ url: `/system/basic/pos/${id}`, method: 'delete' })

// 批量删除职位信息
// export const deletePositions = (arr) => requests({ url: '/system/basic/pos/', method: 'delete', data: arr })