<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关联领养申请ID" prop="applyId">
        <el-input
          v-model="queryParams.applyId"
          placeholder="请输入关联领养申请ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="反馈发布时间" prop="feedbackTime">
        <el-date-picker clearable
          v-model="queryParams.feedbackTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择反馈发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="点赞数" prop="likeCount">
        <el-input
          v-model="queryParams.likeCount"
          placeholder="请输入点赞数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- 将查询表单中的是否隐藏替换为 -->
      <el-form-item label="是否隐藏" prop="hidden">
        <el-select v-model="queryParams.hidden" placeholder="请选择是否隐藏" clearable>
          <el-option label="未隐藏" :value="0" />
          <el-option label="已隐藏" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="反馈人" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入反馈人"
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
          v-hasPermi="['hachimi_admin:feedback:add']"
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
          v-hasPermi="['hachimi_admin:feedback:edit']"
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
          v-hasPermi="['hachimi_admin:feedback:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hachimi_admin:feedback:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="feedbackList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="反馈ID" align="center" prop="feedbackId" />
      <el-table-column label="关联领养申请ID" align="center" prop="applyId" />
      <el-table-column label="反馈内容" align="center" prop="content" />
      <el-table-column label="反馈图片URL" align="center" prop="feedbackImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.feedbackImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="反馈发布时间" align="center" prop="feedbackTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.feedbackTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="点赞数" align="center" prop="likeCount" />
      <!-- 将原来的是否隐藏列替换为 -->
      <el-table-column label="是否隐藏" align="center" prop="hidden">
        <template slot-scope="scope">
          <el-tag :type="scope.row.hidden === 0 ? 'success' : 'danger'">
            {{ scope.row.hidden === 0 ? '未隐藏' : '已隐藏' }}
          </el-tag>
        </template>
      </el-table-column>
      <!-- 将原来的反馈人列替换为 -->
      <el-table-column label="反馈人" align="center" prop="userId" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.userId }}({{ scope.row.username }})</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hachimi_admin:feedback:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hachimi_admin:feedback:remove']"
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

    <!-- 添加或修改领养后反馈对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联领养申请ID" prop="applyId">
          <el-input v-model="form.applyId" placeholder="请输入关联领养申请ID" />
        </el-form-item>
        <el-form-item label="反馈内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="反馈图片URL" prop="feedbackImage">
          <image-upload v-model="form.feedbackImage"/>
        </el-form-item>
        <el-form-item label="反馈发布时间" prop="feedbackTime">
          <el-date-picker clearable
            v-model="form.feedbackTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择反馈发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="点赞数" prop="likeCount">
          <el-input v-model="form.likeCount" placeholder="请输入点赞数" />
        </el-form-item>
        <!-- 将表单中的是否隐藏输入框替换为 -->
        <el-form-item label="是否隐藏" prop="hidden">
          <el-radio-group v-model="form.hidden">
            <el-radio :label="0">未隐藏</el-radio>
            <el-radio :label="1">已隐藏</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="反馈人" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入反馈人" />
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
import { listFeedback, getFeedback, delFeedback, addFeedback, updateFeedback } from "@/api/hachimi_admin/feedback"

export default {
  name: "Feedback",
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
      // 领养后反馈表格数据
      feedbackList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        applyId: null,
        content: null,
        feedbackImage: null,
        feedbackTime: null,
        likeCount: null,
        hidden: null,
        userId: null
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
    /** 查询领养后反馈列表 */
    getList() {
      this.loading = true
      listFeedback(this.queryParams).then(response => {
        this.feedbackList = response.rows
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
        feedbackId: null,
        applyId: null,
        content: null,
        feedbackImage: null,
        feedbackTime: null,
        likeCount: null,
        hidden: null,
        userId: null
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
      this.ids = selection.map(item => item.feedbackId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加领养后反馈"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const feedbackId = row.feedbackId || this.ids
      getFeedback(feedbackId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改领养后反馈"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.feedbackId != null) {
            updateFeedback(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addFeedback(this.form).then(response => {
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
      const feedbackIds = row.feedbackId || this.ids
      this.$modal.confirm('是否确认删除领养后反馈编号为"' + feedbackIds + '"的数据项？').then(function() {
        return delFeedback(feedbackIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hachimi_admin/feedback/export', {
        ...this.queryParams
      }, `feedback_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
