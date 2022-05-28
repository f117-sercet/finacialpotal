<template>
</template>

<script>
import lendApi from '@/api/core/lend'
import lendItemApi from '@/api/core/lend-item'
import lendReturnApi from '@/api/core/lend-return'
import '@/styles/show.css'

export default {
  data() {
    return {
      lendDetail: {
        lend: {
          param: {}
        },
        borrower: {}
      },

      lendItemList: [], //投资列表
      lendReturnList: [] //还款计划列表
    }
  },

  created() {
    if (this.$route.params.id) {
      this.fetchDataById()

      // 投资记录
      this.fetchLendItemList()
      //还款计划
      this.fetchLendReturnList()
    }
  },

  methods: {
    fetchDataById() {
      lendApi.show(this.$route.params.id).then(response => {
        this.lendDetail = response.data.lendDetail
      })
    },

    back() {
      this.$router.push({ path: '/core/lend/list' })
    },

    fetchLendItemList() {
      lendItemApi.getList(this.$route.params.id).then(response => {
        this.lendItemList = response.data.list
      })
    },

    fetchLendReturnList() {
      lendReturnApi.getList(this.$route.params.id).then(response => {
        this.lendReturnList = response.data.list
      })
    }
  }
}
</script>