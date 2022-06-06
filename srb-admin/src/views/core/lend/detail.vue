<template>
<div class="app-container">
<h4>标的信息</h4>
<table
 class="table table-striped table-condenseda table-bordered"
 width="100%"
>
<tbody>
  <tr>
    <th width="15%">标的编号</th>
    <td width="35%">
     <b>{{lendDetail.lend.lendNo}}</b>
    </td>
    <th width="15%">标题</th>
    <td width="35%">{{lendDetail.lend.title}}</td>
  </tr>
  <tr>
    <th>标的金额</th>
  </tr>
</tbody>
</table>
</div>
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