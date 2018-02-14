<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign UP</title>
</head>

<script>
   var winF,popW;
   var isCheckId = 0;
   $(document).ready(function(){
	 
      winF = new dhtmlXWindows();
      winF.attachViewportTo("winVP");
      winF.setSkin("dhx_skyblue");
      popW = winF.createWindow("win1",20,30,420,500);
      //popW.hide(); 
      popW.button("close").hide();
      popW.button("minmax").hide();
      popW.button("park").hide();
      popW.setText("Login"); 

      winF.window("win1").centerOnScreen();
      winF.window("win1").denyMove();
      winF.window("win1").denyResize();
      
      var formObj = [
				{type:"settings", offsetTop:12, name:"join", labelAlign:"left", inputHeigth:"auto"},
				{type:"input", name:"uName", label:"NAME : ", required:true},
				{type:"block", blockOffset:0, list:[
					
					{type:"input", name:"uId", label:"ID : ", required:true},
		            {type: "newcolumn"},
		            {type:"button", name:"checkId", value:"ID Check"},
		            ]},
	            
	            {type:"password", name:"uPwd", label:"PASSWORD : ", required:true},
	            {type:"input", name:"uEmail", label:"Email : ", required:true},
	            {type:"label", label:"관리자권한 : ", list:[
	            	{type: "radio", name:"uAdmin", value:"1", label:"예 "},
	            	{type: "newcolumn"},
	            	{type: "radio", name:"uAdmin", value:"0", label:"   아니요 ", checked:true}
	            ]},
	            {type:"block", blockOffset:0, list:[
	            	{type:"button", name:"joinBtn", value:"Join"},
	            	{type: "newcolumn"},
	                {type:"button", name:"cancelBtn", value:"Cencel"},
	                {type: "newcolumn"},
	                {type:"button", name:"backBtn", value:"Back"}
    				]}
   			];
      
      var form = popW.attachForm(formObj,true);
      
      form.attachEvent("onButtonClick",function(id){
         if(id=="joinBtn"){
        	if(form.validate()){
        		if(isCheckId==0){
        			alert("아이디 중복체크를 먼저 하시오");
        		}else if(isCheckId==1){
                	var aud = new AjaxUtilDx("${root}/user/join", form);
                	aud.send(callback);  	
                }
        	}
            
         }else if(id=="cancelBtn"){
            form.clear();
         }else if(id=="backBtn"){
        	 form.hide();
         }else if(id=="checkId"){
        	 var uId = form.getItemValue("uId");
        	 if(uId!=""){
             	var aud = new AjaxUtilDx("${root}/user/checkId", form);
             	aud.send(checkCB);  	
             }else{
            	 alert("아이디를 입력하시오");
             }
         }
        	 
      });
   })
function callback(res){
	     alert(res.msg);
   }
   
function checkCB(res){
	     alert(res.msg);
	     if(res.biz){
	    	 isCheckId=1;
	     }
 }   

</script>
<body>
   <div id="winVP"></div>
</body>
</html>