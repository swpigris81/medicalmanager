<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<script type="text/javascript">
$(function(){
	$("#tt").tree({
		onClick:function(node){
			alert(node.text);
		}
	});
});
</script>
<html>

<body>
	<c:if test="${childList != null && fn:length(childList) > 0}">
		<ul id="tt" class="easyui-tree">
			<li>
				<ul>
					<c:forEach items="${childList }" var="menu" varStatus="status">
						<li><span>${menu.menuName}</span></li>
					</c:forEach>
				</ul>
			</li>
		</ul>
	</c:if>
</body>
</html>
