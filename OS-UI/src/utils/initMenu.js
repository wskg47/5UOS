import { getMenu } from '@/api'
export const filterAsyncRouter = (asyncRouterMap) => {
  let accessRoutes = []
  asyncRouterMap.forEach(route => {
    let { path, name, iconCls, children, component } = route
    if (children && children instanceof Array) {
      // 递归
      children = filterAsyncRouter(children)
    }

    let fmtRoute = {
      path: path,
      name: name,
      iconCls: iconCls,
      children: children,
      component(resolve) {
        if (component.startsWith('Home')) {
          require([`@/views/${component}/index.vue`], resolve)
        } else if (component.startsWith('Emp')) {
          require([`@/views/emp/${component}/index.vue`], resolve)
        } else if (component.startsWith('Per')) {
          require([`@/views/per/${component}/index.vue`], resolve)
        } else if (component.startsWith('Sal')) {
          require([`@/views/sal/${component}/index.vue`], resolve)
        } else if (component.startsWith('Sta')) {
          require([`@/views/sta/${component}/index.vue`], resolve)
        } else if (component.startsWith('Sys')) {
          require([`@/views/sys/${component}/index.vue`], resolve)
        }
      }
    }
    accessRoutes.push(fmtRoute)
  })
  return accessRoutes
}

export const initMenu = async (router, store) => {
  if (store.state.path.routerList.length > 0) {
    return
  }
  const result = await getMenu()
  if (result) {
    let accessRoutes = filterAsyncRouter(result)
    store.commit('initRouters', accessRoutes)
    router.addRoutes(accessRoutes)
  }
}