<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','temperature') || $check_field('add','temperature') || $check_field('set','temperature')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="体温" prop="temperature">
					<el-input id="temperature" v-model="form['temperature']" placeholder="请输入体温"
							  v-if="user_group === '管理员' || (form['nucleic_acid_test_id'] && $check_field('set','temperature')) || (!form['nucleic_acid_test_id'] && $check_field('add','temperature'))" :disabled="disabledObj['temperature_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','temperature')">{{form['temperature']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','domestic_workers') || $check_field('add','domestic_workers') || $check_field('set','domestic_workers')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="家政人员" prop="domestic_workers">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_domestic_workers(form['domestic_workers']) }}
							<!--<el-input id="business_name" v-model="form['domestic_workers']" placeholder="请输入家政人员"-->
							<!--v-if="user_group === '管理员' || (form['nucleic_acid_test_id'] && $check_field('set','domestic_workers')) || (!form['nucleic_acid_test_id'] && $check_field('add','domestic_workers'))" :disabled="disabledObj['domestic_workers_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','domestic_workers')">{{form['domestic_workers']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['nucleic_acid_test_id'] && $check_field('set','domestic_workers')) || (!form['nucleic_acid_test_id'] && $check_field('add','domestic_workers'))" id="domestic_workers" v-model="form['domestic_workers']" :disabled="disabledObj['domestic_workers_isDisabled']">
								<el-option v-for="o in list_user_domestic_workers" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','domestic_workers')" id="domestic_workers" v-model="form['domestic_workers']" :disabled="true">
								<el-option v-for="o in list_user_domestic_workers" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="domestic_workers" v-model="form['domestic_workers']" :disabled="disabledObj['domestic_workers_isDisabled']">
							<el-option v-for="o in list_user_domestic_workers" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['nucleic_acid_test_id'] && $check_field('set','contact_number')) || (!form['nucleic_acid_test_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','community') || $check_field('add','community') || $check_field('set','community')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="所在小区" prop="community">
					<el-input id="community" v-model="form['community']" placeholder="请输入所在小区"
							  v-if="user_group === '管理员' || (form['nucleic_acid_test_id'] && $check_field('set','community')) || (!form['nucleic_acid_test_id'] && $check_field('add','community'))" :disabled="disabledObj['community_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','community')">{{form['community']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','detailed_address') || $check_field('add','detailed_address') || $check_field('set','detailed_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="详细地址" prop="detailed_address">
					<el-input id="detailed_address" v-model="form['detailed_address']" placeholder="请输入详细地址"
							  v-if="user_group === '管理员' || (form['nucleic_acid_test_id'] && $check_field('set','detailed_address')) || (!form['nucleic_acid_test_id'] && $check_field('add','detailed_address'))" :disabled="disabledObj['detailed_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','detailed_address')">{{form['detailed_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','nucleic_acid_date') || $check_field('add','nucleic_acid_date') || $check_field('set','nucleic_acid_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="核酸日期" prop="nucleic_acid_date">
					<el-date-picker :disabled="disabledObj['nucleic_acid_date_isDisabled']" v-if="user_group === '管理员' || (form['nucleic_acid_test_id'] && $check_field('set','nucleic_acid_date')) || (!form['nucleic_acid_test_id'] && $check_field('add','nucleic_acid_date'))" id="nucleic_acid_date"
						v-model="form['nucleic_acid_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','nucleic_acid_date')">{{form['nucleic_acid_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','nucleic_acid_report') || $check_field('add','nucleic_acid_report') || $check_field('set','nucleic_acid_report')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="核酸报告" prop="nucleic_acid_report">
					<div v-if="disabledObj['nucleic_acid_report_isDisabled']">
						<div v-if="$check_field('get','nucleic_acid_report')">
							<el-button type="primary" @click="$download($fullUrl(form['nucleic_acid_report']),form['nucleic_acid_report'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['nucleic_acid_test_id'] && $check_field('set','nucleic_acid_report')) || (!form['nucleic_acid_test_id'] && $check_field('add','nucleic_acid_report'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_nucleic_acid_report" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','nucleic_acid_report')">
							<el-button type="primary" @click="$download($fullUrl(form['nucleic_acid_report']),form['nucleic_acid_report'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','health_code') || $check_field('add','health_code') || $check_field('set','health_code')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="健康码" prop="health_code">
					<el-upload :disabled="disabledObj['health_code_isDisabled']" id="health_code" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_health_code"
						:show-file-list="false" v-if="user_group === '管理员' || (form['nucleic_acid_test_id'] && $check_field('set','health_code')) || (!form['nucleic_acid_test_id'] && $check_field('add','health_code'))">
						<img v-if="form['health_code']" :src="$fullUrl(form['health_code'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','health_code')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['health_code'])" :preview-src-list="[$fullUrl(form['health_code'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
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
				field: "nucleic_acid_test_id",
				url_add: "~/api/nucleic_acid_test/add?",
				url_set: "~/api/nucleic_acid_test/set?",
				url_get_obj: "~/api/nucleic_acid_test/get_obj?",
				url_upload: "~/api/nucleic_acid_test/upload?",

				query: {
					"nucleic_acid_test_id": 0,
				},

				form: {
					"temperature":'', // 体温
					"domestic_workers": 0, // 家政人员
					"contact_number":'', // 联系电话
					"community":'', // 所在小区
					"detailed_address":'', // 详细地址
					"nucleic_acid_date":'', // 核酸日期
					"nucleic_acid_report":'', // 核酸报告
					"health_code":'', // 健康码
					"nucleic_acid_test_id": 0, // ID

				},
				disabledObj:{
					"temperature_isDisabled": false,
					"domestic_workers_isDisabled": false,
					"contact_number_isDisabled": false,
					"community_isDisabled": false,
					"detailed_address_isDisabled": false,
					"nucleic_acid_date_isDisabled": false,
					"nucleic_acid_report_isDisabled": false,
					"health_code_isDisabled": false,
				},
				// 用户列表
				list_user_domestic_workers: [],
				// 用户组
				group_user_domestic_workers: "",

			}
		},
		methods: {
			/**
			 * 获取家政用户用户列表
			 */
			async get_list_user_domestic_workers() {
                // if(this.user_group !== "管理员" && this.form["domestic_workers"] === 0) {
                //     this.form["domestic_workers"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=家政用户");
                if(json.result && json.result.list){
                    this.list_user_domestic_workers = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取家政用户用户组
			 */
			async get_group_user_domestic_workers() {
				this.form["domestic_workers"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=家政用户");
				if(json.result && json.result.obj){
					this.group_user_domestic_workers = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_domestic_workers(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_domestic_workers.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="domestic_workers") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_domestic_workers(id){
				var obj = this.list_user_domestic_workers.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 上传核酸报告
			 * @param {Object} param文件参数
			 */
			upload_nucleic_acid_report(param){
				this.uploadFile(param.file, "nucleic_acid_report");
			},
			/**
			 * 上传健康码
			 * @param {Object} param图片参数
			 */
			upload_health_code(param){
				this.uploadFile(param.file, "health_code");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "家政人员":
							if(param["domestic_workers"] > 0){
								param["domestic_workers"] = this.user.user_id;
							}
							break;
					}
				}
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
				this.form["nucleic_acid_date"] = this.$toTime(parseInt(this.form["nucleic_acid_date"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["nucleic_acid_date"]) > 9999){
					this.form["nucleic_acid_date"] = this.$toTime(parseInt(this.form["nucleic_acid_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/nucleic_acid_test/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/nucleic_acid_test/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/nucleic_acid_test/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/nucleic_acid_test/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/nucleic_acid_test/view','get');
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
			this.get_list_user_domestic_workers();
			this.get_group_user_domestic_workers();
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
