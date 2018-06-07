package cn.mldn.util.web.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	private FileUploadUtil() {} 
	/**
	 * 进行文件的上传
	 * @param file 要上传的文件
	 * @param dir 保存目录
	 * @param fileName 保存的文件名称
	 * @return 上传成功返回原始的文件名称
	 */
	public static String upload(MultipartFile file, String dir, String fileName) {
		if (file == null || file.getSize() == 0) {	// 现在没有文件上传
			return null ;
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() ;
		String filePath = request.getServletContext().getRealPath(dir) + fileName ;
		try {
			file.transferTo(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
		return fileName ;
	}
	/**
	 * 进行上传文件的保存处理
	 * @param file 是上传的文件内容
	 * @param dir 是需要进行保存的父目录（"/WEB-INF/upload/"）
	 * @return 上传成功之后返回一个生成的文件名称，如果上传失败返回null
	 */
	public static String upload(MultipartFile file,String dir) {
		if (file == null || file.getSize() == 0) {	// 现在没有文件上传
			return null ;
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() ;
		String fileName = UUID.randomUUID() + "." + file.getContentType().substring(file.getContentType().lastIndexOf("/") + 1) ;
		String filePath = request.getServletContext().getRealPath(dir) + fileName ;
		try {
			file.transferTo(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
		return fileName ;
	}
}
