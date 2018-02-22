<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>

$(document).ready(function(){
			function logoutCB(res){
				alert("로그아웃 완료!");
				location.href="${root}/path/db/main";
			}
			var au = new AjaxUtil("${root}/user/logout", null, "get");
			au.send(logoutCB);
	})
</script>
<body>

</body>
</html>