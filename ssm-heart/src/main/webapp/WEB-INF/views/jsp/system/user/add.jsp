<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf"%>


	<link rel="stylesheet" href="${ctx}/js/date/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/js/date/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" media="all" href="${ctx}/datetimepicker/css/bootstrap-datetimepicker.min.css" />
	<script type="text/javascript" src="${ctx}/datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${ctx}/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/js/date/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/date/moment.js"></script>
	<script type="text/javascript" src="${ctx}/js/system/user/add.js"></script>
	<script type="text/javascript" src="${ctx}/js/moment.js"></script>

	<style type="text/css">
.col-sm-3 {
	width: 15%;
	float: left;
	text-align: right;
}

.col-sm-9 {
	width: 85%;
	float: left;
	text-align: left;
}

label[class^="btn btn-default"] {
	margin-top: -4px;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/rest/sysUser/addUser">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<div class="col-sm-3">
					<label class="control-label">用户名</label>
				</div>
				<div class="col-sm-9">
					<input type="text" class="form-control"
						placeholder="请输入用户名" name="realName" id="userName">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">账号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkacc"
						placeholder="请输入账号" name="loginName" id="accountName">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">密码</label>
				<div class="col-sm-9" style="color: red;">
					默认密码为:123456
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">生日</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<div class='input-group date' id='birthday' name="birthday">
							<input type='text' class="form-control" />
							<span class="input-group-addon">
            <span class="glyphicon glyphicon-calendar"></span>
                </span>
						</div>
					</div>

				</div>

			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">年龄</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkacc"
						   placeholder="请输入年龄" name="age" id="age">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">性别</label>
				<div class="col-sm-9">

						<select name="sex" id="sex">
                    <option value="男">男</option>
							<option value="女">女</option>
						</select>

				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer>
	</form>
	<script type="text/javascript">

        onloadurl();
	</script>

	<script type="text/javascript" src="${ctx}/notebook/notebook_files/bootstrap-filestyle.min.js"></script>
</body>
</html>