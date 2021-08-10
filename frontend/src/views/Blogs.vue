<template>
  <div class="blogs">
    <Header></Header>
    <br>
    <div class="block">
      <div class="search-bar">
        <div class="filter">
          筛选
          <el-select v-model="filterUsers" placeholder="请选择作者" @change="userFilter" clearable>
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div class="search">
          <el-input placeholder="请输入搜索内容" v-model="searchText" clearable @keyup.enter.native="Search">
          </el-input> &nbsp;
          <el-button type="primary" @click="Search">搜索</el-button>
        </div>
      </div>
      <br>
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
      showContents: [],
      options: [],
      filterUsers: "",
    }
  },
  methods: {
    async page(currentPage) {
      const res = await this.$axios.get("/blogs?currentPage=" + currentPage)
      console.log(res)
      const _this = this
      this.blogs = res.data.data.records
      this.currentPage = res.data.data.currentPage
      this.total = res.data.data.total
      this.pageSize = res.data.data.size
      this.showContents = this.blogs
      this.userFilter()
    },
    Search() {
      const tempArr = []
      for (var blog of this.blogs) {
        if (blog.title.includes(this.searchText) || blog.description.includes(this.searchText)) {
          tempArr.push(blog)
        }
      }
      this.showContents = tempArr
      console.log(tempArr)
    },
    userFilter() {
      if (this.filterUsers == "") {
        this.showContents = this.blogs
      } else {
        const tempArr = []
          for (var blog of this.blogs) {
            if (this.filterUsers == blog.username) {
              tempArr.push(blog)
          }
        }
        this.showContents = tempArr
      }
    }
  },
  async created() {
    await this.page(1)
    const res = await this.$axios.get("/blogs/getAuthors")
    const _this = this
    for (var author of res.data.data) {
      _this.options.push({
        value: author,
        label: author
      })
    }
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
  display: flex;
  justify-content: space-between;
}

.search {
  display: flex;
}
</style>