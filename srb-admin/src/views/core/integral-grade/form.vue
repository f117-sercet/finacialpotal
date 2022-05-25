<template>
  
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
