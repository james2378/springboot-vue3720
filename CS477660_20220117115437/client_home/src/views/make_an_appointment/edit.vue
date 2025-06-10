<template>
	<div class="diy_edit page_make_an_appointment" id="make_an_appointment_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','entry_name') || $check_field('add','entry_name') || $check_field('get','entry_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>项目名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_entry_name" v-model="form['entry_name']" placeholder="请输入项目名称" v-if="(form['entry_name'] && $check_field('set','entry_name')) || (!form['entry_name'] && $check_field('add','entry_name'))"  :disabled="disabledObj['entry_name_isDisabled']"/>
							<span v-else-if="$check_field('get','entry_name')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','project_type') || $check_field('add','project_type') || $check_field('get','project_type')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>项目类型:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_project_type" v-model="form['project_type']" placeholder="请输入项目类型" v-if="(form['project_type'] && $check_field('set','project_type')) || (!form['project_type'] && $check_field('add','project_type'))"  :disabled="disabledObj['project_type_isDisabled']"/>
							<span v-else-if="$check_field('get','project_type')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','project_price') || $check_field('add','project_price') || $check_field('get','project_price')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>项目价格:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_project_price" v-model="form['project_price']" placeholder="请输入项目价格" v-if="(form['project_price'] && $check_field('set','project_price')) || (!form['project_price'] && $check_field('add','project_price'))"  :disabled="disabledObj['project_price_isDisabled']"/>
							<span v-else-if="$check_field('get','project_price')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','user') || $check_field('add','user') || $check_field('get','user')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_user" :disabled="disabledObj['user_isDisabled']" v-model="form['user']" v-if="(form['user'] && $check_field('set','user')) || (!form['user'] && $check_field('add','user'))" >
								<option v-for="o in list_user_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','user')">{{ form['user'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','door_time') || $check_field('add','door_time') || $check_field('get','door_time')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>上门时间:
							</span>
						</div>
						<!-- 日长 -->
						<div class="diy_field diy_datetime">
							<input type="datetime-local" :disabled="disabledObj['door_time_isDisabled']" id="form_door_time" v-model="form['door_time']" placeholder="请输入上门时间" v-if="(form['door_time'] && $check_field('set','door_time')) || (!form['door_time'] && $check_field('add','door_time'))" />
							<span v-else-if="$check_field('get','door_time')">{{ form[''] }}</span>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/make_an_appointment/get_obj?",
				url_add: "~/api/make_an_appointment/add?",
				url_set: "~/api/make_an_appointment/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"entry_name": "",
					"project_type": "",
					"project_price": "",
					"user": 0,
					"door_time": "",
					"make_an_appointment_id": 0,
				},

				obj: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"project_price":'', // 项目价格
					"user": 0, // 用户
					"door_time": "1970-01-01 00:00:00",
					"make_an_appointment_id": 0,
				},

				// 表单字段
				form: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"project_price":'', // 项目价格
					"user": 0, // 用户
					"door_time": "1970-01-01 00:00:00",
					"make_an_appointment_id": 0,

				},
				disabledObj:{
					"entry_name_isDisabled": false,
					"project_type_isDisabled": false,
					"project_price_isDisabled": false,
					"user_isDisabled": false,
					"door_time_isDisabled": false,
				},
				// 用户列表
				list_user_user: [],

				// ID字段
				field: "make_an_appointment_id",
			}
		},
		methods: {
			/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_user() {
				// if(this.user_group !== "管理员" && this.form["user"] === 0) {
				//     this.form["user"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=普通用户");
				if(json.result && json.result.list){
					this.list_user_user = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_user(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["user"] = user_id
								_this.disabledObj['user' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="user") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/make_an_appointment/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				this.form["door_time"] = this.$toTime(parseInt(this.form["door_time"]),"yyyy-MM-dd hh:mm:ss")
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_user();
			this.get_list_user_user();
		}
	}
</script>

<style>
</style>
