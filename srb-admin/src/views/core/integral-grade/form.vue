<template>
<div class="app-container">
<!--输入表单-->
<el-form label-width="120px">
  <el-form-item label="借款额度">
<el-input-number v-model="integralGrade.borrowAmount" :min="0"/>
  </el-form-item>
</el-form>
</div>
</template>

<script>
//引入api模块
import integralGradeApi from "@/api/core/integral-grade";
export default{

data(){

return {

saveBtnDisabled:false,//是否禁用保存按钮，防止表单重复提交

integralGrade:{} //积分等级对象

}
},
created(){

  //当前路由中存在id属性的时候，回显表单，需要调用回显数据的接口
  
  if(this.$route.params.id){

    this.fetchById(this.$route.params.id)
  }

},
methods:{

  fetchById(id){

    integralGradeApi.getById(id).then(response =>{
    
    this.integralGrade = response.data.record

    })
  },
  


}

}
</script>
