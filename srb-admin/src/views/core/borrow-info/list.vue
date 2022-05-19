<template>

</template>
<script>
import borrowInfoApi from '@/api/core/borrow-info'

export default {
  data() {
    return {
      list: [],
      dialogVisible: false, //审批对话框
      borrowInfoApproval: {
        status: 2,
        serviceRate: 5,
        lendYearRate: 0 //初始化，解决表单中数据修改时无法及时渲染的问题
      } //审批对象
    }
  },

  created() {
    this.fetchData()
  },

  methods: {
    fetchData() {
      borrowInfoApi.getList().then(response => {
        this.list = response.data.list
      })
    },

    approvalShow(row) {
      this.dialogVisible = true
      this.borrowInfoApproval.lendYearRate = row.borrowYearRate * 100
      this.borrowInfoApproval.id = row.id
    },

    approvalSubmit() {
      borrowInfoApi.approval(this.borrowInfoApproval).then(response => {
        this.dialogVisible = false
        this.$message.success(response.message)
        this.fetchData()
      })
    }
  }
}
</script>
