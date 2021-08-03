<template>
  <div class="content">
    <el-row>

      <el-col :span="8" class="action">
        <el-link type="primary" href="/blogs">主页</el-link>
        <el-divider direction="vertical"></el-divider>
        <el-link type="primary" href="/blog/add">发表文章</el-link>

      </el-col>

      <el-col :span="8">
        <h1 class="title">Felix's Blog</h1>
      </el-col>

      <el-col :span="8" class="block">
        <span v-show="!hasLogin">
          <el-link type="primary" href="/register">注册</el-link>
          <el-divider direction="vertical"></el-divider>
        </span>
        <span v-show="!hasLogin">
          <el-link type="primary" href="/login">登陆</el-link>
          <el-divider direction="vertical"></el-divider>
        </span>
        <span v-show="hasLogin">
          <el-link type="primary" @click="logout">退出登陆&nbsp;</el-link>
        </span>
        <span>{{user.username}}&nbsp;</span>
        <el-avatar :size="50" :src="user.avatar"></el-avatar>
      </el-col>

    </el-row>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      user: {
        username: '请先登录',
        avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
      },
      hasLogin: false
    }
  },
  methods: {
    logout() {
      const _this = this
      _this.$axios.get("/logout", {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        _this.$store.commit("REMOVE_INFO")
        _this.$router.push("/login")
      })
    }
  },
  created() {
    if (this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username
      this.user.avatar = this.$store.getters.getUser.avatar

      this.hasLogin = true
    }
  }
}
</script>

<style scoped>
.content {
  background-color: #b3c0d1;
  margin: 0 auto;
  text-align: center;
  max-height: 100px;
  margin-bottom: 5px;
}

.block {
  text-align: right;
  padding: 5px;
}

.title {
  font-size: 40px;
  color: navy;
  text-align: center;
}

.action {
  text-align: left;
  margin-top: 35px;
}

.el-link {

  font-family: STHeiti;
}
</style>