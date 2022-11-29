import { getPoliticsStatus, getNations, getPositions, getJobLevels, getDepartments } from '@/api'

const state = {
  politicsStatus: [],
  nations: [],
  position: [],
  jobLevel: [],
  departments: []
}
const mutations = {
  GETPOLITICSSTATUS(state, data) {
    state.politicsStatus = data
  },
  GETNATION(state, data) {
    state.nations = data
  },
  GETPOSITION(state, data) {
    state.position = data
  },
  GETJOBLEVEL(state, data) {
    state.jobLevel = data
  },
  GETDEPARTMENT(state, data) {
    state.departments = data
  }
}
const actions = {
  // 获取政治面貌
  async getPoliticsStatus(context) {
    const result = await getPoliticsStatus()
    if (result) {
      context.commit("GETPOLITICSSTATUS", result)
    }
  },
  // 获得民族
  async getNations(context) {
    const result = await getNations()
    if (result) {
      context.commit("GETNATION", result)
    }
  },
  // 获得职位
  async getPositions(context) {
    const result = await getPositions()
    if (result) {
      context.commit("GETPOSITION", result)
    }
  },
  // 获得职称
  async getJobLevel(context) {
    const result = await getJobLevels()
    if (result) {
      context.commit("GETJOBLEVEL", result)
    }
  },
  // 获得部门
  async getDepartment(context) {
    const result = await getDepartments()
    if (result) {
      context.commit("GETDEPARTMENT", result)
    }
  },
}

export default {
  state,
  mutations,
  actions
}