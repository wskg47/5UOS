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

// 获取所有操作员
export const getAllOperation = () => requests({ url: `/system/admin/`, method: 'get' })
// 通过关键词搜索操作员
export const getKeywordsSearch = (keywords) => requests({ url: `/system/admin/?keywords=${keywords}`, method: 'get' })
// 更新操作员
export const updateOperation = (data) => requests({ url: '/system/admin/', method: 'post', data })
// 获取所有操作员角色
export const getAdminRoles = () => requests({ url: '/system/admin/roles', method: 'get' })
// 更新操作员角色
export const updateRoles = (url) => requests({ url: url, method: 'put' })

// 获取所有员工
export const getAllEmployee = (currentPage, size, name) => requests({
  url: `/employee/basic/?currentPage=${currentPage}&size=${size}&name=${name}`,
  method: 'get'
})
// 获得所有政治面貌
export const getPoliticsStatus = () => requests({ url: '/employee/basic/politicsStatus', method: 'get' })
// 获取民族
export const getNations = () => requests({ url: '/employee/basic/nations', method: 'get' })
// 获取职位
export const getPositions = () => requests({ url: '/employee/basic/positions', method: 'get' })
// 获得职称
export const getJobLevels = () => requests({ url: '/employee/basic/joblevels', method: 'get' })
// 获得工号
export const getWorkId = () => requests({ url: '/employee/basic/maxWorkId', method: 'get' })
// 获得部门
export const getDepartments = () => requests({ url: '/employee/basic/departments', method: 'get' })
// 添加员工
export const addEmployee = (data) => requests({ url: '/employee/basic/', method: 'post', data })
// 删除员工信息
export const deleteEmployee = (id) => requests({ url: `/employee/basic/${id}`, method: 'delete' })
// 更新员工信息
export const updateEmployeeInfo = (data) => requests({ url: '/employee/basic/', method: 'put', data })