<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="医生姓名" prop="doctor">
        <el-input
          v-model="queryParams.doctor"
          placeholder="请输入医生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="医院" prop="hospital">
        <el-input
          v-model="queryParams.hospital"
          placeholder="请输入医院"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="录入人ID" prop="createUserId">
        <el-input
          v-model="queryParams.createUserId"
          placeholder="请输入录入人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hachimi_admin:record:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hachimi_admin:record:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hachimi_admin:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hachimi_admin:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="健康记录ID" align="center" prop="healthId" />
      <el-table-column label="关联宠物" align="center" prop="petId">
        <template slot-scope="scope">
          <span>{{ scope.row.petId }}({{ scope.row.petName || '未知' }})</span>
        </template>
      </el-table-column>
      <el-table-column label="记录类型" align="center" prop="recordType">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.recordType === 0 ? 'success' : scope.row.recordType === 1 ? 'warning' : 'danger'"
          >
            {{ scope.row.recordType === 0 ? '疫苗' : scope.row.recordType === 1 ? '体检' : '疾病治疗' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="记录内容" align="center" prop="content" />
      <el-table-column label="医生姓名" align="center" prop="doctor" />
      <el-table-column label="医院" align="center" prop="hospital" />
      <el-table-column label="记录发生时间" align="center" prop="recordTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="录入人" align="center" prop="createUserId">
        <template slot-scope="scope">
          <span>{{ scope.row.createUserId }}({{ scope.row.createUserName || '未知' }})</span>
        </template>
      </el-table-column>
      <el-table-column label="是否隐藏" align="center" prop="hidden">
        <template slot-scope="scope">
          <el-tag :type="scope.row.hidden === 0 ? 'success' : 'info'">
            {{ scope.row.hidden === 0 ? '不隐藏' : '隐藏' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hachimi_admin:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hachimi_admin:record:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改宠物健康档案对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联宠物ID" prop="petId">
          <el-input v-model="form.petId" placeholder="请输入关联宠物ID" />
        </el-form-item>
        <el-form-item label="记录内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="医生姓名" prop="doctor">
          <el-input v-model="form.doctor" placeholder="请输入医生姓名" />
        </el-form-item>
        <el-form-item label="医院" prop="hospital">
          <el-input v-model="form.hospital" placeholder="请输入医院" />
        </el-form-item>
        <el-form-item label="记录发生时间" prop="recordTime">
          <el-date-picker clearable
            v-model="form.recordTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录发生时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="录入人ID" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入录入人ID" />
        </el-form-item>
        <el-form-item label="是否隐藏" prop="hidden">
          <el-radio-group v-model="form.hidden">
            <el-radio :label="0">未隐藏</el-radio>
            <el-radio :label="1">已隐藏</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="记录类型" prop="recordType">
          <el-select v-model="form.recordType" placeholder="请选择记录类型">
            <el-option label="疫苗" :value="0"></el-option>
            <el-option label="体检" :value="1"></el-option>
            <el-option label="疾病治疗" :value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-divider content-position="center">宠物信息信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddPet">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeletePet">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="petList" :row-class-name="rowPetIndex" @selection-change="handlePetSelectionChange" ref="pet">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="宠物昵称" prop="petName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.petName" placeholder="请输入宠物昵称" />
            </template>
          </el-table-column>
          <el-table-column label="年龄" prop="age" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.age" placeholder="请输入年龄" />
            </template>
          </el-table-column>
          <el-table-column label="性别：0-公；1-母" prop="gender" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.gender" placeholder="请输入性别：0-公；1-母" />
            </template>
          </el-table-column>
          <el-table-column label="健康状况简述" prop="healthStatus" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.healthStatus" placeholder="请选择健康状况简述">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="性格描述" prop="personality" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.personality" placeholder="请输入性格描述" />
            </template>
          </el-table-column>
          <el-table-column label="救助时间" prop="rescueTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.rescueTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择救助时间" />
            </template>
          </el-table-column>
          <el-table-column label="救助地点" prop="rescuePlace" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.rescuePlace" placeholder="请输入救助地点" />
            </template>
          </el-table-column>
          <el-table-column label="状态：0-待领养；1-已领养；2-救助中；3-已救助" prop="status" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.status" placeholder="请选择状态：0-待领养；1-已领养；2-救助中；3-已救助">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="是否隐藏：0-不隐藏；1-隐藏" prop="hidden" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.hidden" placeholder="请输入是否隐藏：0-不隐藏；1-隐藏" />
            </template>
          </el-table-column>
          <el-table-column label="关联品种ID" prop="breedId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.breedId" placeholder="请输入关联品种ID" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/hachimi_admin/record"

export default {
  name: "Record",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedPet: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 宠物健康档案表格数据
      recordList: [],
      // 宠物信息表格数据
      petList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordType: null,
        content: null,
        doctor: null,
        hospital: null,
        createUserId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        recordType: [
          { required: true, message: "记录类型：0-疫苗；1-体检；2-疾病治疗不能为空", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询宠物健康档案列表 */
    getList() {
      this.loading = true
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        healthId: null,
        petId: null,
        recordType: null,
        content: null,
        doctor: null,
        hospital: null,
        recordTime: null,
        createUserId: null,
        hidden: null,
        createTime: null
      }
      this.petList = []
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.healthId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加宠物健康档案"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const healthId = row.healthId || this.ids
      getRecord(healthId).then(response => {
        this.form = response.data
        this.petList = response.data.petList
        this.open = true
        this.title = "修改宠物健康档案"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.petList = this.petList
          if (this.form.healthId != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addRecord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const healthIds = row.healthId || this.ids
      this.$modal.confirm('是否确认删除宠物健康档案编号为"' + healthIds + '"的数据项？').then(function() {
        return delRecord(healthIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
	/** 宠物信息序号 */
    rowPetIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 宠物信息添加按钮操作 */
    handleAddPet() {
      let obj = {}
      obj.petName = ""
      obj.age = ""
      obj.gender = ""
      obj.healthStatus = ""
      obj.personality = ""
      obj.rescueTime = ""
      obj.rescuePlace = ""
      obj.status = ""
      obj.hidden = ""
      obj.breedId = ""
      this.petList.push(obj)
    },
    /** 宠物信息删除按钮操作 */
    handleDeletePet() {
      if (this.checkedPet.length == 0) {
        this.$modal.msgError("请先选择要删除的宠物信息数据")
      } else {
        const petList = this.petList
        const checkedPet = this.checkedPet
        this.petList = petList.filter(function(item) {
          return checkedPet.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handlePetSelectionChange(selection) {
      this.checkedPet = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hachimi_admin/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
