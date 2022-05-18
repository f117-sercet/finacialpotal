<template>
  <div class="app-container">
<table
 class="table table-striped table-condenseda table-bordered"
      width="100%"
>
<tbody>
  <tr>
    <th width="15%">借款金额</th>
    <td width="35%">{{borrowInfoDetail.borrowInfo.amount}}元</td>
    <th width="15%">借款期限</th>
    <td width="35%">{{borrowInfoDetail.borrowInfo.period}}个月</td>
  </tr>
  <tr>
    <th>年化利率</th>
    <td>{{borrowInfoDetail.borrowInfo.borrowYearRate * 100}}%</td>
    <th>还款方式</th>
    <td>{{borrowInfoDetail.borrowInfo.param.returnMethod}}</td>
  </tr>



</tbody>

</table>

  </div>
</template>

<script>
import borrowInfoApi from '@/api/core/borrow-info'
import '@/styles/show.css'

export default {
  data() {
    return {
      borrowInfoDetail: {
        borrowInfo: {
          param: {}
        },
        borrower: {}
      }
    }
  },

  created() {
    if (this.$route.params.id) {
      this.fetchDataById()
    }
  },

  methods: {
    fetchDataById() {
      borrowInfoApi.show(this.$route.params.id).then(response => {
        this.borrowInfoDetail = response.data.borrowInfoDetail
      })
    },

    back() {
      this.$router.push('/core/borrower/info-list')
    }
  }
}
</script>
