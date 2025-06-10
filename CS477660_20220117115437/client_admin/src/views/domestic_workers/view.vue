<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['domestic_workers_id'] && $check_field('set','full_name')) || (!form['domestic_workers_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gender') || $check_field('add','gender') || $check_field('set','gender')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="性别" prop="gender">
					<el-select id="gender" v-model="form['gender']"
						v-if="user_group === '管理员' || (form['domestic_workers_id'] && $check_field('set','gender')) || (!form['domestic_workers_id'] && $check_field('add','gender'))">
						<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','gender')">{{form['gender']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','telephone') || $check_field('add','telephone') || $check_field('set','telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="电话" prop="telephone">
					<el-input id="telephone" v-model="form['telephone']" placeholder="请输入电话"
							  v-if="user_group === '管理员' || (form['domestic_workers_id'] && $check_field('set','telephone')) || (!form['domestic_workers_id'] && $check_field('add','telephone'))" :disabled="disabledObj['telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','telephone')">{{form['telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','head_portrait') || $check_field('add','head_portrait') || $check_field('set','head_portrait')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="头像" prop="head_portrait">
					<el-upload :disabled="disabledObj['head_portrait_isDisabled']" id="head_portrait" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_head_portrait"
						:show-file-list="false" v-if="user_group === '管理员' || (form['domestic_workers_id'] && $check_field('set','head_portrait')) || (!form['domestic_workers_id'] && $check_field('add','head_portrait'))">
						<img v-if="form['head_portrait']" :src="$fullUrl(form['head_portrait'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','head_portrait')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['head_portrait'])" :preview-src-list="[$fullUrl(form['head_portrait'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','curriculum_vitae') || $check_field('add','curriculum_vitae') || $check_field('set','curriculum_vitae')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="个人简历" prop="curriculum_vitae">
					<el-input type="textarea" id="curriculum_vitae" v-model="form['curriculum_vitae']" placeholder="请输入个人简历"
						v-if="user_group === '管理员' || (form['domestic_workers_id'] && $check_field('set','curriculum_vitae')) || (!form['domestic_workers_id'] && $check_field('add','curriculum_vitae'))" :disabled="disabledObj['curriculum_vitae_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','curriculum_vitae')">{{form['curriculum_vitae']}}</div>
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
				field: "domestic_workers_id",
				url_add: "~/api/domestic_workers/add?",
				url_set: "~/api/domestic_workers/set?",
				url_get_obj: "~/api/domestic_workers/get_obj?",
				url_upload: "~/api/domestic_workers/upload?",

				query: {
					"domestic_workers_id": 0,
				},

				form: {
					"full_name":'', // 姓名
					"gender":'', // 性别
					"telephone":'', // 电话
					"head_portrait":'', // 头像
					"curriculum_vitae":'', // 个人简历
					"domestic_workers_id": 0, // ID

				},
				disabledObj:{
					"full_name_isDisabled": false,
					"gender_isDisabled": false,
					"telephone_isDisabled": false,
					"head_portrait_isDisabled": false,
					"curriculum_vitae_isDisabled": false,
				},
				//性别选项列表
				list_gender: ['男','女'],

			}
		},
		methods: {
			/**
			 * 上传头像
			 * @param {Object} param图片参数
			 */
			upload_head_portrait(param){
				this.uploadFile(param.file, "head_portrait");
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

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/domestic_workers/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/domestic_workers/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/domestic_workers/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/domestic_workers/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/domestic_workers/view','get');
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
