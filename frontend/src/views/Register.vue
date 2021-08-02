<template>
  <div>
    <el-container>
      <el-header>
        <el-col :span="11" class="link">
          <el-link type="primary" href="/blogs" :underline="false">首页</el-link>
        </el-col>
        <el-col :span="13"><i class="el-icon-s-custom header"></i>
          <span class="header"> 注册</span>
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
          <el-form-item label="确认密码" prop="checkPassword">
            <el-input type="password" v-model="ruleForm.checkPassword" auto-complete="off" @keyup.enter.native="submitForm('ruleForm')"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input type="email" v-model="ruleForm.email" @keyup.enter.native="submitForm('ruleForm')"></el-input>
          </el-form-item>
          <el-form-item class="form-button">
            <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
            <br /><br />
            <router-link to="/login" class="jump">
              <el-button>已经注册过？请登录</el-button>
            </router-link>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import md5 from "js-md5";
import lodash from "lodash";
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (this.ruleForm.checkPassword !== "") {
        this.$refs.ruleForm.validateField("checkPass");
      }
      callback();
    };
    var validatePass2 = (rule, value, callback) => {
      if (value !== this.ruleForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username: "",
        password: "",
        checkPassword: "",
        email: "",
      },
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
          {
            min: 3,
            message: "用户名长度不小于 3 个字",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
          {
            validator: validatePass,
            trigger: "blur",
          },
        ],
        checkPassword: [
          {
            required: true,
            message: "请再次输入密码",
            trigger: "blur",
          },
          {
            validator: validatePass2,
            trigger: "blur",
          },
        ],
        email: [
          {
            required: true,
            message: "请输入邮箱",
            trigger: "blur",
          },
          {
            type: "email",
            message: "邮箱格式错误",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var form = lodash.cloneDeep(this.ruleForm);
          form.password = md5(this.ruleForm.password);
          this.$axios.post("/user/save", form).then((res) => {
            console.log(res);
            _this.$router.push("/login");
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
</style>
