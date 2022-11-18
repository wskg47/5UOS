import requests from '@/utils/api'


// 登录
export const submitLogin = data => requests({ url: '/login', method: 'post', data })
// 退出登录
export const loginOut = () => requests({ url: '/logout', method: 'post' })

// 侧边栏菜单
export const getMenu = () => requests({ url: '/system/config/menu', method: 'get' })
// 获取用户信息
export const getUserInfo = () => requests({ url: '/admin/info', method: 'get' })

// 获取所有职位信息
export const getAllPositionData = () => requests({ url: '/system/basic/pos/', method: 'get' })
// 修改职位信息
export const putAllPositionData = (data) => requests({ url: '/system/basic/pos/', method: 'put', data })
// 添加职位
export const addPosition = (data) => requests({ url: '/system/basic/pos/', method: 'post', data })
// 删除一条职位信息
export const deletePosition = (id) => requests({ url: `/system/basic/pos/${id}`, method: 'delete' })

// 获取所有职称
export const getAllTitle = () => requests({ url: '/system/basic/joblevel/', method: 'get' })
// 添加职称
export const addLevel = (data) => requests({ url: '/system/basic/joblevel/', method: 'post', data })
// 更改职位
export const upDateLevel = (data) => requests({ url: '/system/basic/joblevel/', method: 'put', data })
// 删除一条职位
export const deleteRowLevel = (id) => requests({ url: `/system/basic/joblevel/${id}`, method: 'delete' })

// 添加角色权限
export const addRole = (data) => requests({ url: '/system/basic/permiss/role', method: 'post', data })
// 获取所有权限
export const getAllRoles = () => requests({ url: '/system/basic/permiss/', method: 'get' })
// 获取所有权限菜单
export const getAllRolesMenus = () => requests({ url: '/system/basic/permiss/menus', method: 'get' })
// 根据角色ID查询有权限的id
export const getMyIdByRid = (rid) => requests({ url: `/system/basic/permiss/mid/${rid}`, method: 'get' })
// 更新权限菜单
export const updateRoleMenu = (url) => requests({ url: url, method: 'put' })
// 删除权限角色
export const deleteRole = (id) => requests({ url: `/system/basic/permiss/role/${id}`, method: 'delete' })

// 获取所有部门
export const getAllDepartment = () => requests({ url: '/system/basic/department/', method: 'get' })
// 添加部门
export const addDepartment = (data) => requests({ url: '/system/basic/department/', method: 'post', data })
// 删除部门
export const deleteDepartment = (id) => requests({ url: `/system/basic/department/${id}`, method: 'delete' })