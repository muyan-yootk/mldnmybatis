package cn.mldn.ssm.action;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
	private static final String EMP_PHOTO_DIR = "/WEB-INF/upload/emp/" ;
	@Autowired
	private IEmpService empService ;
	
	@RequestMapping("emp_delete")
	public ModelAndView delete(String ids) {
		ModelAndView mav = new ModelAndView(super.getMessage("forward.page")) ;
		Map<Long,String> map = super.splitToSetByCompiste(ids) ;
		if (this.empService.delete(map.keySet())) {	// 进行内容的保存
			super.setUrlAndMsg(mav, "emp.list.action", "vo.delete.success", TITLE);
			map.forEach((key,value)->{
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() ;
				String filePath = request.getServletContext().getRealPath(EMP_PHOTO_DIR) + value ;
				new File(filePath).delete(); 
			});
		} else {
			super.setUrlAndMsg(mav, "emp.list.action", "vo.delete.failure", TITLE);
		}
		return mav ; 
	} 
	
	@RequestMapping("emp_list")
	public ModelAndView list() {
		SplitPageUtil spu = new SplitPageUtil("雇员姓名:name|雇员职位:job", super.getMessage("emp.list.action"));
		ModelAndView mav = new ModelAndView(super.getMessage("emp.list.page")) ;
		mav.addAllObjects(
				this.empService.list(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyword()));
		return mav ;
	}
	@RequestMapping("emp_edit")
	public ModelAndView edit(Emp emp,MultipartFile pic) {
		ModelAndView mav = new ModelAndView(super.getMessage("forward.page")) ;
		FileUploadUtil.upload(pic, EMP_PHOTO_DIR, emp.getPhoto());
		if (this.empService.edit(emp)) {	// 进行内容的保存
			super.setUrlAndMsg(mav, "emp.list.action", "vo.edit.success", TITLE);
		} else {
			super.setUrlAndMsg(mav, "emp.list.action", "vo.edit.failure", TITLE);
		}
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
	@RequestMapping("emp_edit_pre")
	public ModelAndView editPre(long empno) {
		ModelAndView mav = new ModelAndView(super.getMessage("emp.edit.page")) ;
		mav.addObject("emp", this.empService.preEdit(empno)) ;
		return mav ; 
	}
}
