<template>
  <div>
    <Header></Header>
    <div class="blog">
      <div class="blog-title">
        <h1>{{blog.title}}</h1>
        <p>作者：{{blog.username}}</p>
        <el-link icon="el-icon-edit" v-if="ownBlog">
          <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}">
            编辑
          </router-link>
        </el-link>
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
        description: '内容',
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
      _this.blog.title = blog.title

      var MarkdownIt = require("markdown-it")
      var md = new MarkdownIt()

      var result = md.render(blog.content)
      _this.blog.content = result

      _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)
    })
  }
}
</script>

<style scoped>
.blog {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  width: 100%;
  min-height: 700px;
  padding: 20px 15px;
}

.blog-title {
  margin: 0 auto;
  text-align: center;
}
</style>