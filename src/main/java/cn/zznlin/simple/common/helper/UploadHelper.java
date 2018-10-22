package cn.zznlin.simple.common.helper;

import cn.zznlin.simple.common.init.SystemPropertyInit;
import cn.zznlin.simple.common.utils.DateUtils;
import cn.zznlin.simple.common.utils.ImageUtils;
import cn.zznlin.simple.common.utils.LoggerUtils;
import cn.zznlin.simple.common.utils.ValidateUtils;
import cn.zznlin.simple.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
@Component
public class UploadHelper {
//	public static final String REAL_PATH = "D:/work/upload/";
	public static final String UPLOAD_PATH = "upload/";
	public static final String QRCODE_PATH = "qrcode/";
	public static final String REAL_NAME = "realName";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_EXT = "fileExt";
	public static final String VOICE_PATH = "voice/";
	public static final String UNDERLINE = "_";
	public static final String SEPARATOR = "/";

	private static final String CLASS_NAME = UploadHelper.class.getName();

	/**
	 * 
	 * @param request
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getFileNameByUpload(
			HttpServletRequest request, String inputFileName) throws Exception {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");

			List<MultipartFile> files = multipartRequest.getFiles(inputFileName);
			if(ValidateUtils.isEmpty(files)){
				return null;
			}
			MultipartFile file = files.get(0);
			long size = file.getSize();
			if(size>0){
				Map<String, String> map = new HashMap<String, String>();
//				byte[] data = new byte[(int) size];
//				InputStream input = file.getInputStream();
//				input.read(data);
				
				String realName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
				map.put(REAL_NAME, realName);
				
				LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
				
				String fileName = getWholeFileName();
				map.put(FILE_NAME, fileName);
				LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
				
				String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
				map.put(FILE_EXT, fileExt);
				LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
				
				String wholeFileName = SystemPropertyInit.getInstance().getProperty("file.path") + fileName + fileExt;
				LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
				File outFile = new File(wholeFileName);

				if (!outFile.exists()) {
					makeDir(outFile.getParentFile());
					outFile.createNewFile();
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				} else {
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				}
				
				// 复制文件
				file.transferTo(outFile);
				
//				FileOutputStream outStream = new FileOutputStream(outFile);

//				outStream.write(data);
//				outStream.close();
//				input.close();
				
				return map;
			}
		}
		
		return null;
	}

	/**
	 * Save image as 640X320 size
	 * 
	 * @param realDirPath
	 * @param fileExt
	 * @throws Exception
	 */
	public static void save640X320Image(String realDirPath, String fileExt)
			throws Exception {
		String outFilePath = realDirPath + UNDERLINE
				+ ImageUtils.EXT_MEDIUM_SIZE + fileExt;
		ImageUtils.saveImageAsJpg(realDirPath + fileExt, outFilePath,
				ImageUtils.MEDIUM_WIDTH, ImageUtils.MEDIUM_HEIGHT);
	}
	
