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
          v-hasPermi="['hachimiSys:apply:add']"
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
          v-hasPermi="['hachimiSys:apply:edit']"
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
          v-hasPermi="['hachimiSys:apply:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hachimiSys:apply:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请唯一ID" align="center" prop="applyId" />
      <el-table-column label="申请人ID" align="center" prop="userId" />
      <el-table-column label="申请领养的宠物ID" align="center" prop="petId" />
      <el-table-column label="领养理由" align="center" prop="applyReason" />
      <el-table-column label="申请提交时间" align="center" prop="applyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核人ID" align="center" prop="auditUserId" />
      <el-table-column label="审核状态：0-待审核；1-通过；2-拒绝" align="center" prop="auditStatus" />
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
      <el-table-column label="是否隐藏：0-不隐藏；1-隐藏" align="center" prop="hidden" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hachimiSys:apply:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hachimiSys:apply:remove']"
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

    <!-- 添加或修改领养申请信息对话框 -->
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
        <el-form-item label="是否隐藏：0-不隐藏；1-隐藏" prop="hidden">
          <el-input v-model="form.hidden" placeholder="请输入是否隐藏：0-不隐藏；1-隐藏" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listApply, getApply, delApply, addApply, updateApply } from "@/api/hachimiSys/apply"

export default {
  name: "Apply",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 领养申请信息表格数据
      applyList: [],
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
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        petId: [
          { required: true, message: "申请领养的宠物ID不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询领养申请信息列表 */
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
      this.title = "添加领养申请信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const applyId = row.applyId || this.ids
      getApply(applyId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改领养申请信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
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
      this.$modal.confirm('是否确认删除领养申请信息编号为"' + applyIds + '"的数据项？').then(function() {
        return delApply(applyIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hachimiSys/apply/export', {
        ...this.queryParams
      }, `apply_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
