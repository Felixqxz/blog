<template>
  <div class="blogs">
    <Header></Header>
    <br>
    <div class="block">
      <div class="search-bar">
        <div class="filter">
          筛选
          <el-select v-model="filterUsers" placeholder="请选择作者" @change="filter" clearable>
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div class="search">
          <el-input placeholder="请输入标题" v-model="searchText" clearable @change="filter">
          </el-input> &nbsp;
          <el-button type="primary" @click="filter">搜索</el-button>
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
      const res = await this.$axios.get("/blogs?currentPage=" + currentPage + "&author=" + this.filterUsers + "&search=" + this.searchText)
      console.log(res)
      this.blogs = res.data.data.records
      this.currentPage = res.data.data.current
      this.total = res.data.data.total
      this.pageSize = res.data.data.size
      this.showContents = this.blogs
    },
    filter() {
      this.page(1)
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