<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关联案例ID" prop="caseId">
        <el-input
          v-model="queryParams.caseId"
          placeholder="请输入关联案例ID"
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
          v-hasPermi="['hachimiSys:caseimage:add']"
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
          v-hasPermi="['hachimiSys:caseimage:edit']"
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
          v-hasPermi="['hachimiSys:caseimage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hachimiSys:caseimage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="caseimageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案例图片ID" align="center" prop="caseImageId" />
      <el-table-column label="关联案例ID" align="center" prop="caseId" />
      <el-table-column label="图片URL地址" align="center" prop="imageUrl" />
      <el-table-column label="图片排序" align="center" prop="sort" />
      <el-table-column label="是否隐藏：0-不隐藏；1-隐藏" align="center" prop="hidden" />
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hachimiSys:caseimage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hachimiSys:caseimage:remove']"
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

    <!-- 添加或修改案例图片信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联案例ID" prop="caseId">
          <el-input v-model="form.caseId" placeholder="请输入关联案例ID" />
        </el-form-item>
        <el-form-item label="图片URL地址" prop="imageUrl">
          <el-input v-model="form.imageUrl" placeholder="请输入图片URL地址" />
        </el-form-item>
        <el-form-item label="图片排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入图片排序" />
        </el-form-item>
        <el-form-item label="是否隐藏：0-不隐藏；1-隐藏" prop="hidden">
          <el-input v-model="form.hidden" placeholder="请输入是否隐藏：0-不隐藏；1-隐藏" />
        </el-form-item>
        <el-form-item label="上传时间" prop="uploadTime">
          <el-date-picker clearable
            v-model="form.uploadTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上传时间">
          </el-date-picker>
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
import { listCaseimage, getCaseimage, delCaseimage, addCaseimage, updateCaseimage } from "@/api/hachimiSys/caseimage"

export default {
  name: "Caseimage",
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
      // 案例图片信息表格数据
      caseimageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        caseId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        caseId: [
          { required: true, message: "关联案例ID不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询案例图片信息列表 */
    getList() {
      this.loading = true
      listCaseimage(this.queryParams).then(response => {
        this.caseimageList = response.rows
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
        caseImageId: null,
        caseId: null,
        imageUrl: null,
        sort: null,
        hidden: null,
        uploadTime: null
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
      this.ids = selection.map(item => item.caseImageId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加案例图片信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const caseImageId = row.caseImageId || this.ids
      getCaseimage(caseImageId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改案例图片信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.caseImageId != null) {
            updateCaseimage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCaseimage(this.form).then(response => {
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
      const caseImageIds = row.caseImageId || this.ids
      this.$modal.confirm('是否确认删除案例图片信息编号为"' + caseImageIds + '"的数据项？').then(function() {
        return delCaseimage(caseImageIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hachimiSys/caseimage/export', {
        ...this.queryParams
      }, `caseimage_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
