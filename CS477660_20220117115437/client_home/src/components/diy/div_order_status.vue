<template>
	<div class="diy_details diy_div_order_status">
		<div class="warp">
			<div class="container">
				<div class="row">
				<div class="col-12 col-md-6" v-for="(item,index) in eleList" :key="item+index" v-show="$check_field('get',item.name)">
					<div class="view">
						<div class="diy_title">
							<span v-text="item.title"></span>
						</div>
						<div class="diy_field diy_img" v-if="item.type == '图片'">
							<img :src="$fullUrl(obj[item.name])" />
						</div>
						<div class="diy_field diy_uid" v-else-if="item.type == 'UID'">
							<span>
								{{ get_user_info(obj[item.name]) }}
							</span>
						</div>
						<div class="diy_field diy_video" v-else-if="item.type == '视频'">
							<router-link :to="'/media/video?filename=' + $fullUrl(obj[item.name])" v-if="obj[item.name]" >
								<span>
									查看视频
								</span>
							</router-link>
						</div>
						<div class="diy_field diy_music" v-else-if="item.type == '音频'">
							<audio v-if="obj[item.name]" style="text-align: left" :src="$fullUrl(obj[item.name])" controls></audio>
						</div>
						<div class="diy_field diy_music" v-else-if="item.type == '文件'">
							<a :href="$fullUrl(obj[item.name])" target="_blank" style="color: rgb(64, 158, 255);">点击下载</a>
						</div>
						<div class="diy_field diy_date" v-else-if="item.type == '日期'">
							<span>
								{{ $toTime(obj[item.name] ,"yyyy-MM-dd") }}
							</span>
						</div>
						<div class="diy_field diy_time" v-else-if="item.type == '时间'">
							<span>
								{{ $toTime(obj[item.name] ,"hh:mm:ss") }}
							</span>
						</div>
						<div class="diy_field diy_datetime" v-else-if="item.type == '日长'">
							<span>
								{{ $toTime(obj[item.name] ,"yyyy-MM-dd hh:mm:ss") }}
							</span>
						</div>
						<div class="diy_field diy_phone"  v-else-if="item.type == '电话' || item.type == '手机'">
							<span>
								{{ obj[item.name] }}
							</span>
						</div>
						<div class="diy_field diy_number" v-else-if="item.type == '数字'">
							<span>
								{{ obj[item.name] }}
							</span>
						</div>
						<div class="diy_field diy_desc" v-else-if="item.type == '多文本'">
							<span>
								{{ obj[item.name] }}
							</span>
						</div>
						<div class="diy_field diy_html"  v-else-if="item.type == '编辑'" v-html="obj[item.name]" >

						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ obj[item.name] }}
							</span>
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		components: {

		},
		props: {
			obj: {
				type: Object,
				default: function() {
					return {};
				},
			}
		},
		data() {
			return {
				listType: [
						{
							title: "项目名称",
							name: "entry_name",
							type: "文本"
						},
						{
							title: "项目类型",
							name: "project_type",
							type: "文本"
						},
						{
							title: "服务价格",
							name: "service_price",
							type: "文本"
						},
						{
							title: "用户",
							name: "user",
							type: "UID"
						},
						{
							title: "预约时间",
							name: "time_of_appointment",
							type: "文本"
						},
						{
							title: "订单状态",
							name: "order_status",
							type: "下拉"
						},
						{
							title: "服务人员",
							name: "service_personal",
							type: "UID"
						},
						{
							title: "备注",
							name: "remarks",
							type: "文本"
						},
				],
				// 用户列表
				list_user_user: [],
				// 用户列表
				list_user_service_personal: [],
			};
		},
		methods: {
			/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_user() {
				var json = await this.$get("~/api/user/get_list?user_group=普通用户");
				if(json.result && json.result.list){
					this.list_user_user = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_info(id){
				var obj = this.list_user_user.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
			/**
			 * 获取家政用户用户列表
			 */
			async get_list_user_service_personal() {
				var json = await this.$get("~/api/user/get_list?user_group=家政用户");
				if(json.result && json.result.list){
					this.list_user_service_personal = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_info(id){
				var obj = this.list_user_service_personal.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
		},
		created() {
			this.get_list_user_user();
			this.get_list_user_service_personal();
		},
		computed:{
			eleList(){
					let arr = [];
					let _source = JSON.parse(JSON.stringify(this.listType));
					this.listType.forEach(item => {
						if(item.type == "编辑"){
							arr.push(item);
							_source.splice(_source.findIndex(i => i.type == "编辑"), 1)
						}
					})				
					return [..._source, ...arr];
				}
		}
	};
</script>

<style>

</style>
