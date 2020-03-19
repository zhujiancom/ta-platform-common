package com.ta.platform.common.system.model;

import com.ey.tax.toolset.core.date.DateUtil;

import java.util.List;

public class SysUserCacheInfo {

	private String sysUserId;
	
	private String sysUserCode;
	
	private String sysUserName;
	
	private String sysOrgCode;

	private String sysOrgId;

	private String sysDepartCode;
	
	private List<String> sysMultiOrgCode;
	
	private boolean oneDepart;
	
	public boolean isOneDepart() {
		return oneDepart;
	}

	public void setOneDepart(boolean oneDepart) {
		this.oneDepart = oneDepart;
	}

	public String getSysDate() {
		return DateUtil.today();
	}

	public String getSysTime() {
		return DateUtil.now();
	}

	public String getSysUserCode() {
		return sysUserCode;
	}

	public void setSysUserCode(String sysUserCode) {
		this.sysUserCode = sysUserCode;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getSysOrgCode() {
		return sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	public List<String> getSysMultiOrgCode() {
		return sysMultiOrgCode;
	}

	public void setSysMultiOrgCode(List<String> sysMultiOrgCode) {
		this.sysMultiOrgCode = sysMultiOrgCode;
	}

	public String getSysOrgId() {
		return sysOrgId;
	}

	public void setSysOrgId(String sysOrgId) {
		this.sysOrgId = sysOrgId;
	}

	public String getSysDepartCode() {
		return sysDepartCode;
	}

	public void setSysDepartCode(String sysDepartCode) {
		this.sysDepartCode = sysDepartCode;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
}
