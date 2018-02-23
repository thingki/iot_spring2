<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HiediSQL</title>
</head>
<style>
</style>
<script>
var bodyLayout, dbTree, winF, popW, winDB, popDB; 
var aLay, bLay, cLay;
var bTabs, bTab1, bTab2, bTab3;
var tableInfoGrid, tableDataGrid
var resultGrids = [];
var selectDatabase, rowId;
var userDatabaseInfo;


//레벨 3 테이블을 클릭하면 받아오는 컬럼데이터와 테이블 데이터 콜백
function columnListCB(res){
	if(res.list){
		tableInfoGrid = bTabs.tabs("tableInfo").attachGrid();
		var columns = res.list[0];
		var headerStr = "";
		var colTypeStr = "";
		for(var key in columns){
			if(key=="id") continue;
			headerStr += key + ",";
			colTypeStr += "ro,";
		}
		headerStr = headerStr.substr(0, headerStr.length-1);
		colTypeStr = colTypeStr.substr(0, colTypeStr.length-1);
        tableInfoGrid.setColumnIds(headerStr);
		tableInfoGrid.setHeader(headerStr);
		tableInfoGrid.setColTypes(colTypeStr);
        tableInfoGrid.init();
		tableInfoGrid.parse({data:res.list},"js");
		console.log(res);
	}
	if(res.tableDatas){
		tableDataGrid = bTabs.tabs("tableData").attachGrid();
		var columns = res.tableDatas[0];
		var headerStr = "";
		var colTypeStr = "";
		for(var key in columns){
			if(key=="id") continue;
			headerStr += key + ",";
			colTypeStr += "ro,";
		}
		headerStr = headerStr.substr(0, headerStr.length-1);
		colTypeStr = colTypeStr.substr(0, colTypeStr.length-1);
		tableDataGrid.setColumnIds(headerStr);
		tableDataGrid.setHeader(headerStr);
		tableDataGrid.setColTypes(colTypeStr);
		tableDataGrid.init();
		tableDataGrid.parse({data:res.tableDatas},"js");
		console.log(res);
	}
}

