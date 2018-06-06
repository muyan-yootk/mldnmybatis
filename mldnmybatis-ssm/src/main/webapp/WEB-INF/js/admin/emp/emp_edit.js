window.onload = function() {
	document.getElementById("ename").addEventListener("blur",function(){
		validateEname() ;
	},false) ;
	document.getElementById("jid").addEventListener("blur",function(){
		validateJid() ;
	},false) ;
	document.getElementById("did").addEventListener("blur",function(){
		validateDid() ;
	},false) ;
	document.getElementById("sal").addEventListener("blur",function(){
		validateSal() ;
	},false) ;
	document.getElementById("myForm").addEventListener("submit",
		function(event){
		if (!(validateEname() & validateJid() & 
				validateDid() & validateSal())) {	// 返回false表示正确
			event.preventDefault() ;	// 阻止表单提交
		}
	},false) ;
}
function validateJid() {
	return validateEmpty("jid") ;
}
function validateDid() {
	return validateEmpty("did") ;
}
function validateEname() {
	return validateEmpty("ename") ;
}
function validateSal() {
	return validateRegex("sal",/^\d+(\.\d{1,2})?$/) ;
}