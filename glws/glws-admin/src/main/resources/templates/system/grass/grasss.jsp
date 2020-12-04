<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机场代码</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/base/airport/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<div class="clearfix nav nav-tabs nav_header">
		<p class="pull-left p-nav nav_title"><a href="${ctx}/base/airport/list">机场代码</a></p>
		<div class="pull-right p-add">
			<shiro:hasPermission name="base:airport:edit">
				<a href="${ctx}/base/airport/form"><p class="btn btn_add"><i class="fa fa-plus"></i>新建</p></a>
			</shiro:hasPermission>
		</div>
	</div>
	<form:form id="searchForm" modelAttribute="airport" action="${ctx}/base/airport/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li>
				<label style="width:60px">编码</label>
				<form:input path="code" htmlEscape="false" maxlength="50" class="input-medium input150"/>
			</li>
			<li>
				<label style="width:60px">名称</label>
				<form:input path="airportName" htmlEscape="false" maxlength="50" class="input-medium input150"/>
			</li>
			<li>
				<label>国际/国内</label>
				<form:select path="di">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sys_ID')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label style="width:50px">类型</label>
				<form:select path="airportType">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('base_airport_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属区域</label>
				<form:select path="airportArea">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('base_airport_area')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn_search" type="submit" value="查询" onclick="return page();"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编码</th>
				<th>名称</th>
				<th width="8%">类型</th>
				<th width="9%">所属区域</th>
				<th>所属公司</th>
				<th>所属城市编码</th>
				<th width="7%">国际/国内</th>
				<th>航站ID</th>
				<th width="9%">是否需要监控</th>
				<th width="9%">是否枢纽站点</th>
				<shiro:hasPermission name="base:airport:edit">
					<th width="8%">操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="airport">
			<tr>
				<td>${airport.code}</td>
				<td>${airport.airportName}</td>
				<td>
					${fns:getDictLabel(airport.airportType, "base_airport_type", "")}
				</td>
				<td>
					${fns:getDictLabel(airport.airportArea, "base_airport_area", "")}
				</td>
				<td>${airport.companyId}</td>
				<td>${airport.cityCode}</td>
				<td>
					${fns:getDictLabel(airport.di, "sys_ID", "")}
				</td>
				<td>${airport.airportId}</td>
				<td>
					${fns:getDictLabel(airport.isMonitor, "yes_no", "")}
				</td>
				<td>
					${fns:getDictLabel(airport.isJunction, "yes_no", "")}
				</td>
				<shiro:hasPermission name="base:airport:edit">
				<td class="td-icon">
    				<a href="${ctx}/base/airport/form?id=${airport.id}"  title="修改"><i class="fa fa-pencil"></i></a>
					<a href="${ctx}/base/airport/delete?id=${airport.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)" title="删除"><i class="fa fa-trash-o"></i></a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>