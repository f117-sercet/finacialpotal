<template>
<div class="app-container">
  <el-form label-width="100" class="form-table">
    <el-row>
      <el-col :span="6">
        <el-form-item label="状态">
          {{borrower.status}}
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</div>

</template>
<script>
// 引入组件
import  borrowerApi from '@/api/core/borrower'

export default {



}

</script>
