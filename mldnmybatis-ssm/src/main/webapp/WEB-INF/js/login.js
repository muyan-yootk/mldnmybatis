window.onload = function() {
	document.getElementById("mid").addEventListener("blur",function(){
		validateMid() ;
	},false) ;
	document.getElementById("password").addEventListener("blur",function(){
		validatePassword() ;
	},false) ;
	document.getElementById("myForm").addEventListener("submit",
		function(event){
		if (!(validateMid() & validatePassword())) {	// 返回false表示正确
			event.preventDefault() ;	// 阻止表单提交
		}
	},false) ;
}

function validateMid() {	// 进行验证处理
	return validateEmpty("mid") ;
}
function validatePassword() {	// 进行验证处理
	return validateEmpty("password") ;
}