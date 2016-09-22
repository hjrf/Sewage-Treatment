function save()
{
	$("#tab").submit();
	alert("更新信息成功！");
	window.open('user.jsp','_parent','',false);
}
function hjr_updata()
{
	$("#tab2").submit();
	alert("密码更改成功！");
	window.open('user.jsp','_parent','',false);
}

function hjr_clear()
{	
	document.getElementById("hjr_id_cardnum").value="";
	document.getElementById("hjr_id_unit").value="";
	document.getElementById("hjr_id_telephone").value="";
	document.getElementById("hjr_id_email").value="@qq.com";
	document.getElementById("hjr_id_note").value="";
}
