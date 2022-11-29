<template>
  <div>
    <el-form ref="employeeForm" :model="employeeData" :rules="rules">
      <el-row>
        <el-col :span="6">
          <el-form-item label="姓名:" prop="name">
            <el-input
              v-model="employeeData.name"
              placeholder="请输入员工姓名"
              prefix-icon="el-icon-edit"
              style="width: 160px"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="性别:" prop="gender">
            <el-radio-group v-model="employeeData.gender">
              <el-radio :label="'男'">男</el-radio>
              <el-radio :label="'女'">女</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="出生日期:" prop="birthday">
            <el-date-picker
              v-model="employeeData.birthday"
              type="date"
              placeholder="选择日期"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              style="width: 160px"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="政治面貌:" prop="politicId">
            <el-select
              v-model="employeeData.politicId"
              placeholder="请选择"
              style="width: 160px"
            >
              <el-option
                v-for="item in politicsStatus"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="民族:" prop="nationId">
            <el-select
              v-model="employeeData.nationId"
              placeholder="请选择"
              style="width: 160px"
            >
              <el-option
                v-for="item in nations"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="籍贯:" prop="nativePlace">
            <el-input
              v-model="employeeData.nativePlace"
              placeholder="请输入籍贯"
              prefix-icon="el-icon-edit"
              style="width: 160px"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="电子邮件:" prop="email">
            <el-input
              v-model="employeeData.email"
              placeholder="请输入电子邮箱"
              prefix-icon="el-icon-message"
              style="width: 160px"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="联系地址:" prop="address">
            <el-input
              v-model="employeeData.address"
              placeholder="请输入联系地址"
              prefix-icon="el-icon-edit"
              style="width: 160px"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="职位:" prop="posId">
            <el-select
              v-model="employeeData.posId"
              placeholder="请选择"
              style="width: 160px"
            >
              <el-option
                v-for="item in position"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="职称:" prop="jobLevelId">
            <el-select
              v-model="employeeData.jobLevelId"
              placeholder="请选择"
              style="width: 160px"
            >
              <el-option
                v-for="item in jobLevel"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="所属部门:" prop="departmentId">
            <el-popover
              placement="bottom"
              title="选择部门"
              width="200"
              trigger="hover"
            >
              <el-tree
                :data="departments"
                default-expand-all
                highlight-current
                :props="defaultProps"
                @node-click="selectNode"
              ></el-tree>
              <div class="select-department" slot="reference">
                {{ selectDepartment ? selectDepartment : '请选择部门' }}
              </div>
            </el-popover>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="电话号码:" prop="phone">
            <el-input
              v-model="employeeData.phone"
              placeholder="请输入电话号码"
              prefix-icon="el-icon-phone"
              style="width: 160px"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="工号:" prop="workID">
            <el-input
              v-model="employeeData.workID"
              placeholder="请输入工号"
              prefix-icon="el-icon-edit"
              disabled
              style="width: 160px"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="学历:" prop="tiptopDegree">
            <el-select
              v-model="employeeData.tiptopDegree"
              placeholder="请选择"
              style="width: 160px"
            >
              <el-option
                v-for="item in tiptopDegree"
                :key="item"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="毕业院校:" prop="school">
            <el-input
              v-model="employeeData.school"
              placeholder="请输入毕业院校"
              prefix-icon="el-icon-edit"
              style="width: 160px"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="专业名称:" prop="specialty">
            <el-input
              v-model="employeeData.specialty"
              placeholder="请输入毕业院校"
              prefix-icon="el-icon-edit"
              style="width: 160px"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="入职日期:" prop="beginDate">
            <el-date-picker
              v-model="employeeData.beginDate"
              type="date"
              placeholder="选择日期"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              style="width: 132px"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="转正日期:" prop="conversionTime">
            <el-date-picker
              v-model="employeeData.conversionTime"
              type="date"
              placeholder="选择日期"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              style="width: 132px"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="合同起始日期:" prop="beginContract">
            <el-date-picker
              v-model="employeeData.beginContract"
              type="date"
              placeholder="选择日期"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              style="width: 132px"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="合同截止日期:" prop="endContract">
            <el-date-picker
              v-model="employeeData.endContract"
              type="date"
              placeholder="选择日期"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              style="width: 132px"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="身份证号:" prop="idCard">
            <el-input
              v-model="employeeData.idCard"
              placeholder="请输入身份证号"
              prefix-icon="el-icon-edit"
              style="width: 220px"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="聘用形式:" prop="engageForm">
            <el-radio-group v-model="employeeData.engageForm">
              <el-radio :label="'劳动合同'">劳动合同</el-radio>
              <el-radio :label="'劳务合同'">劳务合同</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="婚姻状况:" prop="wedlock">
            <el-radio-group v-model="employeeData.wedlock">
              <el-radio :label="'未婚'">未婚</el-radio>
              <el-radio :label="'已婚'">已婚</el-radio>
              <el-radio :label="'离异'">离异</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'AddEmployeeData',
  props: ['editData', 'workId'],
  data() {
    return {
      employeeData: {
        id: null,
        name: '',
        gender: '',
        birthday: '',
        idCard: '',
        wedlock: '',
        nationId: null,
        nativePlace: '',
        politicId: null,
        email: '',
        phone: '',
        address: '',
        departmentId: null,
        jobLevelId: null,
        posId: null,
        engageForm: '',
        tiptopDegree: '',
        specialty: '',
        school: '',
        beginDate: '',
        workState: '在职',
        workID: '',
        contractTerm: null,
        conversionTime: '',
        notWorkDate: null,
        beginContract: '',
        endContract: '',
        workAge: null,
        salaryId: null,
      },
      tiptopDegree: [
        '小学',
        '初中',
        '高中',
        '大专',
        '本科',
        '硕士',
        '博士',
        '其它',
      ],
      defaultProps: {
        children: 'children',
        label: 'name',
      },
      selectDepartment: '',
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' },
        ],
        gender: [{ required: true, message: '请选择性别', trigger: 'blur' }],
        birthday: [
          { required: true, message: '请输入出生日期', trigger: 'blur' },
        ],
        idCard: [
          { required: true, message: '请输入身份证号码', trigger: 'blur' },
          {
            pattern:
              /^[1-9]\d{5}(?:18|19|20)\d{2}(?:0[1-9]|10|11|12)(?:0[1-9]|[1-2]\d|30|31)\d{3}[\dXx]$/,
            message: '身份证号码不正确',
            trigger: 'blur',
          },
        ],
        wedlock: [
          { required: true, message: '请选择婚姻状况', trigger: 'blur' },
        ],
        nationId: [{ required: true, message: '请输入民族', trigger: 'blur' }],
        nativePlace: [
          { required: true, message: '请输入籍贯', trigger: 'blur' },
        ],
        politicId: [
          { required: true, message: '请输入政治面貌', trigger: 'blur' },
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '邮箱地址格式不正确', trigger: 'blur' },
        ],
        phone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' },
          {
            pattern:
              /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[1589]))\d{8}$/,
            message: '请输入正确得手机号码',
            trigger: 'blur',
          },
        ],
        address: [
          { required: true, message: '请输入员工地址', trigger: 'blur' },
        ],
        departmentId: [
          { required: true, message: '请输入部门名称', trigger: 'blur' },
        ],
        jobLevelId: [
          { required: true, message: '请输入职称', trigger: 'blur' },
        ],
        posId: [{ required: true, message: '请输入职位', trigger: 'blur' }],
        engageForm: [
          { required: true, message: '请选择聘用形式', trigger: 'blur' },
        ],
        tiptopDegree: [
          { required: true, message: '请输入学历', trigger: 'blur' },
        ],
        specialty: [{ required: true, message: '请输入专业', trigger: 'blur' }],
        school: [{ required: true, message: '请输入学校', trigger: 'blur' }],
        beginDate: [
          { required: true, message: '请输入q入职日期', trigger: 'blur' },
        ],
        workState: [
          { required: true, message: '请输入工作状态', trigger: 'blur' },
        ],
        workID: [{ required: true, message: '请输入工号', trigger: 'blur' }],
        contractTerm: [
          { required: true, message: '请输入合同期限', trigger: 'blur' },
        ],
        conversionTime: [
          { required: true, message: '请输入转正日期', trigger: 'blur' },
        ],
        notWorkDate: [
          { required: true, message: '请输入离职日期', trigger: 'blur' },
        ],
        beginContract: [
          { required: true, message: '请输入合同起始日期', trigger: 'blur' },
        ],
        endContract: [
          { required: true, message: '请输入合同结束日期', trigger: 'blur' },
        ],
        workAge: [{ required: true, message: '请输入工龄', trigger: 'blur' }],
      },
    }
  },
  methods: {
    // 部门选中
    selectNode(data) {
      this.selectDepartment = data.name
      this.employeeData.departmentId = data.id
    },
  },
  mounted() {
    // 页面挂载显示部门名称
    this.employeeData.workID = this.$props.workId
    this.selectDepartment = ''
    if (this.$props.editData.id) {
      // 页面初次挂载调用
      this.employeeData = this.$props.editData || {}
      this.selectDepartment = this.$props.editData.department.name || ''
    }
  },
  computed: {
    ...mapState({
      politicsStatus: (state) => state.department.politicsStatus,
      nations: (state) => state.department.nations,
      position: (state) => state.department.position,
      jobLevel: (state) => state.department.jobLevel,
      departments: (state) => state.department.departments,
    }),
  },
  watch: {
    // 当父组件editData数据发生变化时调用
    editData(val) {
      this.selectDepartment = ''
      this.employeeData = val
      if (val.id) {
        this.selectDepartment = val.department.name
      }
    },
  },
}
</script>
<style scoped>
.select-department {
  width: 160px;
  height: 32px;
  box-sizing: border-box;
  border: 1px solid #dcdfe6;
  cursor: pointer;
  padding: 0 15px;
  margin-left: 83px;
  padding-left: 15px;
  border-radius: 4px;
  color: #606266;
  line-height: 32px;
  font-size: 13px;
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
}
</style>