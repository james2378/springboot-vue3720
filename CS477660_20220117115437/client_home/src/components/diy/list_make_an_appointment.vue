<template>
	<div class="diy_home diy_list diy_make_an_appointment" id="diy_make_an_appointment_list">
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods" v-for="(o, i) in list" :key="i"
				:to="'/make_an_appointment/details?make_an_appointment_id=' + o['make_an_appointment_id']">
				<div class="diy_row" v-if="$check_field('get','entry_name','/make_an_appointment/details') &&0">
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
				<div class="diy_row" v-if="$check_field('get','project_type','/make_an_appointment/details') &&0">
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
				<div class="diy_row" v-if="$check_field('get','project_price','/make_an_appointment/details') &&0">
					<div class="diy_title">
						<span>项目价格
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["project_price"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','user','/make_an_appointment/details') &&0">
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
				<div class="diy_row" v-if="$check_field('get','door_time','/make_an_appointment/details') &&0">
					<div class="diy_title">
						<span>上门时间
						</span>
					</div>
					<div class="diy_field diy_datetime">
						<span>
							{{ $toTime(o["door_time"],"yyyy-MM-dd hh:mm:ss") }}
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
					<th class="diy_title" v-if="$check_field('get','project_price')">项目价格
					</th>
					<th class="diy_title" v-if="$check_field('get','user')">用户
					</th>
					<th class="diy_title" v-if="$check_field('get','door_time')">上门时间
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
					<td class="diy_field diy_text" v-if="$check_field('get','project_price')">
						<span>
							{{ o["project_price"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','user')">
						<span>
							{{ get_user_user(o['user']) }}
						</span>
					</td>
					<td class="diy_field diy_datetime" v-if="$check_field('get','door_time')">
						<span>
							{{ $toTime(o["door_time"] ,"yyyy-MM-dd hh:mm:ss") }}
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
		},
		created() {
			this.get_list_user_user();
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

