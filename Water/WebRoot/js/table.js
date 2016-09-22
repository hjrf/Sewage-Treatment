function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}


function getUrlParam(name){
	//构造一个含有目标参数的正则表达式对象
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	//匹配目标参数
	var r = window.location.search.substr(1).match(reg);
	//返回参数值
	if (r!=null) return unescape(r[2]);
	return null;
}
$(function() { 
	$(".hjr_td").click(function() { 
	var td = $(this); 
	var td_id = $(this).attr("id") 
	var txt = td.text(); 
	var input = $("<input type='text'value='" + txt + "'/>"); 
	td.html(input); 
	input.click(function() { return false; }); 
	input.trigger("focus"); 
	input.blur(function() { 
	var newtxt = $(this).val(); 
	if (newtxt != txt) { 
	td.html(newtxt); 
	var tableName = getUrlParam('tableName');
	id_split = td_id.toString().split("_");
//	alert(tableName);
//	alert(id_split[0]);
//	alert(id_split[1]);
		//Ajax数据库
		$.ajax({
		    type:"post",
		    url:"UsersTable.do",
		    data:{
			tableName:tableName,
			filed:id_split[0],
		    id:id_split[1],
		    updata:newtxt
		    },
		    success:function(data,textStatus){
//             	alert(data);
		    },  
		    error:function(){  
            	alert("ajax数据传输出错");  
            }  
	    });
	} 
	else 
	{ 
	td.html(newtxt); 
	} 
	}); 
	}); 
}); 


$(function() { 
	$(".hjr_delete").click(function() { 
	var tableName = getUrlParam('tableName')
	var bt_id = $(this).attr("id") 
		//Ajax数据库
		$.ajax({
		    type:"post",
		    url:"UsersTable.do",
		    data:{
			tableName:tableName,
			bt_id:bt_id
		    },
		    success:function(data,textStatus){
//             	alert(data);
		    	window.location.reload();
		    },  
		    error:function(){  
            	alert("ajax数据传输出错");  
            }  
	    });
	}); 

}); 




window.onload=function(){
	altRows('alternatecolor');
}