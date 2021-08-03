<template>
  <div>
    <el-container>
      <el-header>
        <el-col :span="11" class="link">
          <el-link type="primary" href="/blogs" :underline="false">首页</el-link>
        </el-col>
        <el-col :span="13"><i class="el-icon-s-custom header"></i>
          <span class="header"> 登陆</span>
        </el-col>
      </el-header>
      <el-main>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="login-form">
          <el-form-item label="用户名" prop="username">
            <el-input type="text" v-model="ruleForm.username" auto-complete="off" @keyup.enter.native="submitForm('ruleForm')"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password" auto-complete="off" @keyup.enter.native="submitForm('ruleForm')"></el-input>
          </el-form-item>
          <el-form-item class="form-button">
            <el-button type="primary" @click="submitForm('ruleForm')">登陆</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button><br /><br />
            <router-link to="/register" class="jump">
              <el-button>注册</el-button>
            </router-link>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;
          this.$axios.post("/login", this.ruleForm).then((res) => {
            console.log(res);
            const token = res.headers["authorization"];
            _this.$store.commit("SET_TOKEN", token);
            _this.$store.commit("SET_USERINFO", res.data.data);
            _this.$router.push("/blogs");
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style>
@import "../assets/css/container.css";
.el-link {
  font-family: STHeiti;
}
</style>
