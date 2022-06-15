<template>
    
</template>

<script>
export default {

data(){

    return {

        active:null,//步骤
        borrowInfoStatus:null,//审批状态
        //借款申请
        borrowInfo:{
            borrowYearRate:'12',
        },
            borrowAmount:0, //借款额度
            submitBtnDisabled:false,
            returnMethodList:[],//还款方式列表
            monneyUserList:[],//资金用途列表

        }
    },
    watch:{
      
      'borrowInfo.amount'(value) {

        if(value > this.borrowAmount) {

            let _this = this
            this.$alert('您的借款余额不足',{
        
         type:'error',
         callback(){

            _this.borrowInfo.borrowAmount = _this.borrowAmount
         },  
            })
        }
      },

    },

    created(){

       this.getBorrowInfoStatus()

    },
   
   methods:{

    getBorrowInfoStatus(){

        this.$axios
        .$get('/api/core/borrowInfo/getBorrowInfoStatus')
        .then((response) =>{

            this.borrowInfoStatus = response.data.borrowInfoStatus
        
        if(this.borrowInfoStatus === 0){

            //未认证
            this.active = 0

            //获取借款额度
            this.getBorrowAmount()

            //初始化下拉列表
            this.initSelectd()
        }else if (this.borrowInfoStatus === 1){

            //审批中
          this.active = 1
        }else if(this.borrowInfoStatus === 2){

            //审批成功
            this.active = 2
        }else if(this.borrowInfoStatus === -1){
         
         //审批失败
         this.active = 2

        }

        })
    },

    //初始化下拉列表的数据

    initSelectd() {

        //还款方式列表

        this.$axios
        .$get('/api/core/dict/findByDictCode/returnMethod')
        .$then((response)=>{

            this.returnMethodList = response.data.dictList
        })
    }

   }

}

}
</script>
