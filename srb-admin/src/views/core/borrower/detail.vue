<template>
<div class="app-container">
  <el-form label-width="100" class="form-table">
    <el-row>
      <el-col :span="6">
        <el-form-item label="状态">
          {{borrower.status}}
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</div>

</template>
<script>
// 引入组件
import  borrowerApi from '@/api/core/borrower'

export default {
  data(){

    return{
      borrower:{}, // 借款人信息
      saveBtnDisabled:false,//防止重复提交
      approvalForm:{
        //审批表单
        borrowerId:0,
        status:2,
        content:'',
        infoIntegral:30,
        isIdCardOk:false,
        isHouseOk:false,
        isCarOk:false
      }
    }
  },
  created(){

    if (this.$route.param.id){
      this.fetchDataById()
    }
  },

  methods: {

fetchDataById(){

  borrowerApi.show(this.$route.param.id).then(response =>{

    this.borrower = response.data.borrowerDetailVo
  })
},

back(){

this.$router.push('/core/borrower/list')

},

approvalSubmit(){

  this.saveBtnDisabled = true
  this.approvalForm.borrowerId = this.$route.params.id


}


  }


}

</script>
