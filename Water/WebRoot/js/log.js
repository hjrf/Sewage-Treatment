function $(id){
	return document.getElementById(id);
}
    var code = "";  
function createCode(){  
	 code = "";
     var codeLength = 4;//验证码的长度  
     var checkCode = document.getElementById("code");   
     var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
     'S','T','U','V','W','X','Y','Z');//随机数  
     for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }  
    checkCode.value = code;//把code值赋给验证码  
}  
//校验验证码  
	function validate(){  
    var inputCode = document.getElementById("lgchk").value.toUpperCase(); //取得输入的验证码并转化为大写        
    if(inputCode.length <= 0) { //若输入的验证码长度为0  
        return false;
    }         
    else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时  
        createCode();//刷新验证码  
        document.getElementById("lgchk").value = "";//清空文本框  
        return false;
    }     
    else
    {
    	return true;
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
        	return true;
        }
    }
	
window.onload = function(){
	createCode();
	$('lgname').focus();
}