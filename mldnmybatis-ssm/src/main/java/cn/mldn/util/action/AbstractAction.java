package cn.mldn.util.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
	 * 实现请求的数据与Set集合的转换，里面的类型为Long
	 * @param paramName 参数名称
	 * @return 不管是否有数据都会返回有Set集合
	 */
	public Set<Long> splitToSetByLong(String value) {
		Set<Long> all = new HashSet<Long>() ;
		String result [] = value.split(",") ;
		for (int x = 0 ; x < result.length ; x ++) {
			all.add(Long.parseLong(result[x])) ;
		}
		return all ; 
	}
	/**
	 * 实现请求的数据与Set集合的转换，里面的类型为Long
	 * @param paramName 参数名称
	 * @return 不管是否有数据都会返回有Map集合,key是id，value是图片
	 */
	public Map<Long,String> splitToSetByCompiste(String value) {
		Map<Long, String> all = new HashMap<Long, String>();
		String result [] = value.split(",") ;
		for (int x = 0 ; x < result.length ; x ++) {
			String temp [] = result[x].split(":") ;
			all.put(Long.parseLong(temp[0]),temp[1]) ;
		} 
		return all ; 
	}
	
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
 