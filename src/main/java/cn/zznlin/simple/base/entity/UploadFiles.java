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
package cn.zznlin.simple.base.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Jiafa Lv
 * @date Apr 21, 2014 10:58:37 AM
 * @email simon-jiafa@126.com
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "simple_base_upload")
public class UploadFiles extends BaseEntity {
	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "upload_id", columnDefinition = "bigint")
	private Long uploadid;
	
	private String filepath;
	
	private String mediumpath;
	
	private String smallpath;
	
	private String fileExt;

	/**
	 * @return the uploadid
	 */
	public Long getUploadid() {
		return uploadid;
	}

	/**
	 * @param uploadid the uploadid to set
	 */
	public void setUploadid(Long uploadid) {
		this.uploadid = uploadid;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the mediumpath
	 */
	public String getMediumpath() {
		return mediumpath;
	}

	/**
	 * @param mediumpath the mediumpath to set
	 */
	public void setMediumpath(String mediumpath) {
		this.mediumpath = mediumpath;
	}

	/**
	 * @return the smallpath
	 */
	public String getSmallpath() {
		return smallpath;
	}

	/**
	 * @param smallpath the smallpath to set
	 */
	public void setSmallpath(String smallpath) {
		this.smallpath = smallpath;
	}

	/**
	 * @return the fileExt
	 */
	public String getFileExt() {
		return fileExt;
	}

	/**
	 * @param fileExt the fileExt to set
	 */
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
}
