<template>
<div>
    <el-container>
        <el-header>
            <i class="el-icon-s-custom header"></i>
            <span class="header"> 注册</span>
        </el-header>
        <el-main>
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="login-form">
                <el-form-item label="用户名" prop="username">
                    <el-input type="text" v-model="ruleForm.username" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPassword">
                    <el-input type="password" v-model="ruleForm.checkPassword" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input type="email" v-model="ruleForm.email"></el-input>
                </el-form-item>
                <el-form-item class="form-button">
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
			<router-link to="/login" class="jump"> 已经注册过？请登录</router-link>
        </el-main>
    </el-container>
</div>
</template>

<script>
export default {
    data() {
        var validatePass = (rule, value, callback) => {
			if (this.value === '') {
				callback(new Error("请输入密码"));
			} else if (this.ruleForm.checkPassword !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
        };
        var validatePass2 = (rule, value, callback) => {
			if (this.value === '') {
				callback(new Error("请输入密码"));
			} else if (value !== this.ruleForm.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
				username: '',
                password: '',
                checkPassword: '',
				email: ''
            },
            rules: {
				username: [{
                    required: true,
					message: '请输入用户名',
                    trigger: 'blur'
                }],
                password: [
				{
					required: true,
					validator: validatePass,
                    trigger: 'blur'
                }],
                checkPassword: [
				{
					required: true,
					validator: validatePass2,
                    trigger: 'blur'
                }],
				email: [
				{
					required: true,
					message: '请输入邮箱',
					trigger: 'blur'
				},
				{
					type: 'email',
					message: '邮箱格式错误',
					trigger: 'blur'
				}]
            }
        };
    },
    methods: {
        submitForm(formName) {
			const _this = this;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                     this.$axios.post('http://localhost:8181/save', this.ruleForm).then((res)=>{
			              const token = res.headers['authorization']
			              _this.$store.commit('SET_TOKEN', token)
			              _this.$store.commit('SET_USERINFO', res.data.data)
			              _this.$router.push("/blogs")
					 })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}
</script>

<style>
@import "../assets/css/container.css";
</style>
