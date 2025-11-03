<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="案例标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入案例标题"
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
          v-hasPermi="['hachimi_admin:rescue_case:add']"
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
          v-hasPermi="['hachimi_admin:rescue_case:edit']"
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
          v-hasPermi="['hachimi_admin:rescue_case:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hachimi_admin:rescue_case:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="rescue_caseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案例ID" align="center" prop="caseId" />
      <el-table-column label="案例标题" align="center" prop="title" />
      <el-table-column label="关联宠物" align="center" prop="petId">
        <template slot-scope="scope">
          <span>{{ scope.row.petId }}（{{ scope.row.petName || '未知' }}）</span>
        </template>
      </el-table-column>
      <el-table-column label="救助过程详情" align="center" prop="content" :show-overflow-tooltip="true" />
      <el-table-column label="救助花费" align="center" prop="rescueCost" />
      <el-table-column label="案例状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 0 ? 'warning' : 'success'">
            {{ scope.row.status === 0 ? '救助中' : '已完成' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布人" align="center" prop="createUserId">
        <template slot-scope="scope">
          <span>{{ scope.row.createUserId }}（{{ scope.row.createUserName || '未知' }}）</span>
        </template>
      </el-table-column>
      <el-table-column label="浏览量" align="center" prop="viewCount" />
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
            v-hasPermi="['hachimi_admin:rescue_case:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hachimi_admin:rescue_case:remove']"
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

    <!-- 添加或修改救助案例信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="案例标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入案例标题" />
        </el-form-item>
        <el-form-item label="关联宠物ID" prop="petId">
          <el-input v-model="form.petId" placeholder="请输入关联宠物ID" />
        </el-form-item>
        <el-form-item label="救助过程详情">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="救助花费" prop="rescueCost">
          <el-input v-model="form.rescueCost" placeholder="请输入救助花费" />
        </el-form-item>
        <el-form-item label="案例状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :label="0">救助中</el-radio>
            <el-radio :label="1">已完成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="发布时间" prop="publishTime">
          <el-date-picker clearable
                          v-model="form.publishTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发布人ID" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入发布人ID" />
        </el-form-item>
        <el-form-item label="浏览量" prop="viewCount">
          <el-input v-model="form.viewCount" placeholder="请输入浏览量" />
        </el-form-item>
        <el-form-item label="是否隐藏" prop="hidden">
          <el-radio-group v-model="form.hidden">
            <el-radio :label="0">不隐藏</el-radio>
            <el-radio :label="1">隐藏</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-divider content-position="center">案例图片信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCaseImage">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCaseImage">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="caseImageList" :row-class-name="rowCaseImageIndex" @selection-change="handleCaseImageSelectionChange" ref="caseImage">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="图片排序" prop="sort" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.sort" placeholder="请输入图片排序" />
            </template>
          </el-table-column>
          <el-table-column label="是否隐藏" prop="hidden" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.hidden" placeholder="请选择">
                <el-option label="不隐藏" :value="0"></el-option>
                <el-option label="隐藏" :value="1"></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="上传时间" prop="uploadTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.uploadTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择上传时间" />
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
import { listRescue_case, getRescue_case, delRescue_case, addRescue_case, updateRescue_case } from "@/api/hachimi_admin/rescue_case"

export default {
  name: "Rescue_case",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCaseImage: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 救助案例信息表格数据
      rescue_caseList: [],
      // 案例图片表格数据
      caseImageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
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
    /** 查询救助案例信息列表 */
    getList() {
      this.loading = true
      listRescue_case(this.queryParams).then(response => {
        this.rescue_caseList = response.rows
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
        caseId: null,
        title: null,
        petId: null,
        content: null,
        rescueCost: null,
        status: 0,
        publishTime: null,
        createUserId: null,
        viewCount: null,
        hidden: 0
      }
      this.caseImageList = []
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
      this.ids = selection.map(item => item.caseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加救助案例信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const caseId = row.caseId || this.ids
      getRescue_case(caseId).then(response => {
        this.form = response.data
        this.caseImageList = response.data.caseImageList
        this.open = true
        this.title = "修改救助案例信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.caseImageList = this.caseImageList
          if (this.form.caseId != null) {
            updateRescue_case(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addRescue_case(this.form).then(response => {
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
      const caseIds = row.caseId || this.ids
      this.$modal.confirm('是否确认删除救助案例信息编号为"' + caseIds + '"的数据项？').then(function() {
        return delRescue_case(caseIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 案例图片序号 */
    rowCaseImageIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 案例图片添加按钮操作 */
    handleAddCaseImage() {
      let obj = {}
      obj.imageUrl = ""
      obj.sort = ""
      obj.hidden = 0
      obj.uploadTime = ""
      this.caseImageList.push(obj)
    },
    /** 案例图片删除按钮操作 */
    handleDeleteCaseImage() {
      if (this.checkedCaseImage.length == 0) {
        this.$modal.msgError("请先选择要删除的案例图片数据")
      } else {
        const caseImageList = this.caseImageList
        const checkedCaseImage = this.checkedCaseImage
        this.caseImageList = caseImageList.filter(function(item) {
          return checkedCaseImage.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleCaseImageSelectionChange(selection) {
      this.checkedCaseImage = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hachimi_admin/rescue_case/export', {
        ...this.queryParams
      }, `rescue_case_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
