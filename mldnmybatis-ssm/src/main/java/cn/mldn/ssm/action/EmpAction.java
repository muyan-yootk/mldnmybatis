package cn.mldn.ssm.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.ssm.service.IEmpService;
import cn.mldn.util.action.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;
@Controller
@RequestMapping("/pages/admin/emp/*")
public class EmpAction extends AbstractAction { 
	@Autowired
	private IEmpService empService ;
	@RequestMapping("emp_list")
	public ModelAndView list() {
		SplitPageUtil spu = new SplitPageUtil("雇员姓名:name|雇员职位:job", super.getMessage("emp.list.action"));
		ModelAndView mav = new ModelAndView(super.getMessage("emp.list.page")) ;
		mav.addAllObjects(
				this.empService.list(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyword()));
		return mav ;
	}  
}