//sql문 입력 콜백
function sqlRunCB(xhr,res){
	res = JSON.parse(res);
	cTabs = cLay.attachTabbar();
	console.log(res);
	var idx=1;
	if(res.list){
		for(var i=0; i<res.list.length; i++){
			cTabs.addTab("result"+(i+1),"result"+(i+1));
			var resList = res.list[i];
			resultGrids[i] = cTabs.tabs("result"+(i+1)).attachGrid();
			var columns = resList[0];
			var headerStr = "";
			var colTypeStr = "";
			for(var key in columns){
				if(key=="id") continue;
				headerStr += key + ",";
				colTypeStr += "ro,";
				}
			headerStr = headerStr.substr(0, headerStr.length-1);
			colTypeStr = colTypeStr.substr(0, colTypeStr.length-1);
			resultGrids[i].setColumnIds(headerStr);
			resultGrids[i].setHeader(headerStr);
			resultGrids[i].setColTypes(colTypeStr);
			resultGrids[i].init();
			resultGrids[i].parse({data:resList},"js");
		   }
	}else if(res.errorMsg){
		alert(res.errorMsg);
	}
	cTabs.tabs("result1").setActive(true);
	msgLog(res);
	
	/* use를 사용하면 해당 데이터베이스가 선택되게 하기!
	var used = ${useDatabase};
	if(used!=null){
		selectDatabase = ${useDatabase};
		dbTree.openItem(parentId);
	} */
}
//커넥션 추가 콜백
function addConnectionCB(xhr, res){
	res = JSON.parse(res);
	alert(res.msg);
	
	if(res.msg){
		alert(msg);
	}
	console.log(res.msg[0]);
}
//더블클릭하면 테이블 리스트를 레벨2에 나열 콜백
function tableListCB(res){
	var parentId = res.parentId;
	var i=1;
	for(var table of res.list){
		var id = parentId + "_" + i++;
		var text = table.tableName;
		if(table.tableComment!=""){
			text += "[" + table.tableComment + "]";
		}
		text += ":"+ table.tableSize + "KB"; 
		dbTree.addItem(id, text, parentId);
		dbTree.setUserData(id,"orgText",table.tableName);
	}
	dbTree.openItem(parentId);
}
//데이터 베이스 리스트 받아옴 콜백
function dbListCB(res){
	console.log(res);
	if(res.error){
		alert(res.error);
		return;
	}
	var parentId = res.parentId;
	for(var db of res.list){
		var id = db.id;
		var text = db.text;
		dbTree.addItem(id, text, parentId);
	}
	dbTree.openItem(parentId);
}
//레이아웃 만듬
dhtmlxEvent(window,"load",function(){
	bodyLayout = new dhtmlXLayoutObject({
	      parent: document.body,
	      pattern:"3L",
	      offsets:{
	             top: "auto",
	             right: "auto",
	             bottom: 105,
	             left: "auto"
	      }
	});
	bodyLayout.attachFooter("footDiv");
	aLay = bodyLayout.cells("a");
	aLay.setWidth(300);
	aLay.setText("Connection Info List");
	var aToolbar = aLay.attachToolbar();
	aToolbar.addButton("addcon",1,"add Connector");
	aToolbar.addButton("condb",2,"Connection");
	aToolbar.attachEvent("onClick",function(id){
		if(id=="condb"){
			rowId =dbTree.getSelectedId();
			if(!rowId){
			alert("접속할 커넥션을 선택해주세요.");
			return;
         }
		popDB.show();
      }else if(id=="addcon"){
         popW.show();
      }
   })
	var au = new AjaxUtil("${root}/connection/connector_list",null,"get");
	au.send(connectionListCB); 
	
	bLay = bodyLayout.cells("b");
 	bTabs = bLay.attachTabbar({
		align:"left",
		tabs:[
			{id:"tableInfo", text:"Table Info"},
			{id:"tableData", text:"Table Datas"},
			{id:"sql", text:"Run Sql", active:true}
		]
	});
	var sqlFormObj = [
		{type: "block", blockOffset: 10, list: [
			{type: "button", name:"runBtn", id:"runBtn", value: "실행"},
			{type: "newcolumn"},
			{type: "button", name:"resetBtn", id:"resetBtn", value: "취소"} 
		]},
		{type:"label",name:"label", label:"", list:[
			{type:"input",name:"sqlTa",label:"",required:true,
				rows:12,style:"border:1px solid #39c"}
			]}
		];	
	var sqlForm = bTabs.tabs("sql").attachForm(sqlFormObj);
	//
	var w = bLay.getWidth();
	var stylePlusWidth = w - 45;
	var Names = document.getElementsByName("sqlTa");
	for(var name of Names){
		name.style.width = stylePlusWidth + "px";	
	}
	//
	sqlForm.attachEvent("onButtonClick",function(id){
		if(id=="runBtn"){
			if(sqlForm.validate()){
	            sqlForm.send("${root}/sql/run?selectDatabase="+selectDatabase,
	            		"post", sqlRunCB);
	         }
      }else if(id=="resetBtn"){
    	  sqlForm.clear();
      }
   })
	cLay = bodyLayout.cells("c");
	
	//데이터 베이스 추가 창 만듬
	winF = new dhtmlXWindows();
	popW = winF.createWindow("win1",20,40,400,450);
	//popW.hide(); 
	popW.setText("Add Connection Info");
	winF.window("win1").centerOnScreen();
    winF.window("win1").denyMove();
    winF.window("win1").denyResize();  
	var formObj = [
		{type:"label",name:"label", label:"", list:[
			{type:"settings", offsetTop:12,name:"connectionInfo",labelAlign:"left"},
            {type:"input",name:"ciName", label:"커넥션이름",required:true},
            {type:"input",name:"ciUrl", label:"접속URL",required:true},
            {type:"input",name:"ciPort", label:"PORT번호",validate:"ValidInteger",required:true},
            {type:"input",name:"ciDatabase", label:"데이터베이스",required:true},
            {type:"input",name:"ciUser", label:"유저ID",required:true},
            {type:"password",name:"ciPwd", label:"비밀번호",required:true},
            {type:"input",name:"ciEtc", label:"설명"},
            {type: "block", blockOffset: 0, list: [
               {type: "button", name:"saveBtn",value: "저장"},
               {type: "newcolumn"},
               {type: "button", name:"resetBtn",value: "수정"},
               {type: "newcolumn"},
               {type: "button", name:"cancelBtn",value: "닫기"}
            ]}
		]}
      ];
   var form = popW.attachForm(formObj,true);
   //form.setItemValue("ciUrl", "!@#"); 이거 응용해서 데이터 넣기!!
   popW.hide();
   form.attachEvent("onButtonClick",function(id){
      if(id=="saveBtn"){
         if(form.validate()){
            form.send("${root}/connection/insert", "post", addConnectionCB);
         }
      }else if(id=="resetBtn"){
         form.clear();
      }else if(id=="cancelBtn"){
    	  popW.hide();
      }
   });
})
/*    $(window).unload(function() {
		var au = new AjaxUtil("${root}/user/logout", null, "get");
		au.send();
   }); */

