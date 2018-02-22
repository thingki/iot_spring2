<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script>

   var winF,popW;
   $(document).ready(function(){
      winF = new dhtmlXWindows();
      winF.setSkin("dhx_skyblue");
      popW = winF.createWindow("win1", "center", "center", 320, 300);

      popW.button("close").hide();
      popW.button("minmax").hide();
      popW.button("park").hide();
      winF.window("win1").centerOnScreen();
      
      popW.setText("Login"); 


      
      var formObj = [
                 
	                  {type: "button", name:"loginBtn",value: "로그인"},
	                  {type: "newcolumn"},
	                  {type: "button", name:"cancelBtn",value: "취소"},
	                  {type: "newcolumn"},
	                  {type: "button", name:"joinBtn",value: "회원가입"},
	                  {type: "newcolumn"},
	                  {type: "image", name: "heidisql", imageWidth: 50, imageHeight: 50, url: "${rPath}/imgs/icon/heidisql.png"}
         ];
      
      var form = popW.attachForm(formObj,true);
      
      form.attachEvent("onButtonClick",function(id){
         if(id=="loginBtn"){
            if(form.validate()){
               form.send("${root}/user/login", "post", callback);
            }
         }else if(id=="cancelBtn"){
            form.clear();
         }else if(id=="joinBtn"){
            location.href="${pPath}/user/join";
         }
      });
      
     
   });
   
   function callback(loader, res){
      if(loader.xmlDoc.status == 200){
         var res = JSON.parse(res);
         alert(res.msg);
         if(res.biz){
            location.href="${root}/path/db/main";
         }
      }else{  
         console.log(res);
      }
   }
</script>
<body>

</body>
</html>