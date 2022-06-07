<template>
  <div class="app-container">
<!---列表-->
 <el-table :data="list" stripe>
      <el-table-column type="index" label="序号" width="60" align="center" />
      <el-table-column prop="lendNo" label="标的编号" width="160" />
      <el-table-column prop="amount" label="标的金额" />
      <el-table-column prop="period" label="投资期数" />
      <el-table-column label="年化利率">
     <template slot-scope="list">
      {{list.row.lendYearRate *100}}
     </template>
     </el-table-column>
        <el-table-column prop="investAmount" label="已投金额" />
      <el-table-column prop="investNum" label="投资人数" />
      <el-table-column prop="publishDate" label="发布时间" width="150" />
      <el-table-column prop="lendStartDate" label="开始日期" />
      <el-table-column prop="lendEndDate" label="结束日期" />
      <el-table-column prop="param.returnMethod" label="还款方式" />

</el-table>
  </div>
</template>
<script>
</script>
