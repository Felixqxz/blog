<template>
  <div class="blog-edit">
    <Header></Header>
    <br>
    <div class="content">
      <el-form ref="editForm" status-icon :model="editForm" :rules="rules" label-width="70px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="editForm.title"></el-input>
        </el-form-item>
        <el-form-item label="摘要" prop="description">
          <el-input v-model="editForm.description" clearable></el-input>
        </el-form-item>
        
        <el-form-item label="内容" prop="content">
          <mavon-editor v-model="editForm.content" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('editForm')">立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
export default {
  name: "BlogEdit.vue",
  components: { Header },
  data() {
    return {
      editForm: {
        id: null,
        username: '',
        title: '',
        description: '',
        content: ''
      },
      rules: {
        title: [
          {
            required: true,
            message: "请输入标题",
            trigger: "blur"
          },
          {
            min: 2,
            max: 50,
            message: "长度在 2 到 50 个字符",
            trigger: "blur"
          }
        ],
        description: [
          {
            required: true,
            message: "请输入摘要",
            trigger: "blur"
          }
        ]
      }
    }
  },
  created() {
    const blogId = this.$route.params.blogId
    const _this = this
    if (blogId) {
      this.$axios.get("/blog/" + blogId).then((res) => {
        const blog = res.data.data
        _this.editForm.id = blog.id
        _this.editForm.username = blog.username
        _this.editForm.title = blog.title
        _this.editForm.description = blog.description
        _this.editForm.content = blog.content
      })
    }
  },
  methods: {
     submitForm(formName) {
        const _this = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post('/blog/edit', this.editForm, {
              headers: {
                "Authorization": localStorage.getItem("token")
              }
            }).then((res) => {
              _this.$alert('发布成功', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push("/blogs")
                }
              });
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        })
     }
  }
}
</script>

<style scoped>

.el-form {
  margin: 0 100px 0 50px;
  text-align: center;
}
</style>