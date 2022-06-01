<template>

<div class="app-container">
<!--表格-->
<el-table data="list" border stripe>
<el-table-column type="index" width="50" />
      <el-table-column prop="borrowAmount" label="借款额度" />
      <el-table-column prop="integralStart" label="积分区间开始" />
      <el-table-column prop="integralEnd" label="积分区间结束" />
<el-table-column label="操作"/>
<template slot-scope="scope">
   <router-link
            :to="'/core/integral-grade/edit/' + scope.row.id"
            style="margin-right:5px;"
          >
          <el-button type="primary" size="mini" icon="el-icon-edit">
            修改
          </el-button>
   </router-link>

</template>

</el-table>
</div>
</template>>
<script>
import integralGradeApi from '@/api/core/integral-grade'
import integralGrade from '@/api/core/integral-grade'
export default {

data (){

return {

saveBtnDisabled:false, //是否禁用保存按钮，防止表单重复提交
integralGrade:{} //积分等级对象
}
},

created () {

//当路由中存在id属性的时候，就是回显表单，需要调用回显数据的接口

if(this.$route.params.id){

  this.fetchById(this.$route.params.id)
}
},
methods: {

  fetchById(id){

    integralGradeApi.getById(id).then(response =>{

      this.integralGrade = response.data.record
    })
  },

// 保存或更新
saveOrUpdate(){

//禁用保存按钮
this.saveBtnDisabled =true

if(this.integralGrade.id){

  //调用新增
  this.saveData()
}else{

this.updateData()

}

//调用更新
},

saveData() {
      integralGradeApi.save(this.integralGrade).then(response => {
        // this.$message({
        //   type: 'success',
        //   message: response.message
        // })
        this.$message.success(response.message)
        this.$router.push('/core/integral-grade/list')
      })
    },

    updateData() {
      integralGradeApi.updateById(this.integralGrade).then(response => {
        this.$message.success(response.message)
        this.$router.push('/core/integral-grade/list')
      })
    }
  }
}
</script>
