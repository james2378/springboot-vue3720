<template>
	<div>
		<b-form-group v-if="$check_register_field('add','gender','/housekeeping_user/view')" id="input-group-0" label="性别" label-for="input-0">
			<b-form-input id="input-0" v-model="form['gender']" type="text" placeholder="性别" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','community','/housekeeping_user/view')" id="input-group-1" label="所在小区" label-for="input-1">
			<b-form-input id="input-1" v-model="form['community']" type="text" placeholder="所在小区" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','current_address','/housekeeping_user/view')" id="input-group-2" label="现居住址" label-for="input-2">
			<b-form-input id="input-2" v-model="form['current_address']" type="text" placeholder="现居住址" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','domestic_companies_','/housekeeping_user/view')" id="input-group-3" label="家政公司" label-for="input-3">
			<b-form-input id="input-3" v-model="form['domestic_companies_']" type="text" placeholder="家政公司" trim></b-form-input>
		</b-form-group>
		<div v-if="$check_register_field('add','authentication_picture','/housekeeping_user/view')">
			<div>认证图片</div>
			<div>
				<input type="file" @change="uploadFile($event.target.files,'authentication_picture')" />
				<img v-if="form['authentication_picture']" style="width: 50px;height: 50px;" :src="$fullUrl(form['authentication_picture'])" />
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		model: {
			prop: "form",
			event: "change"
		},
		props: {
			form: {
				type: Object,
				default: () => {
					return {

					}
				}
			}
		},
		data(){
			return {
				url_upload: "~/api/housekeeping_user/upload?",
			}
		},
		methods: {
			// 下拉数据列表转换
			getDropList(e){
				let resultArr = [];
				if(e){
					let arr = e.split(",");
					arr.forEach(item => {
						resultArr.push({
							value: item,
							text: item
						})
					});
				}
				return resultArr;
			},
			/**
			 * 上传图片
			 * @param {Object} file 文件对象
			 * @param {key} 保存键名
			 */
			uploadFile(file, key = "img") {
				var _this = this;
				var form = new FormData() // FormData 对象
				form.append('file', file[0]) // 文件对象
				this.$upload(this.url_upload, form, function(json) {
					if (json.result) {
						// _this.form[key] = json.result.url;
						_this.$delete(_this.form,key);
						_this.$set(_this.form,key,json.result.url);
						console.log(_this.form[key])
					} else {
						_this.$toast('上传失败！');
					}
				});
			},
		}
	}
</script>

<style>
</style>
