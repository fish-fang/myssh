package org.login.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.opensymphony.xwork2.ActionSupport;

@Service("uploadImg")
public class UploadImgAction   extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -7777178019831981194L;
	private static final int BUFFER_SIZE = 16 * 1024;
	private HttpServletRequest request = null;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	private File image;
	
	private String imageFileName;
	private String imgFileContentType;
	
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	@Autowired(required = false)
	@Qualifier("userService")
	private IUserService userService = null;
	
	@JSON(serialize=false)
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public String uploadImg() throws Exception {
		
//		HttpServletResponse response = ServletActionContext.getResponse();
		String path="D:\\MyEclipseWorkSpace\\SSH02\\WebRoot\\images";
		String imageFileName = new Date().getTime() + getExtention(getImageFileName());
		System.out.println("@@@@@@@@@@@@@@@@@@@" + image.getName()); 
		File imageFile = new File(path + File.separator + imageFileName); 
		System.out.println(imageFile.getAbsolutePath() + imageFile.getName());
		copy(image,imageFile);
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		sb.append("<img src=\"images/");
		sb.append(imageFileName);
		sb.append("\"/>");
		System.out.println("###################" + sb.toString());
//		
		map.put("imgurl", sb.toString());
		JSONObject jo = JSONObject.fromObject(map);
		this.result = jo.toString();
		return SUCCESS;
	}
	
	private static String getExtention(String fileName)  {  

        int pos = fileName.lastIndexOf( "." );  

        return fileName.substring(pos);  

	} 

	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.flush();
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 检测是否是图片文件 */  
    private boolean isImageFile() {  
        boolean isImage = false;  
        String[] imgExts = {".gif", ".jpg", ".jpeg",".bmp", ".png"};  
        for(String ext : imgExts) {  
            if(imageFileName.toLowerCase().endsWith(ext)) {  
                isImage = true;  
            } else {
				throw new RuntimeException();
			}
        }  
  
        return isImage;  
    } 
}
