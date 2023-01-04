<template>
  <div>
    <el-menu
      class="el-menu-vertical-demo"
      background-color="#F3F4FD"
      text-color="#696969"
      active-text-color="#FFFFFF"
      router
      unique-opened
      :default-active="$route.path"
    >
      <el-submenu
        :index="index + ''"
        v-for="(item, index) in routers"
        :key="index"
        v-show="!item.hidden"
      >
        <template slot="title">
          <i :class="item.iconCls"></i>
          <span>{{ item.name }}</span>
        </template>
        <el-menu-item-group v-for="(children, i) in item.children" :key="i">
          <!-- <template slot="title">分组一</template> -->
          <el-menu-item :index="children.path">
            <!-- <i class="el-icon-location"></i> -->
            {{ children.name }}
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'Aside',
  data() {
    return {
      data: [
        {
          path: '/home',
          name: '员工资料',
          iconCls: 'fa fa-user-circle-o',
          children: [
            {
              path: '/emp/basic',
              name: '基本资料',
              iconCls: null,
              children: null,
            },
          ],
        },
        {
          path: '/home',
          name: '人事管理',
          iconCls: 'fa fa-address-card-o',
          children: [
            {
              path: '/per/emp',
              name: '员工资料',
              iconCls: null,
              children: null,
            },
            {
              path: '/per/ec',
              name: '员工奖惩',
              iconCls: null,
              children: null,
            },
            {
              path: '/per/train',
              name: '员工培训',
              iconCls: null,
              children: null,
            },
            {
              path: '/per/salary',
              name: '员工调薪',
              iconCls: null,
              children: null,
            },
            {
              path: '/per/mv',
              name: '员工调动',
              iconCls: null,
              children: null,
            },
          ],
        },
        {
          path: '/home',
          name: '薪资管理',
          iconCls: 'fa fa-money',
          children: [
            {
              path: '/sal/sob',
              name: '工资账套管理',
              iconCls: null,
              children: null,
            },
            {
              path: '/sal/sobcfg',
              name: '员工账套设置',
              iconCls: null,
              children: null,
            },
            {
              path: '/sal/table',
              name: '工资表管理',
              iconCls: null,
              children: null,
            },
            {
              path: '/sal/month',
              name: '月末处理',
              iconCls: null,
              children: null,
            },
            {
              path: '/sal/search',
              name: '工资表查询',
              iconCls: null,
              children: null,
            },
          ],
        },
        {
          path: '/home',
          name: '统计管理',
          iconCls: 'fa fa-bar-chart',
          children: [
            {
              path: '/sta/all',
              name: '综合信息统计',
              iconCls: null,
              children: null,
            },
            {
              path: '/sta/score',
              name: '员工积分统计',
              iconCls: null,
              children: null,
            },
            {
              path: '/sta/pers',
              name: '人事信息统计',
              iconCls: null,
              children: null,
            },
            {
              path: '/sta/record',
              name: '人事记录统计',
              iconCls: null,
              children: null,
            },
          ],
        },
        {
          path: '/home',
          name: '系统管理',
          iconCls: 'fa fa-windows',
          children: [
            {
              path: '/sys/basic',
              name: '基础信息设置',
              iconCls: null,
              children: null,
            },
            {
              path: '/sys/cfg',
              name: '系统管理',
              iconCls: null,
              children: null,
            },
            {
              path: '/sys/log',
              name: '操作日志管理',
              iconCls: null,
              children: null,
            },
            {
              path: '/sys/admin',
              name: '操作员管理',
              iconCls: null,
              children: null,
            },
            {
              path: '/sys/data',
              name: '备份恢复数据库',
              iconCls: null,
              children: null,
            },
            {
              path: '/sys/init',
              name: '初始化数据库',
              iconCls: null,
              children: null,
            },
          ],
        },
      ],
    }
  },
  computed: {
    routers() {
      return this.$store.state.path.routerList
    },
  },
  methods: {},
}
</script>
<style lang="scss"  scoped>
.el-menu-vertical-demo {
  min-width: 220px;
  overflow-x: hidden;
  overflow-y: hidden;
  height: 100%;
  padding: 10px;
  // height: 83vh;
  user-select: none;
  border-radius: 0 0 15px 15px;
}
/deep/.is-active,
/deep/.el-menu-item:hover,
/deep/.el-submenu__title:hover {
  background: linear-gradient(
    270deg,
    rgb(1, 80, 208) 0%,
    rgb(28, 110, 242) 100%
  ) !important;
  border-radius: 15px !important;
  color: #fff !important;
}
</style>