	/**
	 * Save image as 200X200 size
	 * 
	 * @param realDirPath
	 * @param fileExt
	 * @throws Exception
	 */
	public static void save200X200Image(String realDirPath, String fileExt)
			throws Exception {
		String outFilePath = realDirPath + UNDERLINE
				+ ImageUtils.EXT_LANGE_SIZE + fileExt;
		ImageUtils.saveImageAsJpg(realDirPath + fileExt, outFilePath,
				ImageUtils.LANGE_SIZE, ImageUtils.LANGE_SIZE);
	}
	
	
	/**
	 * LE CHEN
	 * @param file
	 * @return
	 */
	public static Map<String,String> uploadFile(MultipartFile file){

		Map<String,String> map = Maps.newHashMap();

		try {
			String realName = file.getOriginalFilename();
			
			String fileName = UploadHelper.getWholeFileName();
			String fileExt = "." + StringUtils.getFilenameExtension(realName);

			String path = SystemPropertyInit.getInstance().getProperty("file.path") + fileName + fileExt;

			map.put(FILE_NAME, fileName);
			LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
			map.put(FILE_EXT, fileExt);
			LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
			map.put(REAL_NAME, realName);
			LoggerUtils.debug(CLASS_NAME, "file realName ==> " + realName);
			LoggerUtils.debug(CLASS_NAME, "file path ==> " + path);
			
			File outFile = new File(path);
			try {
				if(!outFile.getParentFile().exists()){
					makeDir(outFile.getParentFile());
				}
				file.transferTo(outFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * Save image as 80X80 size
	 * 
	 * @param realDirPath
	 * @param fileExt
	 * @throws Exception
	 */
	public static void save80X80Image(String realDirPath, String fileExt)
			throws Exception {
		String outFilePath = realDirPath + UNDERLINE
				+ ImageUtils.EXT_SMALL_SIZE + fileExt;
		ImageUtils.saveImageAsJpg(realDirPath + fileExt, outFilePath,
				ImageUtils.SMALL_SIZE, ImageUtils.SMALL_SIZE);
	}

	/**
	 * Get the whole file name
	 * 
	 * @return
	 */
	public static String getWholeFileName() {
		StringBuffer sb = new StringBuffer(UPLOAD_PATH);
		sb.append(DateUtils.todayToString()).append(SEPARATOR)
				.append(UUID.randomUUID());

		return sb.toString();
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean isSucc4DelFile(String sPath) {
		boolean flag = false;
		LoggerUtils.debug(CLASS_NAME, sPath);
		if(StringUtils.isNoEmpty(sPath)){
			File file = new File(sPath);
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				LoggerUtils.debug(CLASS_NAME, "File is delete successfully!");
				file.delete();
				flag = true;
			}
		}
		
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(SEPARATOR)) {
			sPath = sPath + SEPARATOR;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = isSucc4DelFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 多个文件上传
	 * @param request
	 * @param inputFileName
	 * @throws IOException
	 */
	public static List<Map<String, String>> getFileNamesByUpload(
			HttpServletRequest request, String inputFileName) throws Exception {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME,"Spring3 MVC upload file with Multipart form");
			List<MultipartFile> fileList = multipartRequest.getFiles(inputFileName);
			if(ValidateUtils.isNotEmpty(fileList)){
				List<Map<String, String>> list = Lists.newArrayList();
				for(MultipartFile file: fileList){
					long size = file.getSize();
					if (size > 0) {
						Map<String, String> map = new HashMap<String, String>();
						byte[] data = new byte[(int) size];
						InputStream input = file.getInputStream();
						input.read(data);

						// create file, if no app context path, will throws access
						// denied.
						// seems like you could not create any file at tomcat/bin
						// directory!!!

						LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> "
								+ file.getContentType());
						String fileName = getWholeFileName();
						map.put(FILE_NAME, fileName);
						String realName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
						map.put(REAL_NAME, realName);
						LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
						String fileExt = "."
								+ StringUtils.getFilenameExtension(file
										.getOriginalFilename());
						map.put(FILE_EXT, fileExt);
						LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
						String wholeFileName = SystemPropertyInit.getInstance()
								.getProperty("file.path") + fileName + fileExt;
						LoggerUtils.debug(CLASS_NAME, "file WholeName ==> "
								+ wholeFileName);
						File outFile = new File(wholeFileName);

						if (!outFile.exists()) {
							makeDir(outFile.getParentFile());
							outFile.createNewFile();
							LoggerUtils.debug(CLASS_NAME,
									"full path = " + outFile.getAbsolutePath());
						} else {
							LoggerUtils.debug(CLASS_NAME,
									"full path = " + outFile.getAbsolutePath());
						}
						FileOutputStream outStream = new FileOutputStream(outFile);

						outStream.write(data);
						outStream.close();
						input.close();

						list.add(map);
					}
				}
				return list;
			}
		}

		return null;
	}
	
	/**
	 * Get the whole file name
	 * 
	 * @return
	 */
	public static String getVoiceWholeFileName() {
		StringBuffer sb = new StringBuffer(VOICE_PATH);
		sb.append(DateUtils.todayToString()).append(SEPARATOR)
				.append(UUID.randomUUID());

		return sb.toString();
	}
	
	/**
	 * 二维码生成路径
	 * 
	 * @return
	 */
	public static String getQRCodeWholeFileName() {
		StringBuffer sb = new StringBuffer(QRCODE_PATH);
		sb.append(DateUtils.todayToString()).append(SEPARATOR)
		.append(UUID.randomUUID());
		return sb.toString();
	}

	/**
	 * Create package
	 * 
	 * @param dir
	 */
	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
}
