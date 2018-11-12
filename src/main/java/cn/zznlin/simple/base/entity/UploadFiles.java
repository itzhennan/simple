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
 * 文件实体类
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "simple_base_upload")
public class UploadFiles extends BaseEntity {
	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "upload_id",columnDefinition = "BIGINT(11) COMMENT '文件ID' ")
	private Long uploadId;

	@Column(name = "file_name",columnDefinition = "VARCHAR(255) NOT NULL COMMENT '文件名称'")
	private String fileName;

	@Column(name = "file_ext",columnDefinition = "VARCHAR(20) NOT NULL COMMENT '文件后缀名'")
	private String fileExt;

	@Column(name = "file_path",columnDefinition = "VARCHAR(255) NOT NULL COMMENT '文件路径'")
	private String filePath;

	@Column(name = "is_del",columnDefinition = "int(11) DEFAULT 0 NOT NULL COMMENT'是否作废 0:否  1:是'")
	private Integer isDel=0;

	public Long getUploadId() {
		return uploadId;
	}

	public void setUploadId(Long uploadId) {
		this.uploadId = uploadId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
}
