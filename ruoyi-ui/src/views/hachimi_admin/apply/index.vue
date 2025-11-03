<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请人ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入申请人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请领养的宠物ID" prop="petId">
        <el-input
          v-model="queryParams.petId"
          placeholder="请输入申请领养的宠物ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核人ID" prop="auditUserId">
        <el-input
          v-model="queryParams.auditUserId"
          placeholder="请输入审核人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- 在查询表单中添加审核状态选择 -->
      <el-form-item label="审核状态" prop="auditStatus">
        <el-select v-model="queryParams.auditStatus" placeholder="请选择审核状态" clearable>
          <el-option label="待审核" :value="0" />
          <el-option label="通过" :value="1" />
          <el-option label="拒绝" :value="2" />
        </el-select>
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
          v-hasPermi="['hachimi_admin:apply:add']"
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
          v-hasPermi="['hachimi_admin:apply:edit']"
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
          v-hasPermi="['hachimi_admin:apply:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hachimi_admin:apply:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请唯一ID" align="center" prop="applyId" />
      <el-table-column label="申请人" align="center" prop="userId" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.userId }}({{ scope.row.username }})</span>
        </template>
      </el-table-column>
      <el-table-column label="申请领养的宠物" align="center" prop="petId" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.petId }}({{ scope.row.petName }})</span>
        </template>
      </el-table-column>
      <el-table-column label="领养理由" align="center" prop="applyReason" />
      <el-table-column label="申请提交时间" align="center" prop="applyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" prop="auditUserId" width="150">
        <template slot-scope="scope">
          <span v-if="scope.row.auditUserId">{{ scope.row.auditUserId }}({{ scope.row.auditUsername }})</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatus" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.auditStatus === 0" type="warning">待审核</el-tag>
          <el-tag v-else-if="scope.row.auditStatus === 1" type="success">通过</el-tag>
          <el-tag v-else-if="scope.row.auditStatus === 2" type="danger">拒绝</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核备注" align="center" prop="auditRemark" />
      <el-table-column label="审核时间" align="center" prop="auditTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.auditTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际领养时间" align="center" prop="adoptTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.adoptTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否隐藏" align="center" prop="hidden">
        <template slot-scope="scope">
          <el-tag :type="scope.row.hidden === 0 ? 'success' : 'danger'">
            {{ scope.row.hidden === 0 ? '未隐藏' : '已隐藏' }}
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
            v-hasPermi="['hachimi_admin:apply:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hachimi_admin:apply:remove']"
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

    <!-- 添加或修改领养申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="申请人ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入申请人ID" />
        </el-form-item>
        <el-form-item label="申请领养的宠物ID" prop="petId">
          <el-input v-model="form.petId" placeholder="请输入申请领养的宠物ID" />
        </el-form-item>
        <el-form-item label="领养理由" prop="applyReason">
          <el-input v-model="form.applyReason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="申请提交时间" prop="applyTime">
          <el-date-picker clearable
            v-model="form.applyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请提交时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核人ID" prop="auditUserId">
          <el-input v-model="form.auditUserId" placeholder="请输入审核人ID" />
        </el-form-item>
        <!-- 在表单中添加审核状态（在审核人ID后面） -->
        <el-form-item label="审核状态" prop="auditStatus">
          <el-radio-group v-model="form.auditStatus">
            <el-radio :label="0">待审核</el-radio>
            <el-radio :label="1">通过</el-radio>
            <el-radio :label="2">拒绝</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核备注" prop="auditRemark">
          <el-input v-model="form.auditRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="审核时间" prop="auditTime">
          <el-date-picker clearable
            v-model="form.auditTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择审核时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际领养时间" prop="adoptTime">
          <el-date-picker clearable
            v-model="form.adoptTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择实际领养时间">
          </el-date-picker>
        </el-form-item>
        <!-- 将表单中的是否隐藏输入框替换为 -->
        <el-form-item label="是否隐藏" prop="hidden">
          <el-radio-group v-model="form.hidden">
            <el-radio :label="0">未隐藏</el-radio>
            <el-radio :label="1">已隐藏</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-divider content-position="center">领养后反馈信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddAdoptFeedback">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteAdoptFeedback">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="adoptFeedbackList" :row-class-name="rowAdoptFeedbackIndex" @selection-change="handleAdoptFeedbackSelectionChange" ref="adoptFeedback">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="反馈发布时间" prop="feedbackTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.feedbackTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择反馈发布时间" />
            </template>
          </el-table-column>
          <el-table-column label="点赞数" prop="likeCount" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.likeCount" placeholder="请输入点赞数" />
            </template>
          </el-table-column>
          <!-- 在子表中将是否隐藏改为下拉选择 -->
          <el-table-column label="是否隐藏" prop="hidden" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.hidden" placeholder="请选择">
                <el-option label="未隐藏" :value="0" />
                <el-option label="已隐藏" :value="1" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="反馈人" prop="userId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userId" placeholder="请输入反馈人" />
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
import { listApply, getApply, delApply, addApply, updateApply } from "@/api/hachimi_admin/apply"

export default {
  name: "Apply",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedAdoptFeedback: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 领养申请表格数据
      applyList: [],
      // 领养后反馈表格数据
      adoptFeedbackList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        petId: null,
        auditUserId: null,
        auditStatus: null,
        auditRemark: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询领养申请列表 */
    getList() {
      this.loading = true
      listApply(this.queryParams).then(response => {
        this.applyList = response.rows
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
        applyId: null,
        userId: null,
        petId: null,
        applyReason: null,
        applyTime: null,
        auditUserId: null,
        auditStatus: null,
        auditRemark: null,
        auditTime: null,
        adoptTime: null,
        hidden: null
      }
      this.adoptFeedbackList = []
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
      this.ids = selection.map(item => item.applyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加领养申请"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const applyId = row.applyId || this.ids
      getApply(applyId).then(response => {
        this.form = response.data
        this.adoptFeedbackList = response.data.adoptFeedbackList
        this.open = true
        this.title = "修改领养申请"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.adoptFeedbackList = this.adoptFeedbackList
          if (this.form.applyId != null) {
            updateApply(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addApply(this.form).then(response => {
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
      const applyIds = row.applyId || this.ids
      this.$modal.confirm('是否确认删除领养申请编号为"' + applyIds + '"的数据项？').then(function() {
        return delApply(applyIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
	/** 领养后反馈序号 */
    rowAdoptFeedbackIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 领养后反馈添加按钮操作 */
    handleAddAdoptFeedback() {
      let obj = {}
      obj.content = ""
      obj.feedbackImage = ""
      obj.feedbackTime = ""
      obj.likeCount = ""
      obj.hidden = ""
      obj.userId = ""
      this.adoptFeedbackList.push(obj)
    },
    /** 领养后反馈删除按钮操作 */
    handleDeleteAdoptFeedback() {
      if (this.checkedAdoptFeedback.length == 0) {
        this.$modal.msgError("请先选择要删除的领养后反馈数据")
      } else {
        const adoptFeedbackList = this.adoptFeedbackList
        const checkedAdoptFeedback = this.checkedAdoptFeedback
        this.adoptFeedbackList = adoptFeedbackList.filter(function(item) {
          return checkedAdoptFeedback.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleAdoptFeedbackSelectionChange(selection) {
      this.checkedAdoptFeedback = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hachimi_admin/apply/export', {
        ...this.queryParams
      }, `apply_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
