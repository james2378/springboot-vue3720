<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','aggregate_income_') || $check_field('add','aggregate_income_') || $check_field('set','aggregate_income_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="总计收入" prop="aggregate_income_">
					<el-input id="aggregate_income_" v-model="form['aggregate_income_']" placeholder="请输入总计收入"
							  v-if="user_group === '管理员' || (form['order_report_id'] && $check_field('set','aggregate_income_')) || (!form['order_report_id'] && $check_field('add','aggregate_income_'))"  @focus="set_aggregate_income_()" :disabled="disabledObj['aggregate_income__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','aggregate_income_')">{{form['aggregate_income_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','report_date') || $check_field('add','report_date') || $check_field('set','report_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="报表日期" prop="report_date">
					<el-date-picker :disabled="disabledObj['report_date_isDisabled']" v-if="user_group === '管理员' || (form['order_report_id'] && $check_field('set','report_date')) || (!form['order_report_id'] && $check_field('add','report_date'))" id="report_date"
						v-model="form['report_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','report_date')">{{form['report_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','entry_name') || $check_field('add','entry_name') || $check_field('set','entry_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目名称" prop="entry_name">
					<el-input id="entry_name" v-model="form['entry_name']" placeholder="请输入项目名称"
							  v-if="user_group === '管理员' || (form['order_report_id'] && $check_field('set','entry_name')) || (!form['order_report_id'] && $check_field('add','entry_name'))" :disabled="disabledObj['entry_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','entry_name')">{{form['entry_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_appointments') || $check_field('add','number_of_appointments') || $check_field('set','number_of_appointments')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预约次数" prop="number_of_appointments">
					<el-input-number id="number_of_appointments" v-model.number="form['number_of_appointments']"
						v-if="user_group === '管理员' || (form['order_report_id'] && $check_field('set','number_of_appointments')) || (!form['order_report_id'] && $check_field('add','number_of_appointments'))"></el-input-number>
					<div v-else-if="$check_field('get','number_of_appointments')">{{form['number_of_appointments']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_price') || $check_field('add','project_price') || $check_field('set','project_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目价格" prop="project_price">
					<el-input-number id="project_price" v-model.number="form['project_price']"
						v-if="user_group === '管理员' || (form['order_report_id'] && $check_field('set','project_price')) || (!form['order_report_id'] && $check_field('add','project_price'))"></el-input-number>
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
				field: "order_report_id",
				url_add: "~/api/order_report/add?",
				url_set: "~/api/order_report/set?",
				url_get_obj: "~/api/order_report/get_obj?",
				url_upload: "~/api/order_report/upload?",

				query: {
					"order_report_id": 0,
				},

				form: {
					"aggregate_income_":'', // 总计收入
					"report_date":'', // 报表日期
					"entry_name":'', // 项目名称
					"number_of_appointments":0, // 预约次数
					"project_price":0, // 项目价格
					"order_report_id": 0, // ID

				},
				disabledObj:{
					"aggregate_income__isDisabled": false,
					"report_date_isDisabled": false,
					"entry_name_isDisabled": false,
				},

			}
		},
		methods: {
			set_aggregate_income_(){
				this.form.aggregate_income_ =this.form.project_price * this.form.number_of_appointments
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
				this.form["report_date"] = this.$toTime(parseInt(this.form["report_date"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["report_date"]) > 9999){
					this.form["report_date"] = this.$toTime(parseInt(this.form["report_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/order_report/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/order_report/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/order_report/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/order_report/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/order_report/view','get');
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
