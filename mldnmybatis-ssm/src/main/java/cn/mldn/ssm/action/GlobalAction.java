package cn.mldn.ssm.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalAction {
	@RequestMapping("/error")
	public String error() {
		return "plugins/errors" ;
	} 
	@RequestMapping("/forward")
	public String forward() {
		return "plugins/forward" ;
	} 
}
