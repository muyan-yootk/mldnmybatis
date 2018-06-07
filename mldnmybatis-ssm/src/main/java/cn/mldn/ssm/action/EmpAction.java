package cn.mldn.ssm.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.ssm.service.IEmpService;
import cn.mldn.ssm.vo.Emp;
import cn.mldn.util.action.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;
import cn.mldn.util.web.upload.FileUploadUtil;
@Controller
@RequestMapping("/pages/admin/emp/*")
public class EmpAction extends AbstractAction { 
	private static final String TITLE = "雇员" ;
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
	@RequestMapping("emp_add")
	public ModelAndView add(Emp emp,MultipartFile pic) {
		ModelAndView mav = new ModelAndView(super.getMessage("forward.page")) ;
		String photo = FileUploadUtil.upload(pic,"/WEB-INF/upload/emp/") ;
		emp.setPhoto(photo); // 如果有内容则进行保存，没有内容不保存就是null
		if (this.empService.add(emp)) {	// 进行内容的保存
			super.setUrlAndMsg(mav, "emp.add.action", "vo.add.success", TITLE);
		} else {
			super.setUrlAndMsg(mav, "emp.add.action", "vo.add.failure", TITLE);
		}
		return mav ; 
	}
	@RequestMapping("emp_add_pre")
	public String addPre() {
		return super.getMessage("emp.add.page") ; 
	}
}
