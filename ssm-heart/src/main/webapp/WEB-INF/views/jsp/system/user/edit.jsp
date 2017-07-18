<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script type="text/javascript" src="${ctx}/js/system/user/edit.js"></script>

<style type="text/css">
.col-sm-3 {
	width: 15%;
	float: left;
}

.col-sm-9 {
	width: 85%;
	float: left;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		  action="${ctx}/rest/sysUser/update">
		<input type="hidden" class="form-control checkacc"
			value="${sysUser.id}" name="id" id="id">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">用户名</label>
				<div class="col-sm-9">
					<input type="text" class="form-control"
						placeholder="请输入用户名" value="${sysUser.realName}"
						name="realName" id="realName">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">账号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkacc"
						placeholder="请输入账号" value="${sysUser.loginName}"
						name="loginName" id="loginName" readonly="readonly">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">生日</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<div class='input-group date' id='birthday' name="birthday">
							<input type='text'  name="birthday" class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd " value="${sysUser.birthday }"/>"/>
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
					<input type="text" class="form-control " name="age"
						   placeholder="请输入年龄" name="age" id="age" value="${sysUser.age}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">性别</label>
				<div class="col-sm-9">

					<select name="sex" id="sex">
						<option <c:if test="${sysUser.sex}==男">selected="selected"</c:if>>男</option>
						<option <c:if test="${sysUser.sex}==女">selected="selected"</c:if>>女</option>
					</select>

				</div>
			</div>

		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">保存</button>
			<button type="button" class="btn btn-primary btn-s-xs" id="but_close">关闭</button>
		</footer> </section>
	</form>
	<script type="text/javascript">
	onloadurl();
</script>
</body>
</html>