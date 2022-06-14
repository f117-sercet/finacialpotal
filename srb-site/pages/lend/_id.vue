<template>
    
</template>

<script>
import '~/assets/css/index.css'
import '~/assets/css/detail.css'
import cookie from 'js-cookie'

export default {

    async asyncData({$axios,params}) {

        let lendId = params.id
        let response = await $axios.$get('/api/core/lend/show/' + lendId)
        
        //投资记录
        let responseLendItemList = await $axios.$get(
        
        '/api/core/lendItem/list/'+lendId
        )

        //还款计划
       let responseLendReturnList = await $axios.$get(
        '/api/core/lendReturn/list/'+lendId
       )

 return {

    lend:response.data.lendDetail.lend, //标的详情
    borrower:response.data.lendDetail.borrower, //借款人信息
    lendItemList: responseLendItemList.data.list, //投资记录
    lendReturnList: responseLendReturnList.data.list, //还款计划

 }
       
    },

    data() {

     return{

        account: 0,//账户余额
        agree:false, //是否同意协议
        invest:{

            lendId:0,//标的id
            investAmount:100,//投资金额
        },
        interestCount:0,//获得收益
        userType:0,//用户类型
        lendItemReturnList:[],//回款计划
     }

    },

   //此时方法在客户端的浏览器中执行，可以获取到cookie   

   mounted(){

    //查询账户余额
    this.fetchAccount()

    //判断登录人的用户信息
    this.fetchUserType()

    //回款计划
    this.fetchLendItemRetrunList()
   },

   methods:{

   //查看账户余额
   fetchAccount(){

   let userInfo = cookie.get("userInfo")
   if(userInfo){
    
    this.$axios
    .$get('/api/core/userAccount/auth/getAccount')
    .then((response)=>{

        this.account = response.data.account
    })

   }

   },

//获取登录人的用户类型
fetchUserType() {

    let userInfo = cookie.get('userInfo')
    if(userInfo){

        userInfo = JSON.parse(userInfo)
        this.userType = userInfo.userType
    }
},

//计算收益
getInterestCount() {

    this.$axios.$get(
          `/api/core/lend/getInterestCount/${this.invest.investAmount}/${this.lend.lendYearRate}/${this.lend.period}/${this.lend.returnMethod}`
        )
        .then((response)=>{

            this.interestCount = response.data.interestCount
        })
   },

 //投资
 communityInvest() {

    //校验用户是否登录
    let userInfo = cookie.get('userInfo')

    if(!userInfo){
        
        console.log('跳转到登录页面')
        window.location.href = '/login'

        return
    }
    
    //校验当前用户是否是投资人

    let userInfoObj = JSON.parse(userInfo)
    if(userInfoObj.userType == 2){

        //借款人
        this.$message.error('借款人无法投资')
        return
    }

    //判读标的是否超卖：标的已投金额+本次投资金额>标的总金额
    
    if(
        this.lend.investAmount+Number(this.invest.investAmount) >
        this.lend.amount
    ){
        this.$message.error('标的投资金额不足')
        return 
    }

    //余额的判断
    if(this.invest.investAmount > this.account){

        this.$message.error('余额不足，请充值')
        return
    }

      //数据提交
      this.$alert(
        '<div style="size: 18px;color: red;">您即将前往汇付宝确认标的</div>',
        '前往汇付宝资金托管平台',
        {
          dangerouslyUseHTMLString: true,
          confirmButtonText: '立即前往',
          callback: (action) => {
            console.log('action', action)
            if (action === 'confirm') {
              this.invest.lendId = this.lend.id
              this.$axios
                .$post('/api/core/lendItem/auth/commitInvest', this.invest)
                .then((response) => {
                  // console.log(response.data.formStr)
                  // debugger
                  document.write(response.data.formStr)
                })
            }
          },
        }
      )
 },

 //回款计划
 fetchLendItemRetrunList(){

    this.$axios
    .$get('/api/core/lendItemReturn/list/' + this.$route.params.id)
    .then((response)=>{

        this.lendItemReturnList = response.data.list
    })
 },
   commitReturn(lendReturnId) {
      this.$alert(
        '<div style="size: 18px;color: red;">您即将前往汇付宝确认还款</div>',
        '前往汇付宝资金托管平台',
        {
          dangerouslyUseHTMLString: true,
          confirmButtonText: '立即前往',
          callback: (action) => {
            if (action === 'confirm') {
              this.$axios
                .$post('/api/core/lendReturn/auth/commitReturn/' + lendReturnId)
                .then((response) => {
                  document.write(response.data.formStr)
                })
            }
          },
        }
      )
    },
  },
}
</script>
