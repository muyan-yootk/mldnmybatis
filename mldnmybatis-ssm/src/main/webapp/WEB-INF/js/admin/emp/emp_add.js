window.onload = function() {
	document.getElementById("name").addEventListener("blur",function(){
		validateName() ;
	},false) ;
	document.getElementById("job").addEventListener("blur",function(){
		validateJob() ;
	},false) ;
	document.getElementById("empno").addEventListener("blur",function(){
		validateEmpno() ;
	},false) ;
	document.getElementById("salary").addEventListener("blur",function(){
		validateSalary() ;
	},false) ;
	document.getElementById("myForm").addEventListener("submit",
		function(event){
		if (!(validateName() & validateJob() & 
				validateEmpno() & validateSalary())) {	// 返回false表示正确
			event.preventDefault() ;	// 阻止表单提交
		}
	},false) ;
}
function validateEmpno() {
	return validateRegex("empno",/^\d+$/) ;
}
function validateJob() {
	return validateEmpty("job") ;
}
function validateName() {
	return validateEmpty("name") ;
}
function validateSalary() {
	return validateRegex("salary",/^\d+(\.\d{1,2})?$/) ;
}