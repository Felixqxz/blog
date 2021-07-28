<template>
  <div class="content">
    <h1>Felix's Blog</h1>
    <div class="block">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <div>{{user.username}}</div>
    </div>

    <div class="action">
      <el-link type="primary" href="/blogs">主页</el-link>
      <el-divider direction="vertical"></el-divider>
      <el-link type="primary" href="/blog/edit">发表文章</el-link>
      <span v-show="!hasLogin">
        <el-divider direction="vertical"></el-divider>
        <el-link type="primary" href="/register">注册</el-link>
      </span>
      <span v-show="!hasLogin">
        <el-divider direction="vertical"></el-divider>
        <el-link type="primary" href="/login">登陆</el-link>
      </span>
      <span v-show="hasLogin">
        <el-divider direction="vertical"></el-divider>
        <el-link type="primary" @click="logout">退出登陆</el-link>
      </span>
    </div>
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
  max-width: 960px;
  margin: 0 auto;
  text-align: center;
}

.action {
}
</style>