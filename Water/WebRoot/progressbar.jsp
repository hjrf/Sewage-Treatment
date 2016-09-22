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
            height: 55%; 
            padding: 20px; 
            border: 10px solid orange; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
    </style> 


 
    <div class="content"> 
    
       <p>示例弹出层：<a href = "javascript:void(0)" onclick = "open_small_window();">请点这里</a></p> 
        <div id="light" class="white_content">这是一个层窗口示例程序. 
        
       
        <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">点这里关闭本窗口</a>
       <progress id="pro" value="30" max="100"></progress>        
        </div> 
        <div id="fade" class="black_overlay"></div> 
    
	    <form id="hjr_form" action="xxx.do" method="get">
	       <label>xxx</label>
	       <input id="hjr_id_xx" name="hjr_name_xx" type="text" value="xxx" class="input-xlarge">
	       <button onclick="hjr_submit();" class="btn btn-primary"><i class="icon-save"></i>提交</button>
	    
	    </form>
    </div>


<script>
function hjr_submit()
{
	$("#hjr_form").submit();
	//alert('xxx成功');
}
function open_small_window()
{
	document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'
	document.getElementById('pro').value = 50;
	
	var flag = 1; 
		//Ajax数据库
		$.ajax({
		    type:"post",
		    url:"test.do",
		    data:{
			flag:flag
		    },
		    success:function(data,textStatus){
//             	alert(data);
             	document.getElementById('pro').value = data;
//		    	window.location.reload();
		    },  
		    error:function(){  
            	alert("ajax数据传输出错");  
            }  
	    });

}
</script>   
<script language="javascript" src="js/xxx.js"></script>
 <%@ include file = "level_foot_model.jsp" %> 
    