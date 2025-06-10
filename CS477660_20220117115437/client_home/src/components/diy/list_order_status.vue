<template>
	<div class="diy_home diy_list diy_order_status" id="diy_order_status_list">
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods" v-for="(o, i) in list" :key="i"
				:to="'/order_status/details?order_status_id=' + o['order_status_id']">
				<div class="diy_row" v-if="$check_field('get','entry_name','/order_status/details') &&0">
					<div class="diy_title">
						<span>项目名称
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["entry_name"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','project_type','/order_status/details') &&0">
					<div class="diy_title">
						<span>项目类型
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["project_type"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','service_price','/order_status/details') &&0">
					<div class="diy_title">
						<span>服务价格
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["service_price"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','user','/order_status/details') &&0">
					<div class="diy_title">
						<span>用户
						</span>
					</div>
					<div class="diy_field diy_uid">
						<span>
							{{ get_user_user(o['user']) }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','time_of_appointment','/order_status/details') &&0">
					<div class="diy_title">
						<span>预约时间
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["time_of_appointment"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','order_status','/order_status/details') &&0">
					<div class="diy_title">
						<span>订单状态
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["order_status"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','service_personal','/order_status/details') &&0">
					<div class="diy_title">
						<span>服务人员
						</span>
					</div>
					<div class="diy_field diy_uid">
						<span>
							{{ get_user_service_personal(o['service_personal']) }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','remarks','/order_status/details') &&0">
					<div class="diy_title">
						<span>备注
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["remarks"] }}
						</span>
					</div>
				</div>
			</router-link>
		</div>
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','entry_name')">项目名称
					</th>
					<th class="diy_title" v-if="$check_field('get','project_type')">项目类型
					</th>
					<th class="diy_title" v-if="$check_field('get','service_price')">服务价格
					</th>
					<th class="diy_title" v-if="$check_field('get','user')">用户
					</th>
					<th class="diy_title" v-if="$check_field('get','time_of_appointment')">预约时间
					</th>
					<th class="diy_title" v-if="$check_field('get','order_status')">订单状态
					</th>
					<th class="diy_title" v-if="$check_field('get','service_personal')">服务人员
					</th>
					<th class="diy_title" v-if="$check_field('get','remarks')">备注
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list">
					<td class="diy_field diy_text" v-if="$check_field('get','entry_name')">
						<span>
							{{ o["entry_name"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','project_type')">
						<span>
							{{ o["project_type"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','service_price')">
						<span>
							{{ o["service_price"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','user')">
						<span>
							{{ get_user_user(o['user']) }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','time_of_appointment')">
						<span>
							{{ o["time_of_appointment"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','order_status')">
						<span>
							{{ o["order_status"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','service_personal')">
						<span>
							{{ get_user_service_personal(o['service_personal']) }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','remarks')">
						<span>
							{{ o["remarks"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
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
			get_user_user(id){
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
			get_user_service_personal(id){
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
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

