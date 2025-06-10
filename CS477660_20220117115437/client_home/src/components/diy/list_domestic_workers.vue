<template>
	<div class="diy_home diy_list diy_domestic_workers" id="diy_domestic_workers_list">
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods" v-for="(o, i) in list" :key="i"
				:to="'/domestic_workers/details?domestic_workers_id=' + o['domestic_workers_id']">
				<div class="diy_row" v-if="$check_field('get','full_name','/domestic_workers/details') &&1">
					<div class="diy_title">
						<span>姓名
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["full_name"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','gender','/domestic_workers/details') &&0">
					<div class="diy_title">
						<span>性别
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["gender"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','telephone','/domestic_workers/details') &&1">
					<div class="diy_title">
						<span>电话
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["telephone"] }}
						</span>
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','head_portrait','/domestic_workers/details') &&1">
					<div class="diy_title">
						<span>头像
						</span>
					</div>
					<div class="diy_field diy_img">
						<img :src="$fullUrl(o['head_portrait'])" style="width:100%;height:100%" />
					</div>
				</div>
				<div class="diy_row" v-if="$check_field('get','curriculum_vitae','/domestic_workers/details') &&0">
					<div class="diy_title">
						<span>个人简历
						</span>
					</div>
					<div class="diy_field diy_text">
						<span>
							{{ o["curriculum_vitae"] }}
						</span>
					</div>
				</div>
			</router-link>
		</div>
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','full_name')">姓名
					</th>
					<th class="diy_title" v-if="$check_field('get','gender')">性别
					</th>
					<th class="diy_title" v-if="$check_field('get','telephone')">电话
					</th>
					<th class="diy_title" v-if="$check_field('get','head_portrait')">头像
					</th>
					<th class="diy_title" v-if="$check_field('get','curriculum_vitae')">个人简历
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list">
					<td class="diy_field diy_text" v-if="$check_field('get','full_name')">
						<span>
							{{ o["full_name"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','gender')">
						<span>
							{{ o["gender"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','telephone')">
						<span>
							{{ o["telephone"] }}
						</span>
					</td>
					<td class="diy_field" v-if="$check_field('get','head_portrait')">
						<img class="diy_img" :src="o['head_portrait']" />
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','curriculum_vitae')">
						<span>
							{{ o["curriculum_vitae"] }}
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
			};
		},
		methods: {
		},
		created() {
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

