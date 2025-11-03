<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="宠物昵称" prop="petName">
        <el-input
          v-model="queryParams.petName"
          placeholder="请输入宠物昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input
          v-model="queryParams.age"
          placeholder="请输入年龄"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select v-model="queryParams.gender" placeholder="请选择性别" clearable>
          <el-option label="公" :value="0"></el-option>
          <el-option label="母" :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="救助地点" prop="rescuePlace">
        <el-input
          v-model="queryParams.rescuePlace"
          placeholder="请输入救助地点"
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
          v-hasPermi="['hachimi_admin:pet:add']"
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
          v-hasPermi="['hachimi_admin:pet:edit']"
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
          v-hasPermi="['hachimi_admin:pet:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hachimi_admin:pet:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="petList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="宠物唯一ID" align="center" prop="petId" />
      <el-table-column label="宠物昵称" align="center" prop="petName" />
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="性别" align="center" prop="gender">
        <template slot-scope="scope">
          <el-tag :type="scope.row.gender === 0 ? 'primary' : 'danger'">
            {{ scope.row.gender === 0 ? '公' : '母' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="健康状况简述" align="center" prop="healthStatus" />
      <el-table-column label="性格描述" align="center" prop="personality" />
      <el-table-column label="救助时间" align="center" prop="rescueTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rescueTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="救助地点" align="center" prop="rescuePlace" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.status === 0 ? 'warning' :
             scope.row.status === 1 ? 'success' :
             scope.row.status === 2 ? 'danger' : 'info'"
          >
            {{ scope.row.status === 0 ? '待领养' :
            scope.row.status === 1 ? '已领养' :
              scope.row.status === 2 ? '救助中' : '已救助' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="是否隐藏" align="center" prop="hidden">
        <template slot-scope="scope">
          <el-tag :type="scope.row.hidden === 0 ? 'success' : 'info'">
            {{ scope.row.hidden === 0 ? '不隐藏' : '隐藏' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="关联品种" align="center" prop="breedId">
        <template slot-scope="scope">
          <span>{{ scope.row.breedId }}({{ scope.row.breedName || '未知' }})</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hachimi_admin:pet:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hachimi_admin:pet:remove']"
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

    <!-- 添加或修改宠物信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="宠物昵称" prop="petName">
          <el-input v-model="form.petName" placeholder="请输入宠物昵称" />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="form.gender">
            <el-radio :label="0">公</el-radio>
            <el-radio :label="1">母</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="健康状况" prop="healthStatus">
          <el-input v-model="form.healthStatus" placeholder="请输入健康状况简述" />
        </el-form-item>
        <el-form-item label="性格描述" prop="personality">
          <el-input v-model="form.personality" placeholder="请输入性格描述" />
        </el-form-item>
        <el-form-item label="救助时间" prop="rescueTime">
          <el-date-picker clearable
            v-model="form.rescueTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择救助时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="救助地点" prop="rescuePlace">
          <el-input v-model="form.rescuePlace" placeholder="请输入救助地点" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option label="待领养" :value="0"></el-option>
            <el-option label="已领养" :value="1"></el-option>
            <el-option label="救助中" :value="2"></el-option>
            <el-option label="已救助（非领养）" :value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否隐藏" prop="hidden">
          <el-radio-group v-model="form.hidden">
            <el-radio :label="0">不隐藏</el-radio>
            <el-radio :label="1">隐藏</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="关联品种ID" prop="breedId">
          <el-input v-model="form.breedId" placeholder="请输入关联品种ID" />
        </el-form-item>
        <el-divider content-position="center">宠物品种信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddBreed">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteBreed">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="breedList" :row-class-name="rowBreedIndex" @selection-change="handleBreedSelectionChange" ref="breed">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="品种名称" prop="breedName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.breedName" placeholder="请输入品种名称" />
            </template>
          </el-table-column>
          <el-table-column label="所属物种" prop="species" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.species" placeholder="请输入所属物种" />
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
import { listPet, getPet, delPet, addPet, updatePet } from "@/api/hachimi_admin/pet"

export default {
  name: "Pet",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedBreed: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 宠物信息表格数据
      petList: [],
      // 宠物品种表格数据
      breedList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        petName: null,
        age: null,
        gender: null,
        rescuePlace: null,
        status: null,
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
    /** 查询宠物信息列表 */
    getList() {
      this.loading = true
      listPet(this.queryParams).then(response => {
        this.petList = response.rows
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
        petId: null,
        petName: null,
        age: null,
        gender: null,
        healthStatus: null,
        personality: null,
        rescueTime: null,
        rescuePlace: null,
        status: null,
        hidden: null,
        createTime: null,
        updateTime: null,
        breedId: null
      }
      this.breedList = []
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
      this.ids = selection.map(item => item.petId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加宠物信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const petId = row.petId || this.ids
      getPet(petId).then(response => {
        this.form = response.data
        this.breedList = response.data.breedList
        this.open = true
        this.title = "修改宠物信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.breedList = this.breedList
          if (this.form.petId != null) {
            updatePet(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addPet(this.form).then(response => {
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
      const petIds = row.petId || this.ids
      this.$modal.confirm('是否确认删除宠物信息编号为"' + petIds + '"的数据项？').then(function() {
        return delPet(petIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
	/** 宠物品种序号 */
    rowBreedIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 宠物品种添加按钮操作 */
    handleAddBreed() {
      let obj = {}
      obj.breedName = ""
      obj.species = ""
      obj.hidden = ""
      this.breedList.push(obj)
    },
    /** 宠物品种删除按钮操作 */
    handleDeleteBreed() {
      if (this.checkedBreed.length == 0) {
        this.$modal.msgError("请先选择要删除的宠物品种数据")
      } else {
        const breedList = this.breedList
        const checkedBreed = this.checkedBreed
        this.breedList = breedList.filter(function(item) {
          return checkedBreed.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleBreedSelectionChange(selection) {
      this.checkedBreed = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hachimi_admin/pet/export', {
        ...this.queryParams
      }, `pet_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
