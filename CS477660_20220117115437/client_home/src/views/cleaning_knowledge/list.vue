<template>
	<div class="diy_list page_cleaning_knowledge" id="cleaning_knowledge_list">
		<div class="warp">
			<div class="container">
				<div class="row">
					<div class="col">
						<span class="title">保洁常识列表</span>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">
							<b-form-input size="sm" class="mr-sm-2" placeholder="标题搜索" v-model="query['title']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="标签搜索" v-model="query['label']" />
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
						<list_cleaning_knowledge :list="list" />
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
	import list_cleaning_knowledge from "@/components/diy/list_cleaning_knowledge.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_cleaning_knowledge
		},
		data() {
			return {
				url_get_list: "~/api/cleaning_knowledge/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"title": "", // 标题
					"label": "", // 标签
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
						name: "标题正序",
						value: "`title` asc",
					},
					{
						name: "标题倒序",
						value: "`title` desc",
					},
					{
						name: "标签正序",
						value: "`label` asc",
					},
					{
						name: "标签倒序",
						value: "`label` desc",
					},
				],
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
			 * 筛选
			 */

			/**
			 * 重置
			 */
			reset() {
				this.query.title = ""
				this.query.label = ""
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
		}
	}
</script>

<style>
</style>
