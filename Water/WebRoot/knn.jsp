<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "level_navbar_judge.jsp" %>
<link href="css/xxx.css" rel="stylesheet" type="text/css"/>
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
	       <label>knn算法训练</label>
	       <label>训练表：</label>
	       <input id="hjr_id_xx" name="hjr_name_xx" type="text" value="real_train_data" class="input-xlarge">
	       <label>评价标准表：</label>
	       <input id="hjr_id_xx" name="hjr_name_xx" type="text" value="excel_input_standard" class="input-xlarge">
	       </br>
	       <label>【随机抽取】</label>
	       </br>
	       <label>训练数据条数：</label>
	       <input id="train_num" type="text" value="100" class="input-xlarge">
	       </br>
	       <label>验证数据条数：</label>
	       <input id="test_num" type="text" value="100" class="input-xlarge">
	       </br>
	       <label>k值：</label>
	       <input id="kValue" type="text" value="6" class="input-xlarge">
	       </br>
	       
	      <select id = "select1">  
		  <option value ="1">处理厂1</option>  
		  <option value ="2">处理厂2</option>  
		  <option value="3">处理厂3</option>  
		  </select>  
		  <select id = "select2">  
		  <option value ="1">区段1</option>  
		  <option value ="2">区段2</option>  
		  <option value="3">区段3</option>  
		  <option value="4">区段4</option> 
		  </select>  
	</br>
	     <button onclick="upload();" class="btn btn-primary"><i class="icon-save"></i>开始训练</button>
    </div>


<script>
function open_small_window()
{
	document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'
}
function upload()
{
	open_small_window();
	var train_num = $("#train_num").val();
	var test_num = $("#test_num").val();
	var select1 = $("#select1").val();
	var select2 = $("#select2").val();
	var kValue = $("#kValue").val();
 setTimeout(function () {
        	document.getElementById('pro').value = 20;;
    }, 1);
		//Ajax数据库
		$.ajax({
		    type:"post",
		    url:"knn.do",
		    data:{
			train_num:train_num,
			test_num:test_num,
			select1:select1,
			select2:select2,
			kValue:kValue
		    },
		    success:function(data,textStatus){
//             	alert(data);
             	document.getElementById('pro').value = data;
             		alert("训练结束！");
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
</script>   
<script language="javascript" src="js/xxx.js"></script>
 <%@ include file = "level_foot_model.jsp" %> 
    