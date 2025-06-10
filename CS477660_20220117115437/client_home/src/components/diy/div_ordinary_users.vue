<template>
	<div class="diy_details diy_div_ordinary_users">
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
							title: "性别",
							name: "gender",
							type: "下拉"
						},
				],
			};
		},
		methods: {
		},
		created() {
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
