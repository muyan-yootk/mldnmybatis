/**
 * 进行删除事件的公共处理定义，格式：“url?ids=id,id,...”
 * @param delBut 要进行删除事件绑定的组件ID（删除按钮）
 * @param valBox 要定义删除全选的复选框组件ID
 * @param url 要执行删除的操作
 */
function deleteHandle(delBut,valBox,url) {
	delButObject = document.getElementById(delBut) ;
	delButObject.addEventListener("click",function(){
		ids = "" ;
		boxArray = document.all(valBox) ;
		if (boxArray.length == undefined) {	// 一个元素
			if (boxArray.checked) {	// 选中了数据
				ids += boxArray.value ;
			}
		} else {
			for (x = 0 ; x < boxArray.length ; x ++) {
				if (boxArray[x].checked) {
					ids += boxArray[x].value + "," ;
				} 
			}
		}
		if (ids == "") {// 现在没有数据被选择
			alert("您还未选择任何要删除的数据。") ;
		} else {
			if (window.confirm("确定要删除这些数据吗？")) {
				window.location = url + "?ids=" + ids ;
			}
		}
	},false) ;
}
/**
 * 进行复选框全选的操作
 * @param keyEle 要触发此全选操作的组件id
 * @param eles 要参与全选的组件id
 */
function selectAllHandle(keyEle,eles) {
	keyObject = document.getElementById(keyEle) ;
	keyObject.addEventListener("click",function(){
		boxArray = document.all(eles) ;
		if (boxArray.length == undefined) {
			boxArray.checked = this.checked ;
		} else {
			for (x = 0 ; x < boxArray.length ; x ++) {
				boxArray[x].checked = this.checked ; 
			}
		}
	},false) ;
}

function validateRegex(eleId,regex) {	// 进行数据是否为null的验证
	eleObject = document.getElementById(eleId) ; // 根据传入的id获取对象
	eleDivObject = document.getElementById(eleId + "Div") ; // 获取表单的div元素
	if (eleObject != null) {	// 该对象存在
		if (regex.test(eleObject.value)) {
			eleDivObject.className = "form-group has-success" ;
			return true ;
		} else {
			eleDivObject.className = "form-group has-error" ;
			return false ;
		}
	}
}

function jumpTime() {	// 实现定时的数据跳转处理
	jumpTimeObject = document.getElementById("jumpTime") ; // 获取定时跳转的处理路径
	timeValue = parseInt(jumpTimeObject.innerHTML) ; // 获取原始的数据
	if (timeValue == 0) {	// 时间到了，可以进行跳转
		window.location = jsForwardUrl ;	// jsForwardUrl的路径由其它的JS来定义
	} else {
		jumpTimeObject.innerHTML = timeValue -1 ;
		setTimeout(jumpTime,1000) ; // 每秒调用一次本函数
	}
}

function validateEmpty(eleId) {	// 进行数据是否为null的验证
	eleObject = document.getElementById(eleId) ; // 根据传入的id获取对象
	eleDivObject = document.getElementById(eleId + "Div") ; // 获取表单的div元素
	if (eleObject != null) {	// 该对象存在
		if (eleObject.value == "") {	// 验证失败
			if (eleDivObject != null) {
				eleDivObject.className = "form-group has-error" ;
			}
			return false ;
		} else {
			if (eleDivObject != null) {
				eleDivObject.className = "form-group has-success" ;
			}
			return true ;
		}
	}
}