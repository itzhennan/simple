package cn.zznlin.simple.common.utils;

import cn.zznlin.simple.common.init.SystemPropertyInit;
import com.UpYun;
import com.google.common.collect.Maps;
import com.upyun.Base64Coder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
* 
* @author zhennan zhang
* @email  itzhennan@163.com
* @date   2018年10月18日 下午4:09:42
* 
* 类说明 :
*       
*/
public class UPYunRestUtils {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	// 提交地址主域名
	private String domain = "";
	// 视频表单提交的密钥
	private String secretKey = "";
	private String imageSecretKey = "";
	// bucket名
	private String bucketName = "";
	private String imageBucketName = "";
	// 操作员帐号密码
	private String userName = "";
	private String password = "";
	// 服务器地址
	private String server = "";
	// 同步回调地址
	// private String returnUrl="";
	private String path = "";

	// policy和signature
	private static String POLICY = "policy";
	private static String SIGNATURE = "signature";

	public static String SEPARATOR = "/";

//		private static String SAVE_PATH = "path";
	private static String SAVE_KEY = "save-key";
	private static String EXPIRATION = "expiration";
	private static String CONTENT_LENGTH = "content-length";
	
	// 同步通知 URL
	 private static String RETURN_URL_STR="return-url";
	// 异步通知 URL
	private static String NOTIFY_URL_STR = "notify-url";
	// 扩展参数
	private static String EXT_PARAM = "ext-param";
	private static String BUCKET = "bucket";

	private static UpYun upYun;

	private static UPYunRestUtils upYunRestUtils;

	// 初始化资源bucket
	public UpYun getInstance() {
		if (upYun == null) {
			upYun = new UpYun(bucketName, userName, password);
			upYun.setApiDomain(UpYun.ED_TELECOM);
		}
		return upYun;
	}

	private UPYunRestUtils() {
		domain = SystemPropertyInit.getInstance().getProperty("upyun.domain");
		secretKey = SystemPropertyInit.getInstance().getProperty("upyun.secretKey");
		imageSecretKey = SystemPropertyInit.getInstance().getProperty("upyun.imageSecretKey");
		bucketName = SystemPropertyInit.getInstance().getProperty("upyun.bucketName");
		imageBucketName = SystemPropertyInit.getInstance().getProperty("upyun.imageBucketName");
		userName = SystemPropertyInit.getInstance().getProperty("upyun.userName");
		password = SystemPropertyInit.getInstance().getProperty("upyun.password");
		server = SystemPropertyInit.getInstance().getProperty("upyun.server");
		path = SystemPropertyInit.getInstance().getProperty("upyun.path");
	}

	public static UPYunRestUtils newUpYunRestUtils() {
//		if (upYunRestUtils != null) {
//			return upYunRestUtils;
//		} else {
//		}
		return new UPYunRestUtils();
	}
	
	
	
	private String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is unsupported", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MessageDigest不支持MD5Util", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
    private byte[] hashHmac(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return mac.doFinal(data.getBytes());
    }
    private String sign(String key, String secret, String method, String uri, String date, String policy,
            String md5) throws Exception {
        String value = method + "&" + uri + "&" + date;
        if (policy != "") {
            value = value + "&" + policy;
        }
        if (md5 != "") {
            value = value + "&" + md5;
        }
        byte[] hmac = hashHmac(value, secret);
        String sign = new String(Base64Coder.encode(hmac));
        return "UPYUN " + key + ":" + sign;
    }
    private String getRfc1123Time() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }
    
    // 
    private String newSign(String key, String secret, String method, String uri, String date, String CONTENT_LENGTH,
            String bucket ) throws Exception {
        String value = method + "&/"+bucket + uri + "&" + date + "&" + CONTENT_LENGTH + "&"+secret;
        
        
//        byte[] hmac = hashHmac(value, secret);
//        String sign = new String(Base64Coder.encode(hmac));
        System.out.println(value);
        String sign = md5(value);
        	
        return "UpYun " + key + ":" + sign;
    }
    
//    public static void main(String[] args) throws Exception {
//    	UPYunRestUtils newUpYunRestUtils = UPYunRestUtils.newUpYunRestUtils();
//        String key = newUpYunRestUtils.userName;
//        String secret = newUpYunRestUtils.password;
//        String method = "GET";
//        String uri = newUpYunRestUtils.path;
//        String date = getRfc1123Time();
//
//        System.out.println(date);
//        System.out.println(md5(secret));
//        // 上传，处理，内容识别有存储
//        System.out.println(sign(key, md5(secret), method, uri, date, "", ""));
//        // 内容识别无存储，容器云
////        System.out.println(sign(key, secret, method, uri, date, "", ""));
//    }
    
    // 获得续点上传signature
    public Map<String, String> getMultiUpload(String fileName,String CONTENT_LENGTH){
    	Map<String, String> map = Maps.newHashMap();
        String method = "POST";
        String filePath = "/video";
        String date = getRfc1123Time();
        String sign = "";
        System.out.println(filePath);
        System.out.println(date);
        System.out.println(password);
        System.out.println(md5(password));
		try {
			sign = newSign(userName, md5(password), method, filePath, date, CONTENT_LENGTH, bucketName);
			System.out.println(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("signature", sign);
		map.put("url", domain+bucketName+filePath+"/to/file");
    	return map;
    }
    
    private String getPath(String fileName){
    	// 文件路径 /video/{year}/{mon}/{day}/{random32}/{filename}{.suffix}
    	//日期
    	String date = DateUtils.todayToPatternStr(DateUtils.DATETIME_YYYYMMDD);
    	String random32 = UUID.randomUUID().toString().replace("-", "");
    	StringBuffer sb = new StringBuffer();
    	sb.append("/video/").append(date).append("/").append(random32).append("/").append(fileName);
    	return sb.toString();
    }

}
