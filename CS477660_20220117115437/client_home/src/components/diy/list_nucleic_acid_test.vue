<template>
	<div class="diy_home diy_list diy_nucleic_acid_test" id="diy_nucleic_acid_test_list">
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods" v-for="(o, i) in list" :key="i"
				:to="'/nucleic_acid_test/details?nucleic_acid_test_id=' + o['nucleic_acid_test_id']">
				<div class="diy_row" v-if="$check_field('get','temperature','/nucleic_acid_test/details') &&0">
					<div class="diy_title">
						<span>体温
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["temperature"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','domestic_workers','/nucleic_acid_test/details') &&0">
					<div class="diy_title">
						<span>家政人员
						</span>
					</div>
					<div class="diy_field diy_uid">
						<span>
							{{ get_user_domestic_workers(o['domestic_workers']) }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','contact_number','/nucleic_acid_test/details') &&0">
					<div class="diy_title">
						<span>联系电话
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["contact_number"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','community','/nucleic_acid_test/details') &&0">
					<div class="diy_title">
						<span>所在小区
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["community"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','detailed_address','/nucleic_acid_test/details') &&0">
					<div class="diy_title">
						<span>详细地址
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["detailed_address"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','nucleic_acid_date','/nucleic_acid_test/details') &&0">
					<div class="diy_title">
						<span>核酸日期
						</span>
					</div>
					<div class="diy_field diy_date">
						<span>
							{{ $toTime(o["nucleic_acid_date"],"yyyy-MM-dd") }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','health_code','/nucleic_acid_test/details') &&0">
					<div class="diy_title">
						<span>健康码
						</span>
					</div>
					<div class="diy_field diy_img">
						<img :src="$fullUrl(o['health_code'])" style="width:100%;height:100%" />
					</div>
				</div>
			</router-link>
		</div>
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','temperature')">体温
					</th>
					<th class="diy_title" v-if="$check_field('get','domestic_workers')">家政人员
					</th>
					<th class="diy_title" v-if="$check_field('get','contact_number')">联系电话
					</th>
					<th class="diy_title" v-if="$check_field('get','community')">所在小区
					</th>
					<th class="diy_title" v-if="$check_field('get','detailed_address')">详细地址
					</th>
					<th class="diy_title" v-if="$check_field('get','nucleic_acid_date')">核酸日期
					</th>
					<th class="diy_title" v-if="$check_field('get','health_code')">健康码
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list">
					<td class="diy_field diy_text" v-if="$check_field('get','temperature')">
						<span>
							{{ o["temperature"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','domestic_workers')">
						<span>
							{{ get_user_domestic_workers(o['domestic_workers']) }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','contact_number')">
						<span>
							{{ o["contact_number"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','community')">
						<span>
							{{ o["community"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','detailed_address')">
						<span>
							{{ o["detailed_address"] }}
						</span>
					</td>
					<td class="diy_field diy_date" v-if="$check_field('get','nucleic_acid_date')">
						<span>
							{{ $toTime(o["nucleic_acid_date"] ,"yyyy-MM-dd") }}
						</span>
					</td>
					<td class="diy_field" v-if="$check_field('get','health_code')">
						<img class="diy_img" :src="o['health_code']" />
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
				list_user_domestic_workers: [],
			};
		},
		methods: {
			/**
			 * 获取家政用户用户列表
			 */
			async get_list_user_domestic_workers() {
				var json = await this.$get("~/api/user/get_list?user_group=家政用户");
				if(json.result && json.result.list){
					this.list_user_domestic_workers = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_domestic_workers(id){
				var obj = this.list_user_domestic_workers.getObj({"user_id":id});
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
			this.get_list_user_domestic_workers();
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

