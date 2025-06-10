<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','title') || $check_field('add','title') || $check_field('set','title')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="标题" prop="title">
					<el-input id="title" v-model="form['title']" placeholder="请输入标题"
							  v-if="user_group === '管理员' || (form['cleaning_knowledge_id'] && $check_field('set','title')) || (!form['cleaning_knowledge_id'] && $check_field('add','title'))" :disabled="disabledObj['title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title')">{{form['title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','details') || $check_field('add','details') || $check_field('set','details')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="详情" prop="details">
					<quill-editor v-model.number="form['details']"
						v-if="user_group === '管理员' || (form['cleaning_knowledge_id'] && $check_field('set','details')) || (!form['cleaning_knowledge_id'] && $check_field('add','details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','details')" v-html="form['details']"></div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','label') || $check_field('add','label') || $check_field('set','label')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="标签" prop="label">
					<el-input id="label" v-model="form['label']" placeholder="请输入标签"
							  v-if="user_group === '管理员' || (form['cleaning_knowledge_id'] && $check_field('set','label')) || (!form['cleaning_knowledge_id'] && $check_field('add','label'))" :disabled="disabledObj['label_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','label')">{{form['label']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover_picture') || $check_field('add','cover_picture') || $check_field('set','cover_picture')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="封面图" prop="cover_picture">
					<el-upload :disabled="disabledObj['cover_picture_isDisabled']" id="cover_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['cleaning_knowledge_id'] && $check_field('set','cover_picture')) || (!form['cleaning_knowledge_id'] && $check_field('add','cover_picture'))">
						<img v-if="form['cover_picture']" :src="$fullUrl(form['cover_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_picture'])" :preview-src-list="[$fullUrl(form['cover_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','release_time') || $check_field('add','release_time') || $check_field('set','release_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="发布时间" prop="release_time">
					<el-date-picker :disabled="disabledObj['release_time_isDisabled']" v-if="user_group === '管理员' || (form['cleaning_knowledge_id'] && $check_field('set','release_time')) || (!form['cleaning_knowledge_id'] && $check_field('add','release_time'))" id="release_time"
						v-model="form['release_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_time')">{{form['release_time']}}</div>
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
				field: "cleaning_knowledge_id",
				url_add: "~/api/cleaning_knowledge/add?",
				url_set: "~/api/cleaning_knowledge/set?",
				url_get_obj: "~/api/cleaning_knowledge/get_obj?",
				url_upload: "~/api/cleaning_knowledge/upload?",

				query: {
					"cleaning_knowledge_id": 0,
				},

				form: {
					"title":'', // 标题
					"details":'', // 详情
					"label":'', // 标签
					"cover_picture":'', // 封面图
					"release_time":'', // 发布时间
					"cleaning_knowledge_id": 0, // ID

				},
				disabledObj:{
					"title_isDisabled": false,
					"details_isDisabled": false,
					"label_isDisabled": false,
					"cover_picture_isDisabled": false,
					"release_time_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传封面图
			 * @param {Object} param图片参数
			 */
			upload_cover_picture(param){
				this.uploadFile(param.file, "cover_picture");
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
				this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["release_time"]) > 9999){
					this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/cleaning_knowledge/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/cleaning_knowledge/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/cleaning_knowledge/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/cleaning_knowledge/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/cleaning_knowledge/view','get');
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
