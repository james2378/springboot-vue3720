<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="公司动态"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="在线交流"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/order_status/list', 'get')"
				:list="result_order_status_entry_name"
				title="订单状态项目名称"
				source_table="order_status"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/order_status/list', 'get')"
				:list="result_order_status_project_type"
				title="订单状态项目类型"
				source_table="order_status"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/order_status/list', 'get')"
				:list="result_order_status_order_status"
				title="订单状态订单状态"
				source_table="order_status"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_gender"
				title="普通用户性别"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/housekeeping_user/list', 'get')"
				:list="result_housekeeping_user_community"
				title="家政用户所在小区"
				source_table="housekeeping_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/housekeeping_user/list', 'get')"
				:list="result_housekeeping_user_domestic_companies_"
				title="家政用户家政公司"
				source_table="housekeeping_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/domestic_workers/list', 'get')"
				:list="result_domestic_workers_full_name"
				title="家政人员姓名"
				source_table="domestic_workers"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/type_management/list', 'get')"
				:list="result_type_management_project_type"
				title="类型管理项目类型"
				source_table="type_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/service_items/list', 'get')"
				:list="result_service_items_entry_name"
				title="服务项目项目名称"
				source_table="service_items"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/service_items/list', 'get')"
				:list="result_service_items_project_type"
				title="服务项目项目类型"
				source_table="service_items"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/make_an_appointment/list', 'get')"
				:list="result_make_an_appointment_door_time"
				title="预约上门时间"
				source_table="make_an_appointment"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/order_report/list', 'get')"
				:list="result_order_report_report_date"
				title="订单报表报表日期"
				source_table="order_report"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/order_report/list', 'get')"
				:list="result_order_report_entry_name"
				title="订单报表项目名称"
				source_table="order_report"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/nucleic_acid_test/list', 'get')"
				:list="result_nucleic_acid_test_nucleic_acid_date"
				title="核酸检测核酸日期"
				source_table="nucleic_acid_test"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/nucleic_acid_test/list', 'get')"
				:list="result_nucleic_acid_test_health_code"
				title="核酸检测健康码"
				source_table="nucleic_acid_test"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/cleaning_knowledge/list', 'get')"
				:list="result_cleaning_knowledge_title"
				title="保洁常识标题"
				source_table="cleaning_knowledge"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/cleaning_knowledge/list', 'get')"
				:list="result_cleaning_knowledge_label"
				title="保洁常识标签"
				source_table="cleaning_knowledge"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_order_status_entry_name":[],
			"result_order_status_project_type":[],
			"result_order_status_order_status":[],
			"result_ordinary_users_gender":[],
			"result_housekeeping_user_community":[],
			"result_housekeeping_user_domestic_companies_":[],
			"result_domestic_workers_full_name":[],
			"result_type_management_project_type":[],
			"result_service_items_entry_name":[],
			"result_service_items_project_type":[],
			"result_make_an_appointment_door_time":[],
			"result_order_report_report_date":[],
			"result_order_report_entry_name":[],
			"result_nucleic_acid_test_nucleic_acid_date":[],
			"result_nucleic_acid_test_health_code":[],
			"result_cleaning_knowledge_title":[],
			"result_cleaning_knowledge_label":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取在线交流
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取entry_name
	 */
	get_order_status_entry_name(){
		this.$get("~/api/order_status/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_status_entry_name = json.result.list;
			result_order_status_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_order_status_entry_name = result_order_status_entry_name
		 	}
		});
	},
	/**
	 * 获取project_type
	 */
	get_order_status_project_type(){
		this.$get("~/api/order_status/get_list?like=0", { page: 1, size: 10, "project_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_status_project_type = json.result.list;
			result_order_status_project_type.map(o => o.title = o['project_type'])
	  			this.result_order_status_project_type = result_order_status_project_type
		 	}
		});
	},
	/**
	 * 获取order_status
	 */
	get_order_status_order_status(){
		this.$get("~/api/order_status/get_list?like=0", { page: 1, size: 10, "order_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_status_order_status = json.result.list;
			result_order_status_order_status.map(o => o.title = o['order_status'])
	  			this.result_order_status_order_status = result_order_status_order_status
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_ordinary_users_gender(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_gender = json.result.list;
			result_ordinary_users_gender.map(o => o.title = o['gender'])
	  			this.result_ordinary_users_gender = result_ordinary_users_gender
		 	}
		});
	},
	/**
	 * 获取community
	 */
	get_housekeeping_user_community(){
		this.$get("~/api/housekeeping_user/get_list?like=0", { page: 1, size: 10, "community": this.query.word }, (json) => {
		  if (json.result) {
			var result_housekeeping_user_community = json.result.list;
			result_housekeeping_user_community.map(o => o.title = o['community'])
	  			this.result_housekeeping_user_community = result_housekeeping_user_community
		 	}
		});
	},
	/**
	 * 获取domestic_companies_
	 */
	get_housekeeping_user_domestic_companies_(){
		this.$get("~/api/housekeeping_user/get_list?like=0", { page: 1, size: 10, "domestic_companies_": this.query.word }, (json) => {
		  if (json.result) {
			var result_housekeeping_user_domestic_companies_ = json.result.list;
			result_housekeeping_user_domestic_companies_.map(o => o.title = o['domestic_companies_'])
	  			this.result_housekeeping_user_domestic_companies_ = result_housekeeping_user_domestic_companies_
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_domestic_workers_full_name(){
		this.$get("~/api/domestic_workers/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_domestic_workers_full_name = json.result.list;
			result_domestic_workers_full_name.map(o => o.title = o['full_name'])
	  			this.result_domestic_workers_full_name = result_domestic_workers_full_name
		 	}
		});
	},
	/**
	 * 获取project_type
	 */
	get_type_management_project_type(){
		this.$get("~/api/type_management/get_list?like=0", { page: 1, size: 10, "project_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_type_management_project_type = json.result.list;
			result_type_management_project_type.map(o => o.title = o['project_type'])
	  			this.result_type_management_project_type = result_type_management_project_type
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_service_items_entry_name(){
		this.$get("~/api/service_items/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_service_items_entry_name = json.result.list;
			result_service_items_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_service_items_entry_name = result_service_items_entry_name
		 	}
		});
	},
	/**
	 * 获取project_type
	 */
	get_service_items_project_type(){
		this.$get("~/api/service_items/get_list?like=0", { page: 1, size: 10, "project_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_service_items_project_type = json.result.list;
			result_service_items_project_type.map(o => o.title = o['project_type'])
	  			this.result_service_items_project_type = result_service_items_project_type
		 	}
		});
	},
	/**
	 * 获取door_time
	 */
	get_make_an_appointment_door_time(){
		this.$get("~/api/make_an_appointment/get_list?like=0", { page: 1, size: 10, "door_time": this.query.word }, (json) => {
		  if (json.result) {
			var result_make_an_appointment_door_time = json.result.list;
			result_make_an_appointment_door_time.map(o => o.title = o['door_time'])
	  			this.result_make_an_appointment_door_time = result_make_an_appointment_door_time
		 	}
		});
	},
	/**
	 * 获取report_date
	 */
	get_order_report_report_date(){
		this.$get("~/api/order_report/get_list?like=0", { page: 1, size: 10, "report_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_report_report_date = json.result.list;
			result_order_report_report_date.map(o => o.title = o['report_date'])
	  			this.result_order_report_report_date = result_order_report_report_date
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_order_report_entry_name(){
		this.$get("~/api/order_report/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_report_entry_name = json.result.list;
			result_order_report_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_order_report_entry_name = result_order_report_entry_name
		 	}
		});
	},
	/**
	 * 获取nucleic_acid_date
	 */
	get_nucleic_acid_test_nucleic_acid_date(){
		this.$get("~/api/nucleic_acid_test/get_list?like=0", { page: 1, size: 10, "nucleic_acid_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_nucleic_acid_test_nucleic_acid_date = json.result.list;
			result_nucleic_acid_test_nucleic_acid_date.map(o => o.title = o['nucleic_acid_date'])
	  			this.result_nucleic_acid_test_nucleic_acid_date = result_nucleic_acid_test_nucleic_acid_date
		 	}
		});
	},
	/**
	 * 获取health_code
	 */
	get_nucleic_acid_test_health_code(){
		this.$get("~/api/nucleic_acid_test/get_list?like=0", { page: 1, size: 10, "health_code": this.query.word }, (json) => {
		  if (json.result) {
			var result_nucleic_acid_test_health_code = json.result.list;
			result_nucleic_acid_test_health_code.map(o => o.title = o['health_code'])
	  			this.result_nucleic_acid_test_health_code = result_nucleic_acid_test_health_code
		 	}
		});
	},
	/**
	 * 获取title
	 */
	get_cleaning_knowledge_title(){
		this.$get("~/api/cleaning_knowledge/get_list?like=0", { page: 1, size: 10, "title": this.query.word }, (json) => {
		  if (json.result) {
			var result_cleaning_knowledge_title = json.result.list;
			result_cleaning_knowledge_title.map(o => o.title = o['title'])
	  			this.result_cleaning_knowledge_title = result_cleaning_knowledge_title
		 	}
		});
	},
	/**
	 * 获取label
	 */
	get_cleaning_knowledge_label(){
		this.$get("~/api/cleaning_knowledge/get_list?like=0", { page: 1, size: 10, "label": this.query.word }, (json) => {
		  if (json.result) {
			var result_cleaning_knowledge_label = json.result.list;
			result_cleaning_knowledge_label.map(o => o.title = o['label'])
	  			this.result_cleaning_knowledge_label = result_cleaning_knowledge_label
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_order_status_entry_name();
		this.get_order_status_project_type();
		this.get_order_status_order_status();
		this.get_ordinary_users_gender();
		this.get_housekeeping_user_community();
		this.get_housekeeping_user_domestic_companies_();
		this.get_domestic_workers_full_name();
		this.get_type_management_project_type();
		this.get_service_items_entry_name();
		this.get_service_items_project_type();
		this.get_make_an_appointment_door_time();
		this.get_order_report_report_date();
		this.get_order_report_entry_name();
		this.get_nucleic_acid_test_nucleic_acid_date();
		this.get_nucleic_acid_test_health_code();
		this.get_cleaning_knowledge_title();
		this.get_cleaning_knowledge_label();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_order_status_entry_name();
	  this.get_order_status_project_type();
	  this.get_order_status_order_status();
	  this.get_ordinary_users_gender();
	  this.get_housekeeping_user_community();
	  this.get_housekeeping_user_domestic_companies_();
	  this.get_domestic_workers_full_name();
	  this.get_type_management_project_type();
	  this.get_service_items_entry_name();
	  this.get_service_items_project_type();
	  this.get_make_an_appointment_door_time();
	  this.get_order_report_report_date();
	  this.get_order_report_entry_name();
	  this.get_nucleic_acid_test_nucleic_acid_date();
	  this.get_nucleic_acid_test_health_code();
	  this.get_cleaning_knowledge_title();
	  this.get_cleaning_knowledge_label();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
