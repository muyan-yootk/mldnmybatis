package cn.mldn.util.action;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractAction {
	@Autowired
	private MessageSource messageSource ;
	/**
	 * 进行跳转信息的设置操作方法
	 * @param mav 要操作的ModelAndView对象
	 * @param urlKey 要进行跳转的url资源key
	 * @param msgKey 要显示的提示信息的资源key
	 * @param params 与提示信息相符的占位参数内容
	 */
	public void setUrlAndMsg(ModelAndView mav , String urlKey, String msgKey, String... params) {
		mav.addObject("url", this.getMessage(urlKey)) ;
		mav.addObject("msg", this.getMessage(msgKey, params)) ;
	}
	
	/**
	 * 实现资源文件内容的读取
	 * @param key 要读取的资源KEY
	 * @param args 设置的占位符参数
	 * @return 资源的内容
	 */
	public String getMessage(String key,String ... args) {
		try {
			return this.messageSource.getMessage(key, args, Locale.getDefault()) ;
		} catch (Exception e) {
			return null ; 
		}
	} 
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));	
	}
	public HttpServletResponse getResponse() { 
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse() ;
	} 
	
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() ;
	}
}
 