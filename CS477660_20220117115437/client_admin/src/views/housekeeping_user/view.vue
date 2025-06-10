<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="头像" prop="avatar">
					<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"
						action="" :http-request="uploadimg" :show-file-list="false">
						<img v-if="form.avatar" :src="$fullUrl(form.avatar)" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="账号" prop="username">
			        <span v-if="obj.username">{{obj.username}}</span>
					<el-input v-else v-model="form.username" placeholder="请输入账号"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="密码" prop="password">
					<span v-if="obj.password">{{obj.password}}</span>
			        <el-input v-else type="password" v-model="form.password" placeholder="请输入密码" show-password></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="昵称" prop="nickname">
					<el-input v-model="form.nickname" placeholder="请输入昵称"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="手机号码" prop="phone">
					<el-input type="phone" v-model="form.phone" placeholder="请输入手机号码"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="邮箱" prop="email">
					<el-input type="email" v-model="form.email" placeholder="请输入邮箱"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="手机认证" prop="phone_state">
					<el-select v-model="form.phone_state" placeholder="请选择">
						<el-option v-for="group in list_state" :key="group.value" :label="group.name"
							:value="group.value">
						</el-option>
					</el-select>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="邮箱认证" prop="email_state">
					<el-select v-model="form.email_state" placeholder="请选择">
						<el-option v-for="group in list_state" :key="group.value" :label="group.name"
							:value="group.value">
						</el-option>
					</el-select>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="状态" prop="state">
					<el-select v-model="form.state" placeholder="请选择">
						<el-option v-for="group in list_user_state" :key="group.value" :label="group.name"
							:value="group.value">
						</el-option>
					</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gender') || $check_field('add','gender') || $check_field('set','gender')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="性别" prop="gender">
					<el-input id="gender" v-model="form_sub['gender']" placeholder="请输入性别"
							  v-if="user_group === '管理员' || (form_sub['housekeeping_user_id'] && $check_field('set','gender')) || (!form_sub['housekeeping_user_id'] && $check_field('add','gender'))"></el-input>
					<div v-else-if="$check_field('get','gender')">{{form_sub['gender']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','community') || $check_field('add','community') || $check_field('set','community')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="所在小区" prop="community">
					<el-input id="community" v-model="form_sub['community']" placeholder="请输入所在小区"
							  v-if="user_group === '管理员' || (form_sub['housekeeping_user_id'] && $check_field('set','community')) || (!form_sub['housekeeping_user_id'] && $check_field('add','community'))"></el-input>
					<div v-else-if="$check_field('get','community')">{{form_sub['community']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','current_address') || $check_field('add','current_address') || $check_field('set','current_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="现居住址" prop="current_address">
					<el-input id="current_address" v-model="form_sub['current_address']" placeholder="请输入现居住址"
							  v-if="user_group === '管理员' || (form_sub['housekeeping_user_id'] && $check_field('set','current_address')) || (!form_sub['housekeeping_user_id'] && $check_field('add','current_address'))"></el-input>
					<div v-else-if="$check_field('get','current_address')">{{form_sub['current_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','domestic_companies_') || $check_field('add','domestic_companies_') || $check_field('set','domestic_companies_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="家政公司" prop="domestic_companies_">
					<el-input id="domestic_companies_" v-model="form_sub['domestic_companies_']" placeholder="请输入家政公司"
							  v-if="user_group === '管理员' || (form_sub['housekeeping_user_id'] && $check_field('set','domestic_companies_')) || (!form_sub['housekeeping_user_id'] && $check_field('add','domestic_companies_'))"></el-input>
					<div v-else-if="$check_field('get','domestic_companies_')">{{form_sub['domestic_companies_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','authentication_picture') || $check_field('add','authentication_picture') || $check_field('set','authentication_picture')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="认证图片" prop="authentication_picture">
					<el-upload :disabled="disabledObj['authentication_picture_isDisabled']" id="authentication_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_authentication_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form_sub['housekeeping_user_id'] && $check_field('set','authentication_picture')) || (!form_sub['housekeeping_user_id'] && $check_field('add','authentication_picture'))">
						<img v-if="form_sub['authentication_picture']" :src="$fullUrl(form_sub['authentication_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','authentication_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form_sub['authentication_picture'])" :preview-src-list="[$fullUrl(form_sub['authentication_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form_sub['examine_state']"
						v-if="user_group === '管理员' || (form_sub['examine_state'] && $check_examine()) || (!form_sub['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form_sub["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "user_id",
				url_add: "~/api/user/register?",
				url_set: "~/api/user/set?",
				url_get_obj: "~/api/user/get_obj?",
				url_upload: "~/api/user/upload?",

				group_table: "housekeeping_user",
				is_password: true,

				query: {
					"user_id": 0,
				},

				obj: {
					user_id: 0,
					username: '',
					nickname: '',
					password: '',
					avatar: '',
					phone: '',
					email: '',
					user_group: "家政用户",
					phone_state: 0,
					email_state: 0,
					state: 1
				},

				form: {
					user_id: 0,
				    username: '',
					nickname: '',
				    password: '',
					avatar: '',
					phone: '',
					email: '',
					user_group: "家政用户",
					phone_state: 0,
					email_state: 0,
					state: 1
				},

				disabledObj:{
					"gender_isDisabled": false,
					"community_isDisabled": false,
					"current_address_isDisabled": false,
					"domestic_companies__isDisabled": false,
					"authentication_picture_isDisabled": false,
				},

				form_sub: {
					"gender":'', // 性别
					"community":'', // 所在小区
					"current_address":'', // 现居住址
					"domestic_companies_":'', // 家政公司
					"authentication_picture":'', // 认证图片
					"examine_state": "未审核",
				    "user_id": 0,
					"housekeeping_user_id": 0 // ID
				},

				list_state: [{
					value: 0,
					name: "未认证"
				}, {
					value: 1,
					name: "审核中"
				}, {
					value: 2,
					name: "已认证"
				}],

				list_user_state: [{
					value: 1,
					name: "可用"
				}, {
					value: 2,
					name: "异常"
				}, {
					value: 3,
					name: "已冻结"
				}, {
					value: 4,
					name: "已注销"
				}],

			}
		},
		methods: {
			/**
			 * 上传认证图片
			 * @param {Object} param图片参数
			 */
			upload_authentication_picture(param){
				let _this = this;
				var form = new FormData() // FormData 对象
				form.append('file', param.file) // 文件对象
				this.$upload(this.url_upload, form, function(json) {
					if (json.result) {
						_this.form_sub['authentication_picture'] = json.result.url;
					} else {
						_this.$toast('上传失败！');
					}
				});
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							if(dbKey === "charging_standard"){
								this.form['charging_rules'] = form[dbKey];
								this.disabledObj['charging_rules_isDisabled'] = true;
							};
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				var user = json.result.obj;
				this.is_password = user.password ? false : true;
				var user_id = user.user_id;
				this.$get("~/api/" + this.group_table + "/get_obj?" ,{user_id} ,(res)=>{
					if(res.result && res.result.obj){
						var o = res.result.obj;
						delete o["create_time"];
						delete o["update_time"];
						this.form_sub = res.result.obj;
					}else if(res.error){
						console.log(res.error);
						console.log("获取不到相关信息");
					}
				})

			},
			async submit(param, func){
				if (!param) {
					param = this.form;
				}
				var pm = this.events("submit_before", Object.assign({}, param)) || param;
				var msg = await this.events("submit_check", pm);
				var ret;
				if (msg) {
					this.$toast(msg, 'danger');
				} else {
					ret = this.events("submit_main", pm, func);
				}
				return ret;
			},

			// 提交前校验
			async submit_check(param){
				var ret = null;

				var email_regular = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
				var phone_regular = /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;

				var {username ,password ,nickname ,user_group ,email ,phone} = param;

				var confirm_password = this.confirm_password;

				console.log("表单校验username ,password ,email ,nickname ,user_group" ,username ,password ,email ,nickname ,user_group);

				if(!username){
					ret = "账号不能为空";
				}
				else if(username.length > 16 || username.length < 5){
					ret = "账号长度应为5到16个字符之间！";
				}
				else if(!password){
					ret = "密码不能为空!";
				}
				else if(this.is_password && (password.length > 16 || password.length < 5)){
					ret = "密码长度应为5到16个字符之间！";
				}
				else if(nickname && nickname.length > 12 || nickname.length < 2){
					ret = "昵称长度应为2个字符到12个字符之间";
				}
				else if(email && !email_regular.test(email)){
					ret = "请输入正确的邮箱地址 例：test@test.com!";
				}
				else if(phone && !phone_regular.test(phone)){
					ret = "请输入正确的手机号码 例：18955552312!";
				}
				else if(!user_group){
					ret = "请选择用户组!";
				}

				var p = {"username": param.username};

				return ret;
			},

			// 提交成功后
			submit_after(json,func){
				var form = Object.assign({} ,this.form);
				delete form.password;
				console.log("查询表单form",form)
				this.get_register(form);
			},

			/**
			 * 获取注册表信息
			 * @param {Object} form
			 * @param {Object} table
			 */
			get_register(form){
				var form_sub = this.form_sub;
				this.$get("~/api/user/get_obj?",form,(res)=>{
					console.log("注册表信息res",res);
					if(res.result && res.result.obj){
						form_sub.user_id = res.result.obj.user_id;
						this.submit_sub(form_sub);
					}else if(res.error){
						console.error(res.error);
						this.$toast(res.error.message,"error");
					}
				})
			},

			// 提交附加信息
			submit_sub(form_sub){
				if(form_sub["housekeeping_user_id"]){
					// 提交事件
					this.$post("~/api/" + this.group_table + "/set?housekeeping_user_id=" + form_sub["housekeeping_user_id"],form_sub,(res)=>{
						console.log("提交结果：" ,res)
						if(res.result){
							this.$toast("修改成功!" ,"success");
							this.$router.go(-1);
						}else if(res.error){
							console.error(res.error);
							this.$toast(res.error.message,"error");
						}
					});
				}
				else{
					// 提交事件
					this.$post("~/api/" + this.group_table + "/add?",form_sub,(res)=>{
						console.log("提交结果：" ,res)
						if(res.result){
							this.$toast("添加成功!" ,"success");
							this.$router.go(-1);
						}else if(res.error){
							var user_id = form_sub["user_id"];
							this.$get("~/api/user/del?",{user_id});
							console.error(res.error);
							this.$toast(res.error.message,"error");
						}
					});
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/housekeeping_user/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/housekeeping_user/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/housekeeping_user/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/housekeeping_user/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/housekeeping_user/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
