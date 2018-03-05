<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
var urlStr = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=5a65d41ca88992e25db6d0c0d433a4ed&targetDt=20180304";
function KobisOpenAPIRestService(){
		$.ajax({
			type: "get",
			url : urlStr,
			success:function(res){
				var retVal = eval(res.boxOfficeResult.dailyBoxOfficeList);
				for(e of retVal){
					console.log(e);	
				}
				send(retVal);
					
			},
			error:function(jqXHR, textStatus, err){
				throw new KobisOpenAPIError(jqXHR.responseText);
			},
			dataType: "json"
		});
};
function send(retVal){
	retVal = JSON.stringify(retVal);
    $.ajax({ 
        type     : "post"
    ,   url      : "${root}/team/movie"
    ,   data     : retVal
    ,   success : function(){
    	alert("보냄");	
    }
    })
    }
 
   

</script>
<body>
<h1 style="color:white" onclick=KobisOpenAPIRestService()>$
</h1>

</body>
</html>