<template>
   
</template>
<script>
export default {
  data() {
    let BASE_API = process.env.BASE_API

    return {
      active: null, //步骤
      borrowerStatus: null,
      submitBtnDisabled: false,
      //借款人信息
      borrower: {
        borrowerAttachList: [], //附件列表
      },
      educationList: [], //学历列表
      industryList: [], //行业列表
      incomeList: [], //月收入列表
      returnSourceList: [], //还款来源列表
      contactsRelationList: [], //联系人关系
      uploadUrl: BASE_API + '/api/oss/file/upload', //文件上传地址
    }
  },

  created() {
    this.getUserInfo()
  },

  methods: {
    getUserInfo() {
      this.$axios
        .$get('/api/core/borrower/auth/getBorrowerStatus')
        .then((response) => {
          this.borrowerStatus = response.data.borrowerStatus

          if (this.borrowerStatus === 0) {
            //未认证
            this.active = 0
            this.initSelected()
          } else if (this.borrowerStatus === 1) {
            //认证中
            this.active = 1
            // } else if (this.borrowerStatus === 2) {
            //   this.active = 2
            // } else if (this.borrowerStatus === -1) {
            //   this.active = 2
            // }
          } else {
            this.active = 2
          }
        })
    },

    initSelected() {
      //学历列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/education')
        .then((response) => {
          this.educationList = response.data.dictList
        })

      //行业列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/industry')
        .then((response) => {
          this.industryList = response.data.dictList
        })

      //收入列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/income')
        .then((response) => {
          this.incomeList = response.data.dictList
        })

      //还款来源列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/returnSource')
        .then((response) => {
          this.returnSourceList = response.data.dictList
        })

      //联系人关系列表
      this.$axios
        .$get('/api/core/dict/findByDictCode/relation')
        .then((response) => {
          this.contactsRelationList = response.data.dictList
        })
    },

    save() {
      this.submitBtnDisabled = true
      this.$axios
        .$post('/api/core/borrower/auth/save', this.borrower)
        .then((response) => {
          this.active = 1
        })
    },

    onUploadSuccessIdCard1(response, file) {
      this.onUploadSuccess(response, file, 'idCard1')
    },

    onUploadSuccessIdCard2(response, file) {
      this.onUploadSuccess(response, file, 'idCard2')
    },

    onUploadSuccessHouse(response, file) {
      this.onUploadSuccess(response, file, 'house')
    },

    onUploadSuccessCar(response, file) {
      this.onUploadSuccess(response, file, 'car')
    },

    onUploadSuccess(response, file, type) {
      if (response.code === 0) {
        //业务成功，填充borrower.borrowerAttachList列表
        this.borrower.borrowerAttachList.push({
          imageName: file.name,
          imageUrl: response.data.url,
          imageType: type,
        })
      } else {
        //业务失败
        this.$message.error(response.message)
      }
    },

    onUploadRemove(file, fileList) {
      console.log('onUploadRemove file', file)
      console.log('onUploadRemove fileList', fileList)
      //调用远程文件删除接口
      this.$axios
        .$delete('/api/oss/file/remove?url=' + file.response.data.url)
        .then((response) => {
          console.log('远程删除文件成功')
          //从this.borrower.borrowerAttachList列表中删除对象
          this.borrower.borrowerAttachList = this.borrower.borrowerAttachList.filter(
            function(item) {
              return item.imageUrl != file.response.data.url
            }
          )
        })
    },
  },
}
</script>
