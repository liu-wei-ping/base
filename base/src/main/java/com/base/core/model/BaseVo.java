package com.base.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author liu wp
 * @data  2017年8月29日
 */
public class BaseVo implements Serializable {
	/*** */
	private static final long serialVersionUID = 1L;
	@Column(name = "create_time")
	@ApiModelProperty(hidden = true, value = "创建时间")
	private Date createTime;
	@Id
	@Column(name = "id")
	@ApiModelProperty(hidden = true, value = "数据库主键id")
	private Long id;
	@Column(name = "update_time")
	@ApiModelProperty(hidden = true, value = "跟新时间")
	private Date updateTime;

	public BaseVo() {
		this.createTime = new Date();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Long getId() {
		return id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}

	public void setId(final Long id) {
		this.id = id;
	}
	public void setUpdateTime(final Date updateTime) {
		this.updateTime = updateTime;
	}

}
