<template>
	<div class="diy_list page_service_items" id="service_items_list">
		<div class="warp">
			<div class="container">
				<div class="row">
					<div class="col">
						<span class="title">服务项目列表</span>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">
							<b-form-input size="sm" class="mr-sm-2" placeholder="项目名称搜索" v-model="query['entry_name']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="项目类型搜索" v-model="query['project_type']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="row">
					<div class="col">
						<!-- 筛选 -->
						<div class="view">
							<b-dropdown text="项目类型" variant="outline-dark" left>
								<b-dropdown-item @click="filter_set('全部','project_type')">全部</b-dropdown-item>
							    <b-dropdown-item v-for="(o, i) in list_project_type" :key="i" @click="filter_set(o['project_type'],'project_type')" >
							        {{ o['project_type'] }}
							    </b-dropdown-item>
							</b-dropdown>
						</div>
						<!-- /筛选 -->
					</div>
				</div>
				<div class="row">
					<div class="col">
						<!-- 排序 -->
						<div class="view">
							<b-dropdown text="排序" variant="outline-dark" left>
							    <b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
							        {{ o.name }}
							    </b-dropdown-item>
							</b-dropdown>
						</div>
						<!--/排序 -->
					</div>
				</div>
				<div class="row">
					<div class="col">
						<!-- 列表 -->
						<list_service_items :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_service_items from "@/components/diy/list_service_items.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_service_items
		},
		data() {
			return {
				url_get_list: "~/api/service_items/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"entry_name": "", // 项目名称
					"project_type": "", // 项目类型
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "`create_time` desc",
					},
					{
						name: "创建时间从低到高",
						value: "`create_time` asc",
					},
					{
						name: "更新时间从高到低",
						value: "`update_time` desc",
					},
					{
						name: "更新时间从低到高",
						value: "`update_time` asc",
					},
					{
						name: "项目名称正序",
						value: "`entry_name` asc",
					},
					{
						name: "项目名称倒序",
						value: "`entry_name` desc",
					},
					{
						name: "项目类型正序",
						value: "`project_type` asc",
					},
					{
						name: "项目类型倒序",
						value: "`project_type` desc",
					},
				],
				// 项目类型列表
				"list_project_type": [],
			}
		},
		methods: {
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},
			/**
			 * 获取项目类型列表
			 */
			async get_list_project_type() {
				var json = await this.$get("~/api/type_management/get_list?");
				if (json.result) {
					this.list_project_type = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			filter_project_type(o) {
				if (o == "全部") {
					this.query["project_type"] = "";
				} else {
					this.query["project_type"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.entry_name = ""
				this.query.project_type = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},
		},
		computed: {
		},
		created() {
			/**
			 * 获取项目类型列表
			 */
			this.get_list_project_type();
		}
	}
</script>

<style>
</style>