function getUserDatabaseInfo(id){
	for(var key in userDatabaseInfo.list){
		var udi = userDatabaseInfo.list[key];
		if(udi.ciNo==id){
			return udi;
		}
	}
	return null;
 }
//더블클릭, 클릭부분!
function connectionListCB(res){
	userDatabaseInfo = res;
	dbTree = aLay.attachTreeView({
	items: res.list
	});
	
	
	
	dbTree.attachEvent("onDblClick",function(id){
		var level = dbTree.getLevel(id);
		if(level==2){
			var text = dbTree.getItemText(id);
			selectDatabase = text;
			var au = new AjaxUtil("${root}/connection/tables/" + text + "/" + id, null, "get");
			au.send(tableListCB); 
		}
	});
	dbTree.attachEvent("onClick",function(id){
		var level = dbTree.getLevel(id);
		if(level==3){
			var pId= dbTree.getParentId(id);
			var dbName = dbTree.getItemText(pId);
			var tableName = dbTree.getUserData(id, "orgText");
			var au = new AjaxUtil("${root}/connection/columns/" + dbName + "/" + tableName, null, "get");
			au.send(columnListCB);
		}else if(level==1){
				console.log(userDatabaseInfo);
			   //세션관리자 창
			   winDB = new dhtmlXWindows();
			   popDB = winDB.createWindow("win2",20,30,320,450);
			   popDB.setText("Session Manager"); 
			   winDB.window("win2").centerOnScreen();
			   winDB.window("win2").denyMove();
			   winDB.window("win2").denyResize();
			   var udi = getUserDatabaseInfo(id);
			   if(!udi){
				   alert("존재하지 않는 데이터 베이스 일수 있습니다. 다시 연결해주세요.")
				   return;
			   }
			   var formDBObj = [
			   		{type:"settings", offsetTop:12, name:"sessionManager",labelAlign:"left"},
			           {type:"input",name:"network", label:"네트워크 유형", value:"MySQL(TCP/IP)"},
			           {type:"input",name:"ciName", label:"세션 이름", value:udi.ciName},
			           {type:"input",name:"ciUrl", label:"호스트명/IP", value:udi.ciUrl, validate:"ValidInteger", required:true},
			           {type:"input",name:"ciUser", label:"사용자", value:udi.ciUser, required:true},
			           {type:"password",name:"ciPwd", label:"암호",required:true},
			           {type:"input",name:"ciPort", label:"포트", value:udi.ciPort, validate:"ValidInteger", required:true},
			           {type:"input",name:"ciDatabase", value:udi.ciDatabase, label:"데이터베이스"}, //value
			           {type:"input",name:"ciEtc", value:udi.ciEtc, label:"설명"},
			           {type: "block", blockOffset: 0, list: [
			              {type: "button", name:"openBtn",value: "열기"},
			              {type: "newcolumn"},
			              {type: "button", name:"cancelBtn1",value: "취소"}
			           ]}
			     ];
			   var formDB = popDB.attachForm(formDBObj,true);
			   popDB.hide();
			   
			   formDB.attachEvent("onButtonClick",function(id){
				      if(id=="openBtn"){
				    	  var au = new AjaxUtil("${root}/connection/db_list/" + rowId, null, "get");
				          au.send(dbListCB);
				          popDB.hide();
				      }else if(id=="cancelBtn1"){
				    	  popDB.hide();
				      }
				   }); 
			   //-------------------------------------------------
		} 
	});
	var waitForFinalEvent = (function() {
		var timers = {};
		return function(callback, ms, uniqueId) {
			if (!uniqueId) {
				uniqueId = "Don't call this twice without a uniqueId";
			}
			if (timers[uniqueId]) {
				clearTimeout(timers[uniqueId]);
			}
			timers[uniqueId] = setTimeout(callback, ms);
		};
	})();
	$(window).resize(function() {  
		waitForFinalEvent(function() {
			var w = bLay.getWidth();
			var stylePlusWidth = w - 45;
			var Names = document.getElementsByName("sqlTa");
			for(var name of Names){
				name.style.width = stylePlusWidth + "px";	
			}
		}, 300, "some unique string");
	});
}
</script>
<body>
	<div id="footDiv" class="my_ftr">
		<div id="resultMsg" class="text">
			<em><b>LOG</b></em>
		</div>
	</div>
</body>
</html>