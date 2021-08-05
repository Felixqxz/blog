<template>
  <div class="blog-detail">
    <Header></Header>
    <br>
    <div class="blog">
      <div class="blog-title">
        <h1>{{blog.title}}</h1>
        <h4>{{blog.description}}</h4>
        <p> 作者：{{blog.username}} &nbsp;&nbsp;
          <el-link icon="el-icon-edit" v-if="ownBlog">
            <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}">
              编辑
            </router-link>
          </el-link>
          &nbsp;
          <el-link icon="el-icon-delete" v-if="ownBlog" :underline="false">
            <el-button type="text" @click="deleteBlog">删除</el-button>
          </el-link>
        </p>
      </div>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
import "github-markdown-css";

export default {
  name: "BlogDetail.vue",
  components: { Header },
  data() {
    return {
      blog: {
        userId: null,
        username: '用户名',
        title: '标题',
        description: '摘要',
        content: ''
      },
      ownBlog: false
    }
  },
  created() {
    const blogId = this.$route.params.blogId
    const _this = this
    this.$axios.get("/blog/" + blogId).then((res) => {
      console.log(res.data.data)
      const blog = res.data.data
      _this.blog.id = blog.id
      _this.blog.username = blog.username
      _this.blog.description = blog.description
      _this.blog.title = blog.title

      var MarkdownIt = require("markdown-it")
      var md = new MarkdownIt()

      var result = md.render(blog.content)
      _this.blog.content = result

      _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)
    })
  },
  methods: {
    deleteBlog() {
      const _this = this
      this.$confirm('此操作将永久删除该文档, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await _this.$axios.post("/blog/delete", this.blog, {
            headers: {
                "Authorization": localStorage.getItem("token")
              }
        })
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        _this.$router.push("/blogs")
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  }
}
</script>

<style scoped>
.blog {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  min-height: 700px;
  padding: 20px 15px;
  text-align: center;
  margin: 0 100px 0 100px;
}

.blog-title {
  margin: 0 auto;
  text-align: center;
}
</style>