<template>
  <div class="blogs">
    <Header></Header>
    <div class="block">
      <div class="search-bar">
        <el-input placeholder="请输入内容" v-model="searchText" clearable @keyup.enter.native="Search">
        </el-input>
        <el-button type="primary" @click="Search">搜索</el-button>
      </div>
      <el-timeline>
        <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in showContents">
          <el-card>
            <h2>
              <router-link :to="{name: 'BlogDetail', params: {blogId: blog.id}}">{{blog.title}}</router-link>
            </h2>
            <p>{{blog.description}}</p>
            <el-row class="author">作者：{{blog.username}}</el-row>
          </el-card>
        </el-timeline-item>
      </el-timeline>

      <el-pagination class="page" layout="prev, pager, next" :current-page="currentPage" :page-size="pageSize" :total="total" @current-change="page">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
export default {
  name: "Blogs.vue",
  components: { Header },
  data() {
    return {
      blogs: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      searchText: '',
      showContents: []
    }
  },
  methods: {
    page(currentPage) {
      const _this = this
      _this.$axios.get("/blogs?currentPage=" + currentPage).then(res => {
        console.log(res.data.data)
        _this.blogs = res.data.data.records
        _this.currentPage = res.data.data.currentPage
        _this.total = res.data.data.total
        _this.pageSize = res.data.data.size
        _this.showContents = res.data.data.records
      })
    },
    Search() {
      const tempArr = []
      for (let i = 0; i < this.blogs.length; i += 1) {
        if (this.blogs[i].title.includes(this.searchText) || this.blogs[i].username.includes(this.searchText) || this.blogs[i].description.includes(this.searchText)) {
          tempArr.push(this.blogs[i])
        }
      }
      this.showContents = tempArr
      console.log(tempArr)
    }
  },
  created() {
    this.page(1)
  },

};
</script>

<style scoped>
.blogs {
  justify-content: center;
}

.block {
  margin: auto;
  max-width: 960px;
}
.page {
  margin: 0 auto;
  text-align: center;
}

.author {
  text-align: right;
}

.el-input {
  max-width: 200px;
}

.search-bar {
  text-align: end;
}
</style>