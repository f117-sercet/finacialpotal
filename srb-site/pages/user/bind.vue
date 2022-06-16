<template>
<div>

</div>
</template>

<script>
export default {

data(){

    return{

        agree:false,
        userBind:{},
    }
},

methods:{

commitBind(){

this.$alert(
'<div style="size: 18px;color: red;">您即将前往汇付宝绑定账号</div>',
        '前往汇付宝资金托管平台',

 {
    dangerouslyUserHtmlString:true,
    confirmButtonText:'立即前往',
    callback:(action)=>{

        //提交用户信息

        console.log('action',action)
        if(action === 'confirm'){

            this.$axios
            .$post('/api/core/userBind/auth/bind',this.userBind)
            .then((response) =>{

                document.write(response.data.formstr)
            })
        }
    },
 }       

)},
},
    }
</script>