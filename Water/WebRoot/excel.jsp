<%@ page language="java" import="java.util.*"pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file = "level_navbar_judge.jsp" %>
<script language="javascript" src="js/excel.js"></script>
        <style> 
        .black_overlay{ 
            display: none; 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
            background-color: black; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
        .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 55%; 
            height: 15%; 
            padding: 20px; 
            border: 10px solid orange; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
    </style> 
  <div class="content">
  
   
        <div id="light" class="white_content">
        
       
        <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">点这里关闭本窗口</a>
       <progress id="pro" value="0" max="100"></progress>        
        </div> 
        <div id="fade" class="black_overlay"></div> 
  
  
 <% 
 	String tableName = request.getParameter("tableName"); 
 %>
 	<label>上传数据类型：</label>
 	<select id ="dropdown1" name = "excelName">  
 		  <option value ="0">请选择</option>  
		  <option value ="real_train_data">污水实际数据</option>  
		  <option value ="excel_input_standard">污水评价标准</option>  
		  <option value="knowledge">专家规则</option>  
	</select>  
  	  <label>自定义表名：</label>
      <input id="tbName" name="tbname" type="text" value="test" class="input-xlarge">
      </br>
      
      </br>
      <input id="excelFile" name="file" type="file"/>
      <input type="button" value="提交" onclick="upload()"/>
      </br>
        </br>
          </br>
          
    <a href="assets/WaterData.xls"> 污水数据下载</a>
  </div>
<script>

function open_small_window()
{
	document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'
}
function upload()
{
	open_small_window();
	var tableName = $("#dropdown1").val();
	var excelFile = $("#excelFile").val();
       if(excelFile=='') {alert("请选择需上传的文件!");return false;}
       if(excelFile.indexOf('.xls')==-1){alert("文件格式不正确，请选择正确的Excel文件(后缀名.xls)！");return false;}

	if(tableName == "0")
	{
		tableName = $("#tbName").val();
	}
 setTimeout(function () {
        	document.getElementById('pro').value = 20;;
    }, 1);
		//Ajax数据库
		$.ajax({
		    type:"post",
		    url:"ExcelDataInput.do",
		    data:{
			tbName:tableName,
			file:excelFile
		    },
		    success:function(data,textStatus){
//             	alert(data);
             	document.getElementById('pro').value = data;
             		alert("上传成功！");
             		document.getElementById('pro').value = 0;
             		document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'
//		    	window.location.reload();
		    },  
		    error:function(){  
            	alert("ajax数据传输出错");  
            }  
	    });
////       window.open('excel.jsp','_parent','',false);
}


</script>   
  
 <%@ include file = "level_foot_model.jsp" %> 
 