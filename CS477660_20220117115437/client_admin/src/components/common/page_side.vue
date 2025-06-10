<template>
	<el-menu class="side el-menu-vertical-demo" default-active="/" @open="handleOpen" @close="handleClose"
		background-color="#545c64" text-color="#fff" active-text-color="#38f" :collapse="isCollapse" :router="true" :unique-opened="true">
		<el-menu-item index="/">
			<i class="el-icon-monitor"></i>
			<span slot="title">后台首页</span>
		</el-menu-item>
		<el-submenu index="web"
			v-show="user_group == '管理员' || $check_group(['/nav/table','/link/table','/notice/table','/slides/table','/ad/table','/message/table'])">
			<template slot="title"><i class="el-icon-star-off"></i><span>站点内容</span></template>
			<!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/auth/table')" index="/auth/table">
				<span>权限管理</span>
			</el-menu-item> -->
			<!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/link/table')" index="/link/table">
				<span>友情链接</span>
			</el-menu-item> -->
			<el-menu-item v-show="user_group == '管理员' || $check_action('/slides/table')" index="/slides/table">
				<span>轮播图</span>
			</el-menu-item>
			<!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/ad/table')" index="/ad/table">
				<span>广告</span>
			</el-menu-item> -->
			<el-menu-item v-show="user_group == '管理员' || $check_action('/notice/table')" index="/notice/table">
				<span>公告栏</span>
			</el-menu-item>
		</el-submenu>

		<el-submenu index="user"
			v-show="user_group == '管理员' || $check_group(['/user/table','/user_group/table','/auth/table'
			,'/ordinary_users/table'
			,'/housekeeping_user/table'
			])">
			<template slot="title"><i class="el-icon-user-solid"></i><span>系统用户</span></template>
			<el-menu-item index="/user/table" v-show="user_group == '管理员'"><span>管理员</span></el-menu-item>
			<el-menu-item index="/ordinary_users/table" v-show="user_group == '管理员' || $check_action('/ordinary_users/table')"><span>普通用户</span></el-menu-item>
			<el-menu-item index="/housekeeping_user/table" v-show="user_group == '管理员' || $check_action('/housekeeping_user/table')"><span>家政用户</span></el-menu-item>
			<!-- <el-menu-item index="/user_group/table"><span>用户组</span></el-menu-item> -->
		</el-submenu>
		<el-submenu index="content"
			v-show="$check_group(['/forum/table','/forum_type/table','/article/table','/article_type/table','/exam/table'])">
			<template slot="title"><i class="el-icon-warning-outline"></i><span>公共内容</span></template>
			<el-menu-item v-show="user_group == '管理员' || $check_action('/forum/table')" index="/forum/table">
				<!-- <span>论坛</span> -->
				<span>{{$page_title("/forum/table") || "论坛"}}</span>
			</el-menu-item>
			<el-menu-item v-show="user_group == '管理员' || $check_action('/forum_type/table')" index="/forum_type/table">
				<!-- <span>论坛类型</span> -->
				<span>{{$page_title("/forum_type/table") || "论坛分类"}}</span>
			</el-menu-item>
			<el-menu-item v-show="user_group == '管理员' || $check_action('/article/table')" index="/article/table">
				<!-- <span>文章</span> -->
				<span>{{$page_title("/article/table") || "文章"}}</span>
			</el-menu-item>
			<el-menu-item v-show="user_group == '管理员' || $check_action('/article_type/table')"
				index="/article_type/table">
				<!-- <span>文章类型</span> -->
				<span>{{$page_title("/article_type/table") || "文章分类"}}</span>
			</el-menu-item>
		</el-submenu>

		<el-submenu index="more" v-show="auth.length">
			<template slot="title"><i class="el-icon-s-help"></i><span>模块管理</span></template>
			<el-menu-item v-for="(o, idx) in auth" v-show="$check_action(o.path)" :index="o.path">
				<span>{{o.page_title}}</span>
			</el-menu-item>
		</el-submenu>
	</el-menu>
</template>

<script>
	export default {
		props: {
			isCollapse: {
				trye: Boolean,
				default: false
			}
		},
		data: function() {
			return {
				user_group: this.$store.state.user.user_group
			}
		},
		methods: {
			handleOpen(key, keyPath) {
				// console.log(key, keyPath);
			},

			handleClose(key, keyPath) {
				// console.log(key, keyPath);
			},

		},
		computed: {
			auth() {
				var tables = this.$store.state.web.auth;
				var lt = [];
				for (var i = 0; i < tables.length; i++) {
					var o = tables[i];
					if (o.path.indexOf("/table") !== -1) {
						let list = ["exam","article", "article_type",,"comment","forum","forum_type"];
						list.push("ordinary_users");
						list.push("housekeeping_user");
						if (list.indexOf(o.table_name) === -1) {
							lt.push(o);
						}
					}
				}
				return lt;
			}
		}
	}
</script>




<style>
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		width: 16rem;
	}
  
	.page{
		/* padding-left: 16rem!important; */
	}
	.page .warp .action_left{
		display: none;
	}
	.page .side {
		/* float: left; */
		/* position: absolute!important;
		top: 0;
		left: 0; */
		z-index: 9999999999;
		background: #545c64;
		color: #fff;
		/* overflow-y: scroll; */
		height: 100vh;
	}

	.side::-webkit-scrollbar{
		display: none;
	}
	.page .side .el-submenu .el-menu-item{
		background: rgb(48, 54, 53)!important;
		border-radius: 2px;
		margin-bottom: 2px;
	}
	.page .side .el-submenu .is-active{
		background: rgb(38, 90, 86)!important;
		color: #fff!important;
	}
	.page .side .el-submenu .el-menu-item:hover{
		background: rgb(89, 136, 128)!important;
	}
	.page .side .el-submenu .el-menu{
		position: absolute;
		left: 210px;
		top: 0;
		padding: 2px;
	}
	.page .side .is-opened .el-submenu__title{
		background: rgb(38, 90, 86)!important;
		color: #fff!important;
	}

</style>
