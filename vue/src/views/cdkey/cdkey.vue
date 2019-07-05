<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate" v-if="hasPerm('cdkey:add')">添加
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="cdkey" label="CdKey" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="创建时间" width="170">
        <template slot-scope="scope">
          <span>{{scope.row.createdAt}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="管理" width="200" v-if="hasPerm('cdkey:update')">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)">二维码</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempCdKey" label-position="left" label-width="60px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="备注">
          <el-input type="text" v-model="tempCdKey.remark">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createCdKey">创 建</el-button>
        <el-button type="primary" v-else @click="updateCdKey">修 改</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="qrcode" :visible.sync="qrcodeDialogFormVisible">
      <el-form class="small-space" :model="tempCdKey" label-position="left" label-width="60px"
               style='width: 300px; margin-left:50px;'>
      </el-form>
      <div class="app-container"  align="center">
        <img :src="tempCdKey.qrcode" style="width: 300px;"/>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="qrcodeDialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script>
  export default {
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          name: ''
        },
        dialogStatus: 'create',
        dialogFormVisible: false,
        qrcodeDialogFormVisible: false,
        textMap: {
          update: '二维码',
          create: '创建CdKey'
        },
        tempCdKey: {
          id: "",
          remark: "",
          qrcode:""
        }
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('cdkey:list')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/cdkey/list",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },
      handleSizeChange(val) {
        //改变每页数量
        this.listQuery.pageRow = val
        this.handleFilter();
      },
      handleCurrentChange(val) {
        //改变页码
        this.listQuery.pageNum = val
        this.getList();
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      showCreate() {
        //显示新增对话框
        this.tempCdKey.remark = "";
        this.dialogStatus = "create"
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        //显示修改对话框
        this.tempCdKey.id = this.list[$index].id;
        this.dialogStatus = "update"
        this.qrcodeDialogFormVisible = true
        this.tempCdKey.qrcode = "api/cdkey/qrcode?id="+this.tempCdKey.id;
        this.listLoading = false;

      },
      createCdKey() {
        //保存新CdKey
        this.api({
          url: "/cdkey/add",
          method: "post",
          data: this.tempCdKey
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateCdKey() {
        //修改CdKey
        this.api({
          url: "/cdkey/update",
          method: "post",
          data: this.tempCdKey
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
    }
  }
</script>
