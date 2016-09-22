function $(id){
	return document.getElementById(id);
}
window.onload = function(){
	$('regname').focus();
	var cname1,cname2,cpwd1,cpwd2,cemail;
	function chkreg(){
		if((cname1 == 'yes') && (cname2 == 'yes') && (cpwd1 == 'yes') && (cpwd2 == 'yes')&&(cemail='yes')){
			$('regbtn').disabled = false;
		}else{
			$('regbtn').disabled = true;
		}
	}
	$('regname').onkeyup = function (){
		name = $('regname').value;
		cname2 = '';
		if(name.match(/^[a-zA-Z_]*/) == ''){
			$('namediv').innerHTML = '<font color=red>必须以字母或下划线开头</font>';
			cname1 = '';
		}else if(name.length < 2){
			$('namediv').innerHTML = '<font color=red>注册名称必须大于等于2位</font>';
			cname1 = '';
		}else{
			$('namediv').innerHTML = '<font color=green>注册名称符合标准</font>';
			cname1 = 'yes';
		}
		chkreg();
	}
	//验证是否存在该用户
	$('regname').onblur = function(){  //onblur失去焦点
	var xmlhttp = null;
    if(window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }else{
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
		name = $('regname').value;
		if(cname1 == 'yes'){
			xmlhttp.open('get','RegCheck.do?name='+name,true);
			xmlhttp.onreadystatechange = function(){
				if(xmlhttp.readyState == 4){
					if(xmlhttp.status == 200){
						var msg = xmlhttp.responseText;
						if(msg == '1'){
							$('namediv').innerHTML="<font color=green>恭喜您，该用户名可以使用!</font>";
							cname2 = 'yes';
						}else if(msg == '2'){
							$('namediv').innerHTML="<font color=red>该用户已经存在！</font>";
							cname2 = '';
						}else{
							$('namediv').innerHTML="<font color=red>"+msg+"</font>";
							cname2 = '';
						}
					}
				}
				chkreg();
			}
			xmlhttp.send(null);
		}
	}
	//验证密码
	$('regpwd1').onkeyup = function(){
		pwd = $('regpwd1').value;
		pwd2 = $('regpwd2').value;
		if(pwd.length < 6){
			$('pwddiv1').innerHTML = '<font color=red>密码长度最少需要6位</font>';
			cpwd1 = '';
		}else if(pwd.length >= 6 && pwd.length < 12){
			$('pwddiv1').innerHTML = '<font color=green>密码符合要求。密码强度：弱</font>';
			cpwd1 = 'yes';
		}else if((pwd.match(/^[0-9]*$/)!=null) || (pwd.match(/^[a-zA-Z]*$/) != null )){
			$('pwddiv1').innerHTML = '<font color=green>密码符合要求。密码强度：中</font>';
			cpwd1 = 'yes';
		}else{
			$('pwddiv1').innerHTML = '<font color=green>密码符合要求。密码强度：高</font>';
			cpwd1 = 'yes';
		}
		if(pwd2 != '' && pwd != pwd2){
			$('pwddiv2').innerHTML = '<font color=red>两次密码不一致!</font>';
			cpwd2 = '';
		}else if(pwd2 != '' && pwd == pwd2){
			$('pwddiv2').innerHTML = '<font color=green>密码输入正确</font>';
			cpwd2 = 'yes';
		}
		chkreg();
	}
	//验证确认密码
	$('regpwd2').onkeyup = function(){
		pwd1 = $('regpwd1').value;
		pwd2 = $('regpwd2').value;
		if(pwd1!=''&&pwd1 != pwd2){
			$('pwddiv2').innerHTML = '<font color=red>两次密码不一致!</font>';
			cpwd2 = '';
		}
		else{
			$('pwddiv2').innerHTML = '<font color=green>密码输入正确</font>';
			cpwd2 = 'yes';
			chkreg();
		}
	}
	var level;
	    function check_radio() {
        var flag = 0;
        var _radio = document.getElementsByName("level");//获取单选框集合
         for (var i = 0; i < _radio.length; i++)
            if (_radio[i].checked == true) {
                flag = 1;
                break;
            }
        if (!flag) {       
            return false;
        }
        else {
        	level = _radio[i].value;
        	//alert(level);
        	return true;
        }
    }
	
	//验证email
	$('email').onkeyup = function(){
		emailreg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		$('email').value.match(emailreg);
		if($('email').value.match(emailreg) == null){
			$('emaildiv').innerHTML = '<font color=red>错误的email格式</font>';
			cemail = '';
		}else{
			$('emaildiv').innerHTML = '<font color=green>输入正确</font>';
			cemail = 'yes';			
		}
		chkreg();
	}                       

	//显示/隐藏详细信息
	$('morebtn').onclick = function(){
		
		if($('morediv').style.display == ''){
			$('morediv').style.display = 'none';
		}else{
			$('morediv').style.display = '';
		}
	}
	//登录按钮
	$('logbtn').onclick = function(){
		window.open('log.jsp','_parent','',false);
	}
	//正式注册
	$('regbtn').onclick = function(){

	if(!check_radio()){
		alert("请先选择用户级别!");
		return false;
	}
		var xmlhttp = null;
	    if(window.XMLHttpRequest){
	        xmlhttp = new XMLHttpRequest();
	    }else{
	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	    var cardtype = $('cardtype').options.value;
		//$('imgdiv').style.visibility = 'visible';
		url = 'RegInfEntry.do?subcek=1&name='+$('regname').value+'&pwd='+$('regpwd1').value+'&level='+level;
		url += '&email='+$('email').value+'&realname='+$('realname').value+'&telephone='+$('telephone').value+'&unit='+$('unit').value+'&cardnum='+$('cardnum').value+'&cardtype='+cardtype+'&note='+$('note').value;
		xmlhttp.open('get',url,true);
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState == 4){
				if(xmlhttp.status == 200){
					msg = xmlhttp.responseText;
					if(msg == "regt"){            
						alert('注册成功!');
						location='log.jsp';    //页面跳转
					}else{
						alert(msg);
					}
					//$('imgdiv').style.visibility = 'hidden';
				}
			}
		}
		xmlhttp.send(null);
	}
	
	$('resetbtn').onclick = function(){
		
		$('regname').value = "";
		$('regpwd1').value = "";
		$('regpwd2').value = "";
		$('email').value = "";
	}
}