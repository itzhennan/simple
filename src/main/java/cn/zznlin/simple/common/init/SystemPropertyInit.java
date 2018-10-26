/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2014 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
package cn.zznlin.simple.common.init;

import cn.zznlin.simple.common.utils.LoggerUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author zhennan
 * @Date 2018/7/15 10:50
 * @Description  system.properties 信息工具类
 */
public class SystemPropertyInit {
	private static final String CLASS_NAME = SystemPropertyInit.class.getName();
	private static SystemPropertyInit propertyUtils = null;
	private Properties propObject = null;
	private static String propetyFilePath = "system.properties";
	protected long lastModifiedData = -1;

	private SystemPropertyInit() {
		this(propetyFilePath);
	}

	private SystemPropertyInit(final String filePath) {
		propObject = new Properties();
		propertyUtils = this;
		File propertyFile = null;
		propertyFile = new File(SystemPropertyInit.class.getResource("/")
				.getPath() + filePath);
		loadFile(propertyFile);
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						File propertyFile = new File(SystemPropertyInit.class
								.getResource("/").getPath() + filePath);
						if (propertyUtils.lastModifiedData != propertyFile
								.lastModified()) {
							LoggerUtils.debug(CLASS_NAME, "Property file is changed to reload!");
							propertyUtils.loadFile(propertyFile);
							propertyUtils.lastModifiedData = propertyFile
									.lastModified();
						}
					} catch (Exception e) {

					}
				}
			}
		}).start();
	}

	private static synchronized SystemPropertyInit getInstance(String filePath) {
		if (propertyUtils == null) {
			propertyUtils = new SystemPropertyInit(filePath);
		}
		return propertyUtils;
	}

	public static synchronized SystemPropertyInit getInstance() {
		return getInstance(propetyFilePath);
	}

	private void loadFile(File propertyFile) {
		LoggerUtils.debug(CLASS_NAME, "Load System property file info ...");
		FileInputStream inputStreamLocal = null;
		try {
			inputStreamLocal = new FileInputStream(propertyFile);
			propObject.load(inputStreamLocal);
			lastModifiedData = propertyFile.lastModified();
			LoggerUtils.debug(CLASS_NAME, "Load System property file SUCCESS !");
		} catch (FileNotFoundException e) {
			LoggerUtils.debug(CLASS_NAME, "No Local Properties File Found");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStreamLocal != null) {
					inputStreamLocal.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getProperty(String property) {
		return getProperty(property,""	);
	}

	public String getProperty(String property, String defaultValue) {
		String val = null;
		if (propObject != null) {
			val = propObject.getProperty(property, defaultValue);
		} else {
			val = defaultValue;
		}
		return val;
	}

	public Integer getInt(String property) {
		return getIntProperty(property,-99);
	}

	public Integer getIntProperty(String property, Integer defaultValue) {
		String val = getProperty(property);
		Integer nVal = null;
		try {
			nVal = val == null ? defaultValue : Integer.parseInt(val);
		} catch (Exception e) {
			nVal = defaultValue;
		}
		return nVal;
	}

	public Double getDouble(String property) {
		return getDoubleProperty(property,0.0);
	}

	public Double getDoubleProperty(String property, Double defaultValue) {
		String val = getProperty(property);
		Double nVal = null;
		try {
			nVal = val == null ? defaultValue : Double.parseDouble(val);
		} catch (Exception e) {
			nVal = defaultValue;
		}
		return nVal;
	}

	public Long getLong(String property) {
		return getLong(property,0l);
	}

	public Long getLong(String property, Long defaultValue) {
		String val = getProperty(property);
		Long nVal = null;
		try {
			nVal = val == null ? defaultValue :Long.parseLong(val);
		} catch (Exception e) {
			nVal = defaultValue;
		}
		return nVal;
	}

	public boolean getBooleanProperty(String property, boolean defaultValue) {
		boolean retval = false;
		String val = getProperty(property);
		if (val == null || val.equals(""))
			retval = defaultValue;
		else if (val.trim().equalsIgnoreCase("true") || val.trim().equals("1"))
			retval = true;
		return (retval);
	}
}
