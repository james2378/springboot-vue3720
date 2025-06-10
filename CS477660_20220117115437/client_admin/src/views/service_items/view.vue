<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','entry_name') || $check_field('add','entry_name') || $check_field('set','entry_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目名称" prop="entry_name">
					<el-input id="entry_name" v-model="form['entry_name']" placeholder="请输入项目名称"
							  v-if="user_group === '管理员' || (form['service_items_id'] && $check_field('set','entry_name')) || (!form['service_items_id'] && $check_field('add','entry_name'))" :disabled="disabledObj['entry_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','entry_name')">{{form['entry_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_type') || $check_field('add','project_type') || $check_field('set','project_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目类型" prop="project_type">
					<el-select id="project_type" v-model="form['project_type']"
						v-if="user_group === '管理员' || (form['service_items_id'] && $check_field('set','project_type')) || (!form['service_items_id'] && $check_field('add','project_type'))">
						<el-option v-for="o in list_project_type" :key="o['project_type']" :label="o['project_type']"
							:value="o['project_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','project_type')">{{form['project_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','service_details') || $check_field('add','service_details') || $check_field('set','service_details')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="服务详情" prop="service_details">
					<el-input type="textarea" id="service_details" v-model="form['service_details']" placeholder="请输入服务详情"
						v-if="user_group === '管理员' || (form['service_items_id'] && $check_field('set','service_details')) || (!form['service_items_id'] && $check_field('add','service_details'))" :disabled="disabledObj['service_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','service_details')">{{form['service_details']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_poster') || $check_field('add','project_poster') || $check_field('set','project_poster')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目海报" prop="project_poster">
					<el-upload :disabled="disabledObj['project_poster_isDisabled']" id="project_poster" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_project_poster"
						:show-file-list="false" v-if="user_group === '管理员' || (form['service_items_id'] && $check_field('set','project_poster')) || (!form['service_items_id'] && $check_field('add','project_poster'))">
						<img v-if="form['project_poster']" :src="$fullUrl(form['project_poster'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','project_poster')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['project_poster'])" :preview-src-list="[$fullUrl(form['project_poster'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_price') || $check_field('add','project_price') || $check_field('set','project_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目价格" prop="project_price">
					<el-input id="project_price" v-model="form['project_price']" placeholder="请输入项目价格"
							  v-if="user_group === '管理员' || (form['service_items_id'] && $check_field('set','project_price')) || (!form['service_items_id'] && $check_field('add','project_price'))" :disabled="disabledObj['project_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','project_price')">{{form['project_price']}}</div>
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
				field: "service_items_id",
				url_add: "~/api/service_items/add?",
				url_set: "~/api/service_items/set?",
				url_get_obj: "~/api/service_items/get_obj?",
				url_upload: "~/api/service_items/upload?",

				query: {
					"service_items_id": 0,
				},

				form: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"service_details":'', // 服务详情
					"project_poster":'', // 项目海报
					"project_price":'', // 项目价格
					"service_items_id": 0, // ID

				},
				disabledObj:{
					"entry_name_isDisabled": false,
					"project_type_isDisabled": false,
					"service_details_isDisabled": false,
					"project_poster_isDisabled": false,
					"project_price_isDisabled": false,
				},
				//项目类型选项列表
				list_project_type: [],

			}
		},
		methods: {
			/**
			 * 获取项目类型列表
			 */
			async get_list_project_type() {
				var json = await this.$get("~/api/type_management/get_list?");
				if(json.result && json.result.list){
					this.list_project_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传项目海报
			 * @param {Object} param图片参数
			 */
			upload_project_poster(param){
				this.uploadFile(param.file, "project_poster");
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
					bl = this.$check_action('/service_items/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/service_items/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/service_items/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/service_items/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/service_items/view','get');
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
			this.get_list_project_type();
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
