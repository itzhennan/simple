
package cn.zznlin.simple.base.controller;

import cn.zznlin.simple.base.entity.UploadFiles;
import cn.zznlin.simple.base.service.UploadService;
import cn.zznlin.simple.common.controller.CommonController;
import cn.zznlin.simple.common.helper.UploadHelper;
import cn.zznlin.simple.common.init.SystemPropertyInit;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UploadController extends CommonController {
	
	@Autowired
	private UploadService uploadFilesService;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public Object getUploadFile(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String,String> responseMap = Maps.newHashMap();
		try {
			Map<String, String> fileMap = UploadHelper.getFileNameByUpload(
					request, "upload");

			String filePath = fileMap.get(UploadHelper.FILE_PATH);
			String realName = fileMap.get(UploadHelper.REAL_NAME);
			String ext = fileMap.get(UploadHelper.FILE_EXT);
			String imageHost = SystemPropertyInit.getInstance().getProperty("image.host");

			UploadFiles uploadFile = new UploadFiles();
			uploadFile.setFileName(realName);
			uploadFile.setFilePath(filePath);
			uploadFile.setFileExt(ext);
			uploadFilesService.save(uploadFile);

			responseMap.put("uploaded","1");
            responseMap.put("url",imageHost + filePath + ext);
		} catch (Exception e) {
			responseMap.put("uploaded","0");
		}
		return responseMap;
	}


	@Override
	protected String getModule() {
		return null;
	}
}
